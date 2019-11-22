package br.com.britawallet.base.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.britawallet.BuildConfig
import br.com.britawallet.R

fun Context.showTodoDialog() {
    AlertDialog.Builder(this)
        .setTitle(R.string.todo_title)
        .setMessage(R.string.todo_message)
        .setPositiveButton(android.R.string.ok) { _, _ -> }
        .create()
        .show()
}

fun Context.showMessageDialog(
    message: String,
    title: String? = null,
    textButton: String = getString(android.R.string.ok),
    cancel: String? = null,
    confirmAction: () -> Unit = {}
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(textButton) { _, _ -> confirmAction() }
        .apply {
            cancel?.let {
                this.setNegativeButton(cancel) { _, _ -> }
            }
        }
        .create()
        .show()
}

fun <T> Context.showRadioDialog(
    items: List<T>,
    selectedItem: T? = null,
    title: String,
    positiveButton: ItemMenuOptions<T>?,
    negativeButton: ItemMenu? = null,
    neutralButton: ItemMenu? = null,
    transformLabel: (T) -> String
) {
    AlertDialog.Builder(this).apply {
        setTitle(title)

        val itemsArray = items.map { transformLabel(it) }.toTypedArray()
        var selected = selectedItem ?: items[0]
        val checkedItem = items.indexOf(selected)

        setSingleChoiceItems(itemsArray, checkedItem) { _, which ->
            selected = items[which]
        }

        positiveButton?.let {
            setPositiveButton(it.titleResId) { dialog, _ ->
                dialog.dismiss()
                it.action(selected)
            }
        }

        negativeButton?.let {
            setNegativeButton(it.titleResId) { _, _ ->
                it.action()
            }
        }

        neutralButton?.let {
            setNeutralButton(it.titleResId) { dialog, _ ->
                dialog.dismiss()
                it.action()
            }
        }
    }.create().show()
}

fun log(message: String) {
    if (BuildConfig.DEBUG) {
        Log.i("INTERNAL_LOG", message)
    }
}

fun Context.toast(str: String) {
    Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
}

class ItemMenu(val id: Int, val titleResId: Int, val action: () -> Unit = {})
class ItemMenuOptions<T>(
    val id: Int,
    val titleResId: Int,
    val action: (T) -> Unit = {}
)