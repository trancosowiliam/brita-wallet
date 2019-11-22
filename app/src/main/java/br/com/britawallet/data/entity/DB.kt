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

    object TRANSACTION {
        const val TABLE = "TRANSACTION"

        object COLUMNS {
            const val BUY_TYPE = "BUY_TYPE"
            const val SELL_TYPE = "SELL_TYPE"
            const val BUY_VALUE = "BUY_VALUE"
            const val SELL_VALUE = "SELL_VALUE"
            const val TRANSACTION_DATE = "TRANSACTION_DATE"
            const val USER_ID = "USER_ID"
        }
    }
}
