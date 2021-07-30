package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details

import android.os.Bundle
import android.view.View
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.BackButtonListener
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentDetailsBinding

class DetailsFragment : MvpAppCompatFragment(R.layout.fragment_details), DetailsView, BackButtonListener {
    private val presenter by moxyPresenter {
        DetailsPresenter(
            arguments?.getParcelable(DETAILS_TAG)!!
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }
    private var adapter: DetailsAdapter? = null
    private var binding: FragmentDetailsBinding? = null
    private val b get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
    }

    override fun init(user: User) {
        adapter = DetailsAdapter(
            presenter.listPresenter, user,
        ).apply {
            App.instance.appComponent.inject(this)
        }
        b.rvContainer.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        private const val DETAILS_TAG = "ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details"

        fun newInstance(user: User): DetailsFragment {
            val args = Bundle()
            args.putParcelable(DETAILS_TAG, user)
           return DetailsFragment().apply { arguments = args }
        }
    }
}