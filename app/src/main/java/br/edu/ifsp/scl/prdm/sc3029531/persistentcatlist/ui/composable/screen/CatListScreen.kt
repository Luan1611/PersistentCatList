package br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.ui.composable.screen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.model.Cat
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.ui.theme.PersistentCatListTheme

@Composable
fun CatListScreen(
    catList: List<Cat>,
    modifier: Modifier = Modifier,
    onViewCat: (Cat) -> Unit,
    onEditCat: (Cat) -> Unit,
    onRemoveCat: (Cat) -> Unit
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {

        items(items = catList, key = {currentItCat -> currentItCat.id}) { currentIterationCat ->

            var isExpanded by remember { mutableStateOf(value = false) }

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = {
                                onViewCat(currentIterationCat)
                            },
                            onLongClick = { isExpanded = true }
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = currentIterationCat.name)
                        Text(text = currentIterationCat.age)
                    }
                }
            }

            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                DropdownMenuItem(
                    text = {Text(text = "Edit cat")},
                    onClick = {
                        onEditCat(currentIterationCat)
                        isExpanded = false
                    }
                )

                DropdownMenuItem(
                    text = {Text(text = "Remove cat")},
                    onClick = {
                        onRemoveCat(currentIterationCat)
                        isExpanded = false
                    }
                )
            }

        }

    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CatListScreenPreview() {
    PersistentCatListTheme {
        Surface {
            CatListScreen(
                catList = listOf(),
                onViewCat = {},
                onEditCat = {},
                onRemoveCat = {}
            )
        }
    }
}