package com.onats.core_android

import com.squareup.moshi.Moshi
import okhttp3.ResponseBody

inline fun <reified T> ResponseBody.errorModel(): T? {
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(T::class.java)
    return adapter.fromJson(this.string())
}

