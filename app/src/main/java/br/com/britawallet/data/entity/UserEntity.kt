package br.com.britawallet.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(
    tableName = DB.USER.TABLE,
    primaryKeys = [DB.USER.COLUMNS.LOGIN]
)
data class UserEntity(
    @ColumnInfo(name = DB.USER.COLUMNS.LOGIN) val login: String,
    @ColumnInfo(name = DB.USER.COLUMNS.NAME) val name: String,
    @ColumnInfo(name = DB.USER.COLUMNS.ACTIVE) val active: Boolean = false
)