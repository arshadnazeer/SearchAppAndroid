package `in`.arshad.data

import `in`.arshad.domain.PresenterCallBack
import `in`.arshad.domain.search.SearchRepository
import `in`.arshad.domain.search.SearchResponse
import `in`.arshad.network.retrofit.RetrofitCallBack
import `in`.arshad.network.search.SearchService

class SearchRepositoryImpl (private val searchService: SearchService) : SearchRepository{
    override fun getGallery(value: String, presenterCallBack: PresenterCallBack<SearchResponse>) {
        val call = searchService.getGallery(value)
        call.enqueue(RetrofitCallBack(presenterCallBack))
    }
}
