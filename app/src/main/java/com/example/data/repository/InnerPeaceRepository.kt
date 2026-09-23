package com.example.data.repository

import com.example.R
import com.example.data.local.AppDatabase
import com.example.data.model.ChallengeDayEntity
import com.example.data.model.FaqItem
import com.example.data.model.JournalEntryEntity
import com.example.data.model.MembershipPlan
import com.example.data.model.PillarItem
import com.example.data.model.StopTechniqueStep
import com.example.data.model.TestimonialItem
import com.example.data.model.UserProgressEntity
import kotlinx.coroutines.flow.Flow

class InnerPeaceRepository(private val database: AppDatabase) {

    val allDays: Flow<List<ChallengeDayEntity>> = database.challengeDao().getAllChallengeDays()
    val userProgress: Flow<UserProgressEntity?> = database.userProgressDao().getUserProgress()
    val journalEntries: Flow<List<JournalEntryEntity>> = database.journalDao().getAllJournalEntries()

    suspend fun updateDay(day: ChallengeDayEntity) {
        database.challengeDao().updateDay(day)
    }

    suspend fun toggleDayTask(day: ChallengeDayEntity, taskIndex: Int, isChecked: Boolean) {
        val updated = when (taskIndex) {
            1 -> day.copy(isTask1Done = isChecked)
            2 -> day.copy(isTask2Done = isChecked)
            3 -> day.copy(isTask3Done = isChecked)
            else -> day
        }
        val allCompleted = updated.isTask1Done && updated.isTask2Done && updated.isTask3Done
        val finalDay = updated.copy(isCompleted = allCompleted)
        database.challengeDao().updateDay(finalDay)
    }

    suspend fun saveDayReflection(dayNumber: Int, reflection: String) {
        database.challengeDao().updateDayNotes(dayNumber, reflection)
    }

    suspend fun saveJournalEntry(entry: JournalEntryEntity): Long {
        return database.journalDao().insertJournalEntry(entry)
    }

    suspend fun claimCertificate(date: String) {
        database.userProgressDao().claimCertificate(date)
    }

    suspend fun updateUserName(name: String) {
        database.userProgressDao().updateUserName(name)
    }

    suspend fun registerUser(name: String, email: String, whatsapp: String, batch: String) {
        database.userProgressDao().registerUser(name, email, whatsapp, batch)
    }

    suspend fun incrementStreak() {
        database.userProgressDao().incrementStreak()
    }

    // EXACT Testimonials from www.pathtoinnerpeace.in with real downloaded avatars
    fun getTestimonials(): List<TestimonialItem> = listOf(
        TestimonialItem(
            id = "1",
            name = "Kakali Mukherjee",
            role = "Sr. Executive, Antardarshan",
            location = "Kolkata, India",
            avatarRes = R.drawable.seeker_kakali,
            rating = 5,
            quote = "I never imagined such clarity and emotional healing was possible in such a short time. An absolute blessing for my emotional well-being."
        ),
        TestimonialItem(
            id = "2",
            name = "Ujjal Gayan",
            role = "Facilitator, Antardarshan",
            location = "Kolkata, India",
            avatarRes = R.drawable.seeker_ujjal,
            rating = 5,
            quote = "Whole Program completely changed how I think, feel, and live."
        ),
        TestimonialItem(
            id = "3",
            name = "Swati Das",
            role = "Coordinator, Antardarshan",
            location = "West Bengal, India",
            avatarRes = R.drawable.seeker_swati,
            rating = 5,
            quote = "My relationship and emotional stability improved a lot."
        ),
        TestimonialItem(
            id = "4",
            name = "Manabendra Roy",
            role = "Executive, Antardarshan",
            location = "Kolkata, India",
            avatarRes = R.drawable.seeker_manabendra,
            rating = 5,
            quote = "I came broken, I left empowered. thanks to Path to Inner peace."
        ),
        TestimonialItem(
            id = "5",
            name = "Moloy Gayan",
            role = "Executive, Antardarshan",
            location = "West Bengal, India",
            avatarRes = R.drawable.seeker_moloy,
            rating = 5,
            quote = "Within weeks, my stress reduced and my clarity improved. It feels like I finally have control over my life."
        ),
        TestimonialItem(
            id = "6",
            name = "Biswajot Roy",
            role = "Secretary, Antardarshan",
            location = "Kolkata, India",
            avatarRes = R.drawable.seeker_biswajot,
            rating = 5,
            quote = "From confusion to clarity, the transformations we have seen during holistic program of Path to Inner Peace are truly remarkable."
        ),
        TestimonialItem(
            id = "7",
            name = "Amrita Ghosh",
            role = "Owner, Monginis Batanagar",
            location = "Kolkata, India",
            avatarRes = R.drawable.seeker_amrita,
            rating = 5,
            quote = "Very much satisfied after 3 sessions, I just tell it is really helpful for me.."
        )
    )

