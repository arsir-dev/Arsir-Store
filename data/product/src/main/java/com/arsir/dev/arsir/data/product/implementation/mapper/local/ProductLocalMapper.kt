package com.arsir.dev.arsir.data.product.implementation.mapper.local

import com.arsir.dev.arsir.core.common.ext.orEmpty
import com.arsir.dev.arsir.data.product.implementation.database.entity.CartEntity
import com.arsir.dev.arsir.data.product.implementation.database.entity.FavoriteEntity
import com.arsir.dev.arsir.data.product.implementation.database.entity.ProductEntity
import com.arsir.dev.arsir.data.product.implementation.database.entity.RatingEntity
import com.arsir.dev.arsir.data.product.implementation.remote.response.ProductResponse
import com.arsir.dev.arsir.data.product.implementation.remote.response.RatingResponse
import com.arsir.dev.arsir.domain.product.model.Cart
import com.arsir.dev.arsir.domain.product.model.Favorite
import com.arsir.dev.arsir.domain.product.model.Rating

internal fun ProductResponse.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id.orEmpty(),
        title = title.orEmpty(),
        price = price ?: 0.0,
        description = description.orEmpty(),
        category = category.orEmpty(),
        image = image.orEmpty(),
        rating = rating.toRatingEntity()
    )
}

internal fun RatingResponse.toRatingEntity(): RatingEntity {
    return RatingEntity(
        rate = rate ?: 0.0,
        count = count.orEmpty(),
    )
}

internal fun Rating.toRatingEntity(): RatingEntity {
    return RatingEntity(
        rate = rate,
        count = count,
    )
}

internal fun RatingEntity.toRating(): Rating {
    return Rating(
        rate = rate,
        count = count,
    )
}

internal fun Favorite.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = id,
        isFavorite = isFavorite
    )
}

internal fun FavoriteEntity.toFavorite(): Favorite {
    return Favorite(
        id = id,
        isFavorite = isFavorite
    )
}

internal fun CartEntity.toCart(): Cart {
    return Cart(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        quantity = quantity,
        isFavorite = isFavorite,
        rating = rating.toRating(),
    )
}

internal fun Cart.toCartEntity(): CartEntity {
    return CartEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        quantity = quantity,
        isFavorite = isFavorite,
        rating = rating.toRatingEntity(),
    )
}