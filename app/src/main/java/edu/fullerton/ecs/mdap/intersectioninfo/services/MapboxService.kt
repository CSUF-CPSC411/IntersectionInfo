package edu.fullerton.ecs.mdap.intersectioninfo.services

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Class used for connecting to the Mapbox service.
 */
class MapboxService {

    companion object GeoCoding {
        // Base URL of the API
        private const val BASE_URL = "https://api.mapbox.com/"

        // Access token. Replace with your own in future projects.
        const val ACCESS_TOKEN = "pk.eyJ1IjoicGludmVudGFkbyIsImEiOiJja3cwd2wzZjQxNHl0Mm5vYmNzbjdlemc4In0.7JJaksZTGFSR85coY8HNWg"

        // Retrofit object for retrieving data from the internet.
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        // List of API endpoints you want to access
        interface MapboxGeoCodingApiService {
            /**
             * Retrieves a valid address that closely matches the search string.
             *
             * The @GET annotation describes the API endpoint. You can include
             * variables using {} that enclose a variable name.
             *
             * The @PATH annotation assigns the parameter value to the variable
             * on the URL string marked with { }.
             *
             * The @Query annotation adds a query to the end of the URL with the
             * specified name and value from the associated parameter.
             *
             * @param search search string
             * @param token access token
             * @return data from the API
             */
            @GET("geocoding/v5/mapbox.places/{search_string}.json")
            fun getPlaces(@Path(value = "search_string") search: String,
                         @Query("access_token") token: String = ACCESS_TOKEN):
                    Call<String>
        }


        object Api {
            /**
             * by lazy allows us to create an uninitialized constant proprerty.
             * It will be assigned a value returned by the closure when it is
             * accessed the first time thereby saving processing time.
             */
            val retrofitService : MapboxGeoCodingApiService by lazy {
                retrofit.create(MapboxGeoCodingApiService::class.java) }
        }
    }
}