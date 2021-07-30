package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.BackButtonListener
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentDetailsBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.User
import ru.geekbrains.geekbrains_popular_libraries_kotlin.data.local.LocalDatabase
import ru.geekbrains.geekbrains_popular_libraries_kotlin.navigation.AndroidScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.network.AndroidNetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.util.GlideImageLoader
import ru.geekbrains.geekbrains_popular_libraries_kotlin.util.ImageCacheImpl
import javax.inject.Inject

class DetailsFragment : MvpAppCompatFragment(R.layout.fragment_details), DetailsView, BackButtonListener {
    @Inject lateinit var database: LocalDatabase
    @Inject lateinit var router: Router

    private val presenter by moxyPresenter {
        DetailsPresenter(
            arguments?.getParcelable(DETAILS_TAG)!!,
            AndroidSchedulers.mainThread(),
            App.instance.repository,
            App.instance.router,
            AndroidScreens()
        )
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
            GlideImageLoader(
                AndroidNetworkStatus(requireContext()),
                ImageCacheImpl(App.instance.database, requireContext()),
                AndroidSchedulers.mainThread()
            )
        )
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
           return DetailsFragment().apply {
               arguments = args
               App.instance.appComponent.inject(this)
           }
        }
    }
}