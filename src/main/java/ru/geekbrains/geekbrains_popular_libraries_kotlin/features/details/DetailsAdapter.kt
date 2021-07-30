package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ItemHeaderBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ItemRepoBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.util.IImageLoader
import javax.inject.Inject

class DetailsAdapter(private val presenter: IDetailsListPresenter, private val owner: User)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    override fun getItemViewType(position: Int) = when (position) {
        0 -> VIEW_TYPE_HEADER
        else -> VIEW_TYPE_REPO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(
                ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEW_TYPE_REPO -> RepoViewHolder(
                ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepoViewHolder) presenter.bindView(holder.apply{pos = position-1})
    }

    override fun getItemCount() = presenter.getCount() + 1

    companion object {
        const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_REPO = 2
    }

    inner class HeaderViewHolder(binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                owner.avatar?.let {imageLoader.loadInto(it, ivAvatar)}
                tvUser.text = owner.login
            }
        }
    }

    inner class RepoViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root), IDetailItemView {

        init {
            binding.root.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

        override fun setTitle(txt: String) {
            binding.tvTitle.text = txt
        }

        override fun setDescription(txt: String) {
            binding.tvDescription.text = txt
        }

        override fun setStars(count: Int) {
            binding.stars.apply {
                isVisible = true
                text = count.toString()
            }
        }

        override fun setLanguage(txt: String) {
            binding.language.apply {
                isVisible = true
                text = txt
            }
        }

        override var pos = -1
    }
}
