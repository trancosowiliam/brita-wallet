package br.com.britawallet.data.remote

import br.com.britawallet.data.model.ServiceResponse
import br.com.britawallet.data.model.User

interface UserRemoteRepository {
    suspend fun login(
        login: String,
        password: String
    ): ServiceResponse<User>

    suspend fun attUserWallet(
        user: User
    )
}