package com.example.yurich.keddit.features.news.draghelper

import android.os.Vibrator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.yurich.keddit.features.news.adapter.NewsAdapter
import com.example.yurich.keddit.features.news.adapter.NewsDelegateAdapter

/**
 * Created by yurich on 10.12.16.
 */
class NewsDragHelperCallback(val vibrator: Vibrator) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        return makeMovementFlags(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
                0
        )
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        (recyclerView!!.adapter as NewsAdapter).swapItems(viewHolder!!.adapterPosition, target!!.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        // Do nothing
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder is NewsDelegateAdapter.NewsViewHolder) {
            viewHolder.apply {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    itemView.alpha = 0.5f
                    vibrator.vibrate(10)
                }
            }
        }

        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
        super.clearView(recyclerView, viewHolder)

        viewHolder!!.itemView.alpha = 1f
    }
}