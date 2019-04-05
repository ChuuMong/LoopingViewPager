package space.chuumong.loopingviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import space.chuumong.loopingviewpager.databinding.FragmentPagerBinding

/**
 * Created by chuumong on 2019-04-05.
 */
class PagerFragment : Fragment() {

    companion object {
        private const val EXTRA_TEXT = "EXTRA_TEXT"

        fun newInstance(text: String): PagerFragment {
            val fragment = PagerFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_TEXT, text)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pager, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = arguments?.getString(EXTRA_TEXT) ?: return
        binding.tvText.text = text
    }
}