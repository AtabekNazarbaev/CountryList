package kr.texnopos.testtask.di

import com.google.gson.GsonBuilder
import kr.texnopos.testtask.data.retrofit.ApiInterface
import kr.texnopos.testtask.ui.detail.DetailViewModel
import kr.texnopos.testtask.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = "https://restcountries.com"

val dataModule = module {
    single {
        GsonBuilder()
            .setLenient()
            .create()
    }
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(ApiInterface::class.java)
    }
}
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
