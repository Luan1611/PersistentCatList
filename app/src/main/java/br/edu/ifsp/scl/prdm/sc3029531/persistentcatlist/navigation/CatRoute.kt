package br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.CatViewModel
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.ui.composable.screen.CatScreen

@Composable
fun CatRoute(modifier: Modifier = Modifier, catViewModel: CatViewModel, onDone: () -> Unit) {

    val catFromVM by catViewModel.currentCat.collectAsStateWithLifecycle()

    val operationFromVM by catViewModel.operation.collectAsStateWithLifecycle()

    CatScreen(
        modifier = modifier,
        cat = catFromVM,
        onSaveAndQuit = { newOrEditedCat ->
            catViewModel.saveCat(newOrEditedCat)
            onDone() //quem vai implementar é o MainNavHost quando chamar CatRoute
        },
        operation = operationFromVM
    )

}