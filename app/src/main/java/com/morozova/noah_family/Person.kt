package com.morozova.noah_family

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Person (
    @StringRes val titleResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @DrawableRes val imageResourceId: Int
)