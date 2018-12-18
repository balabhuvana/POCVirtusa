package restservice

import model.ResponseData
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by bala on 25/11/18.
 */

interface VirtusaApi {
    @get:GET("facts.json")
    val countryData: Call<ResponseData>
}
