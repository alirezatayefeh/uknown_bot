package com.github.alirezatayefeh.service

import com.github.alirezatayefeh.config.App
import com.github.alirezatayefeh.utils.randomShortId
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class BotService : TelegramLongPollingBot() {
    // ToDo Alireza: should be multi thread
    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            if (update.message.text == "/start") {
                val message = SendMessage().apply {
                    text = "Hi, what can I do for you? \n" +
                            "If you want create your link select mylink"
                    chatId = update.message.chatId!!.toString()
                }

                val replayKeyboardMarkup = ReplyKeyboardMarkup()
                replayKeyboardMarkup.resizeKeyboard = true
                replayKeyboardMarkup.selective = true
                val keyList = ArrayList<KeyboardRow>()
                val keyboardRow = KeyboardRow()
                val keyboardButton = KeyboardButton("mylink")
                keyboardRow.add(keyboardButton)
                keyList.add(keyboardRow)
                replayKeyboardMarkup.keyboard = keyList
                message.replyMarkup = replayKeyboardMarkup
                try {
                    execute(message)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
            } else if (update.message.text == "/mylink") {
                val generateLink = SendMessage().apply {
                    text = generateUserLink()
                    println("new link: $text")
                    chatId = update.message.chatId.toString()
                }
                try {
                    execute(generateLink)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
            } else if (update.message.text == "/exit") {
                val message1 = SendMessage().apply {
                    text = "bye :))"
                    chatId = update.message.chatId.toString()
                }
                try {
                    execute(message1)
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun generateUserLink(): String{
       return "https://telegram.me/MakhfiBegoBot?start=${randomShortId(8)}"
    }

    override fun getBotUsername(): String {
        return App.config.bot.username
    }

    override fun getBotToken(): String {
        return App.config.bot.token
    }
}

