package com.example.practicandoexamen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domain.Movie
import com.example.practicandoexamen.screen.CinemaMap
import com.example.practicandoexamen.screen.MovieDetailScreen
import com.example.practicandoexamen.screen.MoviesScreen
import com.example.practicandoexamen.screen.PromotionScreen
import com.example.practicandoexamen.viewmodel.MovieViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    var uuid = UUID.randomUUID().toString()
    // Write a message to the database
    val database = Firebase.database
    val hora1 = database.getReference("hora 1")
    val hora2 = database.getReference("hora 2")
    val venta1 = database.getReference("venta 1")
    val venta2 = database.getReference("venta 2")

    var title_hora1 by remember { mutableStateOf("")  }
    var title_hora2 by remember { mutableStateOf("")  }
    var title_venta1 by remember { mutableStateOf("")  }
    var title_venta2 by remember { mutableStateOf("")  }
    var text = "12:50"
//    myRef.setValue(text)




    hora1.addValueEventListener( object: ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


        override fun onDataChange(p0: DataSnapshot) {
            val value = p0.getValue(String::class.java)
            title_hora1 = value.toString()
        }
    })
    hora2.addValueEventListener( object: ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


        override fun onDataChange(p0: DataSnapshot) {
            val value = p0.getValue(String::class.java)
            title_hora2 = value.toString()
        }
    })
    venta1.addValueEventListener( object: ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


        override fun onDataChange(p0: DataSnapshot) {
            val value = p0.getValue(String::class.java)
            title_venta1 = value.toString()
        }
    })
    venta2.addValueEventListener( object: ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


        override fun onDataChange(p0: DataSnapshot) {
            val value = p0.getValue(String::class.java)
            title_venta2 = value.toString()
        }
    })


    NavHost(
        navController = navController,
        startDestination = Screens.PromotionScreen.route
    ) {
        composable(Screens.MoviesScreen.route) {
            val movieViewModel : MovieViewModel = hiltViewModel()
            MoviesScreen(
                onClick = {
                        movieId -> navController.navigate("${Screens.MovieDetailScreen.route}/${movieId}")
                },
                movieViewModel
            )
        }
        composable(
            route = "${Screens.MovieDetailScreen.route}/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                }
            )
        ) {
            MovieDetailScreen(
                onBackPressed = {
                    navController.popBackStack()
                },
                movieId = it.arguments?.getString("movieId")?:""
            )
        }
        composable(Screens.CinemaMapScreen.route) {
            CinemaMap()
        }
        composable(Screens.PromotionScreen.route) {
            PromotionScreen(hora1 = title_hora1, hora2=title_hora2,venta1=title_venta1,venta2=title_venta2)
        }

    }
}
