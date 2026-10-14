package br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.CatViewModel
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.model.Operation
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.ui.composable.screen.CatListScreen

@Composable
fun CatListRoute(modifier: Modifier = Modifier, catViewModel: CatViewModel, onNavigate: () -> Unit) {

    val catListFromVM by catViewModel.currentCatList.collectAsStateWithLifecycle()

    CatListScreen(
        modifier = modifier,
        catList = catListFromVM,
        onViewCat = { currentCat ->
            catViewModel.updateCurrentCatAndOperation(
                cat = currentCat,
                operation = Operation.VIEW
            )
            onNavigate()
        },
        onEditCat = { currentCat ->
            catViewModel.updateCurrentCatAndOperation(
                cat = currentCat,
                operation = Operation.NEW_OR_EDIT
            )
            onNavigate()
        },
        onRemoveCat = {currentCat ->
            catViewModel.removeCat(currentCat)
        }
    )

}