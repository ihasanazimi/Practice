package ir.ha.practice.repository

import ir.ha.practice.model.DeveloperDetails
import ir.ha.practice.services.http.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface DeveloperRepository {
    suspend fun developerDetails() : Flow<DeveloperDetails>
}



class DeveloperRepositoryImpl @Inject constructor(
    private val apiService: ApiService
    ) : DeveloperRepository {


    override suspend fun developerDetails()= flow {
        emit(apiService.api.getDevelopersByCoroutines())
    }
}