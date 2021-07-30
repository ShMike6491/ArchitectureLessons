package ru.geekbrains.geekbrains_popular_libraries_kotlin.dependencies

import dagger.Component
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.activity.MainActivity
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.activity.MainPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details.DetailsAdapter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details.DetailsFragment
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.details.DetailsPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users.UsersAdapter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users.UsersFragment
import ru.geekbrains.geekbrains_popular_libraries_kotlin.features.users.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NavigationModule::class,
        PersistenceModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(usersPresenter: UsersPresenter)
    fun inject(DetailsPresenter: DetailsPresenter)

    fun inject(UsersAdapter: UsersAdapter)
    fun inject(DetailsAdapter: DetailsAdapter)
}