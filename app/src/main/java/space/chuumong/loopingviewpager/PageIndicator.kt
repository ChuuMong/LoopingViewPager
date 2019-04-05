package space.chuumong.loopingviewpager

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.view.children
import androidx.viewpager2.widget.ViewPager2

/**
 * Created by chuumong on 2019-04-05.
 */
class PageIndicator(
    private val context: Context,
    private val container: LinearLayout,
    private val viewPager: ViewPager2,
    @DrawableRes private val drawableRes: Int
) : ViewPager2.OnPageChangeCallback() {

    companion object {
        private const val DEFAULT_SIZE_IN_DP = 12
        private const val DEFAULT_SPACING_IN_DP = 12
    }

    var initialPage = 0
    var size = 0
    var spacing = 0
    var pageCount = 0

    private fun initIndicators() {
        if (pageCount <= 0) {
            return
        }

        viewPager.registerOnPageChangeCallback(this)

        val res = context.resources
        container.removeAllViews()

        for (i in 0 until pageCount) {
            val view = View(context)
            val dimen =
                if (size != 0) {
                    res.getDimensionPixelSize(size)
                } else {
                    res.displayMetrics.density.toInt() * DEFAULT_SIZE_IN_DP
                }

            val margin =
                if (spacing != 0) {
                    res.getDimensionPixelSize(spacing)
                } else {
                    res.displayMetrics.density.toInt() * DEFAULT_SPACING_IN_DP
                }

            val params = LinearLayout.LayoutParams(dimen, dimen)
            params.setMargins(if (i == 0) 0 else margin, 0, 0, 0)
            view.layoutParams = params
            view.setBackgroundResource(drawableRes)
            view.isSelected = i == 0
            container.addView(view)
        }
    }

    fun show() {
        initIndicators()
        setIndicatorAsSelected(initialPage)
    }

    private fun setIndicatorAsSelected(index: Int) {
        for (i in 0 until container.childCount) {
            val view = container.getChildAt(i)
            view.isSelected = i == index
        }
    }

    fun cleanup() {
        viewPager.unregisterOnPageChangeCallback(this)
    }

    override fun onPageSelected(position: Int) {
        val index = position % pageCount
        setIndicatorAsSelected(index)
    }
}