package com.example.practicandoexamen.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PromotionScreen(hora1: String,hora2: String, venta1:String, venta2:String) {
    Scaffold() {
            paddingValues -> PromotionContent(modifier = Modifier.padding(paddingValues),
        hora1,hora2,venta1,venta2)
    }
}


@Composable
fun PromotionContent(modifier: Modifier, hora1: String,hora2: String, venta1:String, venta2:String){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(venta1)
        Text(venta2)
        Text(hora1)
        Text(hora2)
    }
}

