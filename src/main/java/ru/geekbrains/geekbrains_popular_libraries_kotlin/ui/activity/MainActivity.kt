package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ActivityMainBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.MainPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private val presenter by lazy { MainPresenter(this) }
    private var _vb: ActivityMainBinding? = null
    private val vb get() = _vb!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.btnCounter1.setOnClickListener { presenter.btnOneClick() }
        vb.btnCounter2.setOnClickListener { presenter.btnTwoClick() }
        vb.btnCounter3.setOnClickListener { presenter.btnThreeClick() }
    }

    override fun setButtonOneTxt(text: String) { vb.btnCounter1.text = text}

    override fun setButtonTwoTxt(text: String) { vb.btnCounter2.text = text }

    override fun setButtonThreeTxt(text: String) { vb.btnCounter3.text = text }

}