package com.avrians.mysimplecleanarchitecture.data

import com.avrians.mysimplecleanarchitecture.domain.IMessageRepository
import com.avrians.mysimplecleanarchitecture.domain.MessageEntity

class MessageRepository(private val messageDataSource: IMessageDataSource) : IMessageRepository {
    override fun getWelcomeMessage(name: String): MessageEntity {
        return messageDataSource.getMessageFromSource(name)
    }
}