package `in`.arshad.searchapp.search

import `in`.arshad.searchapp.MainActivity
import `in`.arshad.searchapp.R
import `in`.arshad.ui.BaseActivity
import `in`.arshad.ui.helper.ViewOnClickListener
import `in`.arshad.ui.helper.recyclerview.BaseBindingRVModel
import `in`.arshad.ui.helper.recyclerview.RVModelBindingAdapter
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity(), SearchContract.View, ViewOnClickListener {

    private lateinit var presenter: SearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        presenter = SearchPresenter(this)

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (edtSearch.text.toString().isNotEmpty() && edtSearch.text.toString().length >= 3) {
                    presenter.searchItems(edtSearch.text.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onViewClick(id: Int, data: Any) {
        when (id) {
            R.id.item_clicked -> {
                val itemData = data as ItemsDataVM
                SessionData.itemData = itemData
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    override fun setItems(list: List<BaseBindingRVModel>) {
        rvItems.adapter = RVModelBindingAdapter(list, this, SearchVHFactory())
    }
}
