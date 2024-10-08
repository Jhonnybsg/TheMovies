package com.example.myapplication.SOLID

import com.example.myapplication.SOLID.O.EmailNotificationService
import com.example.myapplication.SOLID.O.SmsNotificationService
import com.example.myapplication.SOLID.O.UserRegistrationOCP
import com.example.myapplication.SOLID.S.User
import com.example.myapplication.SOLID.S.UserRepository

class O {
    interface NotificationService {
        fun sendNotification(user: S.User)
    }

    class EmailNotificationService : NotificationService {
        override fun sendNotification(user: User) {
            println("Email sent to ${user.email}")
        }
    }

    class SmsNotificationService : NotificationService {
        override fun sendNotification(user: User) {
            println("SMS sent to ${user.name}")
        }
    }

    class UserRegistrationOCP(
        private val userRepository: UserRepository,
        private val notificationService: NotificationService
    ) {
        fun register(user: User) {
            userRepository.saveUser(user)
            notificationService.sendNotification(user)
        }
    }
}

fun main() {
    val emailService = EmailNotificationService()
    val smsService = SmsNotificationService()
    val userRepository = UserRepository()

    // Pass EmailNotificationService or SmsNotificationService
    val userRegistration = UserRegistrationOCP(userRepository, emailService)
    userRegistration.register(User("Alice", "alice@example.com"))

    val userRegistrationSms = UserRegistrationOCP(userRepository, smsService)
    userRegistrationSms.register(User("Bob", "bob@example.com"))
}