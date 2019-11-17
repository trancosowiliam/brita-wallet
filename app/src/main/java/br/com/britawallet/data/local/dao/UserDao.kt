package br.com.britawallet.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.britawallet.data.entity.UserEntity

@Dao
abstract class UserDao {
    @Query("SELECT * FROM USER")
    abstract fun getAll(): List<UserEntity>

    @Query("SELECT * FROM USER WHERE LOGIN LIKE :login")
    abstract fun getUser(login: String): UserEntity?

    @Query("UPDATE USER SET ACTIVE = (:login like NAME)")
    abstract fun setActiveUser(login: String)

    @Query("UPDATE USER SET ACTIVE = 0")
    abstract fun logout()

    @Query("SELECT * FROM USER WHERE ACTIVE = 1")
    abstract fun getActiveUser() : UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(vararg user: UserEntity)
}