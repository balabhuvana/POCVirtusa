package viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import model.ResponseData
import restservice.VirtusaApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import util.Constant


/**
 * Created by bala on 4/12/18.
 */
class SampleViewModel : ViewModel() {

    private var ResponseDataList: MutableLiveData<ResponseData>? = null

    fun getResponseDataList(): LiveData<ResponseData> {
        if (ResponseDataList == null) {
            ResponseDataList = MutableLiveData()
            loadCountryList()
        }

        return ResponseDataList as MutableLiveData<ResponseData>
    }

    private fun loadCountryList() {


        val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val ResponseDataApi = retrofit.create(VirtusaApi::class.java)
        ResponseDataApi.countryData.enqueue(object : Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>?, t: Throwable?) {
                try {
                    Log.d("---> ", t!!.localizedMessage)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            override fun onResponse(call: Call<ResponseData>?, response: Response<ResponseData>?) {
                ResponseDataList!!.postValue(response!!.body())
            }
        })

    }

}