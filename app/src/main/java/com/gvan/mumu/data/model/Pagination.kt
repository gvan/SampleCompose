package com.gvan.mumu.data.model

data class Pagination(
    val page: Int,
    val pageSize: Int,
    val pageCount: Int,
    val total: Int
)
