package `in`.arshad.searchapp.search

import `in`.arshad.searchapp.R
import `in`.arshad.searchapp.BR
import `in`.arshad.ui.helper.ViewOnClickListener
import `in`.arshad.ui.helper.recyclerview.BaseBindingRVModel
import `in`.arshad.ui.helper.recyclerview.BaseBindingViewHolder
import `in`.arshad.ui.helper.recyclerview.BaseViewHolderBindingFactory
import androidx.databinding.ViewDataBinding
import kotlinx.android.synthetic.main.item_products.view.*

data class ItemsDataVM(var title: String? = null, var image: String? = null, var id: String? = null)

class SearchVHFactory : BaseViewHolderBindingFactory() {

    override fun create(binding: ViewDataBinding, viewType: Int, viewClickCallback: ViewOnClickListener?): BaseBindingViewHolder<out BaseBindingRVModel> {
        return when (viewType) {
            R.layout.item_products -> ItemsViewHolder(binding, viewClickCallback)
            else -> BaseBindingViewHolder(binding)
        }
    }
}

class ItemsRVModel(val itemsDataVM: ItemsDataVM) : BaseBindingRVModel {
    override fun getLayoutId() = R.layout.item_products

    override fun getBindingPairs(): List<Pair<Int, Any>> =
            listOf(Pair(BR.bindVariableItemsVm, itemsDataVM))
}

class ItemsViewHolder(binding: ViewDataBinding, val viewClickCallback: ViewOnClickListener?) : BaseBindingViewHolder<ItemsRVModel>(binding), ViewOnClickListener {
    override fun bind(model: ItemsRVModel) {
      itemView.imgItem.setOnClickListener {
          viewClickCallback?.onViewClick(R.id.item_clicked, model.itemsDataVM)
      }
    }

    override fun onViewClick(id: Int, data: Any) {

    }
}