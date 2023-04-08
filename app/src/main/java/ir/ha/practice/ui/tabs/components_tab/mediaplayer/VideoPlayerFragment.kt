package ir.ha.practice.ui.tabs.components_tab.mediaplayer

import android.os.Bundle
import android.view.View
import android.widget.MediaController
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentVideoPlayerBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.hide
import ir.ha.practice.utility.extentions.showToast

class VideoPlayerFragment :  BaseFragment<FragmentVideoPlayerBinding>() {
    override val layoutId: Int get() = R.layout.fragment_video_player

    private val videoUrl = "https://hajifirouz10.asset.aparat.com/aparat-video/3f9b17a17f0a77644ab730cc9186a06249895267-240p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjExYmI4OTBhN2I0MDhjNGI4YzUyMWNmMTg0YTQ5ZGVmIiwiZXhwIjoxNjczOTc0Mjc5LCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.NLsHY2kNTKQL0Al2HYj2u5AnNSreDRJwYXlUGoK8Q98"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun clickEvents() {
        super.clickEvents()

        binding.videoPlayer.setVideoPath(videoUrl)
        binding.videoPlayer.setOnPreparedListener { mediaPlayer ->
            // set controller for video player
            MediaController(requireContext()).apply {
                setMediaPlayer(binding.videoPlayer)
                binding.videoPlayer.setMediaController(this)
                setAnchorView(binding.videoPlayer)
                binding.pb.hide()
            }
            mediaPlayer.start()
        }

        // when video is finished!
        binding.videoPlayer.setOnCompletionListener { showToast(requireContext(),"Video is finished!") }

        binding.videoPlayer.setOnErrorListener { mp, what, extra ->
            showToast(requireContext(),"Error in Video When Downloading..")
            binding.pb.hide()
            return@setOnErrorListener false
        }

    }

    override fun onDestroyView() {
        binding.videoPlayer.stopPlayback()
        super.onDestroyView()
    }
}