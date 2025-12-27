package com.arsir.dev.arsir.navigation

import android.content.Context
import android.content.Intent

fun interface DetailNavigation {
    fun goToDetail(context: Context, productId: Int): Intent
}