package edu.fullerton.ecs.mdap.intersectioninfo

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Adds a custom attribute called imageUrl for the ImageView.
 *
 * @param imgView ImageView object on the layout
 * @param imgUrl URL of the image
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    // Makes sure imgUrl is not null
    imgUrl?.let {
        // Use Glide to create an Image from the given URL and store into
        // the ImageView on the layout
        val imgUri =
            imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}
