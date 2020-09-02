package `in`.arshad.ui.helper.recyclerview

import `in`.arshad.ui.extensions.getBinding
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRVBindingAdapter<T : BaseBindingRVModel>(var listItems: List<T>) : RecyclerView.Adapter<BaseBindingViewHolder<T>>() {

    fun setItems(listItems: List<T>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder<T> {
        return getViewHolder(parent.getBinding(viewType), viewType)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<T>, position: Int) {
        holder.bind(listItems[position])
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<T>, position: Int, payloads: MutableList<Any>) {
        holder.onBindWithPayload(listItems[position], payloads)
    }

    override fun onViewRecycled(holder: BaseBindingViewHolder<T>) {
        super.onViewRecycled(holder)
        holder.onViewRecycled()
    }

    override fun getItemViewType(position: Int): Int = getLayoutId(position, listItems[position])

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(binding: ViewDataBinding, viewType: Int): BaseBindingViewHolder<T>

    override fun getItemCount(): Int = listItems.size
}