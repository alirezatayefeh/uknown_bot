package com.github.alirezatayefeh.service

import com.github.alirezatayefeh.config.App
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.exceptions.TelegramApiException


class BotService : TelegramLongPollingBot() {

    // ToDo Alireza: should be multi thread
    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {

            if (update.message.text == "/start") {
                val message = SendMessage().apply {
                    text = "سلام چکار میتونم برات انجام بدم؟"
                    chatId = update.message.chatId!!.toString()
                }

                try {
                    execute(message)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
            } else {
                val rowsInline: MutableList<List<InlineKeyboardButton>> = ArrayList()
                val firstRowInline: MutableList<InlineKeyboardButton> = ArrayList()
                val secondRowInline: MutableList<InlineKeyboardButton> = ArrayList()

                firstRowInline.add(InlineKeyboardButton().apply { text = "ok1" })
                firstRowInline.add(InlineKeyboardButton().apply { text = "ok2" })
                secondRowInline.add(InlineKeyboardButton().apply { text = "bye1" })
                secondRowInline.add(InlineKeyboardButton().apply { text = "bye2" })

                rowsInline.add(firstRowInline)
                rowsInline.add(secondRowInline)

                val markupInline = InlineKeyboardMarkup().apply {
                    this.keyboard.addAll(rowsInline)
                }

                try {
                    execute(                SendMessage().apply {
                        replyMarkup = markupInline
                        chatId = update.message.chatId!!.toString()

                    }
                    )
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }

            }
        }
    }

    override fun getBotUsername(): String {
        return App.config.bot.username
    }

    override fun getBotToken(): String {
        return App.config.bot.token
    }
}

