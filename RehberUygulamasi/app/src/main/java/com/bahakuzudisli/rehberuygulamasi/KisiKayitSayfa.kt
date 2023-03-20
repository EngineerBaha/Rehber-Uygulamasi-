package com.bahakuzudisli.rehberuygulamasi

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bahakuzudisli.rehberuygulamasi.viewmodel.AnasayfaViewModel
import com.bahakuzudisli.rehberuygulamasi.viewmodel.KisiKayitViewModel
import com.bahakuzudisli.rehberuygulamasi.viewmodelfactory.AnasayfaViewModelFactory
import com.bahakuzudisli.rehberuygulamasi.viewmodelfactory.KisiKayitViewModelFactory

@Composable
fun KisiKayitySayfa() {

    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    val context = LocalContext.current
    val viewModel: KisiKayitViewModel = viewModel(
        factory = KisiKayitViewModelFactory(context.applicationContext as Application)
    )

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Kisi Kaydet") })
    },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(value = tfKisiAd.value, onValueChange = { tfKisiAd.value = it }, label = {
                    Text(
                        text = "KisiAd"
                    )
                })
                TextField(
                    value = tfKisiTel.value,
                    onValueChange = { tfKisiTel.value = it },
                    label = {
                        Text(
                            text = "KisiTel"
                        )
                    })

                Button(onClick = {
                    val kisiAd = tfKisiAd.value
                    val kisiTel = tfKisiTel.value

                    //ROOM İŞLEMLERİ
                   viewModel.kayit(kisiAd,kisiTel)
                    localFocusManager.clearFocus()
                }
                    , modifier = Modifier.size(width = 250.dp, height = 50.dp)

                ) {
                    Text(text = "KAYDET")
                }
            }

        }

    )

}