package com.yiyo.brdigedemo.bridgedb

data class AuthCredentials(
    val username: String,
    val password: String,
    val database: String,
    val graph: String
)