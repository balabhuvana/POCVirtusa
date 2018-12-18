package app.virtusa.com.poc_virtusa

/**
 * Created by bala on 18/12/18.
 */

import com.google.gson.Gson
import model.ResponseData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

import java.util.*

class UserTest {

    private lateinit var jsonData: String
    private lateinit var responseData: ResponseData

    @Before
    fun setUp() {
        jsonData = Scanner(javaClass.getResourceAsStream("/json/response.json")).useDelimiter("\\Z").next()!!
        responseData = Gson().fromJson<ResponseData>(jsonData, ResponseData::class.java)!!
    }

    @Test
    fun testResponseDataSize() {
        assertEquals(responseData.rows.size, 14)
    }

    @Test
    fun testResponseDataWhenDetailsContainsTitle() {
        assertEquals(responseData.rows[6].title, "Public Shame")
    }

    @Test
    fun testResponseDataWhenDetailsContainsTitleNull() {
        assertEquals(responseData.rows[7].title, null)
    }

    @Test
    fun testResponseDataWhenDescription() {
        assertEquals(responseData.rows[4].description, "A chiefly Canadian interrogative utterance, usually expressing surprise or doubt or seeking confirmation.")
    }

    @Test
    fun testResponseDataWhenDescriptionIsNull() {
        assertEquals(responseData.rows[1].description, null)
    }
}