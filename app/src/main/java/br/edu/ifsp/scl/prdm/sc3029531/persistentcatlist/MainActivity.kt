package br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.navigation.MainNavHost
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.navigation.Screen
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.ui.composable.component.MainTopAppBar
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.ui.theme.PersistentCatListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navHostController = rememberNavController()
            val catViewModel: CatViewModel = viewModel()
            val navStackBackEntry by navHostController.currentBackStackEntryAsState()
            val showActions = navStackBackEntry?.destination?.route == Screen.CatList.route

            PersistentCatListTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MainTopAppBar(
                            showActions = showActions
                        ) {
                            navHostController.navigate(route = Screen.Cat.route)
                        }
                    }

                ) { innerPadding ->
                    MainNavHost(
                        navHostController = navHostController,
                        catViewModel = catViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

        }
    }
}