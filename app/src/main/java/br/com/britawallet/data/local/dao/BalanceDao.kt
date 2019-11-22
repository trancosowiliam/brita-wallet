package br.com.britawallet.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.britawallet.data.entity.BalanceEntity

@Dao
abstract class BalanceDao {
    @Query("SELECT * FROM BALANCE WHERE USER_ID = :login")
    abstract fun getBalanceForUser(login: String): List<BalanceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(vararg balance: BalanceEntity)
}