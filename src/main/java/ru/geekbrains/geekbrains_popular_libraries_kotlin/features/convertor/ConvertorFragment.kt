package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.convertor

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.BackButtonListener
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.convertor.IImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.convertor.ImageConvertor
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentConvertorBinding

class ConvertorFragment :
    MvpAppCompatFragment(R.layout.fragment_convertor),
    ConvertorView,
    BackButtonListener {

    companion object {
        fun newInstance() = ConvertorFragment()

        const val IMAGE_PICKER = 1
        const val ON_ERROR = "Convertor Error"
    }

    private var binding: FragmentConvertorBinding? = null
    private val b get() = binding!!

    private val presenter by moxyPresenter {
        ConvertorPresenter(
            AndroidSchedulers.mainThread(),
            App.instance.router,
            ImageConvertor(requireContext())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConvertorBinding.bind(view)

        b.btnConvert.setOnClickListener { presenter.btnConvertClicked() }
        b.btnCancel.setOnClickListener { presenter.btnCancelClicked() }
    }

    override fun onActivityResult(req: Int, result: Int, intent: Intent?) {
        super.onActivityResult(req, result, intent)

        if (req == IMAGE_PICKER && result == RESULT_OK) {
            intent?.data?.also { imageUri ->
                presenter.imageReceived(ImageEnvelope(imageUri))
            }
        }
    }

    override fun openFilePicker() {
        Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            startActivityForResult(this, IMAGE_PICKER)
        }
    }

    override fun showToastMsg(str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }

    override fun initViews() {
        b.apply {
            btnConvert.isVisible = true
            beginTxt.isVisible = true

            errorTxt.isVisible = false
            successTxt.isVisible = false
            loadingGroup.isVisible = false
        }
    }

    override fun showError(error: Throwable) {
        Log.e(ON_ERROR, error.message, error)

        b.apply {
            btnConvert.isVisible = true
            errorTxt.isVisible = true

            successTxt.isVisible = false
            loadingGroup.isVisible = false
            beginTxt.isVisible = false
        }
    }

    override fun showLoading() {
        b.apply {
            loadingGroup.isVisible = true

            btnConvert.isVisible = false
            errorTxt.isVisible = false
            beginTxt.isVisible = false
        }
    }

    override fun showSuccess() {
        b.apply {
            btnConvert.isVisible = true
            successTxt.isVisible = true

            errorTxt.isVisible = false
            loadingGroup.isVisible = false
            beginTxt.isVisible = false
        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    class ImageEnvelope(private val source: Uri) : IImage {
        override fun getPath() = source
    }
}