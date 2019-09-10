package com.stfalcon.sample.features.demo.grid

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import com.github.chrisbanes.photoview.PhotoView
import com.stfalcon.imageviewer.viewer.viewholder.DefaultViewHolder
import com.stfalcon.sample.R
import com.stfalcon.sample.video.EasyVideoCallback
import com.stfalcon.sample.video.EasyVideoPlayer
import kotlinx.android.synthetic.main.item_test.view.*
import java.lang.Exception

class TestViewHolder(photoView: PhotoView) : DefaultViewHolder<ImageWithVideo>(initView(photoView)){

    protected val photoView = photoView

    private var data: ImageWithVideo? = null

    override fun bind(position: Int, image: ImageWithVideo) {
        super.bind(position, image)
        data = image

        itemView.video.setCallback(object : EasyVideoCallback {
            override fun onPrepared(player: EasyVideoPlayer?) {
                photoView.visibility = View.GONE
            }

            override fun onRetry(player: EasyVideoPlayer?, source: Uri?) {
            }

            override fun onStarted(player: EasyVideoPlayer?) {
            }

            override fun onCompletion(player: EasyVideoPlayer?) {
            }

            override fun onSubmit(player: EasyVideoPlayer?, source: Uri?) {
            }

            override fun onBuffering(percent: Int) {
            }

            override fun onPreparing(player: EasyVideoPlayer?) {
            }

            override fun onError(player: EasyVideoPlayer?, e: Exception?) {
            }

            override fun onPaused(player: EasyVideoPlayer?) {
            }
        })
    }

    override fun setIsVisible(isVisible: Boolean) {
        if (isVisible) {
            if(itemView.video.isPrepared) {
                    itemView.video.start()
            } else {
                photoView.visibility = View.VISIBLE
                itemView.video.initializePlayer()
                data?.videoUrl?.let { itemView.video.setSource(Uri.parse(it)) }
            }
        } else {
            if (itemView.video.isPlaying) {
                itemView.video.pause()
            }
        }
    }
}

private fun initView(photoView: PhotoView): View =
    LayoutInflater.from(photoView.context).inflate(R.layout.item_test, null).apply {
        val layoutParams = stub.layoutParams
        root.removeView(stub)
        photoView.layoutParams = layoutParams
        root.addView(photoView)
    }
