package `in`.arshad.ui.helper.recyclerview

import `in`.arshad.ui.helper.ViewOnClickListener
import androidx.databinding.ViewDataBinding

abstract class BaseViewHolderBindingFactory {
    abstract fun create(binding: ViewDataBinding, viewType: Int, viewClickCallback: ViewOnClickListener?): BaseBindingViewHolder<out BaseBindingRVModel>
}