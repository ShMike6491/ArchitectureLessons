package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.convertor

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.geekbrains.geekbrains_popular_libraries_kotlin.convertor.IConvertor
import ru.geekbrains.geekbrains_popular_libraries_kotlin.convertor.IImage

const val ON_CANCEL_MSG = "Canceled"

class ConvertorPresenter(
    private val mainThread: Scheduler,
    private val router: Router,
    private val convertor: IConvertor
) : MvpPresenter<ConvertorView>() {
    private var jobInProgress: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initViews()
    }

    fun btnConvertClicked() {
        viewState.showLoading()
        viewState.openFilePicker()
    }

    fun btnCancelClicked() {
        jobInProgress?.let { disposable ->
            disposable.dispose()
            viewState.showToastMsg(ON_CANCEL_MSG)
            viewState.initViews()
        }

        jobInProgress = null
    }

    fun imageReceived(image: IImage) {
        jobInProgress = convertor.convert(image)
            .observeOn(mainThread)
            .subscribe({
                viewState.showSuccess()
            }, { error ->
                viewState.showError(error)
            })
    }

    fun backPressed(): Boolean {
        if (jobInProgress != null) {
            btnCancelClicked()
        } else {
            router.exit()
        }
        return true
    }
}

@AddToEndSingle
interface ConvertorView : MvpView {
    fun openFilePicker()

    fun showToastMsg(str: String)

    fun initViews()

    fun showError(error: Throwable)

    fun showLoading()

    fun showSuccess()
}