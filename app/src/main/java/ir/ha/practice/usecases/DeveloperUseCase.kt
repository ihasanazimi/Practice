package ir.ha.practice.usecases

import ir.ha.practice.model.DeveloperDetails
import ir.ha.practice.repository.DeveloperRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DeveloperUseCase {

    suspend fun getDeveloperDetails() : Flow<DeveloperDetails>


}




class DeveloperUseCaseImpl @Inject constructor(
    private val developerRepository : DeveloperRepository
): DeveloperUseCase{

    override suspend fun getDeveloperDetails() : Flow<DeveloperDetails>{
        return developerRepository.developerDetails()
    }


}