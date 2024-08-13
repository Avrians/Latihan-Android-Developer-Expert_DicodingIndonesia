package com.dicoding.mysimplelogin

import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

class MainActivityTest : KoinTest {

    private val userRepository: UserRepository by inject()
    private val username = "dicoding"

    @Before
    fun before(){
        loadKoinModules(StorageModule)
        userRepository.loginUser(username)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getUsernameFromRepository() {
        val storedUsername = userRepository.getUser()
        assertEquals(username, storedUsername)
    }
}