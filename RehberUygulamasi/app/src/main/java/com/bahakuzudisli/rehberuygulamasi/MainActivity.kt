package com.bahakuzudisli.rehberuygulamasi

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bahakuzudisli.rehberuygulamasi.entity.Kisi
import com.bahakuzudisli.rehberuygulamasi.ui.theme.RehberUygulamasiTheme
import com.bahakuzudisli.rehberuygulamasi.viewmodel.AnasayfaViewModel
import com.bahakuzudisli.rehberuygulamasi.viewmodelfactory.AnasayfaViewModelFactory
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RehberUygulamasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SayfaGecisleri()
                }
            }
        }
    }
}


@Composable
fun SayfaGecisleri(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Anasayfa" ){
        composable("Anasayfa"){
        Anasayfa(navController)
        }
        composable("KisiKayitSayfa"){
                KisiKayitySayfa()
        }
        composable("KisiDetaySayfa/{kisi}", arguments = listOf(
            navArgument("kisi"){type= NavType.StringType}
        )){
            val json = it.arguments?.getString("kisi")!!
            val nesne = Gson().fromJson(json,Kisi::class.java)
            KisiDetaySayfa(gelenKisi = nesne)
        }
    }
}
@Composable
fun Anasayfa(navController: NavController) {

    val aramaYapiliyorMu = remember { mutableStateOf(false) }
    val tf = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val viewModel:AnasayfaViewModel = viewModel(
        factory = AnasayfaViewModelFactory(context.applicationContext as Application)
    )

    val kisilerListesi = viewModel.kisilerListesi.observeAsState(listOf())

    LaunchedEffect(key1 = true ){
        viewModel.kisileriYukle()
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            if (aramaYapiliyorMu.value) {
                TextField(
                    value = tf.value,
                    onValueChange = {
                        tf.value = it
                        viewModel.ara(it)

                    },
                    label = {
                        Text(
                            text = "ARA"
                        )
                    }, colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        unfocusedIndicatorColor = Color.White
                    )

                )
            } else
                Text(text = "Kisilerim")
        },
            actions = {

                if (aramaYapiliyorMu.value) {
                    IconButton(onClick = {
                        aramaYapiliyorMu.value = false
                        tf.value = ""

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.close_resim),
                            contentDescription = "", tint = Color.White
                        )

                    }
                } else {
                    IconButton(onClick = {
                        aramaYapiliyorMu.value = true

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arama_resim),
                            contentDescription = "", tint = Color.White
                        )
                    }
                }
            }
        )
    },
        content = {
        LazyColumn{
            items(
                count = kisilerListesi.value!!.count(),
                itemContent = {
                    val kisi = kisilerListesi.value!![it]
                    Card(modifier = Modifier
                        .padding(all = 5.dp)
                        .fillMaxWidth()) {
                        Row(modifier = Modifier.clickable {
                            val kisiJson = Gson().toJson(kisi)
                            navController.navigate("KisiDetaySayfa/$kisiJson")
                        }) {
                            Row(modifier = Modifier
                                .padding(all = 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "${kisi.kisiAd} - ${kisi.kisiTel}")

                               IconButton(onClick = { viewModel.sil(kisi.kisiId) }) {
                                   Icon(
                                   painter = painterResource(id = R.drawable.delete_resim),
                                   contentDescription = "", tint = Color.Black)
                               }
                            }
                        }
                        
                        
                        
                    }
                }
            )
        }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("KisiKayitSayfa") },
                backgroundColor = colorResource(id = R.color.teal_200),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.add_resim),
                        contentDescription = "", tint = Color.White
                    )
                }
            )
        }
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RehberUygulamasiTheme {

    }
}