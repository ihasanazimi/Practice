package ir.ha.practice.ui.tabs.developer_about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentDeveloperBinding
import ir.ha.practice.model.ContactInfoByIcon
import ir.ha.practice.model.ContactInfoEnum
import ir.ha.practice.model.DeveloperDetails
import ir.ha.practice.model.adapters.ContactInfoAdapter
import ir.ha.practice.model.adapters.DeveloperTagsAdapter
import ir.ha.practice.model.adapters.OrganizeAdapter
import ir.ha.practice.utility.base.BaseFragmentByVM
import ir.ha.practice.utility.extentions.hide
import ir.ha.practice.utility.extentions.show
import ir.ha.practice.utility.extentions.showToast
import ir.ha.practice.utility.util.IntentActionsUtil
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeveloperFragment : BaseFragmentByVM<FragmentDeveloperBinding,DeveloperFragmentVM>() {

    override val viewModel: DeveloperFragmentVM by viewModels()
    override val layoutId: Int get() = R.layout.fragment_developer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDeveloperDetails()
    }


    override fun registerObservers() {
        super.registerObservers()

        lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.developerRes.collect{
                    updateUi(it)
                }
            }
        }

        viewModel.showLoading.observe(viewLifecycleOwner){
            if (it) binding.loadingbar.root.show()
            else binding.loadingbar.root.hide()
        }


    }

    private fun updateUi(d : DeveloperDetails) {

        if (d == null) {
            showToast(requireContext(),"your model is the null")
            return
        }

        binding.apply {
//            Glide.with(requireContext()).load(d.profileImage).into(developerProfileImage)
            developerNameTv.text = d.getFullName()
            developerNameTvFiled.text = d.getFullName()
            jobTv.text = d.getJobInOrganization()
            jobTitle2.text = d.jobTitle
            followLinkedinBtn.setOnClickListener {
                IntentActionsUtil(requireActivity()).openLinkedInPage(d.contactInfo.linkedin)
            }
            val skillAdapter = DeveloperTagsAdapter()
            val contactInfoAdapter = ContactInfoAdapter(requireActivity())
            val organizeAdapter = OrganizeAdapter(requireActivity())
            skillRecyclerView.adapter = skillAdapter
            contactInfoRecyclerView.adapter = contactInfoAdapter
            projectsRecyclerView.adapter = organizeAdapter
            skillAdapter.setNewList(d.skills)
            contactInfoAdapter.setNewList(
                arrayListOf(
                    ContactInfoByIcon(d.contactInfo.call,R.drawable.baseline_call_24,ContactInfoEnum.call),
                    ContactInfoByIcon(d.contactInfo.email,R.drawable.outline_email_24,ContactInfoEnum.email),
                    ContactInfoByIcon(d.contactInfo.linkedin,R.drawable.linkedin,ContactInfoEnum.linkedin),
                    ContactInfoByIcon(d.contactInfo.telegram,R.drawable.ic_telegram_logo_light_icon,ContactInfoEnum.telegram)
                )
            )
            organizeAdapter.setNewList(d.resume.organizes)
        }
    }
}