package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.repo

import android.os.Bundle
import android.view.View
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.Repo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentRepoPageBinding

class RepoPageFragment : MvpAppCompatFragment(R.layout.fragment_repo_page), RepoPageView {
    private val presenter by moxyPresenter { RepoPagePresenter(arguments?.getParcelable(REPO_TAG)!!) }
    private var binding: FragmentRepoPageBinding? = null
    private val b get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepoPageBinding.bind(view)
    }

    override fun setTitle(txt: String) {
        b.tvTitle.text = txt
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val REPO_TAG = "ru.geekbrains.geekbrains_popular_libraries_kotlin.features.repo"

        fun newInstance(repo: Repo): RepoPageFragment {
            val args = Bundle()
            args.putParcelable(REPO_TAG, repo)
            return RepoPageFragment().apply { arguments = args }
        }
    }
}