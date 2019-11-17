package br.com.britawallet.data.local

import br.com.britawallet.data.model.ServiceResponse
import br.com.britawallet.data.model.User
import br.com.britawallet.data.model.toServiceBody
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever

suspend fun UserLocalRepository.mockHasUser() {
    val user = mock<User>()

    whenever(this.getActiveUser()).thenReturn(user.toServiceBody())
}

suspend fun UserLocalRepository.mockNoHasUser() {
    whenever(this.getActiveUser()).thenReturn(ServiceResponse.BODY(null))
}