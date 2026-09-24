package com.example.data.model

data class ChallengeDay(
    val dayNumber: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val audioDuration: String,
    val microAction: String,
    val tasks: List<String>,
    val completedTasks: List<Boolean>,
    val reflectionNotes: String = ""
)

data class JournalEntry(
    val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val mood: String,
    val stressBefore: Int,
    val stressAfter: Int,
    val reflection: String,
    val gratitude: String,
    val emotionalBurden: String
)

data class UserProgress(
    val id: Long = 1,
    val userName: String = "Mindful Seeker",
    val email: String = "",
    val phone: String = "",
    val batchTime: String = "6:30 AM",
    val completedDaysCount: Int = 0,
    val totalStreak: Int = 0,
    val isCertificateClaimed: Boolean = false,
    val certificateIssuedDate: String = ""
)

data class MembershipPlan(
    val id: String,
    val name: String,
    val price: String,
    val originalPrice: String,
    val period: String,
    val badge: String? = null,
    val description: String,
    val features: List<String>,
    val isPopular: Boolean = false
)

data class Pillar(
    val title: String,
    val subtitle: String,
    val description: String,
    val iconName: String
)

data class Testimonial(
    val name: String,
    val role: String,
    val quote: String,
    val result: String,
    val rating: Int = 5
)

data class FaqItem(
    val question: String,
    val answer: String
)

data class StopStep(
    val letter: String,
    val title: String,
    val instruction: String,
    val durationText: String
)
