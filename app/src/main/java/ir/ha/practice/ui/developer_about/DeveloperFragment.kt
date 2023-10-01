package ir.ha.practice.ui.developer_about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import ir.ha.practice.R
import ir.ha.practice.adapters.ContactInfoAdapter
import ir.ha.practice.adapters.DeveloperTagsAdapter
import ir.ha.practice.adapters.OrganizeAdapter
import ir.ha.practice.data.entities.ContactInfoByIconEntity
import ir.ha.practice.data.entities.DeveloperDetailsEntity
import ir.ha.practice.data.enums.ContactInfoEnum
import ir.ha.practice.utility.base.BaseFragmentByVM
import ir.ha.practice.utility.extentions.hide
import ir.ha.practice.utility.extentions.show
import ir.ha.practice.utility.extentions.showToast
import ir.ha.practice.utility.util.IntentActionsUtil
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeveloperFragment : BaseFragmentByVM<ir.ha.practice.databinding.FragmentDeveloperBinding, DeveloperFragmentVM>() {

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

    private fun updateUi(d : DeveloperDetailsEntity) {

        if (d == null) {
            showErrorMessage("your model is the null")
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
                    ContactInfoByIconEntity(d.contactInfo.call,R.drawable.baseline_call_24,
                        ContactInfoEnum.call),
                    ContactInfoByIconEntity(d.contactInfo.email,R.drawable.outline_email_24,
                        ContactInfoEnum.email),
                    ContactInfoByIconEntity(d.contactInfo.linkedin,R.drawable.linkedin,
                        ContactInfoEnum.linkedin),
                    ContactInfoByIconEntity(d.contactInfo.telegram,R.drawable.ic_telegram_logo_light_icon,
                        ContactInfoEnum.telegram)
                )
            )
            organizeAdapter.setNewList(d.resume.organizes)
        }
    }
}