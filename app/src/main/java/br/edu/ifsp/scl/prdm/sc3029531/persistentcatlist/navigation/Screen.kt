package br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.navigation

sealed class Screen(val route: String) {

    // Aqui, List e Cat são apenas identificadores de objetos que herdam de Screen
    data object CatList: Screen(route = "cat_list_screen")
    data object Cat: Screen(route = "cat_screen")
}