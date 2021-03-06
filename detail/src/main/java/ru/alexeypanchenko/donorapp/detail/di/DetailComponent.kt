package ru.alexeypanchenko.donorapp.detail.di
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.alexeypanchenko.donorapp.detail.DetailInRoute
import ru.alexeypanchenko.donorapp.detail.dependencies.DetailItemRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [DetailModule::class], dependencies = [DetailDependencies::class])
interface DetailComponent {
    fun getInRoute(): DetailInRoute
    fun getRepository(): DetailItemRepository
}

interface DetailDependencies {
    fun getRepository(): DetailItemRepository
}

@Module
class DetailModule {

    @Provides
    @Singleton
    fun getInRoute(): DetailInRoute = DetailInRoute()

}
