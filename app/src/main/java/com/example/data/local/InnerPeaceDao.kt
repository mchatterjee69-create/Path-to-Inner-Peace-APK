package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InnerPeaceDao {

    @Query("SELECT * FROM challenge_days ORDER BY dayNumber ASC")
    fun getAllChallengeDays(): Flow<List<ChallengeDayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllChallengeDays(days: List<ChallengeDayEntity>)

    @Update
    suspend fun updateChallengeDay(day: ChallengeDayEntity)

    @Query("SELECT * FROM challenge_days WHERE dayNumber = :dayNumber")
    suspend fun getChallengeDay(dayNumber: Int): ChallengeDayEntity?

    @Query("SELECT * FROM journal_entries ORDER BY timestamp DESC")
    fun getAllJournalEntries(): Flow<List<JournalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournalEntry(entry: JournalEntity)

    @Query("SELECT * FROM user_progress WHERE id = 1")
    fun getUserProgress(): Flow<UserProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProgress(progress: UserProgressEntity)

    @Update
    suspend fun updateUserProgress(progress: UserProgressEntity)
}
