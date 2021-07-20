package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details

import android.os.Bundle
import android.view.View
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentDetailsBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation.AndroidScreens

class DetailsFragment : MvpAppCompatFragment(R.layout.fragment_details) {
    private lateinit var user: User
    private lateinit var adapter: DetailsAdapter
    private var binding: FragmentDetailsBinding? = null
    private val b get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        user = arguments?.getParcelable(DETAILS_TAG)!!
        adapter = DetailsAdapter(user)
        b.rvContainer.adapter = adapter

        val appRepo = App.instance.repository()
        appRepo.getUserRepos(user.repos!!).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    adapter.setData(it)
            }, { error ->
                error.printStackTrace()
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val DETAILS_TAG = "ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details"

        fun newInstance(user: User): DetailsFragment {
            val args = Bundle()
            args.putParcelable(DETAILS_TAG, user)
           return DetailsFragment().apply { arguments = args }
        }
    }
}