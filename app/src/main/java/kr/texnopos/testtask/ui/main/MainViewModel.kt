package kr.texnopos.testtask.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.texnopos.testtask.core.Resource
import kr.texnopos.testtask.data.model.GenericResponse
import kr.texnopos.testtask.data.retrofit.ApiInterface

class MainViewModel(private val api: ApiInterface) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _countries: MutableLiveData<Resource<List<GenericResponse>>> = MutableLiveData()
    val countries: MutableLiveData<Resource<List<GenericResponse>>> get() = _countries

    fun getCountryList() {
        _countries.value = Resource.loading()
        compositeDisposable.add(
            api.getCountryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _countries.value = Resource.success(it)
                    }, {
                    _countries.value = Resource.error(it.message)
                }
                )
        )
    }
}
