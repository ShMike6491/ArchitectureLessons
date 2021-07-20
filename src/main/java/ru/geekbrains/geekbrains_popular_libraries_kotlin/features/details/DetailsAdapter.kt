package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.Repo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ItemHeaderBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ItemRepoBinding

class DetailsAdapter(private val owner: User) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var reposList: List<Repo> = emptyList()

    fun setData(repos: List<Repo>) {
        reposList = repos
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (position) {
        0 -> VIEW_TYPE_HEADER
        else -> VIEW_TYPE_REPO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(
                owner,
                ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEW_TYPE_REPO -> RepoViewHolder(
                ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepoViewHolder) holder.bind(reposList[position - 1])
    }

    override fun getItemCount() = reposList.size + 1

    companion object {
        const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_REPO = 2
    }
}

class HeaderViewHolder(private val owner: User, binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.apply {
            Glide.with(ivAvatar).load(owner.avatar).into(ivAvatar)
            tvUser.text = owner.login
        }
    }
}

class RepoViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Repo) {
        binding.apply {
            tvTitle.text = item.name
            tvDescription.text = item.description
            item.stars?.let {
                stars.isVisible = true
                stars.text = it.toString()
            }
            item.language?.let {
                language.isVisible = true
                language.text = it
            }
        }
    }
}