package com.arsir.dev.arsir.feature.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arsir.dev.arsir.feature.detail.host.DetailHost
import com.arsir.dev.arsir.uikit.theme.StoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {

    private val productId by lazy { intent.getIntExtra(PRODUCT_ID, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreTheme {
                DetailHost(productId = productId)
            }
        }
    }

    private companion object {
        const val PRODUCT_ID = "product_id"
    }
}