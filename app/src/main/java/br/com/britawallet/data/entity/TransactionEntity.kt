package br.com.britawallet.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = DB.TRANSACTION.TABLE,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [DB.USER.COLUMNS.LOGIN],
            childColumns = [DB.TRANSACTION.COLUMNS.USER_ID],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class TransactionEntity(
    @ColumnInfo(name = DB.TRANSACTION.COLUMNS.BUY_TYPE) val buyType: String,
    @ColumnInfo(name = DB.TRANSACTION.COLUMNS.SELL_TYPE) val sellType: String,
    @ColumnInfo(name = DB.TRANSACTION.COLUMNS.BUY_VALUE) val buy: Double,
    @ColumnInfo(name = DB.TRANSACTION.COLUMNS.SELL_VALUE) val sell: Double,
    @ColumnInfo(name = DB.TRANSACTION.COLUMNS.TRANSACTION_DATE) val date: String,
    @ColumnInfo(name = DB.TRANSACTION.COLUMNS.USER_ID) val user: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)