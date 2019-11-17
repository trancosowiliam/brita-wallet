package br.com.britawallet.base

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
}