package `in`.arshad.ui.helper.recyclerview

import `in`.arshad.ui.helper.ViewOnClickListener
import androidx.databinding.ViewDataBinding

open class RVModelBindingAdapter(list: List<BaseBindingRVModel>, private val viewCB: ViewOnClickListener?, private val baseViewHolderFactory: BaseViewHolderBindingFactory) : BaseRVBindingAdapter<BaseBindingRVModel>(list) {

    override fun getLayoutId(position: Int, obj: BaseBindingRVModel): Int {
        return obj.getLayoutId()
    }

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): BaseBindingViewHolder<BaseBindingRVModel> {
        return baseViewHolderFactory.create(binding, viewType, viewCB) as BaseBindingViewHolder<BaseBindingRVModel>
    }
}
