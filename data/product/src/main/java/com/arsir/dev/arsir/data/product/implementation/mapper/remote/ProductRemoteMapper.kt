package com.arsir.dev.arsir.data.product.implementation.mapper.remote

import com.arsir.dev.arsir.core.common.ext.orEmpty
import com.arsir.dev.arsir.data.product.implementation.remote.response.ProductResponse
import com.arsir.dev.arsir.data.product.implementation.remote.response.RatingResponse
import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.domain.product.model.Rating

internal fun ProductResponse.toProduct(): Product {
    return Product(
        id = id.orEmpty(),
        title = title.orEmpty(),
        price = price ?: 0.0,
        description = description.orEmpty(),
        category = category.orEmpty(),
        image = image.orEmpty(),
        rating = rating.toRating()
    )
}

internal fun RatingResponse.toRating(): Rating {
    return Rating(
        rate = rate ?: 0.0,
        count = count.orEmpty(),
    )
}