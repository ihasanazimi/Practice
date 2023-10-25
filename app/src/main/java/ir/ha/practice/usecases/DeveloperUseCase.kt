package ir.ha.practice.usecases

import ir.ha.practice.data.entities.DeveloperDetailsEntity
import ir.ha.practice.repository.DeveloperRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DeveloperUseCase {

    suspend fun getDeveloperDetails(): Flow<DeveloperDetailsEntity>

}

class DeveloperUseCaseImpl @Inject constructor(
    private val developerRepository: DeveloperRepository
) : DeveloperUseCase {

    override suspend fun getDeveloperDetails(): Flow<DeveloperDetailsEntity> {
        return developerRepository.developerDetails()
    }


}