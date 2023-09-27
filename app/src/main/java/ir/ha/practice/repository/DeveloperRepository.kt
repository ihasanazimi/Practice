package ir.ha.practice.repository

import ir.ha.practice.data.entities.DeveloperDetailsEntity
import ir.ha.practice.data.extentions.developer_details.toDeveloperDetailsEntity
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