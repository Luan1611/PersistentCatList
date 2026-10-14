package br.edu.ifsp.scl.prdm.sc3029531.persistentcatlist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val id: Int = INVALID_CONTACT_ID,
    val name: String = "",
    val age: String = "",
    val breed: String = "",
    val description: String = ""
): Parcelable {
    companion object {
        const val INVALID_CONTACT_ID = -1
    }
}
