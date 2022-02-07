package com.github.alirezatayefeh.config

import com.github.alirezatayefeh.utils.fromJson
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.typesafe.config.ConfigRenderOptions

object App {
    lateinit var config: AppConfig

    fun configureApp() {
        config = parseConfig(ConfigFactory.load())
    }

    private fun parseConfig(config: Config): AppConfig = parseAndCheckConfig(config)

    private inline fun <reified T> parseAndCheckConfig(loadedConfig: Config): T {
        check(!loadedConfig.isEmpty) { "Config should not be empty!" }
        val safeConfigJson = loadedConfig.resolve()
                .root()
                .render(ConfigRenderOptions.concise())
        return safeConfigJson.fromJson()
    }
}

