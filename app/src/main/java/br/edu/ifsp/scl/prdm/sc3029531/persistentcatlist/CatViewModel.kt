package br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist

import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.model.Cat
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.model.Cat.Companion.INVALID_CONTACT_ID
import br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.model.Operation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.emptyList

class CatViewModel: ViewModel() {
    private val _currentCat = MutableStateFlow(value = Cat())

    // é um "modo leitura" de _currentCat que será exposto
    val currentCat: StateFlow<Cat> = _currentCat.asStateFlow()
    private val _currentCatList: MutableStateFlow<List<Cat>> = MutableStateFlow(value = emptyList())
    val currentCatList: StateFlow<List<Cat>> = _currentCatList.asStateFlow()

    private var nextCatId = 0


    fun saveCat(cat: Cat) {

        if (cat.id == INVALID_CONTACT_ID) {

            val newCat = cat.copy(id = nextCatId++)

            _currentCatList.update{ catList -> catList + newCat }
        }
        else {

            _currentCatList.update{ catList -> catList.map { currentIterationCat ->

                //procuro na lista, quando encontro, troco o gato antigo pelo gato atualizado
                //os demais gatos são recolocados na nova lista sem alteração de seus ids
                //o gato atualizado que chegou vai pra nova lista com novo id
                if (cat.id == currentIterationCat.id) cat else currentIterationCat

                }
            }

        }

    }

    private val _operation: MutableStateFlow<Operation> = MutableStateFlow(value = Operation.NEW_OR_EDIT)
    val operation: StateFlow<Operation> = _operation.asStateFlow()

    fun updateCurrentCatAndOperation(cat: Cat, operation: Operation) {
        _currentCat.value = cat
        _operation.value = operation
    }

    fun removeCat(cat: Cat) {
        _currentCatList.update {
            catList -> catList.filter {
            currentIterationCat -> cat.id != currentIterationCat.id
            }
        }
    }

}