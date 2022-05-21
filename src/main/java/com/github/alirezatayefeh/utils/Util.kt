package com.github.alirezatayefeh.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.hashids.Hashids

val gson: Gson = GsonBuilder().setPrettyPrinting().create()

inline fun <reified T> Gson.fromJson(json: String): T =
        fromJson(json, object : TypeToken<T>() {}.type)

inline fun <reified T> String.fromJson(): T =
        gson.fromJson(this)

private val hashIds = Hashids("This is unknown bot", 5)

fun randomShortId(id: Int): String =
        hashIds.encode(id.toLong())