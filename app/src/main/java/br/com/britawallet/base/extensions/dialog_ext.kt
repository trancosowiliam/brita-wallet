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
    textButton: String = getString(android.R.string.ok)) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(textButton) { _, _ -> }
        .create()
        .show()
}

fun log(message: String) {
    if (BuildConfig.DEBUG) {
        Log.i("INTERNAL_LOG", message)
    }
}

fun Context.toast(str: String) {
    Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
}