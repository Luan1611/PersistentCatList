package br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.CatViewModel
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.ui.composable.screen.CatScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    catViewModel: CatViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Screen.CatList.route
    ) {

        composable(
            route = Screen.CatList.route
        ) {
            CatListRoute(
                catViewModel = catViewModel,
                onNavigate = {navHostController.navigate(Screen.Cat.route)}
            )
        }

        composable(
            route = Screen.Cat.route
        ) {
            CatRoute(catViewModel = catViewModel) {
                //aqui é a implementação do OnDone() declarado como parâmetro em CatRoute
                navHostController.popBackStack()
            }
        }

    }

}