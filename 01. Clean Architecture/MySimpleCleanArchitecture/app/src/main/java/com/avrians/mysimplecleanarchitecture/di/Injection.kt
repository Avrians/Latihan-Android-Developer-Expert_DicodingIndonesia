package com.avrians.mysimplecleanarchitecture.di

import com.avrians.mysimplecleanarchitecture.data.IMessageDataSource
import com.avrians.mysimplecleanarchitecture.data.MessageDataSource
import com.avrians.mysimplecleanarchitecture.data.MessageRepository
import com.avrians.mysimplecleanarchitecture.domain.IMessageRepository
import com.avrians.mysimplecleanarchitecture.domain.MessageInteractor
import com.avrians.mysimplecleanarchitecture.domain.MessageUseCase

object Injection {
    fun provideUseCase(): MessageUseCase {
        val messageRepository = provideRepository()
        return MessageInteractor(messageRepository)
    }

    private fun provideRepository(): IMessageRepository {
        val messageDataSource = provideDataSource()
        return MessageRepository(messageDataSource)
    }

    private fun provideDataSource(): IMessageDataSource {
        return MessageDataSource()
    }
}