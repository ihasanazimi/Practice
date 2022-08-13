package ir.ha.dep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentVideoPlayerBinding
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.hide
import ir.ha.dep.utility.extentions.showToast

class VideoPlayerFrg :  BaseFragment() {

    private lateinit var binding : FragmentVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_video_player,container!!)
        return binding.root
    }


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


    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.videoPlayer.stopPlayback()
    }


}