package com.avrians.mysimplecleanarchitecture.data

import com.avrians.mysimplecleanarchitecture.domain.MessageEntity

interface IMessageDataSource {
    fun getMessageFromSource(name: String): MessageEntity
}