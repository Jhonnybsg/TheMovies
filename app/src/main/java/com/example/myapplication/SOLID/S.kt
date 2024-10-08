package com.example.myapplication.SOLID

import com.example.myapplication.SOLID.S.EmailService
import com.example.myapplication.SOLID.S.User
import com.example.myapplication.SOLID.S.UserRegistration
import com.example.myapplication.SOLID.S.UserRepository

class S {
    class User(val name: String, val email: String)

    class UserRepository {
        fun saveUser(user: User) {
            // Save user to database
            println("User saved to database")
        }
    }

    class EmailService {
        fun sendWelcomeEmail(user: User) {
            // Logic to send a welcome email
            println("Welcome email sent to ${user.email}")
        }
    }

    class UserRegistration(
        private val userRepository: UserRepository,
        private val emailService: EmailService
    ) {
        fun register(user: User) {
            userRepository.saveUser(user)
            emailService.sendWelcomeEmail(user)
        }
    }
}

fun main() {
    val userRepository = UserRepository()
    val emailService = EmailService()
    val userRegistration = UserRegistration(userRepository, emailService)

    userRegistration.register(User("John", "john@example.com"))
}