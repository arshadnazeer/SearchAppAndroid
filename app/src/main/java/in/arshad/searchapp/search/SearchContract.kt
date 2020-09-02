package `in`.arshad.searchapp.search

import `in`.arshad.ui.BasePresenter
import `in`.arshad.ui.BaseView
import `in`.arshad.ui.helper.recyclerview.BaseBindingRVModel

interface SearchContract {

    interface Presenter : BasePresenter {
        fun searchItems(enteredValue: String)
    }

    interface View : BaseView {
        fun setItems(list: List<BaseBindingRVModel>)
    }

}