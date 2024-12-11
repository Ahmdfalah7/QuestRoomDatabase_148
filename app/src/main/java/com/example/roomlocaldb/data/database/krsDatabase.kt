package com.example.roomlocaldb.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomlocaldb.data.dao.MahasiswaDao
import com.example.roomlocaldb.data.entity.Mahasiswa

@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)
abstract class krsDatabase : RoomDatabase(){
    //Mendefinisikan fungsi untuk mengakses data Mahasiswa
    abstract fun mahasiswaDao(): MahasiswaDao

    companion object {
        @Volatile // Memastikan bahwa nilai variabel Instance selalu sama di semua thread
        private var Instance: krsDatabase? = null

        fun getDatabase(context: Context) : krsDatabase {
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    krsDatabase::class.java,
                    "krsDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}