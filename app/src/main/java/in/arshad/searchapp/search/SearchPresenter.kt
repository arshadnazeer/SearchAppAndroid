package `in`.arshad.searchapp.search

import `in`.arshad.data.SearchRepositoryImpl
import `in`.arshad.domain.ErrorBaseResponse
import `in`.arshad.domain.PresenterCallBack
import `in`.arshad.domain.search.SearchResponse
import `in`.arshad.network.RetrofitManager
import `in`.arshad.network.search.SearchService
import `in`.arshad.ui.helper.recyclerview.BaseBindingRVModel
import `in`.arshad.ui.utils.onResponse
import android.os.Bundle
import okhttp3.ResponseBody

class SearchPresenter(private val view: SearchContract.View?) : SearchContract.Presenter {

    private val itemsListMain = mutableListOf<BaseBindingRVModel>()

    override fun searchItems(enteredValue: String) {
        view?.showProgress()
        val searchServiceImpl = SearchRepositoryImpl(RetrofitManager.getService(SearchService::class.java))
        searchServiceImpl.getGallery(enteredValue, object : PresenterCallBack<SearchResponse> {
            override fun onSuccess(response: SearchResponse) {
                view?.onResponse {
                    hideProgress()
                    parseData(response)
                }
            }

            override fun onInternetError(response: ErrorBaseResponse) {
                view?.onResponse {
                    hideProgress()
                    view.showMessage(response.msg)
                }
            }

            override fun onOtherError(response: ErrorBaseResponse, errorBody: ResponseBody?) {
                view?.onResponse {
                    hideProgress()
                    view.showMessage(response.msg)
                }
            }
        })
    }

    override fun start(bundle: Bundle?) {

    }

    fun parseData(response: SearchResponse) {
        itemsListMain.clear()
        val itemsList = mutableListOf<ItemsRVModel>()
        response.data?.let {
            if (it.isNotEmpty()) {
                for (item in it) {
                    val homeRVModel = ItemsRVModel(ItemsDataVM(item.title, item.images?.getOrNull(0)?.link, item.images?.getOrNull(0)?.id))
                    itemsList.add(homeRVModel)
                }
            } else {
               view?.showMessage("No data available")
            }
        }
        itemsListMain.addAll(itemsList)
        view?.setItems(itemsListMain)
    }
}
