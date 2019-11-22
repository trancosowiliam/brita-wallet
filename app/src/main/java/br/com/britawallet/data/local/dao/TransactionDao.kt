package br.com.britawallet.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.britawallet.data.entity.TransactionEntity

@Dao
abstract class TransactionDao {
    @Query("SELECT * FROM 'TRANSACTION' WHERE USER_ID = :login")
    abstract fun getAll(login:String): List<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(vararg transaction: TransactionEntity)
}