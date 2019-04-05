package space.chuumong.loopingviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import space.chuumong.loopingviewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var loopViewPagerAdapter: LoopViewPagerAdapter
    private lateinit var indicator: PageIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        loopViewPagerAdapter = LoopViewPagerAdapter(this)
        binding.viewPager.adapter = loopViewPagerAdapter

        val texts = listOf("TODAY", "IS", "MY", "BIRTH", "DAY")
        loopViewPagerAdapter.addAll(texts)

        indicator =
            PageIndicator(this, binding.layoutPageIndicator, binding.viewPager, R.drawable.indicator_circle)
        indicator.pageCount = texts.size
        indicator.show()
    }

    override fun onDestroy() {
        indicator.cleanup()
        
        super.onDestroy()
    }
}
