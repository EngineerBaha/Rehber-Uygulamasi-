package com.bahakuzudisli.rehberuygulamasi.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "kisiler")
data class Kisi(@PrimaryKey(autoGenerate = true)
                   @ColumnInfo("kisi_id") @NotNull var kisiId: Int,
                   @ColumnInfo("kisi_ad") @NotNull var kisiAd: String,
                   @ColumnInfo("kisi_tel") @NotNull var kisiTel: String)