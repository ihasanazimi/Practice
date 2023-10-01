package ir.ha.practice.ui.developer_about

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
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
import ir.ha.practice.utility.extentions.singleClick
import ir.ha.practice.utility.util.IntentActionsUtil
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeveloperFragment : BaseFragmentByVM<ir.ha.practice.databinding.FragmentDeveloperBinding, DeveloperFragmentVM>() {

    override val viewModel: DeveloperFragmentVM by viewModels()
    override val layoutId: Int get() = R.layout.fragment_developer

    private lateinit var skillAdapter : DeveloperTagsAdapter
    private lateinit var contactInfoAdapter : ContactInfoAdapter
    private lateinit var organizeAdapter : OrganizeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
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

    private fun init(){
        skillAdapter = DeveloperTagsAdapter()
        contactInfoAdapter = ContactInfoAdapter(requireActivity())
        organizeAdapter = OrganizeAdapter(requireActivity())
    }

    override fun registerClickListeners() {

        binding.header.info.singleClick {
            // todo
        }

    }

    private fun updateUi(developerEntity : DeveloperDetailsEntity) {

        if (developerEntity == null) {
            showErrorMessage("your model is the null")
            return
        }

        binding.apply {

            // header config
            header.info.show()
            header.help.hide()
            header.titleTV.text = getString(R.string.app_name)
            header.logoIV.setImageResource(R.drawable.ic_launcher_background)
            header.headerBackground.setBackgroundColor(ContextCompat.getColor(this@DeveloperFragment.requireContext(),R.color.green_persian))
            header.titleTV.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
            binding.header.info.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
            binding.header.help.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))

            // fill views
            developerNameTv.text = developerEntity.getFullName()
            developerNameTvFiled.text = developerEntity.getFullName()
            jobTv.text = developerEntity.getJobInOrganization()
            jobTitle2.text = developerEntity.jobTitle

            // init recyclerViews
            skillRecyclerView.adapter = skillAdapter
            contactInfoRecyclerView.adapter = contactInfoAdapter
            projectsRecyclerView.adapter = organizeAdapter

            followLinkedinBtn.setOnClickListener {
                IntentActionsUtil(requireActivity()).openLinkedInPage(developerEntity.contactInfo.linkedin)
            }


            // fill lists
            skillAdapter.setNewList(developerEntity.skills)

            contactInfoAdapter.setNewList(
                arrayListOf(
                    ContactInfoByIconEntity(developerEntity.contactInfo.call,R.drawable.baseline_call_24, ContactInfoEnum.call),
                    ContactInfoByIconEntity(developerEntity.contactInfo.email,R.drawable.outline_email_24, ContactInfoEnum.email),
                    ContactInfoByIconEntity(developerEntity.contactInfo.linkedin,R.drawable.linkedin, ContactInfoEnum.linkedin),
                    ContactInfoByIconEntity(developerEntity.contactInfo.telegram,R.drawable.ic_telegram_logo_light_icon, ContactInfoEnum.telegram)
                )
            )
            organizeAdapter.setNewList(developerEntity.resume.organizes)
        }
    }
}