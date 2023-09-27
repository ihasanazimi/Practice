package ir.ha.practice.ui.tabs.developer_about

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ha.practice.model.DeveloperDetails
import ir.ha.practice.usecases.DeveloperUseCase
import ir.ha.practice.utility.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DeveloperFragmentVM @Inject constructor(
    private val developerUseCase: DeveloperUseCase
) : BaseViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { c ,t ->
        errorLiveData.postValue(arrayListOf(t.message.toString()))
    }

    private val _developerRes = MutableSharedFlow<DeveloperDetails>()
    val developerRes = _developerRes.asSharedFlow()



    fun getDeveloperDetails() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showLoading.value = true
            developerUseCase.getDeveloperDetails().collect{
                showLoading.value = false
                _developerRes.emit(it)
            }
        }
    }
}