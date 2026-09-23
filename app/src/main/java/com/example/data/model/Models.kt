package com.example.data.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge_days")
data class ChallengeDayEntity(
    @PrimaryKey val dayNumber: Int,
    val title: String,
    val subtitle: String,
    val themeColorHex: String,
    val durationMinutes: Int,
    val summary: String,
    val breathingGoalMinutes: Int,
    val meditationTrackTitle: String,
    val meditationTrackSubtitle: String,
    val meditationTrackGenre: String,
    val meditationTrackDuration: String,
    val audioDescription: String,
    val lessonContent: String,
    val journalPrompt1: String,
    val journalPrompt2: String,
    val journalPrompt3: String,
    val affirmation: String,
    val takeaway1: String,
    val takeaway2: String,
    val takeaway3: String,
    val isTask1Done: Boolean = false,
    val isTask2Done: Boolean = false,
    val isTask3Done: Boolean = false,
    val isCompleted: Boolean = false,
    val reflectionNotes: String = ""
)

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: Int = 1,
    val userName: String = "Mindful Seeker",
    val email: String = "seeker@pathtoinnerpeace.in",
    val whatsappNumber: String = "+91 98765 43210",
    val isRegistered: Boolean = false,
    val registeredDate: String = "September 2026",
    val completedDaysCount: Int = 1,
    val streakDays: Int = 3,
    val totalMeditationMinutes: Int = 45,
    val xpPoints: Int = 250,
    val isCertificateClaimed: Boolean = false,
    val certificateIssuedDate: String = "",
    val selectedBatch: String = "6:30 AM"
)

@Entity(tableName = "journal_entries")
data class JournalEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dateString: String,
    val mood: String,
    val stressBefore: Int,
    val stressAfter: Int,
    val reflection: String,
    val gratitude: String,
    val releasedBurden: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

data class TestimonialItem(
    val id: String,
    val name: String,
    val role: String,
    val location: String,
    @DrawableRes val avatarRes: Int,
    val rating: Int = 5,
    val quote: String
)

data class FaqItem(
    val question: String,
    val answer: String
)

data class MembershipPlan(
    val id: String,
    val name: String,
    val tagline: String,
    val priceINR: Int,
    val period: String,
    val badge: String,
    val popular: Boolean = false,
    val features: List<String>,
    val buttonText: String,
    val colorScheme: String,
    val paymentUrl: String = "https://razorpay.me/@pathtoinnerpeace"
)

data class PillarItem(
    val id: Int,
    val number: String,
    val title: String,
    val focus: String,
    val summary: String,
    val practices: List<String>
)

data class StopTechniqueStep(
    val letter: String,
    val title: String,
    val guidance: String,
    val durationSec: Int
)
