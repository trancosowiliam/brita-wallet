package br.com.britawallet.base.extensions

fun Any?.whenNull(action: () -> Unit) {
    if (this == null) action()
}