package com.bahakuzudisli.roomkullanimi

import androidx.room.*
import com.bahakuzudisli.rehberuygulamasi.entity.Kisi

@Dao
interface KisilerDao {

    @Query("SELECT * FROM kisiler")
    suspend fun tumKisiler():List<Kisi>

    @Insert
    suspend fun kisiEkle(kisiler: Kisi)

    @Update
    suspend fun kisiGuncelle(kisiler: Kisi)

    @Query("SELECT * FROM kisiler WHERE kisi_ad like '%' || :aramaKelimesi || '%'")
    suspend fun kisiAra(aramaKelimesi:String):List<Kisi>

    @Delete
    suspend fun kisiSil(kisiler: Kisi)

}