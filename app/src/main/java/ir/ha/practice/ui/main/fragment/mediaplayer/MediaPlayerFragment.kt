package ir.ha.practice.ui.main.fragment.mediaplayer

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import com.google.android.material.slider.Slider
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentMediaPlayerBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.convertMilliSecondToMinute
import java.util.*

class MediaPlayerFragment : BaseFragment<FragmentMediaPlayerBinding>() {

    override val layoutId: Int get() = R.layout.fragment_media_player
    private lateinit var mediaPlayer : MediaPlayer
    private lateinit var timer : Timer
    var isDragging = false
    var finish = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaPlayer = MediaPlayer.create(requireContext(),R.raw.xaniar)
        mediaPlayer.start()
        timer = Timer()



        sliderChangedListener(binding.slider)
        binding.slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: Slider) {
                isDragging = true
            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: Slider) {
                isDragging = false
                mediaPlayer.seekTo(slider.value.toInt())
            }
        })





        mediaPlayer.setOnPreparedListener { thisMedia ->
            // timer for slider
            timer = Timer().apply {
                schedule(object : TimerTask() {
                    override fun run() {
                        if (!finish) {
                            if (mediaPlayer.isPlaying) {
                                requireActivity().runOnUiThread {
                                    val currentPosition =
                                        (0 + mediaPlayer.currentPosition.toLong()) ?: 0L
                                    val duration = convertMilliSecondToMinute(currentPosition)
                                    binding.tv.text = duration.toString() ?: ""
                                    binding.slider.value =
                                        mediaPlayer.currentPosition.toFloat() ?: 0.0F
                                }
                            }
                        }
                    }
                },1000,1000)
            }
            // set max value for slider view
            binding.slider.value = 0.0F // reset slider view
            binding.slider.valueTo = mediaPlayer.duration.toFloat()

            thisMedia.start()
        }
    }


    private fun sliderChangedListener(mySlider: Slider) {
        mySlider.addOnChangeListener { slider, value, fromUser ->
            binding.tv.text = convertMilliSecondToMinute(value.toLong())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer.release()
    }

    override fun onDetach() {
        super.onDetach()
        finish = true
    }


}