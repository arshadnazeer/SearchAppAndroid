package `in`.arshad.ui.extensions

import `in`.arshad.ui.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.squareup.picasso.Picasso

fun View.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this.context, message, duration).show()
}

fun ViewGroup.getBinding(viewType: Int): ViewDataBinding {
    val layoutInflater = LayoutInflater.from(this.context)
    return DataBindingUtil.inflate(
            layoutInflater, viewType, this, false)
}

fun loadURL(image: AppCompatImageView, url: String) {
    if (!url.isNullOrEmpty()){
        Picasso.get().load(url).fit().placeholder(R.drawable.ic_cached_black).into(image)
    }
}