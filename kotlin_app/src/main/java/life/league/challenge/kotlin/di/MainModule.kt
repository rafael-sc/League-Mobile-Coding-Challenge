package life.league.challenge.kotlin.di

import org.koin.core.module.Module
import org.koin.dsl.module

//object to hold MainScreen dependencies
object MainModule {
    private val instance: Module = module {

    }

    //todo use this when load dependency injection modules
    val modules = instance + RetrofitModule.instance
}