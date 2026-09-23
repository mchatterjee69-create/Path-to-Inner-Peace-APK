package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.ChallengeDayEntity
import com.example.data.model.JournalEntryEntity
import com.example.data.model.UserProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeDao {
    @Query("SELECT * FROM challenge_days ORDER BY dayNumber ASC")
    fun getAllChallengeDays(): Flow<List<ChallengeDayEntity>>

    @Query("SELECT * FROM challenge_days WHERE dayNumber = :dayNumber")
    fun getDayByNumber(dayNumber: Int): Flow<ChallengeDayEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDays(days: List<ChallengeDayEntity>)

    @Update
    suspend fun updateDay(day: ChallengeDayEntity)

    @Query("UPDATE challenge_days SET isCompleted = :completed WHERE dayNumber = :dayNumber")
    suspend fun setDayCompleted(dayNumber: Int, completed: Boolean)

    @Query("UPDATE challenge_days SET reflectionNotes = :notes WHERE dayNumber = :dayNumber")
    suspend fun updateDayNotes(dayNumber: Int, notes: String)
}

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal_entries ORDER BY timestamp DESC")
    fun getAllJournalEntries(): Flow<List<JournalEntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournalEntry(entry: JournalEntryEntity): Long

    @Query("DELETE FROM journal_entries WHERE id = :id")
    suspend fun deleteJournalEntry(id: Long)
}

@Dao
interface UserProgressDao {
    @Query("SELECT * FROM user_progress WHERE id = 1")
    fun getUserProgress(): Flow<UserProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(progress: UserProgressEntity)

    @Query("UPDATE user_progress SET userName = :name WHERE id = 1")
    suspend fun updateUserName(name: String)

    @Query("UPDATE user_progress SET userName = :name, email = :email, whatsappNumber = :whatsapp, selectedBatch = :batch, isRegistered = 1 WHERE id = 1")
    suspend fun registerUser(name: String, email: String, whatsapp: String, batch: String)

    @Query("UPDATE user_progress SET isCertificateClaimed = 1, certificateIssuedDate = :date WHERE id = 1")
    suspend fun claimCertificate(date: String)

    @Query("UPDATE user_progress SET streakDays = streakDays + 1 WHERE id = 1")
    suspend fun incrementStreak()
}
