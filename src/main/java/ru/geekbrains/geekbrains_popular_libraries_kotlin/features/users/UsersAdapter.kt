package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ItemUserBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users.list.IUserItemView

class UsersAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: ItemUserBinding) :
        RecyclerView.ViewHolder(vb.root), IUserItemView {
        init {
            vb.root.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

        override var pos = -1
    }
}