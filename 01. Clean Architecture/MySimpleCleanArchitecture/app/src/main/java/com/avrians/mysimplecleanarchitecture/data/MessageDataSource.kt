package com.avrians.mysimplecleanarchitecture.data

import com.avrians.mysimplecleanarchitecture.domain.MessageEntity

class MessageDataSource : IMessageDataSource{
    override fun getMessageFromSource(name: String) = MessageEntity("Hello $name! Welcome to Clean Architecture")
}