package `in`.arshad.domain.search

import `in`.arshad.domain.PresenterCallBack

interface SearchRepository {
    fun getGallery(value: String, presenterCallBack: PresenterCallBack<SearchResponse>)
}