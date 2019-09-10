package com.stfalcon.sample.features.demo.grid

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.stfalcon.imageviewer.StfalconImageViewer
import com.stfalcon.sample.R
import com.stfalcon.sample.common.extensions.getDrawableCompat
import com.stfalcon.sample.common.extensions.loadImage
import com.stfalcon.sample.common.models.Demo
import com.stfalcon.sample.common.models.Poster
import kotlinx.android.synthetic.main.activity_demo_posters_grid.*

class PostersGridDemoActivity : AppCompatActivity() {

    private val list = listOf(
            ImageWithVideo("${Demo.POSTERS_PATH}/Vincent.jpg", "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8"),
            ImageWithVideo("${Demo.POSTERS_PATH}/Jules.jpg", null),
            ImageWithVideo("${Demo.POSTERS_PATH}/Korben.jpg", null),
            ImageWithVideo("${Demo.POSTERS_PATH}/Toretto.jpg", null),
            ImageWithVideo("${Demo.POSTERS_PATH}/Marty.jpg", null),
            ImageWithVideo("${Demo.POSTERS_PATH}/Driver.jpg", null),
            ImageWithVideo("${Demo.POSTERS_PATH}/Frank.jpg", null),
            ImageWithVideo("${Demo.POSTERS_PATH}/Max.jpg", null),
            ImageWithVideo("${Demo.POSTERS_PATH}/Daniel.jpg", null))

    private lateinit var viewer: StfalconImageViewer<ImageWithVideo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_posters_grid)

        postersGridView.apply {
            imageLoader = ::loadPosterImage
            onPosterClick = ::openViewer
        }
    }

    private fun openViewer(startPosition: Int, target: ImageView) {
        viewer = StfalconImageViewer.Builder<ImageWithVideo>(this, list, this::loadImage, ::TestViewHolder)
                .withStartPosition(startPosition)
            .withTransitionFrom(target)
            .withImageChangeListener {
                viewer.updateTransitionImage(postersGridView.imageViews[it])
            }
            .show()
    }

    private fun loadPosterImage(imageView: ImageView, poster: Poster?) {
        imageView.apply {
            background = getDrawableCompat(R.drawable.shape_placeholder)
            loadImage(poster?.url)
        }
    }

    private fun loadImage(imageView: ImageView, poster: ImageWithVideo?) {
        imageView.apply {
            background = getDrawableCompat(R.drawable.shape_placeholder)
            loadImage(poster?.imageUrl)
        }
    }
}