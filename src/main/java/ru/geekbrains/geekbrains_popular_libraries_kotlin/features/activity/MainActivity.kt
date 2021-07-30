package ru.geekbrains.geekbrains_popular_libraries_kotlin.features.activity

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.BackButtonListener
import ru.geekbrains.geekbrains_popular_libraries_kotlin.R
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    @Inject lateinit var navHolder: NavigatorHolder
    private val navigator = AppNavigator(this, R.id.container)
    private var _vb: ActivityMainBinding? = null
    private val vb get() = _vb!!
    private val presenter by moxyPresenter {
        MainPresenter().apply{
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}