    // EXACT FAQs from www.pathtoinnerpeace.in
    fun getFaqs(): List<FaqItem> = listOf(
        FaqItem(
            question = "Is the 5-Day Mind Reset Challenge completely free?",
            answer = "Yes! The 5-Day 30-Minute Mind Reset Challenge is 100% free with no hidden charges or credit card required."
        ),
        FaqItem(
            question = "Can beginners with zero meditation experience join?",
            answer = "Absolutely. The program is specifically structured for beginners and busy professionals. Every session is fully guided step-by-step."
        ),
        FaqItem(
            question = "How much time do I need to dedicate daily?",
            answer = "Only 30 minutes per day! You can complete the sessions at your own pace morning, afternoon, or night."
        ),
        FaqItem(
            question = "Will daily recordings and materials be available?",
            answer = "Yes, you get full access to daily video/audio sessions, breathing guides, journal prompts, and guided tracks in your web dashboard."
        ),
        FaqItem(
            question = "Can I access this challenge from my mobile device?",
            answer = "Yes! Path to Inner Peace is built as a progressive web app (PWA), perfectly optimized for smartphones, tablets, and desktop browsers."
        ),
        FaqItem(
            question = "What happens after completing the 5 days?",
            answer = "You will receive an official verifiable Certificate of Completion and the option to join our premium MindForge 360°™ membership for long-term growth."
        )
    )

    // EXACT Membership Plans from www.pathtoinnerpeace.in
    fun getMembershipPlans(): List<MembershipPlan> = listOf(
        MembershipPlan(
            id = "INNER_SHIFT",
            name = "Basic Shift",
            tagline = "Build Your Mental Fitness Foundation",
            priceINR = 199,
            period = "Monthly Access",
            badge = "BEGINNER FRIENDLY",
            popular = false,
            features = listOf(
                "Foundation Meditation Training",
                "Mindfulness Training",
                "Essential Sound Therapy",
                "Daily Stress Relief Techniques",
                "Overthinking Reset Practices",
                "Weekly Guided Practice Plan",
                "Better Sleep Relaxation Audio",
                "Mental Reset Starter Guide",
                "Monthly Mental Fitness Assessment",
                "Progress Tracking",
                "Community Updates",
                "Digital Certificate of Completion"
            ),
            buttonText = "Join Now",
            colorScheme = "emerald",
            paymentUrl = "https://rzp.io/rzp/O6VyUfSW"
        ),
        MembershipPlan(
            id = "MIND_MASTERY_PRO",
            name = "MIND MASTERY PRO",
            tagline = "Accelerate Your Mental Growth",
            priceINR = 499,
            period = "Monthly Access",
            badge = "MOST POPULAR CHOICE",
            popular = true,
            features = listOf(
                "Everything in Basic, PLUS:",
                "Advanced Meditation Training",
                "Professional Mindfulness Program",
                "Guided Sound Therapy Sessions",
                "Emotional Balance Practices",
                "Focus & Mental Clarity Training",
                "Weekly LIVE Coaching Sessions",
                "Standard Personal Practice Roadmap",
                "WhatsApp Community Support",
                "MindForge 360°™ Community Access",
                "Exclusive Monthly Workshops",
                "Advanced CBT Exercises",
                "Digital Certificate of Completion"
            ),
            buttonText = "Upgrade Now (₹499)",
            colorScheme = "gold",
            paymentUrl = "https://rzp.io/rzp/Xv7Q6XB"
        ),
        MembershipPlan(
            id = "INNER_TRANSFORMATION_ELITE",
            name = "INNER TRANSFORMATION ELITE",
            tagline = "Complete Mind Transformation Experience",
            priceINR = 1499,
            period = "Monthly Access",
            badge = "LUXURY ELITE MEMBERSHIP",
            popular = false,
            features = listOf(
                "Everything in Pro, PLUS:",
                "1:1 Personal Coaching with Mainak",
                "Custom Neural Reprogramming Track",
                "Priority WhatsApp VIP Line",
                "Deep Somatic Release Mentorship",
                "Career Axis Alignment Blueprint",
                "Full VIP Community Leadership Circle"
            ),
            buttonText = "Apply for Elite (₹1,499)",
            colorScheme = "emerald",
            paymentUrl = "https://rzp.io/rzp/x8BS9RM"
        )
    )

