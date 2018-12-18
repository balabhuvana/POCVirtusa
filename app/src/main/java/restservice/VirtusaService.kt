package restservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import util.Constant

/**
 * Created by bala on 25/11/18.
 */
class VirtusaService {
    private var retrofit: Retrofit? = null

    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    val api: VirtusaApi
        get() {


            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(Constant.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }

            return retrofit!!.create(VirtusaApi::class.java)
        }
}