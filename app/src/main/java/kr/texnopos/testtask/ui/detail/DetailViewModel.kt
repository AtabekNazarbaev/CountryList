package kr.texnopos.testtask.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.texnopos.testtask.core.Resource
import kr.texnopos.testtask.data.model.Detail
import kr.texnopos.testtask.data.model.GenericResponse
import kr.texnopos.testtask.data.retrofit.ApiInterface

class DetailViewModel(private val api: ApiInterface) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _details: MutableLiveData<Resource<List<Detail>>> = MutableLiveData()
    val details: MutableLiveData<Resource<List<Detail>>> get() = _details

    fun getInfo(id: String) {
        _details.value = Resource.loading()
        compositeDisposable.add(
            api.getInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _details.value = Resource.success(it)
                    }, {
                    _details.value = Resource.error(it.message)
                }
                )
        )
    }
}
