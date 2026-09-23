package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.model.ChallengeDayEntity
import com.example.data.model.FaqItem
import com.example.data.model.JournalEntryEntity
import com.example.data.model.MembershipPlan
import com.example.data.model.PillarItem
import com.example.data.model.StopTechniqueStep
import com.example.data.model.TestimonialItem
import com.example.data.model.UserProgressEntity
import com.example.data.repository.InnerPeaceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
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
    HOLD_IN,
    EXHALE,
    HOLD_OUT
}

class InnerPeaceViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: InnerPeaceRepository
    init {
        val db = AppDatabase.getDatabase(application, viewModelScope)
        repository = InnerPeaceRepository(db)
    }

    val challengeDays: StateFlow<List<ChallengeDayEntity>> = repository.allDays
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userProgress: StateFlow<UserProgressEntity?> = repository.userProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val journalEntries: StateFlow<List<JournalEntryEntity>> = repository.journalEntries
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val testimonials: List<TestimonialItem> = repository.getTestimonials()
    val faqs: List<FaqItem> = repository.getFaqs()
    val membershipPlans: List<MembershipPlan> = repository.getMembershipPlans()
    val stopSteps: List<StopTechniqueStep> = repository.getStopSteps()
    val pillars: List<PillarItem> = repository.getTenPillars()

    // Navigation & View states
    private val _currentTab = MutableStateFlow(AppTab.HOME)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

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

    private val _isBreathingActive = MutableStateFlow(false)
    val isBreathingActive: StateFlow<Boolean> = _isBreathingActive.asStateFlow()

    private var breathingJob: Job? = null

    // Ambient Sound Player
    private val _activeAmbientSound = MutableStateFlow("Gentle Rain with Theta Binaural Tones (6Hz)")
    val activeAmbientSound: StateFlow<String> = _activeAmbientSound.asStateFlow()

    private val _isAmbientSoundPlaying = MutableStateFlow(false)
    val isAmbientSoundPlaying: StateFlow<Boolean> = _isAmbientSoundPlaying.asStateFlow()

    // STOP Technique State
    private val _currentStopIndex = MutableStateFlow(0)
    val currentStopIndex: StateFlow<Int> = _currentStopIndex.asStateFlow()

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

    fun selectBatchTime(batch: String) {
        _selectedBatchTime.value = batch
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
            // Navigate directly to Dashboard Day 1
            _selectedDayNumber.value = 1
            _currentTab.value = AppTab.DASHBOARD

            // Background lead notification to coach Mainak (matching website Jt function)
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

    fun openCareerBooking(type: String = "College / Campus Alignment") {
        _careerBookingType.value = type
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

    fun toggleTask(day: ChallengeDayEntity, taskIndex: Int, isChecked: Boolean) {
        viewModelScope.launch {
            repository.toggleDayTask(day, taskIndex, isChecked)
        }
    }

    fun saveDayReflection(dayNumber: Int, reflection: String) {
        viewModelScope.launch {
            repository.saveDayReflection(dayNumber, reflection)
        }
    }

    fun saveJournal(
        mood: String,
        stressBefore: Int,
        stressAfter: Int,
        reflection: String,
        gratitude: String,
        releasedBurden: String
    ) {
        viewModelScope.launch {
            val dateStr = SimpleDateFormat("MMM dd, yyyy • hh:mm a", Locale.getDefault()).format(Date())
            val entry = JournalEntryEntity(
                dateString = dateStr,
                mood = mood,
                stressBefore = stressBefore,
                stressAfter = stressAfter,
                reflection = reflection,
                gratitude = gratitude,
                releasedBurden = releasedBurden
            )
            repository.saveJournalEntry(entry)
            repository.incrementStreak()
        }
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
            repository.updateUserName(name)
            repository.claimCertificate(dateStr)
        }
    }

    fun openContactCoach() {
        _showContactCoachDialog.value = true
    }

    fun closeContactCoach() {
        _showContactCoachDialog.value = false
    }

    fun openBreathingTool() {
        _showBreathingTool.value = true
        startBreathing()
    }

    fun closeBreathingTool() {
        stopBreathing()
        _showBreathingTool.value = false
    }

    fun toggleAmbientSound() {
        _isAmbientSoundPlaying.value = !_isAmbientSoundPlaying.value
    }

    fun selectAmbientSound(sound: String) {
        _activeAmbientSound.value = sound
        _isAmbientSoundPlaying.value = true
    }

    fun setStopIndex(index: Int) {
        _currentStopIndex.value = index
    }

    private fun startBreathing() {
        _isBreathingActive.value = true
        breathingJob?.cancel()
        breathingJob = viewModelScope.launch {
            while (_isBreathingActive.value) {
                _breathingPhase.value = BreathingPhase.INHALE
                for (i in 4 downTo 1) {
                    _breathingCount.value = i
                    delay(1000)
                }
                _breathingPhase.value = BreathingPhase.HOLD_IN
                for (i in 4 downTo 1) {
                    _breathingCount.value = i
                    delay(1000)
                }
                _breathingPhase.value = BreathingPhase.EXHALE
                for (i in 4 downTo 1) {
                    _breathingCount.value = i
                    delay(1000)
                }
                _breathingPhase.value = BreathingPhase.HOLD_OUT
                for (i in 4 downTo 1) {
                    _breathingCount.value = i
                    delay(1000)
                }
            }
        }
    }

    private fun stopBreathing() {
        _isBreathingActive.value = false
        breathingJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        breathingJob?.cancel()
    }
}
