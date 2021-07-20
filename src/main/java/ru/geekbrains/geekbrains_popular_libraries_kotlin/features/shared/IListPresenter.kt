package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.shared

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}