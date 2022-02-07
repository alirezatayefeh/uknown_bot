package com.github.alirezatayefeh.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

val gson: Gson = GsonBuilder().setPrettyPrinting().create()

inline fun <reified T> Gson.fromJson(json: String): T =
        fromJson(json, object : TypeToken<T>() {}.type)

inline fun <reified T> String.fromJson(): T =
        gson.fromJson(this)