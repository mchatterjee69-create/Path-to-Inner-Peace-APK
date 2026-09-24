package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.FaqItem
import com.example.data.model.Testimonial
import com.example.data.model.UserProgress
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.BrandGold
import com.example.ui.theme.BrandGoldDark
import com.example.ui.theme.CardSurface
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldMedium
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoftBg
import com.example.ui.theme.PageBackground
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextLight
import com.example.ui.theme.TextMuted
import com.example.ui.viewmodel.AppTab

@Composable
fun HomeScreen(
    progress: UserProgress?,
    testimonials: List<Testimonial>,
    faqs: List<FaqItem>,
    onNavigateTab: (AppTab) -> Unit,
    onOpenBreathing: () -> Unit,
    onOpenCoach: () -> Unit,
    onOpenRegistration: (String?) -> Unit,
    onOpenWelcomeKit: () -> Unit,
    onOpenCertificate: () -> Unit,
    onSelectDay: (Int) -> Unit
) {
    val context = LocalContext.current
    var selectedBatch by remember { mutableStateOf("6:30 AM") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground)
            .testTag("home_screen")
    ) {
        // 1. HERO SECTION (Identical copy of www.pathtoinnerpeace.in top fold)
        item {
            HeroSection(
                selectedBatch = selectedBatch,
                onBatchChange = { selectedBatch = it },
                onClaimSeat = { onOpenRegistration(selectedBatch) },
                onWatchIntro = onOpenBreathing
            )
        }

        // 2. SOCIAL PROOF & STATS BAR
        item {
            SocialProofBar()
        }

        // 3. FREE WELCOME KIT HIGHLIGHT CARD
        item {
            WelcomeKitHighlightBanner(
                onOpenWelcomeKit = onOpenWelcomeKit
            )
        }

        // 4. 5-DAY CURRICULUM SYLLABUS OVERVIEW
        item {
            CurriculumOverview(
                onSelectDay = { day ->
                    onSelectDay(day)
                    onNavigateTab(AppTab.DASHBOARD)
                },
                onOpenRegistration = { onOpenRegistration(selectedBatch) }
            )
        }

        // 5. ABOUT THE MENTOR - MAINAK CHATTERJEE
        item {
            MentorSection(
                onContactCoach = onOpenCoach
            )
        }

        // 6. TESTIMONIALS & COMMUNITY WINS
        item {
            TestimonialsSection(testimonials = testimonials)
        }

        // 7. FREQUENTLY ASKED QUESTIONS
        item {
            FaqSection(faqs = faqs)
        }

        // 8. BOTTOM PERSISTENT REGISTRATION CTA
        item {
            BottomCtaBanner(
                onRegister = { onOpenRegistration(selectedBatch) },
                onOpenWelcomeKit = onOpenWelcomeKit
            )
        }
    }
}

