package kr.texnopos.testtask.data.retrofit

import io.reactivex.rxjava3.core.Observable
import kr.texnopos.testtask.data.model.Detail
import kr.texnopos.testtask.data.model.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("v2/all")
    fun getCountryList(): Observable<List<GenericResponse>>

    @GET("v2/name/{name}")
    fun getInfo(@Path("name")name: String): Observable<List<Detail>>
}
