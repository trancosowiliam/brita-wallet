package br.com.britawallet.data.model

fun mockUser(
    login: String = "login",
    name: String = "name",
    wallet: List<User.Balance> = currencyToBalance(),
    isFirstLogin: Boolean = false
) = User(
    login = login,
    name = name,
    wallet = wallet,
    isFirstLogin = isFirstLogin
)

fun currencyToBalance() = Currency.all.mapIndexed { index, currency ->
    User.Balance(
        currencyType = currency(),
        quantity = index.toBigDecimal()
    )
}