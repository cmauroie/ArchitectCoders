package com.developerideas.data.source

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}