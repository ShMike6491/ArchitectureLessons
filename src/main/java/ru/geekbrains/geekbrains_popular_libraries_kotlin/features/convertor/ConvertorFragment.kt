package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.convertor

import android.os.Bundle
import android.view.View
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.BackButtonListener
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentConvertorBinding

class ConvertorFragment : MvpAppCompatFragment(R.layout.fragment_convertor), ConvertorView,
    BackButtonListener {
    private var binding: FragmentConvertorBinding? = null
    private val b get() = binding!!

    private val presenter by moxyPresenter {ConvertorPresenter(App.instance.router)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConvertorBinding.bind(view)
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = ConvertorFragment()
    }
}