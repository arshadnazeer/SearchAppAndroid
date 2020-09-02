package `in`.arshad.network.search

import `in`.arshad.domain.search.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("3/gallery/search/1")
    fun getGallery(@Query("q") value: String): Call<SearchResponse>
}