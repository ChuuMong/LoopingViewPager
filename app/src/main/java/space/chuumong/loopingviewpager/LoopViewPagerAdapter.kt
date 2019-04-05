package space.chuumong.loopingviewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by chuumong on 2019-04-05.
 */
class LoopViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val texts = mutableListOf<String>()

    override fun getItem(position: Int): Fragment {
        return PagerFragment.newInstance(texts[position % texts.size])
    }

    fun addAll(items: List<String>) {
        texts.clear()
        texts.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}