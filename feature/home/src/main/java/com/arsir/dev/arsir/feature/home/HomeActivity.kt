package com.arsir.dev.arsir.feature.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arsir.dev.arsir.feature.home.host.HomeHost
import com.arsir.dev.arsir.navigation.DetailNavigation
import com.arsir.dev.arsir.uikit.theme.StoreTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity: ComponentActivity() {

    @Inject
    lateinit var detailNavigation: DetailNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreTheme {
                HomeHost()
            }
        }
    }

    internal fun navigateToDetail(productId: Int): Intent {
        return detailNavigation.goToDetail(
            context = this,
            productId = productId
        )
    }
}