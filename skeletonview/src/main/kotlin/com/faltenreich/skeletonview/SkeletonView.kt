package com.faltenreich.skeletonview

import android.support.annotation.ColorRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView

class SkeletonView(
        private val recyclerView: RecyclerView,
        @LayoutRes layoutResId: Int,
        itemCount: Int = 5,
        @ColorRes maskColorResId: Int = R.color.greyLight,
        cornerRadius: Float = 0f,
        @ColorRes shimmerColor: Int = R.color.grey
) : Skeleton {

    private val originalAdapter = recyclerView.adapter
    private val skeletonAdapter = SkeletonAdapter(layoutResId, itemCount, ContextCompat.getColor(recyclerView.context, maskColorResId), cornerRadius, ContextCompat.getColor(recyclerView.context, shimmerColor))

    var stateChangeListener: SkeletonStateChangeListener? = null

    override fun show() {
        recyclerView.adapter = skeletonAdapter
        stateChangeListener?.onShow()
    }

    override fun hide() {
        recyclerView.adapter = originalAdapter
        stateChangeListener?.onHide()
    }

    override fun isShown() = recyclerView.adapter === skeletonAdapter
}