package com.bahakuzudisli.roomkullanimi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bahakuzudisli.rehberuygulamasi.entity.Kisi
import java.security.AccessControlContext


@Database(entities = [Kisi::class], version = 1)
abstract class Veritabani : RoomDatabase() {
    abstract fun kisilerDao(): KisilerDao

    companion object {
        var INSTANCE: Veritabani? = null

        fun veritabaniErisim(context: Context): Veritabani? {

            if (INSTANCE == null) {
                synchronized(Veritabani::class) {
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext,
                            Veritabani::class.java,
                            name = "rehber.sqlite"
                        ).createFromAsset("rehber.sqlite").build()
                }
            }
            return INSTANCE
        }
    }

}