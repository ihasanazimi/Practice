package ir.ha.dummy.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.MediaController
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentVideoPlayerBinding
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.hide
import ir.ha.dummy.utility.extentions.showToast

class VideoPlayerFrg :  BaseFragment<FragmentVideoPlayerBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_video_player

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.videoPlayer.setVideoPath("https://hajifirouz6.asset.aparat.com/aparat-video/41d9bd95cd3f6d419b4b9435f9b2ee9146709585-360p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImNiMTQyMTZiYmQ5MjU1YWM0ZGVjZGRjYmRiYTJmOTI1IiwiZXhwIjoxNjYwNDEyMDY1LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.lW-MHak7uNL66sk8OaoiRA4KnEIzlr_2JvIfRlGPQ_U")

        binding.videoPlayer.setOnPreparedListener {
            // set controller for video player
            MediaController(requireContext()).apply {
                setMediaPlayer(binding.videoPlayer)
                binding.videoPlayer.setMediaController(this)
                setAnchorView(binding.videoPlayer)
                binding.pb.hide()
            }
            it.start()
        }

        // when video is finished!
        binding.videoPlayer.setOnCompletionListener {
            showToast(requireContext(),"Video is finished!")
        }

        binding.videoPlayer.setOnErrorListener { mp, what, extra ->
            showToast(requireContext(),"Error in Video When Downloading..")
            binding.pb.hide()
            return@setOnErrorListener false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.videoPlayer.stopPlayback()
    }


}