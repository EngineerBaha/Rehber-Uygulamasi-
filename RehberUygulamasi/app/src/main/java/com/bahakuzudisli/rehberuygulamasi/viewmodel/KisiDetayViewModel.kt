package com.bahakuzudisli.rehberuygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.bahakuzudisli.rehberuygulamasi.repo.KisilerDaoRepository

class KisiDetayViewModel(application: Application): AndroidViewModel(application) {

    val krepo = KisilerDaoRepository(application)

    fun guncelle(kisiId:Int,kisiAd:String,kisiTel:String){
        //ROOM İŞLEMLERİ
        krepo.kisiGuncelle(kisiId,kisiAd,kisiTel)
    }
}