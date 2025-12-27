package com.arsir.dev.arsir.feature.detail.navigation

import android.content.Context
import android.content.Intent
import com.arsir.dev.arsir.feature.detail.DetailActivity
import com.arsir.dev.arsir.navigation.DetailNavigation
import javax.inject.Inject

class DetailNavigationImpl @Inject constructor(): DetailNavigation {
    override fun goToDetail(context: Context, productId: Int): Intent {
        return Intent(context, DetailActivity::class.java).apply {
            putExtra(PRODUCT_ID, productId)
        }
    }

    private companion object {
        const val PRODUCT_ID = "product_id"
    }
}