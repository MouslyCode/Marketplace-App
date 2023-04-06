package com.example.tumbasmas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null,
    val name : String,
    val username : String,
    val kelamin : String,
    val email: String,
    val telp: String,
    val alamat: String,
    val ttl: String
)
