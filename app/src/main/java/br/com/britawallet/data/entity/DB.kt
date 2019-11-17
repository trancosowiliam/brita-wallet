package br.com.britawallet.data.entity

class DB {
    object USER {
        const val TABLE = "USER"

        object COLUMNS {
            const val LOGIN = "LOGIN"
            const val NAME = "NAME"
            const val ACTIVE = "ACTIVE"
        }
    }

    object BALANCE {
        const val TABLE = "BALANCE"

        object COLUMNS {
            const val TYPE = "TYPE"
            const val USER_ID = "USER_ID"
            const val QUANTITY = "QUANTITY"
        }
    }
}
