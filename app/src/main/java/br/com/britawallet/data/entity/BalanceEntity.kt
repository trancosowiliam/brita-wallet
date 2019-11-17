package br.com.britawallet.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = DB.BALANCE.TABLE,
    primaryKeys = [DB.BALANCE.COLUMNS.TYPE, DB.BALANCE.COLUMNS.USER_ID],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [DB.USER.COLUMNS.LOGIN],
            childColumns = [DB.BALANCE.COLUMNS.USER_ID],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class BalanceEntity(
    @ColumnInfo(name = DB.BALANCE.COLUMNS.TYPE) val type: String,
    @ColumnInfo(name = DB.BALANCE.COLUMNS.QUANTITY) var quantity: Double,
    @ColumnInfo(name = DB.BALANCE.COLUMNS.USER_ID) val user: String
)