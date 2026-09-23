package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.ChallengeDayEntity
import com.example.data.model.JournalEntryEntity
import com.example.data.model.UserProgressEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        ChallengeDayEntity::class,
        UserProgressEntity::class,
        JournalEntryEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun challengeDao(): ChallengeDao
    abstract fun userProgressDao(): UserProgressDao
    abstract fun journalDao(): JournalDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "inner_peace_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateInitialData(database)
                    }
                }
            }

            private suspend fun populateInitialData(database: AppDatabase) {
                val challengeDao = database.challengeDao()
                val userProgressDao = database.userProgressDao()
                val journalDao = database.journalDao()

                // Insert User Progress
                userProgressDao.insertOrUpdate(
                    UserProgressEntity(
                        userName = "Mindful Seeker",
                        email = "seeker@pathtoinnerpeace.in",
                        completedDaysCount = 1,
                        streakDays = 3,
                        totalMeditationMinutes = 45,
                        xpPoints = 250,
                        isCertificateClaimed = false,
                        certificateIssuedDate = ""
                    )
                )

                // Insert EXACT 5 Days from www.pathtoinnerpeace.in
                val initialDays = listOf(
                    ChallengeDayEntity(
                        dayNumber = 1,
                        title = "Mental Detox",
                        subtitle = "Negative Thought Awareness & Brain De-Cluttering",
                        themeColorHex = "#0B6B53",
                        durationMinutes = 30,
                        summary = "Identify subconscious negative loops, quiet cognitive chatter, and begin your brain de-cluttering process with gentle breath awareness.",
                        breathingGoalMinutes = 5,
                        meditationTrackTitle = "Mind De-Cluttering & Cognitive Clearance Meditation",
                        meditationTrackSubtitle = "Gentle Rain with Theta Binaural Tones (6Hz)",
                        meditationTrackGenre = "Gentle Rain with Theta Binaural Tones (6Hz)",
                        meditationTrackDuration = "10:00",
                        audioDescription = "Soft continuous rainfall overlayed with 6Hz Theta binaural beat frequency (216Hz/222Hz carrier waves) designed for deep cognitive clearance and subconscious mental detox.",
                        lessonContent = "Welcome to Day 1 of your 5-Day Mind Reset Challenge!\n\nToday we focus on Mental Detox. Most stress stems from unobserved automatic negative thoughts (ANTs). By bringing conscious awareness to these thought patterns without judgment, you loosen their grip on your nervous system.\n\nKey Principle: You are not your thoughts; you are the observer of your thoughts.",
                        journalPrompt1 = "What repeating negative thought or worry occupied your mind today?",
                        journalPrompt2 = "How does your body physically react when you feel stressed or overwhelmed?",
                        journalPrompt3 = "Name 3 things in your immediate environment that bring you comfort.",
                        affirmation = "I am the calm observer of my thoughts. I let go of what no longer serves my mental peace.",
                        takeaway1 = "Recognize Automatic Negative Thoughts (ANTs) early.",
                        takeaway2 = "Use 5-minute conscious breath pauses during high-stress moments.",
                        takeaway3 = "Separate your true identity from temporary mental noise.",
                        isTask1Done = true,
                        isTask2Done = true,
                        isTask3Done = true,
                        isCompleted = true,
                        reflectionNotes = "Noticed how frequently my thoughts jump to work deadlines. Observing them without reacting helped me feel much calmer."
                    ),
                    ChallengeDayEntity(
                        dayNumber = 2,
                        title = "Stress Reset",
                        subtitle = "Nervous System Calming & Cortisol Reduction",
                        themeColorHex = "#134E4A",
                        durationMinutes = 30,
                        summary = "Activate your parasympathetic nervous system using Box Breathing and somatic stress release exercises to lower cortisol levels.",
                        breathingGoalMinutes = 5,
                        meditationTrackTitle = "Vagus Nerve Reset & Deep Stress Relief Meditation",
                        meditationTrackSubtitle = "Deep Ocean Waves with Solfeggio 528Hz",
                        meditationTrackGenre = "Deep Ocean Waves with Solfeggio 528Hz",
                        meditationTrackDuration = "12:00",
                        audioDescription = "Dynamic ocean tidal swells with a 13-second rhythmic swell cycle, combined with 528Hz Solfeggio Miracle frequency and 136.1Hz Om resonance to lower cortisol and activate vagus nerve soothing.",
                        lessonContent = "Day 2 is dedicated to Stress Reset. When stress builds up, your body remains stuck in \"fight or flight\" mode. Through rhythmic 4-4-4-4 breathing and somatic body scanning, we signal your vagus nerve that you are completely safe.\n\nKey Principle: Changing your breathing pattern instantly signals your brain to lower stress hormones.",
                        journalPrompt1 = "What was the primary trigger for your stress over the past week?",
                        journalPrompt2 = "How did your body feel after completing today's 5-minute breathing exercise?",
                        journalPrompt3 = "What is one boundary you can set today to protect your time and energy?",
                        affirmation = "With every breath, my nervous system calms. I choose peace over panic.",
                        takeaway1 = "Master 4-4-4-4 Box Breathing for instant cortisol drop.",
                        takeaway2 = "Identify physical tension points (jaw, shoulders, gut).",
                        takeaway3 = "Anchor a daily 5-minute vagus nerve soothing ritual.",
                        isTask1Done = false,
                        isTask2Done = false,
                        isTask3Done = false,
                        isCompleted = false
                    ),
                    ChallengeDayEntity(
                        dayNumber = 3,
                        title = "Emotional Healing",
                        subtitle = "Forgiveness, Releasing Hurt & Self-Compassion",
                        themeColorHex = "#D4AF37",
                        durationMinutes = 30,
                        summary = "Release old emotional weight, practice radical self-forgiveness, and nurture your heart with loving-kindness practices.",
                        breathingGoalMinutes = 5,
                        meditationTrackTitle = "Heart Center & Emotional Forgiveness Meditation",
                        meditationTrackSubtitle = "Pine Forest Solitude with 639Hz Heart Chakra Harmony",
                        meditationTrackGenre = "Pine Forest Solitude with 639Hz Heart Chakra Harmony & Bamboo Flute",
                        meditationTrackDuration = "12:00",
                        audioDescription = "Whispering pine wind with authentic 639Hz Heart Chakra Solfeggio harmonics and live meditative Pentatonic wood flute melodies to release emotional baggage.",
                        lessonContent = "Day 3 brings Emotional Healing. Unprocessed resentment and guilt act like emotional anchors, holding you back from authentic peace. Forgiveness is not about excusing others—it is about freeing yourself from emotional baggage.\n\nKey Principle: Self-compassion is the foundation of lasting emotional strength.",
                        journalPrompt1 = "Is there a past mistake or person you are still holding resentment toward?",
                        journalPrompt2 = "What words of forgiveness and kindness do you need to hear from yourself today?",
                        journalPrompt3 = "How can you offer yourself more grace when things don't go as planned?",
                        affirmation = "I release all past grievances and open my heart to radical forgiveness and self-love.",
                        takeaway1 = "Understand that forgiveness is self-liberation.",
                        takeaway2 = "Replace harsh self-criticism with supportive self-talk.",
                        takeaway3 = "Nurture your emotional heart with daily gratitude.",
                        isTask1Done = false,
                        isTask2Done = false,
                        isTask3Done = false,
                        isCompleted = false
                    ),
                    ChallengeDayEntity(
                        dayNumber = 4,
                        title = "Confidence Reset",
                        subtitle = "Overcoming Imposter Syndrome & Identity Shift",
                        themeColorHex = "#0B6B53",
                        durationMinutes = 30,
                        summary = "Dismantle self-doubt, reframe limiting beliefs, and step into an empowered self-image grounded in resilience.",
                        breathingGoalMinutes = 5,
                        meditationTrackTitle = "Radiant Self-Confidence & Inner Worth Meditation",
                        meditationTrackSubtitle = "Morning Birdsong with 741Hz Awakening & Alpha Waves",
                        meditationTrackGenre = "Morning Birdsong with 741Hz Awakening Frequency & 10Hz Alpha Waves",
                        meditationTrackDuration = "10:00",
                        audioDescription = "Vibrant spring morning bird calls paired with 741Hz Intuition/Confidence Solfeggio tone and 10Hz Alpha wave pulses for dismantling self-doubt.",
                        lessonContent = "Day 4 focuses on Confidence Reset. Confidence is not the absence of fear; it is the deep trust that you can handle whatever arises. By rewriting your core identity statements, you realign your actions with your highest potential.\n\nKey Principle: Your self-worth is inherent and does not depend on external approval.",
                        journalPrompt1 = "In what area of your life have you been doubting your capabilities?",
                        journalPrompt2 = "What are 3 genuine strengths or victories you have achieved in your life?",
                        journalPrompt3 = "Describe how your most confident, peaceful self walks into a room.",
                        affirmation = "I trust my inner wisdom, honor my worth, and step boldly into my power.",
                        takeaway1 = "Catch and reframe \"I cannot\" thoughts into \"I am learning to\" statements.",
                        takeaway2 = "Practice visualization of your confident future self.",
                        takeaway3 = "Celebrate micro-victories daily.",
                        isTask1Done = false,
                        isTask2Done = false,
                        isTask3Done = false,
                        isCompleted = false
                    ),
                    ChallengeDayEntity(
                        dayNumber = 5,
                        title = "Subconscious Mind Reprogramming",
                        subtitle = "Neural Conditioning, Limiting Belief Reframing & Lifelong Mastery",
                        themeColorHex = "#D4AF37",
                        durationMinutes = 30,
                        summary = "Reprogram deep subconscious belief patterns, anchor lifelong mental clarity, and establish sustainable neural conditioning for peak inner peace.",
                        breathingGoalMinutes = 5,
                        meditationTrackTitle = "Subconscious Reprogramming & Deep Neural Reset Meditation",
                        meditationTrackSubtitle = "Sunrise Meadow with 852Hz Sacred Life Mastery & Harmonic Triad",
                        meditationTrackGenre = "Sunrise Meadow with 852Hz Sacred Life Mastery & Harmonic Chords",
                        meditationTrackDuration = "15:00",
                        audioDescription = "Full dawn meadow soundscape coupled with 852Hz Spiritual Order Solfeggio frequency and A-Major 432Hz Sacred Triad to imprint deep subconscious tranquility.",
                        lessonContent = "Congratulations on reaching Day 5! Today is your Subconscious Mind Reprogramming & Integration Day. You have built momentum, quieted acute stress, and cultivated emotional space. Now we rewire deep subconscious belief patterns and anchor these daily resets into permanent neural pathways.\n\nKey Principle: The subconscious mind learns through repetition, emotion, and alpha-state conditioning. Small daily rituals create permanent inner peace.",
                        journalPrompt1 = "What old subconscious belief or story are you consciously choosing to replace today?",
                        journalPrompt2 = "What daily 10-minute subconscious alignment ritual will you pledge to continue for the next 30 days?",
                        journalPrompt3 = "How will your life, relationships, and decisions change when your subconscious mind is calibrated to inner peace?",
                        affirmation = "My subconscious mind is wired for peace, clarity, and unwavering self-trust.",
                        takeaway1 = "Reprogram subconscious thought triggers with daily neural conditioning.",
                        takeaway2 = "Anchor deep alpha-state visualizations for emotional sovereignty.",
                        takeaway3 = "Establish a non-negotiable 10-minute daily subconscious alignment ritual.",
                        isTask1Done = false,
                        isTask2Done = false,
                        isTask3Done = false,
                        isCompleted = false
                    )
                )

                challengeDao.insertDays(initialDays)

                // Sample reflection
                journalDao.insertJournalEntry(
                    JournalEntryEntity(
                        dateString = "Day 1 Complete • Mental Detox",
                        mood = "Grounded",
                        stressBefore = 7,
                        stressAfter = 2,
                        reflection = "The 5-minute breathing exercise helped me quiet my overactive inner critic.",
                        gratitude = "My quiet morning, clear breath, and new commitment to mental peace.",
                        releasedBurden = "Worrying about things outside my direct control."
                    )
                )
            }
        }
    }
}
