package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.model.ChallengeDay
import com.example.data.model.FaqItem
import com.example.data.model.JournalEntry
import com.example.data.model.MembershipPlan
import com.example.data.model.Pillar
import com.example.data.model.StopStep
import com.example.data.model.Testimonial
import com.example.data.model.UserProgress
import com.example.data.repository.InnerPeaceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class AppTab {
    HOME,
    DASHBOARD,
    INNER_SHIFT,
    CAREER_AXIS,
    GUIDE
}

enum class BreathingPhase {
    INHALE,
    HOLD,
    EXHALE,
    REST
}

class InnerPeaceViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = InnerPeaceRepository(AppDatabase.getDatabase(application))

    val challengeDays: StateFlow<List<ChallengeDay>> = repository.challengeDays
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), InnerPeaceRepository.getInitialDays())

    val userProgress: StateFlow<UserProgress?> = repository.userProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val journalEntries: StateFlow<List<JournalEntry>> = repository.journalEntries
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Navigation Tab
    private val _currentTab = MutableStateFlow(AppTab.HOME)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    // Selected Day in Challenge
    private val _selectedDayNumber = MutableStateFlow(1)
    val selectedDayNumber: StateFlow<Int> = _selectedDayNumber.asStateFlow()

    // Registration Modal
    private val _showRegistrationModal = MutableStateFlow(false)
    val showRegistrationModal: StateFlow<Boolean> = _showRegistrationModal.asStateFlow()

    private val _selectedBatchTime = MutableStateFlow("6:30 AM")
    val selectedBatchTime: StateFlow<String> = _selectedBatchTime.asStateFlow()

    // Welcome Kit Modal
    private val _showWelcomeKitModal = MutableStateFlow(false)
    val showWelcomeKitModal: StateFlow<Boolean> = _showWelcomeKitModal.asStateFlow()

    // Career Booking Modal
    private val _showCareerBookingModal = MutableStateFlow(false)
    val showCareerBookingModal: StateFlow<Boolean> = _showCareerBookingModal.asStateFlow()

    private val _careerBookingType = MutableStateFlow("College / Campus Alignment")
    val careerBookingType: StateFlow<String> = _careerBookingType.asStateFlow()

    // Payment Plan Modal
    private val _showPaymentPlanModal = MutableStateFlow(false)
    val showPaymentPlanModal: StateFlow<Boolean> = _showPaymentPlanModal.asStateFlow()

    private val _selectedPlanForPayment = MutableStateFlow<MembershipPlan?>(null)
    val selectedPlanForPayment: StateFlow<MembershipPlan?> = _selectedPlanForPayment.asStateFlow()

    // Certificate Dialog
    private val _showCertificateDialog = MutableStateFlow(false)
    val showCertificateDialog: StateFlow<Boolean> = _showCertificateDialog.asStateFlow()

    // Contact Coach Dialog
    private val _showContactCoachDialog = MutableStateFlow(false)
    val showContactCoachDialog: StateFlow<Boolean> = _showContactCoachDialog.asStateFlow()

    // Breathing Exercise State
    private val _showBreathingTool = MutableStateFlow(false)
    val showBreathingTool: StateFlow<Boolean> = _showBreathingTool.asStateFlow()

    private val _breathingPhase = MutableStateFlow(BreathingPhase.INHALE)
    val breathingPhase: StateFlow<BreathingPhase> = _breathingPhase.asStateFlow()

    private val _breathingCount = MutableStateFlow(4)
    val breathingCount: StateFlow<Int> = _breathingCount.asStateFlow()

    private var breathingJob: Job? = null

    // Ambient Sound Player
    private val _activeAmbientSound = MutableStateFlow("Gentle Rain with Theta Binaural Tones (6Hz)")
    val activeAmbientSound: StateFlow<String> = _activeAmbientSound.asStateFlow()

    private val _isAmbientSoundPlaying = MutableStateFlow(false)
    val isAmbientSoundPlaying: StateFlow<Boolean> = _isAmbientSoundPlaying.asStateFlow()

    fun setTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun selectDay(dayNumber: Int) {
        _selectedDayNumber.value = dayNumber
    }

    fun openRegistration(batch: String? = null) {
        if (!batch.isNullOrBlank()) {
            _selectedBatchTime.value = batch
        }
        _showRegistrationModal.value = true
    }

    fun closeRegistration() {
        _showRegistrationModal.value = false
    }

    fun registerUser(
        fullName: String,
        email: String,
        whatsapp: String,
        country: String,
        batch: String
    ) {
        viewModelScope.launch {
            repository.registerUser(fullName, email, whatsapp, batch)
            _showRegistrationModal.value = false
            _selectedDayNumber.value = 1
            _currentTab.value = AppTab.DASHBOARD

            // Optional background notification
            kotlin.runCatching {
                val url = java.net.URL("https://formsubmit.co/ajax/mchatterjee69@gmail.com")
                val conn = url.openConnection() as java.net.HttpURLConnection
                conn.requestMethod = "POST"
                conn.setRequestProperty("Content-Type", "application/json")
                conn.setRequestProperty("Accept", "application/json")
                conn.doOutput = true
                conn.connectTimeout = 5000
                conn.readTimeout = 5000
                val json = """
                    {
                        "_subject": "[New Challenge Lead] 5-Day Mind Reset - $fullName",
                        "formType": "5-Day Mind Reset Registration",
                        "fullName": "$fullName",
                        "whatsapp": "$whatsapp",
                        "email": "$email",
                        "country": "$country",
                        "batch": "$batch"
                    }
                """.trimIndent()
                conn.outputStream.use { os ->
                    os.write(json.toByteArray())
                }
                conn.responseCode
                conn.disconnect()
            }
        }
    }

    fun openWelcomeKit() {
        _showWelcomeKitModal.value = true
    }

    fun closeWelcomeKit() {
        _showWelcomeKitModal.value = false
    }

    fun openCareerBooking(bookingType: String = "College / Campus Alignment") {
        _careerBookingType.value = bookingType
        _showCareerBookingModal.value = true
    }

    fun closeCareerBooking() {
        _showCareerBookingModal.value = false
    }

    fun openPaymentPlan(plan: MembershipPlan) {
        _selectedPlanForPayment.value = plan
        _showPaymentPlanModal.value = true
    }

    fun closePaymentPlan() {
        _showPaymentPlanModal.value = false
        _selectedPlanForPayment.value = null
    }

    fun openCertificate() {
        _showCertificateDialog.value = true
    }

    fun closeCertificate() {
        _showCertificateDialog.value = false
    }

    fun claimCertificate(name: String) {
        viewModelScope.launch {
            val dateStr = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date())
            repository.claimCertificate(name, dateStr)
        }
    }

    fun openContactCoach() {
        _showContactCoachDialog.value = true
    }

    fun closeContactCoach() {
        _showContactCoachDialog.value = false
    }

    fun toggleTask(dayNumber: Int, taskIndex: Int, isChecked: Boolean) {
        viewModelScope.launch {
            repository.updateTaskCompletion(dayNumber, taskIndex, isChecked)
        }
    }

    fun saveDayReflection(dayNumber: Int, notes: String) {
        viewModelScope.launch {
            repository.updateReflection(dayNumber, notes)
        }
    }

    fun saveJournal(
        mood: String,
        stressBefore: Int,
        stressAfter: Int,
        reflection: String,
        gratitude: String,
        burden: String
    ) {
        viewModelScope.launch {
            repository.addJournalEntry(mood, stressBefore, stressAfter, reflection, gratitude, burden)
        }
    }

    fun openBreathingTool() {
        _showBreathingTool.value = true
        startBreathingCycle()
    }

    fun closeBreathingTool() {
        _showBreathingTool.value = false
        stopBreathingCycle()
    }

    private fun startBreathingCycle() {
        breathingJob?.cancel()
        breathingJob = viewModelScope.launch {
            while (isActive) {
                _breathingPhase.value = BreathingPhase.INHALE
                for (i in 4 downTo 1) {
                    _breathingCount.value = i
                    delay(1000)
                }
                _breathingPhase.value = BreathingPhase.HOLD
                for (i in 7 downTo 1) {
                    _breathingCount.value = i
                    delay(1000)
                }
                _breathingPhase.value = BreathingPhase.EXHALE
                for (i in 8 downTo 1) {
                    _breathingCount.value = i
                    delay(1000)
                }
                _breathingPhase.value = BreathingPhase.REST
                delay(1000)
            }
        }
    }

    private fun stopBreathingCycle() {
        breathingJob?.cancel()
        breathingJob = null
    }

    fun toggleAmbientSound() {
        _isAmbientSoundPlaying.value = !_isAmbientSoundPlaying.value
    }

    fun selectAmbientSound(soundName: String) {
        _activeAmbientSound.value = soundName
        _isAmbientSoundPlaying.value = true
    }

    // Static website datasets for parity with www.pathtoinnerpeace.in
    val membershipPlans = listOf(
        MembershipPlan(
            id = "plan_quarterly",
            name = "Quarterly Anchor",
            price = "₹2,499",
            originalPrice = "₹4,999",
            period = "for 3 months",
            badge = "Essential Start",
            description = "Solid foundation for daily mental balance and emotional equilibrium with group accountability.",
            features = listOf(
                "Full access to 5-Day Mind Reset Challenge & Replays",
                "Weekly live Sunday S.T.O.P. Group Coherence Calls",
                "MindForge 360° Resource Library & Notion Workspaces",
                "Community Circle WhatsApp access for daily wins"
            ),
            isPopular = false
        ),
        MembershipPlan(
            id = "plan_annual",
            name = "Annual Sovereign",
            price = "₹6,999",
            originalPrice = "₹14,999",
            period = "for 1 full year",
            badge = "Most Popular • 60% OFF",
            description = "Complete cognitive rewiring and career resilience mentorship for ambitious professionals and students.",
            features = listOf(
                "Everything in Quarterly Anchor",
                "1-on-1 Personal Deep-Dive Clarity Audit with Mainak Chatterjee",
                "Emergency 15-min Calm Hot-Line access during panic/burnout",
                "Career Axis Alignment Matrix & Imposter Syndrome Blueprint",
                "Lifetime Certificate of Mastery in Cognitive Serenity"
            ),
            isPopular = true
        ),
        MembershipPlan(
            id = "plan_institutional",
            name = "Campus & Corporate Cohort",
            price = "Custom",
            originalPrice = "Institutional Tier",
            period = "per organization",
            badge = "Partnership Sphere",
            description = "Specialized high-impact workshops tailored for university departments, leadership teams, and corporates.",
            features = listOf(
                "Customized multi-day on-campus or virtual masterclasses",
                "Measurable stress reduction metrics and anonymized audits",
                "Bulk student/employee onboarding & personalized kits",
                "Ongoing monthly retention & resilience masterclasses"
            ),
            isPopular = false
        )
    )

    val pillars = listOf(
        Pillar(
            title = "Somatic Nervous Regulation",
            subtitle = "Biological Calming",
            description = "Move from chronic sympathetic fight-or-flight into restorative parasympathetic rest via science-backed breath cycles.",
            iconName = "Air"
        ),
        Pillar(
            title = "Cognitive Reframing & S.T.O.P.",
            subtitle = "Mental Mastery",
            description = "Disarm cognitive distortions, catastrophizing thoughts, and recurring rumination through the clinical STOP protocol.",
            iconName = "Psychology"
        ),
        Pillar(
            title = "Career Axis Alignment",
            subtitle = "Purposeful Trajectory",
            description = "Bridge deep inner peace with outer professional ambition. Eliminate workplace imposter syndrome and burnout.",
            iconName = "Work"
        ),
        Pillar(
            title = "Identity & Habit Architecture",
            subtitle = "Permanent Transformation",
            description = "Automate mindful micro-actions using the 2-minute rule, identity cues, and sacred morning routine anchors.",
            iconName = "SelfImprovement"
        )
    )

    val testimonials = listOf(
        Testimonial(
            name = "Dr. Ananya Roy",
            role = "Senior Consultant Psychiatrist",
            quote = "Mainak's MindForge methodology bridges contemporary neuroscience with actionable somatic tools. My patients and corporate peers rave about the 5-Day Mind Reset.",
            result = "78% decrease in morning cortisol anxiety",
            rating = 5
        ),
        Testimonial(
            name = "Rohan Sengupta",
            role = "Senior Product Lead at Tech Multinational",
            quote = "As an engineering leader dealing with constant deadlines, panic attacks were creeping in. The S.T.O.P. practice and Career Axis gave me back my quiet evenings.",
            result = "Zero panic incidents in 90 days",
            rating = 5
        ),
        Testimonial(
            name = "Pooja Deshmukh",
            role = "Final Year Engineering Scholar",
            quote = "Campus placement anxiety was suffocating. The Free Welcome Kit and morning 6:30 AM batch brought immense discipline and clarity.",
            result = "Cracked dream offer with calm confidence",
            rating = 5
        )
    )

    val faqs = listOf(
        FaqItem(
            question = "What is the 5-Day Mind Reset Challenge?",
            answer = "It is an intensive, step-by-step 5-day daily habit and mindset reset designed by Mainak Chatterjee to break overthinking, regulate anxiety, and cultivate calm productivity in just 15 minutes a day."
        ),
        FaqItem(
            question = "What timings are available for the live batches?",
            answer = "We offer two daily batches: Morning 6:30 AM IST (recommended for early clarity) and Evening 8:30 PM IST (ideal for workday wind-down). Recordings and audio recaps are accessible anytime."
        ),
        FaqItem(
            question = "Is the Welcome Kit completely free?",
            answer = "Yes! The Welcome Kit is 100% complimentary upon joining. It includes the S.T.O.P. Quick Reference Card, Nervous System Reset Audio, and the MindForge 360° Tracker."
        ),
        FaqItem(
            question = "Will I receive an official certificate?",
            answer = "Yes. Upon completing all 5 days of micro-tasks and reflections, you receive the verified 'MindForge 360° Mastery in Inner Peace' certificate signed by Mainak Chatterjee."
        )
    )

    val stopSteps = listOf(
        StopStep(
            letter = "S",
            title = "Stop",
            instruction = "Pause whatever you are doing. Put down devices and release hand tension.",
            durationText = "15 seconds"
        ),
        StopStep(
            letter = "T",
            title = "Take a Breath",
            instruction = "Inhale slowly through your nose for 4 seconds, exhale through mouth for 6 seconds.",
            durationText = "45 seconds"
        ),
        StopStep(
            letter = "O",
            title = "Observe",
            instruction = "Notice body tension, current emotional state, and mental chatter without judgment.",
            durationText = "45 seconds"
        ),
        StopStep(
            letter = "P",
            title = "Proceed",
            instruction = "Step forward with clear intent, compassion, and purposeful, calm energy.",
            durationText = "15 seconds"
        )
    )
}