@Composable
private fun HeroSection(
    selectedBatch: String,
    onBatchChange: (String) -> Unit,
    onClaimSeat: () -> Unit,
    onWatchIntro: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        EmeraldDark,
                        EmeraldMedium,
                        Color(0xFF063F33)
                    )
                )
            )
            .padding(horizontal = 20.dp, vertical = 28.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Brand Logo & Brand Tagline matching www.pathtoinnerpeace.in
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 18.dp)
            ) {
                androidx.compose.foundation.Image(
                    painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.ic_pathtoinnerpeace_logo),
                    contentDescription = "Path to Inner Peace Official Logo",
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "PATH TO INNER PEACE",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.2.sp
                    )
                    Text(
                        text = "Transform Your Mind, Elevate Your Life",
                        color = BrandGold,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Live Status Pill
            Surface(
                shape = RoundedCornerShape(50),
                color = BrandGold.copy(alpha = 0.15f),
                border = androidx.compose.foundation.BorderStroke(1.dp, BrandGold),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(BrandGold, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "NEW COHORT STARTING MONDAY",
                        color = BrandGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }

            // Headline
            Text(
                text = "Rewire Your Mind.\nReclaim Your Peace.",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                lineHeight = 38.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = "The 5-Day Mind Reset Challenge by Mainak Chatterjee. Break overthinking, eliminate anxiety loops, and master calm high performance in 15 minutes a day.",
                color = Color(0xFFA7F3D0),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.fillMaxWidth(0.95f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Batch Timing Selector
            Text(
                text = "CHOOSE YOUR DAILY LIVE BATCH:",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val batches = listOf("6:30 AM", "8:30 PM")
                batches.forEach { batch ->
                    val isSelected = selectedBatch == batch
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) EmeraldPrimary else EmeraldDark.copy(alpha = 0.6f))
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) BrandGold else Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { onBatchChange(batch) }
                            .padding(vertical = 12.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.AccessTime,
                                    contentDescription = null,
                                    tint = if (isSelected) BrandGold else Color.White.copy(alpha = 0.7f),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "$batch IST",
                                    color = Color.White,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 14.sp
                                )
                            }
                            Text(
                                text = if (batch == "6:30 AM") "Morning Focus" else "Evening Reset",
                                color = if (isSelected) Color(0xFFA7F3D0) else Color.White.copy(alpha = 0.6f),
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Primary Call to Action Button
            Button(
                onClick = onClaimSeat,
                colors = ButtonDefaults.buttonColors(containerColor = BrandGold),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("home_claim_free_seat_button")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Claim Free Seat & Join Challenge",
                        color = EmeraldDark,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = EmeraldDark,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Secondary Quick Somatic Reset button
            OutlinedButton(
                onClick = onWatchIntro,
                shape = RoundedCornerShape(14.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.3f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = BrandGold,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Try 60-Sec Calming Breathwork",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Guarantee Pill
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = BrandGold,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "100% Free Access • Zero Credit Card Required",
                    color = Color(0xFFA7F3D0),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun SocialProofBar() {
    Surface(
        color = CardSurface,
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProofItem(number = "10,000+", label = "Seekers Impacted")
            ProofDivider()
            ProofItem(number = "4.9 ★", label = "Community Rating")
            ProofDivider()
            ProofItem(number = "50+", label = "Campus Workshops")
        }
    }
}

@Composable
private fun ProofItem(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            color = EmeraldDark,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = label,
            color = TextMuted,
            fontSize = 11.sp
        )
    }
}

@Composable
private fun ProofDivider() {
    Box(
        modifier = Modifier
            .height(28.dp)
            .width(1.dp)
            .background(BorderSubtle)
    )
}

@Composable
private fun WelcomeKitHighlightBanner(onOpenWelcomeKit: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = EmeraldSoftBg),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, EmeraldLight.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(EmeraldPrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CardGiftcard,
                    contentDescription = null,
                    tint = BrandGold,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Claim Free Welcome Kit",
                    color = EmeraldDark,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Includes S.T.O.P. Pocket Card, 6Hz Theta Audio & MindForge PDF Tracker.",
                    color = TextMuted,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onOpenWelcomeKit() }
                ) {
                    Text(
                        text = "Access Welcome Kit Vault",
                        color = EmeraldPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = EmeraldPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CurriculumOverview(
    onSelectDay: (Int) -> Unit,
    onOpenRegistration: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "5-DAY TRANSFORMATION",
                    color = BrandGoldDark,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "The Curriculum Syllabus",
                    color = TextDark,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = onOpenRegistration,
                colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Register Free", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        val daysSummary = listOf(
            Triple(1, "Awareness & The Overthinking Trap", "Learn recursive rumination biology & 3-min STOP pause."),
            Triple(2, "Emotional Regulation & Body Anchors", "Somatic grounding, physiological sighs & parasympathetic reset."),
            Triple(3, "Boundary Blueprint & Mental Declutter", "Defend high-focus hours & eliminate toxic draining demands."),
            Triple(4, "Identity Shift & The Inner Critic", "Disarm imposter syndrome & build evidence-based self-trust."),
            Triple(5, "Sustained Serenity & MindForge 360", "Automate sacred 15-min morning ritual & claim Certificate.")
        )

        daysSummary.forEach { (num, title, desc) ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
                    .clickable { onSelectDay(num) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(EmeraldDark, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "0$num",
                            color = BrandGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            color = TextDark,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = desc,
                            color = TextMuted,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = TextLight,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun MentorSection(onContactCoach: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = EmeraldDark),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(BrandGold, CircleShape)
                    .border(3.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "MC",
                    color = EmeraldDark,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Meet Mainak Chatterjee",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Mindset & Executive Career Mentor • Founder, Path to Inner Peace",
                color = BrandGold,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Mainak combines cognitive neuroscience, somatic breathwork, and high-performance career coaching to help executives, scholars, and ambitious professionals dismantle chronic burnout and achieve relentless clarity.",
                color = Color(0xFFA7F3D0),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onContactCoach,
                colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Chat Directly with Coach Mainak", fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
        }
    }
}

@Composable
private fun TestimonialsSection(testimonials: List<Testimonial>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "REAL TRANSFORMATION STORIES",
            color = BrandGoldDark,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(
            text = "What Alumni Say",
            color = TextDark,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        testimonials.forEach { t ->
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = t.name,
                                color = TextDark,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = t.role,
                                color = TextMuted,
                                fontSize = 11.sp
                            )
                        }

                        Row {
                            repeat(t.rating) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = BrandGold,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "\"${t.quote}\"",
                        color = Color(0xFF334155),
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = EmeraldSoftBg,
                        border = androidx.compose.foundation.BorderStroke(1.dp, EmeraldLight.copy(alpha = 0.3f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = EmeraldPrimary,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Result: ${t.result}",
                                color = EmeraldDark,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FaqSection(faqs: List<FaqItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "ANSWERS TO COMMON QUESTIONS",
            color = BrandGoldDark,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(
            text = "Frequently Asked Questions",
            color = TextDark,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        faqs.forEach { faq ->
            var isExpanded by remember { mutableStateOf(false) }

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
                    .clickable { isExpanded = !isExpanded }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = faq.question,
                            color = TextDark,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = EmeraldPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    AnimatedVisibility(visible = isExpanded) {
                        Column(modifier = Modifier.padding(top = 8.dp)) {
                            Text(
                                text = faq.answer,
                                color = TextMuted,
                                fontSize = 12.sp,
                                lineHeight = 17.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomCtaBanner(
    onRegister: () -> Unit,
    onOpenWelcomeKit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(EmeraldDark)
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Begin Your Inner Reset Today",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Join our upcoming live batch. Experience firsthand how somatic grounding and cognitive reframing dissolve overthinking.",
                color = Color(0xFFA7F3D0),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = onRegister,
                colors = ButtonDefaults.buttonColors(containerColor = BrandGold),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Register for 5-Day Challenge (100% Free)",
                    color = EmeraldDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onOpenWelcomeKit,
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Explore Complimentary Welcome Kit",
                    color = Color.White,
                    fontSize = 13.sp
                )
            }
        }
    }
}
