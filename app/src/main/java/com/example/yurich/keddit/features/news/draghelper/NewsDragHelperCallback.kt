package com.example.yurich.keddit.features.news.draghelper

import android.os.Vibrator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.yurich.keddit.features.news.adapter.NewsAdapter
import com.example.yurich.keddit.features.news.adapter.NewsDelegateAdapter
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDragHelperCallback(val vibrator: Vibrator) : ItemTouchHelper.Callback() {

    val DRAG_ALPHA = 0.7f
    val DRAG_LIFT = 4

    val  VIBRATION_DURATION = 10L

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
            viewHolder.itemView.apply {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    alpha = DRAG_ALPHA
                    news_card.cardElevation += DRAG_LIFT
                    vibrator.vibrate(VIBRATION_DURATION)
                }
            }
        }

        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
        super.clearView(recyclerView, viewHolder)
        viewHolder!!.itemView.apply {
            alpha = 1f
            news_card.cardElevation -= DRAG_LIFT
        }
    }
}