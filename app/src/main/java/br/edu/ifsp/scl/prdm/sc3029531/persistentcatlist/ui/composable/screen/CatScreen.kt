package br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.ui.composable.screen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.model.Cat
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.model.Operation
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.ui.theme.PersistentCatListTheme

@Composable
fun CatScreen(cat: Cat, operation: Operation, modifier: Modifier = Modifier, onSaveAndQuit: (Cat) -> Unit) {

    var currentCat by rememberSaveable { mutableStateOf(cat) }

    val isFieldsEnabled: Boolean = operation == Operation.NEW_OR_EDIT

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Cat Name") },
            value = currentCat.name,
            onValueChange = { currentCatName ->
                currentCat = currentCat.copy(name = currentCatName)
            },
            enabled = isFieldsEnabled
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Cat Age") },
            value = currentCat.age,
            onValueChange = { currentCatAge ->
                currentCat = currentCat.copy(age = currentCatAge)
            },
            enabled = isFieldsEnabled
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Cat Breed") },
            value = currentCat.breed,
            onValueChange = { currentCatBreed ->
                currentCat = currentCat.copy(breed = currentCatBreed)
            },
            enabled = isFieldsEnabled
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Cat Description") },
            value = currentCat.description,
            onValueChange = { currentCatDescription ->
                currentCat = currentCat.copy(description = currentCatDescription)
            },
            enabled = isFieldsEnabled
        )

        if (isFieldsEnabled) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onSaveAndQuit(currentCat)
                }
            ) {
                Text(text = "Save and quit")
            }
        }

    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CatScreenPreview() {
    PersistentCatListTheme {
        Surface {
            CatScreen(
                cat = Cat(),
                onSaveAndQuit = {},
                operation = Operation.VIEW
            )
        }
    }
}