    // The STOP Technique Steps
    fun getStopSteps(): List<StopTechniqueStep> = listOf(
        StopTechniqueStep("S", "Stop", "Pause whatever you are doing. Disengage from automatic reactions.", 10),
        StopTechniqueStep("T", "Take a Breath", "Inhale deeply into the belly; exhale slowly to activate vagal braking.", 20),
        StopTechniqueStep("O", "Observe", "Notice bodily sensations, emotions, and thoughts without judging them.", 30),
        StopTechniqueStep("P", "Proceed", "Move forward with calm clarity and sovereign intention.", 15)
    )

    // 10 Pillars Curriculum
    fun getTenPillars(): List<PillarItem> = listOf(
        PillarItem(1, "01", "Stress Management", "Nervous System Regulation & CBT", "Evidence-informed tools to understand, regulate and transform stress.", listOf("Vagus nerve box breathing", "Cortisol downshift ritual", "Somatic tension unclench")),
        PillarItem(2, "02", "Meditation & Mindfulness", "Awareness & Attention Control", "Daily guided mental focus practices to quiet mental chatter and cultivate stillness.", listOf("Thought cloud observation", "Mindful tea grounding", "Body scan serenity")),
        PillarItem(3, "03", "Emotional Healing & Forgiveness", "Heart Coherence & Somatics", "Release repressed sorrow, anger, and guilt with radical self-compassion.", listOf("Forgiveness letter ritual", "Heart-brain coherence breathing", "Emotional weight unburdening")),
        PillarItem(4, "04", "Overthinking & Anxiety Reset", "Metacognitive Decoupling", "Stop catastrophic rumination in under 90 seconds using CBT thought disputation.", listOf("ANTs labeling", "Evidence for vs against grid", "Worry container visualization")),
        PillarItem(5, "05", "Subconscious Reprogramming", "Neural Conditioning & Alpha Waves", "Rewire deep childhood limiting beliefs and step into innate self-worth.", listOf("Quantum self-image reframing", "Morning mirror identity alignment", "Subconscious sleep audio")),
        PillarItem(6, "06", "Restorative Sleep Architecture", "Circadian & Neurochemical Harmony", "Reclaim deep delta-wave sleep through circadian rhythm synchronization.", listOf("Digital sundown blue-light detox", "Yoga Nidra NSDR protocol", "Brain-dump reflection journal")),
        PillarItem(7, "07", "Inner Child & Self-Compassion", "Reparenting the Vulnerable Self", "Silence the relentless inner critic and cultivate protective self-warmth.", listOf("Dialogue with younger self", "Compassionate hand-on-heart pause", "Affirmation of inherent dignity")),
        PillarItem(8, "08", "Relationship Harmony & Boundaries", "Interpersonal Emotional Sovereignty", "Establish clear boundaries with zero guilt and communicate with calm clarity.", listOf("10-second reactive pause rule", "Non-violent clarity scripts", "Energetic detachment visualization")),
        PillarItem(9, "09", "Career Axis & Professional Sanity", "Burnout Prevention & Purpose", "Eliminate corporate burnout, workplace anxiety, and imposter syndrome.", listOf("Flow state deep-work rituals", "Meeting composure protocol", "Career Axis values matrix")),
        PillarItem(10, "10", "Quantum Awareness & Lifelong Mastery", "MindForge 360°™ Transcendence", "Live as the conscious author of your reality with unshakable inner peace.", listOf("Alpha/Theta brainwave anchoring", "Daily gratitude frequency", "MindForge 360° mastery circle"))
    )
}
