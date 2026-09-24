package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.AppBottomNavigation
import com.example.ui.components.BreathingExerciseDialog
import com.example.ui.components.CareerBookingDialog
import com.example.ui.components.CertificateDialog
import com.example.ui.components.CoachContactDialog
import com.example.ui.components.PaymentPlanDialog
import com.example.ui.components.RegistrationDialog
import com.example.ui.components.WelcomeKitDialog
import com.example.ui.screens.CareerAxisScreen
import com.example.ui.screens.ChallengeScreen
import com.example.ui.screens.GuideScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.InnerShiftScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.PageBackground
import com.example.ui.viewmodel.AppTab
import com.example.ui.viewmodel.InnerPeaceViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: InnerPeaceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                InnerPeaceApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun InnerPeaceApp(viewModel: InnerPeaceViewModel) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val challengeDays by viewModel.challengeDays.collectAsStateWithLifecycle()
    val userProgress by viewModel.userProgress.collectAsStateWithLifecycle()
    val journalEntries by viewModel.journalEntries.collectAsStateWithLifecycle()
    val selectedDayNumber by viewModel.selectedDayNumber.collectAsStateWithLifecycle()

    val showCertificate by viewModel.showCertificateDialog.collectAsStateWithLifecycle()
    val showContactCoach by viewModel.showContactCoachDialog.collectAsStateWithLifecycle()
    val showBreathing by viewModel.showBreathingTool.collectAsStateWithLifecycle()
    val breathingPhase by viewModel.breathingPhase.collectAsStateWithLifecycle()
    val breathingCount by viewModel.breathingCount.collectAsStateWithLifecycle()

    val showRegistration by viewModel.showRegistrationModal.collectAsStateWithLifecycle()
    val selectedBatchTime by viewModel.selectedBatchTime.collectAsStateWithLifecycle()
    val showWelcomeKit by viewModel.showWelcomeKitModal.collectAsStateWithLifecycle()
    val showCareerBooking by viewModel.showCareerBookingModal.collectAsStateWithLifecycle()
    val careerBookingType by viewModel.careerBookingType.collectAsStateWithLifecycle()
    val selectedPlanForPayment by viewModel.selectedPlanForPayment.collectAsStateWithLifecycle()

    val activeAmbientSound by viewModel.activeAmbientSound.collectAsStateWithLifecycle()
    val isAmbientPlaying by viewModel.isAmbientSoundPlaying.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PageBackground,
        bottomBar = {
            AppBottomNavigation(
                currentTab = currentTab,
                onTabSelected = { viewModel.setTab(it) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(PageBackground)
        ) {
            AnimatedContent(
                targetState = currentTab,
                transitionSpec = {
                    if (targetState.ordinal > initialState.ordinal) {
                        (slideInHorizontally { width -> width / 3 } + fadeIn()) togetherWith
                                (slideOutHorizontally { width -> -width / 3 } + fadeOut())
                    } else {
                        (slideInHorizontally { width -> -width / 3 } + fadeIn()) togetherWith
                                (slideOutHorizontally { width -> width / 3 } + fadeOut())
                    }
                },
                label = "TabTransitions"
            ) { tab ->
                when (tab) {
                    AppTab.HOME -> {
                        HomeScreen(
                            progress = userProgress,
                            testimonials = viewModel.testimonials,
                            faqs = viewModel.faqs,
                            onNavigateTab = { viewModel.setTab(it) },
                            onOpenBreathing = { viewModel.openBreathingTool() },
                            onOpenCoach = { viewModel.openContactCoach() },
                            onOpenRegistration = { batch -> viewModel.openRegistration(batch) },
                            onOpenWelcomeKit = { viewModel.openWelcomeKit() },
                            onOpenCertificate = { viewModel.openCertificate() },
                            onSelectDay = { viewModel.selectDay(it) }
                        )
                    }
                    AppTab.DASHBOARD -> {
                        ChallengeScreen(
                            days = challengeDays,
                            selectedDayNumber = selectedDayNumber,
                            progress = userProgress,
                            onSelectDay = { viewModel.selectDay(it) },
                            onToggleTask = { day, index, checked ->
                                viewModel.toggleTask(day, index, checked)
                            },
                            onSaveReflection = { dayNum, notes ->
                                viewModel.saveDayReflection(dayNum, notes)
                            },
                            onOpenBreathing = { viewModel.openBreathingTool() },
                            onOpenCertificate = { viewModel.openCertificate() }
                        )
                    }
                    AppTab.INNER_SHIFT -> {
                        InnerShiftScreen(
                            plans = viewModel.membershipPlans,
                            pillars = viewModel.pillars,
                            activeSound = activeAmbientSound,
                            isPlayingSound = isAmbientPlaying,
                            onToggleSound = { viewModel.toggleAmbientSound() },
                            onSelectSound = { viewModel.selectAmbientSound(it) },
                            onOpenCoach = { viewModel.openContactCoach() },
                            onSelectPlan = { plan -> viewModel.openPaymentPlan(plan) }
                        )
                    }
                    AppTab.CAREER_AXIS -> {
                        CareerAxisScreen(
                            onBookConsultation = { viewModel.openContactCoach() },
                            onOpenBooking = { cat -> viewModel.openCareerBooking(cat) }
                        )
                    }
                    AppTab.GUIDE -> {
                        GuideScreen(
                            stopSteps = viewModel.stopSteps,
                            journalEntries = journalEntries,
                            onSaveJournal = { mood, sBefore, sAfter, refl, grat, burden ->
                                viewModel.saveJournal(mood, sBefore, sAfter, refl, grat, burden)
                            },
                            onOpenBreathing = { viewModel.openBreathingTool() }
                        )
                    }
                }
            }

            // Global Dialogs & Landing Tools

            // 1. Registration Dialog (Matching www.pathtoinnerpeace.in registration)
            if (showRegistration) {
                RegistrationDialog(
                    initialBatch = selectedBatchTime,
                    onClose = { viewModel.closeRegistration() },
                    onRegister = { name, email, phone, country, batch ->
                        viewModel.registerUser(name, email, phone, country, batch)
                    }
                )
            }

            // 2. Free Welcome Kit Dialog (Matching welcomekit-pathtoinnerpeace.vercel.app)
            if (showWelcomeKit) {
                WelcomeKitDialog(
                    onClose = { viewModel.closeWelcomeKit() },
                    onOpenChallenge = {
                        viewModel.closeWelcomeKit()
                        viewModel.selectDay(1)
                        viewModel.setTab(AppTab.DASHBOARD)
                    }
                )
            }

            // 3. Career Axis / Partner Sphere / 1:1 Consultation Dialog
            if (showCareerBooking) {
                CareerBookingDialog(
                    initialType = careerBookingType,
                    onClose = { viewModel.closeCareerBooking() }
                )
            }

            // 4. Razorpay Payment Modal for Membership Plans
            selectedPlanForPayment?.let { plan ->
                PaymentPlanDialog(
                    plan = plan,
                    onClose = { viewModel.closePaymentPlan() }
                )
            }

            // 5. Breathing Tool
            if (showBreathing) {
                BreathingExerciseDialog(
                    phase = breathingPhase,
                    count = breathingCount,
                    onClose = { viewModel.closeBreathingTool() }
                )
            }

            // 6. Certificate Dialog
            if (showCertificate) {
                CertificateDialog(
                    currentName = userProgress?.userName ?: "Mindful Seeker",
                    issuedDate = userProgress?.certificateIssuedDate ?: "",
                    onClaimCertificate = { name ->
                        viewModel.claimCertificate(name)
                    },
                    onClose = { viewModel.closeCertificate() }
                )
            }

            // 7. Coach Contact Dialog
            if (showContactCoach) {
                CoachContactDialog(
                    onClose = { viewModel.closeContactCoach() }
                )
            }
        }
    }
}
