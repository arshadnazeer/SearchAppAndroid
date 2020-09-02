package `in`.arshad.ui.helper

import `in`.arshad.ui.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Picasso.get().load(imageUrl).fit().placeholder(R.drawable.ic_cached_black).into(view)
    }
}