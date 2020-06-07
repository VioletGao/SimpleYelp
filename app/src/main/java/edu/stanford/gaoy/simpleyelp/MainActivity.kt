package edu.stanford.gaoy.simpleyelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"
private const val BASE_URL = "https://api.yelp.com/v3/"
private const val API_KEY = "5NN68SXz8nqJmWqVZBNw_euh38dugqsv33k1RoKOBnjcSfsyW46yJv5n69AGMJbAZC0HN3iWoC4stoB05qwtI9rS9ywg6SodDQe2rRWEEsB8j_5wWbtjOJGi8q_cXnYx"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val yelpService = retrofit.create(YelpService::class.java)
        yelpService.searchRestaurants("Bearer $API_KEY","Avocado Toast", "New York").enqueue(object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.i(TAG, "onResponse $response")
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
            }
        })


    }
}
