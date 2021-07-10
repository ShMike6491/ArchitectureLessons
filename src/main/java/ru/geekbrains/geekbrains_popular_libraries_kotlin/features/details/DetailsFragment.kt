package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details

import android.os.Bundle
import android.view.View
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentDetailsBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.model.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.presenter.DetailsPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.view.DetailsView

class DetailsFragment : MvpAppCompatFragment(R.layout.fragment_details), DetailsView {
    private val presenter by moxyPresenter { DetailsPresenter() }

    private var binding: FragmentDetailsBinding? = null
    private val b get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        val user: User = arguments?.getParcelable(DETAILS_TAG)!!
        presenter.userReceived(user)
    }

    override fun showUserData(text: String) { b.tvUser.text = text }

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