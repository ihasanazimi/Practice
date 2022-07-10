package ir.ha.dep.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.slider.Slider
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentMediaPlayerBinding
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.convertMilliSecondToMinute
import ir.ha.dep.utility.extentions.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.sql.Time
import java.util.*

class MediaPlayerFrg : BaseFragment() {

    private lateinit var binding : FragmentMediaPlayerBinding
    private lateinit var mediaPlayer : MediaPlayer
    private lateinit var timer : Timer
    var isDragging = false
    var finish = false


    override fun onAttach(context: Context) {
        super.onAttach(context)
        finish = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_media_player,container!!)
        return binding.root
    }

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