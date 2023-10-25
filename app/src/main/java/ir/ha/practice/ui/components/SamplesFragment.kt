package ir.ha.practice.ui.components

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ir.ha.practice.Components
import ir.ha.practice.R
import ir.ha.practice.adapters.ComponentsAdapter
import ir.ha.practice.data.enums.ComponentEnum
import ir.ha.practice.databinding.FragmentComponentsBinding
import ir.ha.practice.ui.components.animations.AnimationsFragment
import ir.ha.practice.ui.components.broadcast_eventbus.BroadcastReceiverFragment
import ir.ha.practice.ui.components.firebase.FirebaseCloudMessagingFragment
import ir.ha.practice.ui.components.fragment_types.FragmentSamplesContainer
import ir.ha.practice.ui.components.load_image.LoadImagesFragment
import ir.ha.practice.ui.components.local_data_base.RoomDBFragment
import ir.ha.practice.ui.components.material.MaterialDesignFragment
import ir.ha.practice.ui.components.mediaplayer.MediaPlayerFragment
import ir.ha.practice.ui.components.mediaplayer.VideoPlayerFragment
import ir.ha.practice.ui.components.mvvm_arch.MvvmContainerFragment
import ir.ha.practice.ui.components.mvvm_rx.MvvmRxFragment
import ir.ha.practice.ui.components.notification.NotificationFragment
import ir.ha.practice.ui.components.recyclerview.RecyclerViewFragment
import ir.ha.practice.ui.components.retrofit.RetrofitFragment
import ir.ha.practice.ui.components.rxjava.RxJavaFragment
import ir.ha.practice.ui.components.services.ServicesFragment
import ir.ha.practice.ui.components.viewPager.ViewPagerFragment
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.addFragmentByAnimation

class SamplesFragment  : BaseFragment<FragmentComponentsBinding>(), View.OnClickListener,
    ComponentsAdapter.ComponentsEvent {
    override val layoutId: Int get() = R.layout.fragment_components

    private lateinit var componentsAdapter : ComponentsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentsAdapter = ComponentsAdapter(this)
        binding.recyclerView.adapter = componentsAdapter
        componentsAdapter.setNewList(Components.sampleComponents)
    }

    override fun onClick(v: View?) {

    }

    override fun onComponentNameClick(component: String) {
        when(component){

            ComponentEnum.thread.toString() -> {
                //todo
            }
            ComponentEnum.costume_view.toString() -> {
                //todo
            }
            ComponentEnum.animation.toString() -> {
                openFragment(AnimationsFragment())
            }
            ComponentEnum.broadcast_receiver.toString() -> {
                openFragment(BroadcastReceiverFragment())
            }
            ComponentEnum.room.toString() -> {
                openFragment(RoomDBFragment())
            }
            ComponentEnum.fragment.toString() -> {
                openFragment(FragmentSamplesContainer())
            }
            ComponentEnum.material.toString() -> {
                openFragment(MaterialDesignFragment())
            }
            ComponentEnum.firbase.toString() -> {
                openFragment(FirebaseCloudMessagingFragment())
            }
            ComponentEnum.load_images.toString() -> {
                openFragment(LoadImagesFragment())
            }
            ComponentEnum.retrofit.toString() -> {
                openFragment(RetrofitFragment())
            }
            ComponentEnum.notification.toString() -> {
                openFragment(NotificationFragment())
            }
            ComponentEnum.service.toString() -> {
                openFragment(ServicesFragment())
            }
            ComponentEnum.recyclerview.toString() -> {
                openFragment(RecyclerViewFragment())
            }
            ComponentEnum.view_pager.toString() -> {
                openFragment(ViewPagerFragment())
            }
            ComponentEnum.medial_player.toString() -> {
                openFragment(MediaPlayerFragment())
            }
            ComponentEnum.video_player.toString() -> {
                openFragment(VideoPlayerFragment())
            }
            ComponentEnum.exo_player.toString() -> {
                //todo
            }
            ComponentEnum.mvvm.toString() -> {
                openFragment(MvvmContainerFragment())
            }
            ComponentEnum.rx.toString() -> {
                openFragment(RxJavaFragment())
            }
            ComponentEnum.rx_mvvm.toString() -> {
                openFragment(MvvmRxFragment())
            }

        }
    }


    private fun openFragment(fragment : Fragment){
        addFragmentByAnimation(fragment,fragment::class.java.simpleName,true,true,R.id.container)
    }


}