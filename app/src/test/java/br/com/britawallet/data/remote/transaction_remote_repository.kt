package br.com.britawallet.data.remote

import br.com.britawallet.data.model.Transaction
import br.com.britawallet.data.model.toServiceBody
import com.nhaarman.mockitokotlin2.whenever
import org.mockito.ArgumentMatchers.anyString

fun TransactionRemoteRepository.initMock() {
    whenever(this.getAll(anyString())).thenReturn(
        List(5) {
            Transaction(
                buyType = "",
                sellType = "",
                buy = it.toDouble(),
                sell = (it * 2).toDouble(),
                date = "",
                user = ""
            )
        }.toServiceBody()
    )
}