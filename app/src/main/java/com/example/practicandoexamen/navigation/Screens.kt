package com.example.practicandoexamen.navigation

sealed class Screens(val route: String) {
    object MoviesScreen : Screens("movies")
    object MovieDetailScreen: Screens("moviedetail")
    object CinemaScreen: Screens("cinema")
    object CinemaDetailScreen: Screens("cinemadetail")
    object CinemaMapScreen: Screens("cinemamap")
    object PromotionScreen: Screens("promotion")
}