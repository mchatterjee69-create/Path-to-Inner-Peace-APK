package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge_days")
data class ChallengeDayEntity(
    @PrimaryKey val dayNumber: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val audioDuration: String,
    val microAction: String,
    val tasksJson: String,
    val completedTasksJson: String,
    val reflectionNotes: String
)

@Entity(tableName = "journal_entries")
data class JournalEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long,
    val mood: String,
    val stressBefore: Int,
    val stressAfter: Int,
    val reflection: String,
    val gratitude: String,
    val emotionalBurden: String
)

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: Long = 1,
    val userName: String,
    val email: String,
    val phone: String,
    val batchTime: String,
    val completedDaysCount: Int,
    val totalStreak: Int,
    val isCertificateClaimed: Boolean,
    val certificateIssuedDate: String
)
