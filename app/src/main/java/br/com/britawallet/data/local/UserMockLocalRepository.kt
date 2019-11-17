package br.com.britawallet.data.local

import br.com.britawallet.data.entity.BalanceEntity
import br.com.britawallet.data.entity.UserEntity
import br.com.britawallet.data.local.dao.BalanceDao
import br.com.britawallet.data.local.dao.UserDao
import br.com.britawallet.data.model.ServiceResponse
import br.com.britawallet.data.model.User
import br.com.britawallet.data.model.toServiceBody

class UserMockLocalRepository(
    private val userDao: UserDao,
    private val balanceDao: BalanceDao
) : UserLocalRepository {

    override fun getActiveUser(): ServiceResponse<User?> {
        return userDao.getActiveUser().toUser().toServiceBody()
    }

    override fun getUser(login: String): ServiceResponse<User?> {
        return userDao.getUser(login).toUser().toServiceBody()
    }

    override fun saveUser(user: User) = ServiceResponse.OK.apply {
        userDao.save(
            UserEntity(
                login = user.login,
                name = user.name
            )
        )

        balanceDao.save(*user.wallet.map {
            BalanceEntity(
                it.currencyType,
                it.quantity.toDouble(),
                user.login
            )
        }.toTypedArray())
    }

    override fun login(user: User) = ServiceResponse.OK.apply {
        userDao.setActiveUser(user.login)
    }

    override fun logout() = ServiceResponse.OK.apply {
        userDao.logout()
    }

    private fun UserEntity?.toUser(): User? {
        return this?.let { userEntity ->
            val walletEntity = balanceDao.getBalanceForUser(userEntity.login)

            User(
                login = userEntity.login,
                name = userEntity.name,
                isFirstLogin = false,
                wallet = walletEntity.map { balanceEntity ->
                    User.Balance(
                        currencyType = balanceEntity.type,
                        quantity = balanceEntity.quantity.toBigDecimal()
                    )
                }
            )
        }
    }
}