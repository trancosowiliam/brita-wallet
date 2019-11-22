package br.com.britawallet.data.remote

import br.com.britawallet.base.extensions.serverDelaySimulate
import br.com.britawallet.data.global.Dictionary
import br.com.britawallet.data.local.UserLocalRepository
import br.com.britawallet.data.model.Currency
import br.com.britawallet.data.model.ServiceResponse
import br.com.britawallet.data.model.User
import br.com.britawallet.data.model.getBodyOrNull
import br.com.britawallet.data.model.toServiceBody

class UserMockRemoteRepository(
    private val dictionary: Dictionary,
    private val userLocalRepository: UserLocalRepository
) : UserRemoteRepository {

    companion object {
        val users = listOf(
            "Wiliam",
            "Leticia",
            "Pedro",
            "Joao",
            "Jorge"
        )

        const val DEFAULT_PASSWORD = "123456"
    }

    override suspend fun login(
        login: String,
        password: String
    ): ServiceResponse<User> {
        serverDelaySimulate()
        return if (isValidLogin(login, password)) {
            val userDatabase = getLocalUserOrNull(login)

            // if it exists in the database, the user has already been used in this application.
            // else, create a new user
            val user = if (userDatabase == null) {
                val wallet = listOf(
                    User.Balance(Currency.BRL(), 100_000.00),
                    User.Balance(Currency.BRT(), 0.00),
                    User.Balance(Currency.BTC(), 0.00)
                )

                User(login, login, wallet, true).apply {
                    userLocalRepository.saveUser(this)
                }
            } else userDatabase

            return user.toServiceBody()
        } else {
            ServiceResponse.ERROR(dictionary.SERVICE_ERROR_INVALID_LOGIN_OR_PASSWORD)
        }
    }

    override suspend fun attUserWallet(user: User) {
        userLocalRepository.saveWallet(user)
    }

    private fun isValidLogin(
        login: String,
        password: String
    ): Boolean {
        val matchPassword = password == DEFAULT_PASSWORD
        val matchLogin = users.any { it.equals(login, ignoreCase = true) }

        return matchPassword && matchLogin
    }

    private fun getLocalUserOrNull(login: String): User? {
        return userLocalRepository.getUser(login).getBodyOrNull()
    }
}