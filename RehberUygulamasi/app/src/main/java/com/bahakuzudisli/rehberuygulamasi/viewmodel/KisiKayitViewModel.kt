package com.bahakuzudisli.rehberuygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.bahakuzudisli.rehberuygulamasi.repo.KisilerDaoRepository

class KisiKayitViewModel(application: Application): AndroidViewModel(application) {
    val krepo = KisilerDaoRepository(application)


    fun kayit(kisiAd:String,kisiTel:String){
        krepo.kisiKayit(kisiAd,kisiTel)
    }

}