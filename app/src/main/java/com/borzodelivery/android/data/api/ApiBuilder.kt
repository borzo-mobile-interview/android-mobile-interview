package com.borzodelivery.android.data.api

interface ApiBuilder {

    fun <T> createApi(api: Class<T>): T

}
