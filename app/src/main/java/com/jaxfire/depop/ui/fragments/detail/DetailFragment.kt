package com.jaxfire.depop.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jaxfire.depop.R
import com.jaxfire.depop.data.repository.entity.Product.Picture.PictureSize
import com.jaxfire.depop.ui.fragments.detail.adapter.PictureAdapter
import com.jaxfire.depop.ui.fragments.sharedViewModel.ListDetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import kotlin.math.min

class DetailFragment : Fragment() {

    private val viewModel by sharedViewModel<ListDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Could run checks here to determine PictureSize
        // E.g. is DataSaver mode enabled, are they on WI-FI or mobile data etc
        initProductImageViewPager(PictureSize.M)
    }

    private fun initProductImageViewPager(pictureSize: PictureSize) {

        val pictures = viewModel.selectedProduct?.pictures.apply {
            this?.subList(0, min(this.size, 4)) ?: listOf()
        }
        if (!pictures.isNullOrEmpty()) {
            viewPagerDetailFragment.visibility = VISIBLE
            val pagerAdapter =
                PictureAdapter(pictures.map { it.getImageUrl(pictureSize) })
            viewPagerDetailFragment.adapter = pagerAdapter
            worm_dots_indicator.setViewPager2(viewPagerDetailFragment)
        } else {
            viewPagerDetailFragment.visibility = GONE
        }
    }

    override fun onResume() {
        super.onResume()

        textViewDetailFragmentUserId.text = viewModel.selectedProduct?.userId.toString()
        textViewDetailFragmentDescription.text = viewModel.selectedProduct?.description ?: ""
    }
}