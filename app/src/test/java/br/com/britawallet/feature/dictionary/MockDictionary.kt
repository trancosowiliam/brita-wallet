package br.com.britawallet.feature.dictionary

import br.com.britawallet.data.global.Dictionary

class MockDictionary : Dictionary {
    override val LOGIN_EMPTY_USER = ""
    override val LOGIN_EMPTY_PASSWORD = ""

    override val SERVICE_ERROR_INVALID_LOGIN_OR_PASSWORD = ""
}