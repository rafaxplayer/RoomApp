package com.jrs.roomapp.database.user

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true,name="id")
    var id: Int?=null,
    @ColumnInfo(name="name") 
    var name:String,
    @ColumnInfo(name="last_name") 
    var lastname:String,
    @ColumnInfo(name="phone") 
    var phone:String
):Parcelable