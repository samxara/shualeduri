package com.example.shualeduri


import com.example.shualeduri.RetrofitClient
import com.example.shualeduri.RetrofitService

object Common {
    private val BASE_URL = "https://simplifiedcoding.net/demos/"

    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}