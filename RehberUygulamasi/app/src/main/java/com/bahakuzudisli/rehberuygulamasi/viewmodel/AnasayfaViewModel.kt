package com.bahakuzudisli.rehberuygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bahakuzudisli.rehberuygulamasi.entity.Kisi
import com.bahakuzudisli.rehberuygulamasi.repo.KisilerDaoRepository

class AnasayfaViewModel(application: Application):AndroidViewModel(application) {

    val krepo = KisilerDaoRepository(application)
    var kisilerListesi = MutableLiveData<List<Kisi>>()

    init {
        kisileriYukle()
        kisilerListesi=krepo.kisileriGetir()
    }

    fun kisileriYukle(){
    krepo.tumKisileriAl()
    }

    fun ara(aramaKelimesi:String){
    krepo.kisiAra(aramaKelimesi)
    }

    fun sil(kisiId:Int){
        krepo.kisiSil(kisiId)
    }
}