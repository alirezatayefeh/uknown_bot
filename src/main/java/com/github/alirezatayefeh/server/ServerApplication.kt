package com.github.alirezatayefeh.server

import com.github.alirezatayefeh.config.App
import com.github.alirezatayefeh.service.BotService
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class ServerApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App.configureApp()
            TelegramBotsApi(DefaultBotSession::class.java).apply {
                registerBot(BotService())
            }
        }
    }
}

