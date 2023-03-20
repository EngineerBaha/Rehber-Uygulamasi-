package com.bahakuzudisli.rehberuygulamasi.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bahakuzudisli.rehberuygulamasi.viewmodel.AnasayfaViewModel
import com.bahakuzudisli.rehberuygulamasi.viewmodel.KisiKayitViewModel

class KisiKayitViewModelFactory (var application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KisiKayitViewModel(application) as T
    }
}