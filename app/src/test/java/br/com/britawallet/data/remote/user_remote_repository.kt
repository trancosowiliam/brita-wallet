package br.com.britawallet.data.remote

import br.com.britawallet.data.model.ServiceResponse
import br.com.britawallet.data.model.mockUser
import br.com.britawallet.data.model.toServiceBody
import com.nhaarman.mockitokotlin2.whenever

const val LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL"
const val LOGIN_FAILURE = "LOGIN_FAILURE"
const val LOGIN_FIRST_LOGIN = "LOGIN_FIRST_LOGIN"
const val VALID_PASSWORD = "VALID_PASSWORD"

suspend fun UserRemoteRepository.initMock() {

    whenever(this.login(LOGIN_SUCCESSFUL, VALID_PASSWORD))
        .thenReturn(mockUser().toServiceBody())

    whenever(this.login(LOGIN_FIRST_LOGIN, VALID_PASSWORD))
        .thenReturn(mockUser(isFirstLogin = true).toServiceBody())

    whenever(this.login(LOGIN_FAILURE, VALID_PASSWORD))
        .thenReturn(ServiceResponse.ERROR())
}