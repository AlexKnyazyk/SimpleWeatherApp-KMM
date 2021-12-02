package com.simple.weather.app.android.utils.view.recycler

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.ref.WeakReference

class FabHiddenScrollListener(
    fab: FloatingActionButton
) : RecyclerView.OnScrollListener() {

    private val fabWeak = WeakReference(fab)

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE
            && fabWeak.get()?.isOrWillBeHidden == true
            && recyclerView.computeVerticalScrollOffset() == 0
        ) {
            fabWeak.get()?.show()
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy != 0 && fabWeak.get()?.isOrWillBeHidden == false) {
            fabWeak.get()?.hide()
        }
    }
}