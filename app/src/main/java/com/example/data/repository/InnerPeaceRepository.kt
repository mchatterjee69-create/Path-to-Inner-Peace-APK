package com.example.data.repository

import com.example.data.local.AppDatabase
import com.example.data.local.ChallengeDayEntity
import com.example.data.local.JournalEntity
import com.example.data.local.UserProgressEntity
import com.example.data.model.ChallengeDay
import com.example.data.model.JournalEntry
import com.example.data.model.UserProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InnerPeaceRepository(private val database: AppDatabase) {

    private val dao = database.innerPeaceDao()

    val challengeDays: Flow<List<ChallengeDay>> = dao.getAllChallengeDays().map { list ->
        if (list.isEmpty()) {
            val initial = getInitialDays()
            dao.insertAllChallengeDays(initial.map { it.toEntity() })
            initial
        } else {
            list.map { it.toModel() }
        }
    }

    val journalEntries: Flow<List<JournalEntry>> = dao.getAllJournalEntries().map { list ->
        list.map { it.toModel() }
    }

    val userProgress: Flow<UserProgress> = dao.getUserProgress().map { entity ->
        if (entity == null) {
            val defaultProgress = UserProgress()
            dao.insertUserProgress(defaultProgress.toEntity())
            defaultProgress
        } else {
            entity.toModel()
        }
    }

    suspend fun updateTaskCompletion(dayNumber: Int, taskIndex: Int, isChecked: Boolean) {
        val entity = dao.getChallengeDay(dayNumber) ?: return
        val completedList = parseBooleans(entity.completedTasksJson).toMutableList()
        while (completedList.size <= taskIndex) {
            completedList.add(false)
        }
        completedList[taskIndex] = isChecked

        val updatedEntity = entity.copy(
            completedTasksJson = completedList.joinToString(",") { it.toString() }
        )
        dao.updateChallengeDay(updatedEntity)
    }

    suspend fun updateReflection(dayNumber: Int, notes: String) {
        val entity = dao.getChallengeDay(dayNumber) ?: return
        val updated = entity.copy(reflectionNotes = notes)
        dao.updateChallengeDay(updated)
    }

    suspend fun addJournalEntry(
        mood: String,
        stressBefore: Int,
        stressAfter: Int,
        reflection: String,
        gratitude: String,
        emotionalBurden: String
    ) {
        val entry = JournalEntity(
            timestamp = System.currentTimeMillis(),
            mood = mood,
            stressBefore = stressBefore,
            stressAfter = stressAfter,
            reflection = reflection,
            gratitude = gratitude,
            emotionalBurden = emotionalBurden
        )
        dao.insertJournalEntry(entry)
    }

    suspend fun registerUser(name: String, email: String, phone: String, batch: String) {
        val current = UserProgressEntity(
            id = 1,
            userName = name,
            email = email,
            phone = phone,
            batchTime = batch,
            completedDaysCount = 1,
            totalStreak = 1,
            isCertificateClaimed = false,
            certificateIssuedDate = ""
        )
        dao.insertUserProgress(current)
    }

    suspend fun claimCertificate(name: String, date: String) {
        val entity = UserProgressEntity(
            id = 1,
            userName = name,
            email = "",
            phone = "",
            batchTime = "6:30 AM",
            completedDaysCount = 5,
            totalStreak = 5,
            isCertificateClaimed = true,
            certificateIssuedDate = date
        )
        dao.insertUserProgress(entity)
    }

    private fun ChallengeDay.toEntity(): ChallengeDayEntity {
        return ChallengeDayEntity(
            dayNumber = dayNumber,
            title = title,
            subtitle = subtitle,
            description = description,
            audioDuration = audioDuration,
            microAction = microAction,
            tasksJson = tasks.joinToString("|||"),
            completedTasksJson = completedTasks.joinToString(",") { it.toString() },
            reflectionNotes = reflectionNotes
        )
    }

    private fun ChallengeDayEntity.toModel(): ChallengeDay {
        return ChallengeDay(
            dayNumber = dayNumber,
            title = title,
            subtitle = subtitle,
            description = description,
            audioDuration = audioDuration,
            microAction = microAction,
            tasks = tasksJson.split("|||").filter { it.isNotBlank() },
            completedTasks = parseBooleans(completedTasksJson),
            reflectionNotes = reflectionNotes
        )
    }

    private fun JournalEntity.toModel(): JournalEntry {
        return JournalEntry(
            id = id,
            timestamp = timestamp,
            mood = mood,
            stressBefore = stressBefore,
            stressAfter = stressAfter,
            reflection = reflection,
            gratitude = gratitude,
            emotionalBurden = emotionalBurden
        )
    }

    private fun UserProgress.toEntity(): UserProgressEntity {
        return UserProgressEntity(
            id = id,
            userName = userName,
            email = email,
            phone = phone,
            batchTime = batchTime,
            completedDaysCount = completedDaysCount,
            totalStreak = totalStreak,
            isCertificateClaimed = isCertificateClaimed,
            certificateIssuedDate = certificateIssuedDate
        )
    }

    private fun UserProgressEntity.toModel(): UserProgress {
        return UserProgress(
            id = id,
            userName = userName,
            email = email,
            phone = phone,
            batchTime = batchTime,
            completedDaysCount = completedDaysCount,
            totalStreak = totalStreak,
            isCertificateClaimed = isCertificateClaimed,
            certificateIssuedDate = certificateIssuedDate
        )
    }

    private fun parseBooleans(csv: String): List<Boolean> {
        if (csv.isBlank()) return emptyList()
        return csv.split(",").map { it.trim().toBoolean() }
    }

    companion object {
        fun getInitialDays(): List<ChallengeDay> {
            return listOf(
                ChallengeDay(
                    dayNumber = 1,
                    title = "Awareness & The Overthinking Trap",
                    subtitle = "Day 1: Recognize Mental Loops & Reclaim Attention",
                    description = "Discover the neurobiology of recursive rumination. Today, you learn to observe the inner chatter without identifying with it.",
                    audioDuration = "12 mins guided session",
                    microAction = "Practice the 3-minute STOP technique right after checking social media or email.",
                    tasks = listOf(
                        "Listen to Day 1 Masterclass audio (12 mins)",
                        "Identify 2 recurring worry triggers in your notes",
                        "Complete the STOP pause before lunch",
                        "Log your evening stress score"
                    ),
                    completedTasks = listOf(false, false, false, false)
                ),
                ChallengeDay(
                    dayNumber = 2,
                    title = "Emotional Regulation & Body Anchors",
                    subtitle = "Day 2: Somatic Grounding for Acute Anxiety",
                    description = "When the nervous system triggers fight-or-flight, logic shuts down. Master 4-7-8 and physiological sigh breathing.",
                    audioDuration = "14 mins breathwork coaching",
                    microAction = "Perform 3 physiological sighs before any stressful meeting or difficult conversation.",
                    tasks = listOf(
                        "Complete the morning diaphragmatic breathwork (7 mins)",
                        "Notice somatic tension in neck, jaw, or chest",
                        "Apply the 5-4-3-2-1 sensory grounding anchor",
                        "Journal one emotional release in the evening"
                    ),
                    completedTasks = listOf(false, false, false, false)
                ),
                ChallengeDay(
                    dayNumber = 3,
                    title = "Boundary Blueprint & Mental Declutter",
                    subtitle = "Day 3: Protecting Energy from Toxic Depletion",
                    description = "Inner peace requires relational boundaries. Build a clear energetic boundary around your high-focus hours and personal life.",
                    audioDuration = "15 mins mindset training",
                    microAction = "Say a polite, non-negotiable 'No' to one non-essential demand today.",
                    tasks = listOf(
                        "Conduct an energy drain audit (people, habits, tasks)",
                        "Draft your 'Not-to-Do' list for this week",
                        "Set up digital boundaries: zero screens 60 mins before bed",
                        "Complete evening gratitude reflection"
                    ),
                    completedTasks = listOf(false, false, false, false)
                ),
                ChallengeDay(
                    dayNumber = 4,
                    title = "Identity Shift & The Inner Critic",
                    subtitle = "Day 4: From Self-Sabotage to Sovereign Self-Trust",
                    description = "Silence the harsh internal imposter voice. Shift from self-judgment to compassionate, evidence-based self-efficacy.",
                    audioDuration = "16 mins cognitive reframing",
                    microAction = "Whenever you catch self-criticism, replace it with: 'I am learning, growing, and centered.'",
                    tasks = listOf(
                        "Write down 3 false narratives your inner critic tells you",
                        "Reframe each narrative with objective factual counters",
                        "Practice 5 minutes of heart-brain coherence meditation",
                        "Affirm your core professional and personal values"
                    ),
                    completedTasks = listOf(false, false, false, false)
                ),
                ChallengeDay(
                    dayNumber = 5,
                    title = "Sustained Serenity & MindForge 360",
                    subtitle = "Day 5: Long-Term Integration & Mastery",
                    description = "Consolidate your breakthrough into a lifelong lifestyle system. Integrate the MindForge 360 framework and claim your certificate.",
                    audioDuration = "18 mins integration masterclass",
                    microAction = "Commit to your daily non-negotiable 15-minute sacred morning routine.",
                    tasks = listOf(
                        "Design your permanent 15-minute daily reset ritual",
                        "Review all 5-day reflections and celebrate wins",
                        "Claim and personalize your MindForge 360° Certificate",
                        "Explore the Inner Shift Circle & 1:1 Coaching roadmap"
                    ),
                    completedTasks = listOf(false, false, false, false)
                )
            )
        }
    }
}
