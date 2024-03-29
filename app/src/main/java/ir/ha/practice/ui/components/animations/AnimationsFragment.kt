package ir.ha.practice.ui.components.animations

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentAnimationBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast

class AnimationsFragment : BaseFragment<FragmentAnimationBinding>() {
    override val layoutId: Int get() = R.layout.fragment_animation
    private lateinit var activeAnim : Animation


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* lottie animation properties */
        binding.lottieAnimationView.apply {
            setAnimation(R.raw.anim_file)
            repeatCount = 5 //** count */
        }
    }

    override fun registerListeners() {
        super.registerListeners()

        /** Android Animations */
        binding.play.setOnClickListener{

            val durationMil = 2000L

            when (binding.toggleGroup.checkedButtonId) {
                R.id.rotateAnimBtn -> {
                    RotateAnimation(0F, 360F, Animation.RELATIVE_TO_SELF.toFloat(), Animation.RELATIVE_TO_SELF.toFloat()).apply {
                        duration = durationMil
                        repeatCount = Animation.INFINITE
                        interpolator = AccelerateInterpolator()
                        activeAnim = this
                    }
                }

                R.id.scaleAnimBtn -> {
                    // 2X
                    ScaleAnimation(0F, 2F, 0F, 2F, Animation.RELATIVE_TO_SELF.toFloat(), Animation.RELATIVE_TO_SELF.toFloat()).apply {
                        duration = durationMil
                        repeatCount = Animation.INFINITE
                        interpolator = AccelerateInterpolator()
                        activeAnim = this
                    }
                }

                R.id.translateAnimBtn -> {
                    TranslateAnimation(0F, -200F, Animation.RELATIVE_TO_SELF.toFloat(), -1F).apply {
                        repeatCount = Animation.INFINITE
                        duration = durationMil
                        interpolator = AccelerateInterpolator()
                        activeAnim = this
                    }
                }

                R.id.alphaAnimBtn -> {
                    AlphaAnimation(0F, 1F).apply {
                        repeatCount = Animation.INFINITE
                        duration = durationMil
                        interpolator = AccelerateInterpolator()
                        activeAnim = this
                    }
                }

                R.id.setAnimBtn -> {
                    AnimationSet(true).apply {
                        interpolator = AccelerateInterpolator()
                        repeatCount = Animation.INFINITE
                        fillAfter = true
                        duration = durationMil
                        addAnimation(AlphaAnimation(0F, 1F))
                        addAnimation(RotateAnimation(0F, 360F, Animation.RELATIVE_TO_SELF.toFloat(), Animation.RELATIVE_TO_SELF.toFloat()))
                        addAnimation(ScaleAnimation(0F, 2F, 0F, 2F, Animation.RELATIVE_TO_SELF.toFloat(), Animation.RELATIVE_TO_SELF.toFloat()))
                        activeAnim = this
                    }
                }
                else -> showToast(binding.lottieAnimationView.context,"unknown error")
            }
            binding.lottieAnimationView.startAnimation(activeAnim)
        }
    }
}