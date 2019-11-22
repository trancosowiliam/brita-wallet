package br.com.britawallet.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.britawallet.data.entity.BalanceEntity
import br.com.britawallet.data.entity.TransactionEntity
import br.com.britawallet.data.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        BalanceEntity::class,
        TransactionEntity::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun balanceDao(): BalanceDao
    abstract fun transactionDao(): TransactionDao
}

