package br.com.britawallet.data.local

import br.com.britawallet.data.model.ServiceResponse
import br.com.britawallet.data.model.User

interface UserLocalRepository {
    fun getActiveUser(): ServiceResponse<User?>
    fun getUser(login: String): ServiceResponse<User?>
    fun saveUser(user: User): ServiceResponse<Nothing>
    fun saveWallet(user: User): ServiceResponse<Nothing>
    fun login(user: User): ServiceResponse<Nothing>
    fun logout(): ServiceResponse<Nothing>
}