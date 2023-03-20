package com.bahakuzudisli.rehberuygulamasi.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bahakuzudisli.rehberuygulamasi.entity.Kisi
import com.bahakuzudisli.roomkullanimi.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class KisilerDaoRepository(application: Application) {

    var kisilerListesi = MutableLiveData<List<Kisi>>()
    var vt:Veritabani


    init {
        vt=Veritabani.veritabaniErisim(application)!!
        kisilerListesi = MutableLiveData()

    }

    fun kisileriGetir():MutableLiveData<List<Kisi>>{
        return kisilerListesi
    }

    fun tumKisileriAl(){
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value=vt.kisilerDao().tumKisiler()
        }
    }

    fun kisiAra(aramaKelimesi:String){

        val job:Job = CoroutineScope(Dispatchers.Main).launch {
           kisilerListesi.value= vt.kisilerDao().kisiAra(aramaKelimesi)

        }
    }

    fun kisiKayit(kisiAd:String,kisiTel:String){
        //ROOM İŞLEMLERİ
        var kisi = Kisi(0,kisiAd,kisiTel)

        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            vt.kisilerDao().kisiEkle(kisi)

        }
    }

    fun kisiGuncelle(kisiId:Int,kisiAd:String,kisiTel:String){
        //ROOM İŞLEMLERİ
        var kisi = Kisi(kisiId,kisiAd,kisiTel)

        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            vt.kisilerDao().kisiGuncelle(kisi)

        }
    }

    fun kisiSil(kisiId:Int){
        //ROOM İŞLEMLERİ
        var kisi = Kisi(kisiId,"","")

        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            vt.kisilerDao().kisiSil(kisi)
        tumKisileriAl()
        }
    }
}