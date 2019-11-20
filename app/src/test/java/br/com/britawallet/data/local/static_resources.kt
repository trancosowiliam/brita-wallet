package br.com.britawallet.data.local

import br.com.britawallet.data.model.mockUser
import com.nhaarman.mockitokotlin2.whenever

fun StaticResources.mockResources() {
    whenever(this.user).thenReturn(mockUser())
}