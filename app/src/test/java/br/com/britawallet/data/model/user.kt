package br.com.britawallet.data.model

import java.math.BigDecimal

fun mockUser(
    login: String = "login",
    name: String = "name",
    wallet: List<User.Balance> = mockBalanceList(),
    isFirstLogin: Boolean = false
) = User(
    login = login,
    name = name,
    wallet = wallet,
    isFirstLogin = isFirstLogin
)

fun mockBalanceList(
    size: Int = 3
) = List(size) {
    mockBalance(
        currencyType = "type $it",
        quantity = it.toBigDecimal()
    )
}

fun mockBalance(
    currencyType: String,
    quantity: BigDecimal
) = User.Balance(
    currencyType,
    quantity
)