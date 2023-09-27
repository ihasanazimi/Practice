package ir.ha.practice.repository

import ir.ha.practice.model.data.DeveloperDetailsRemoteResponse
import ir.ha.practice.model.entities.DeveloperDetailsEntity
import ir.ha.practice.model.extentions.developer_details.toDeveloperDetailsEntity
import ir.ha.practice.services.http.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface DeveloperRepository {
    suspend fun developerDetails() : Flow<DeveloperDetailsEntity>
}



class DeveloperRepositoryImpl @Inject constructor(
    private val apiService: ApiService
    ) : DeveloperRepository {


    override suspend fun developerDetails()= flow {
        emit(apiService.api.getDevelopersByCoroutines().toDeveloperDetailsEntity())
    }
}