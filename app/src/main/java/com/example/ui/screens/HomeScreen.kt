package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.FaqItem
import com.example.data.model.TestimonialItem
import com.example.data.model.UserProgressEntity
import com.example.ui.theme.BorderEmeraldLight
import com.example.ui.theme.BorderLight
import com.example.ui.theme.BrandEmerald
import com.example.ui.theme.BrandGold
import com.example.ui.theme.BrandGoldDark
import com.example.ui.theme.CardCream
import com.example.ui.theme.CardWhite
import com.example.ui.theme.EmeraldCard
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.MintSoft
import com.example.ui.theme.MintText
import com.example.ui.theme.PageBackground
import com.example.ui.theme.TextEmerald900
import com.example.ui.theme.TextEmerald950
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextSlate
import com.example.ui.theme.TextSlateMuted
import com.example.ui.theme.TextWhite
import com.example.ui.viewmodel.AppTab
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    progress: UserProgressEntity?,
    testimonials: List<TestimonialItem>,
    faqs: List<FaqItem>,
    onNavigateTab: (AppTab) -> Unit,
    onOpenBreathing: () -> Unit,
    onOpenCoach: () -> Unit,
    onOpenRegistration: (batch: String?) -> Unit,
    onOpenWelcomeKit: () -> Unit,
    onOpenCertificate: () -> Unit,
    onSelectDay: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val isRegistered = progress?.isRegistered == true

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .background(PageBackground),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // 1. Top Header Bar matching www.pathtoinnerpeace.in
        item {
            HeaderBar(
                onOpenBreathing = onOpenBreathing,
                onOpenCoach = onOpenCoach
            )
        }

        // 2. Hero Section
        item {
            HeroSection(
                isRegistered = isRegistered,
                onStartChallenge = {
                    onSelectDay(1)
                    onNavigateTab(AppTab.DASHBOARD)
                },
                onOpenRegistration = onOpenRegistration,
                onOpenWelcomeKit = onOpenWelcomeKit,
                onOpenCareerAxis = {
                    onNavigateTab(AppTab.CAREER_AXIS)
                },
                onScrollToCurriculum = {
                    scope.launch {
                        // Scroll to Challenge Breakdown (item index 6)
                        listState.animateScrollToItem(6)
                    }
                }
            )
        }

        // 3. Trust Highlights (4 Badges)
        item {
            TrustHighlightsSection()
        }

        // 4. Social Proof & Statistics
        item {
            StatisticsSection()
        }

        // 5. Free Welcome Kit Highlight Section (Matching www.pathtoinnerpeace.in)
        item {
            WelcomeKitHighlightSection(
                onOpenWelcomeKit = onOpenWelcomeKit
            )
        }

        // 6. How It Works (4 Steps)
        item {
            HowItWorksSection(
                onRegisterClick = { onOpenRegistration(null) },
                onCertificateClick = onOpenCertificate
            )
        }

        // 7. The 5-Day Mind Reset Breakdown
        item {
            ChallengeBreakdownSection(
                isRegistered = isRegistered,
                onSelectDay = { day ->
                    onSelectDay(day)
                    onNavigateTab(AppTab.DASHBOARD)
                },
                onOpenRegistration = { onOpenRegistration(null) }
            )
        }

        // 8. Comprehensive Benefits
        item {
            BenefitsSection()
        }

        // 9. Testimonials Section
        item {
            TestimonialsSection(testimonials = testimonials)
        }

        // 10. Frequently Asked Questions
        item {
            FaqSection(faqs = faqs)
        }

        // 11. Bottom CTA Banner (Matching website's bottom CTA)
        item {
            BottomCtaBannerSection(
                isRegistered = isRegistered,
                onAction = {
                    if (isRegistered) {
                        onSelectDay(1)
                        onNavigateTab(AppTab.DASHBOARD)
                    } else {
                        onOpenRegistration(null)
                    }
                }
            )
        }
    }
}

@Composable
private fun HeaderBar(
    onOpenBreathing: () -> Unit,
    onOpenCoach: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(EmeraldDeep)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Official circular logo with gold border
                Image(
                    painter = painterResource(id = R.drawable.inner_peace_logo),
                    contentDescription = "Path to Inner Peace Logo",
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, BrandGold, CircleShape)
                )

                Column {
                    Text(
                        text = "Path to Inner Peace",
                        color = TextWhite,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.3.sp
                    )
                    Text(
                        text = "MindForge 360°™",
                        color = BrandGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Quick Calm Breathing Trigger
                IconButton(
                    onClick = onOpenBreathing,
                    modifier = Modifier
                        .size(36.dp)
                        .background(EmeraldDark, CircleShape)
                        .border(1.dp, BorderEmeraldLight, CircleShape)
                        .testTag("button_quick_breathing")
                ) {
                    Icon(
                        imageVector = Icons.Default.Air,
                        contentDescription = "Breathing Tool",
                        tint = BrandGold,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Coach Contact Trigger
                IconButton(
                    onClick = onOpenCoach,
                    modifier = Modifier
                        .size(36.dp)
                        .background(EmeraldDark, CircleShape)
                        .border(1.dp, BorderEmeraldLight, CircleShape)
                        .testTag("button_coach_contact")
                ) {
                    Icon(
                        imageVector = Icons.Default.SupportAgent,
                        contentDescription = "Coach Support",
                        tint = TextWhite,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroSection(
    isRegistered: Boolean,
    onStartChallenge: () -> Unit,
    onOpenRegistration: (String) -> Unit,
    onOpenWelcomeKit: () -> Unit,
    onOpenCareerAxis: () -> Unit,
    onScrollToCurriculum: () -> Unit
) {
    val context = LocalContext.current
    var selectedBatch by remember { mutableStateOf("6:30 AM") }
    val morningBatches = listOf("6:30 AM", "7:30 AM", "8:30 AM")
    val eveningBatches = listOf("5:00 PM", "6:00 PM", "7:00 PM")
    val liveVideoUrl = "https://www.youtube.com/live/u42RK5eV_c8?si=wg7ziJNLQNRu7hID"

    fun openYouTubeLive() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(liveVideoUrl))
            context.startActivity(intent)
        } catch (_: Exception) {
            Toast.makeText(context, "Opening YouTube Live...", Toast.LENGTH_SHORT).show()
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = EmeraldDark),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Visual Image Banner with Play Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable { openYouTubeLive() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hero_thumb),
                    contentDescription = "Serene Mind - 5 Day Mind Reset Challenge",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Dark gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, EmeraldDark.copy(alpha = 0.95f)),
                                startY = 80f
                            )
                        )
                )

                // Badge top left
                Box(
                    modifier = Modifier
                        .padding(14.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(EmeraldDeep.copy(alpha = 0.85f))
                        .border(1.dp, BrandGold.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "5-DAY MIND RESET CHALLENGE",
                        color = BrandGold,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }

                // Play icon center
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(BrandGold)
                        .clickable { openYouTubeLive() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Masterclass Video",
                        tint = EmeraldDeep,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            // Headline & Text
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Rewire Your Mind in ",
                        color = TextWhite,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Just 5 Days",
                        color = BrandGold,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Serif
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "A science-backed, 30-minute daily journey to silence negative chatter, reset your nervous system, and reclaim inner peace.",
                    color = Color(0xFFD1FAE5),
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Batch Time Slot Selector (Matching www.pathtoinnerpeace.in)
                Text(
                    text = "SELECT YOUR 30-MIN DAILY BATCH (IST):",
                    color = BrandGold,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                // Morning Slots
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    morningBatches.forEach { slot ->
                        val isSelected = selectedBatch == slot
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) BrandGold else EmeraldDeep)
                                .border(1.dp, if (isSelected) BrandGold else BorderEmeraldLight, RoundedCornerShape(8.dp))
                                .clickable { selectedBatch = slot }
                                .padding(vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🌤 $slot",
                                color = if (isSelected) EmeraldDeep else TextWhite,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Evening Slots
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    eveningBatches.forEach { slot ->
                        val isSelected = selectedBatch == slot
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) BrandGold else EmeraldDeep)
                                .border(1.dp, if (isSelected) BrandGold else BorderEmeraldLight, RoundedCornerShape(8.dp))
                                .clickable { selectedBatch = slot }
                                .padding(vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🌙 $slot",
                                color = if (isSelected) EmeraldDeep else TextWhite,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // CTA Primary Button
                Button(
                    onClick = {
                        if (isRegistered) {
                            onStartChallenge()
                        } else {
                            onOpenRegistration(selectedBatch)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("btn_join_challenge"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandGold,
                        contentColor = EmeraldDeep
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = if (isRegistered) "Go to My Dashboard (Day 1) →" else "Click to Join Free Challenge",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Secondary Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onScrollToCurriculum,
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.horizontalGradient(listOf(BrandGold, BrandEmerald))),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = BrandGold)
                    ) {
                        Text(
                            text = "Curriculum Breakdown",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    OutlinedButton(
                        onClick = onOpenCareerAxis,
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.horizontalGradient(listOf(BrandEmerald, BorderEmeraldLight))),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFA7F3D0))
                    ) {
                        Text(
                            text = "Career Axis Calm",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Hero Right Card (Matching website's right card)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldDeep),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BrandGold.copy(alpha = 0.4f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Reserve Your Free Spot",
                                color = BrandGold,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(BrandGold)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "100% FREE",
                                    color = EmeraldDeep,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        DetailRow("Coach:", "Mainak Chatterjee (MindForge 360°™)")
                        DetailRowClickable("Platform:", "Youtube Live (Daily 30-min guided resets)") {
                            openYouTubeLive()
                        }
                        DetailRow("Format:", "Daily 30-Min Guided Reset")
                        DetailRowClickable("Includes:", "Exclusive pack of free Welcome Kit ↗") {
                            onOpenWelcomeKit()
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { onOpenRegistration(selectedBatch) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp)
                                .testTag("btn_join_now_hero_card"),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BrandGold,
                                contentColor = EmeraldDeep
                            )
                        ) {
                            Text(
                                text = "Join Now →",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color(0xFFA7F3D0), fontSize = 11.sp)
        Text(text = value, color = TextWhite, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun DetailRowClickable(label: String, value: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color(0xFFA7F3D0), fontSize = 11.sp)
        Text(text = value, color = BrandGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun TrustHighlightsSection() {
    val items = listOf(
        "30-Min Sessions",
        "Zero Prior Experience",
        "Actionable & Practical",
        "Lasting Resilience"
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardWhite)
                    .border(1.dp, BorderEmeraldLight, RoundedCornerShape(12.dp))
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = BrandEmerald,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = item,
                        color = TextEmerald950,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun StatisticsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BorderLight, BorderEmeraldLight)))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StatItem(number = "4.9 ★", label = "Average Rating")
            StatItem(number = "5,000+", label = "Lives Impacted")
            StatItem(number = "850+", label = "Verified Reviews")
            StatItem(number = "100%", label = "Free 5-Day Access")
        }
    }
}

@Composable
private fun StatItem(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            color = BrandEmerald,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            color = TextSlate,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

// 5. Free Welcome Kit Highlight Section (Exact Match to www.pathtoinnerpeace.in)
@Composable
private fun WelcomeKitHighlightSection(
    onOpenWelcomeKit: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = EmeraldDeep),
        border = androidx.compose.foundation.BorderStroke(1.dp, BrandGold.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(BrandGold.copy(alpha = 0.2f))
                        .border(1.dp, BrandGold.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "FREE TRANSFORMATION ASSETS",
                        color = BrandGold,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(BrandGold)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "INCLUDED FREE",
                        color = EmeraldDeep,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Exclusive Pack of Free Welcome Kit",
                color = TextWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Explore 5 comprehensive transformation assets designed to kickstart your journey toward lasting inner peace.",
                color = Color(0xFFA7F3D0),
                fontSize = 12.sp,
                lineHeight = 17.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)
            )

            // 5 Transformation Assets previews
            val assetsList = listOf(
                Pair(Icons.Default.Assessment, "1. Mental Fitness Assessment (Interactive diagnostic)"),
                Pair(Icons.Default.Description, "2. Personalised Mind Report (Targeted action blueprint)"),
                Pair(Icons.Default.GraphicEq, "3. 5-Minute Stress Reset Audio (Emergency downshift)"),
                Pair(Icons.Default.Bedtime, "4. Better Sleep Blueprint (Circadian harmony & soundscape)"),
                Pair(Icons.Default.MenuBook, "5. Mental Reset Starter Guide (Cognitive tools handbook)")
            )

            assetsList.forEach { (icon, label) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = BrandGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = label,
                        color = TextWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onOpenWelcomeKit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("button_launch_welcome_kit_home"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrandGold,
                    contentColor = EmeraldDeep
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Launch Free Welcome Kit →",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun HowItWorksSection(
    onRegisterClick: () -> Unit,
    onCertificateClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = EmeraldDeep)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "HOW IT WORKS",
                color = BrandGold,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "How The 5-Day Mind Reset Works",
                color = TextWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "A frictionless, step-by-step transformation path designed for your busy routine.",
                color = Color(0xFFA7F3D0),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )

            val steps = listOf(
                Triple("Step 1", "Register Free: Sign up in 30 seconds with your WhatsApp number & email.", true),
                Triple("Step 2", "Receive Confirmation: Instant welcome & daily challenge reminder alerts straight to your phone.", false),
                Triple("Step 3", "Daily 30-Min Practice: Breathwork, cognitive reframing & guided meditation.", false),
                Triple("Step 4", "Earn Your Certificate: Complete all 5 days to unlock your verifiable digital credential.", true)
            )

            steps.forEachIndexed { index, (step, desc, isActionable) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = isActionable) {
                            if (index == 0) onRegisterClick()
                            if (index == 3) onCertificateClick()
                        }
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(BrandEmerald)
                            .border(1.dp, BrandGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${index + 1}",
                            color = BrandGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = step,
                                color = BrandGold,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (isActionable) {
                                Text(
                                    text = "• Tap to View",
                                    color = Color(0xFFA7F3D0),
                                    fontSize = 10.sp
                                )
                            }
                        }
                        Text(
                            text = desc,
                            color = Color(0xFFE2E8F0),
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChallengeBreakdownSection(
    isRegistered: Boolean,
    onSelectDay: (Int) -> Unit,
    onOpenRegistration: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "DAILY BLUEPRINT",
            color = BrandEmerald,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(
            text = "The 5-Day Challenge Breakdown",
            color = TextEmerald950,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 2.dp, bottom = 4.dp)
        )
        Text(
            text = "30 minutes each day carefully engineered to rewire your nervous system.",
            color = TextSlate,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        val days = listOf(
            Triple(1, "Day 1 • Mental Detox", "Negative Thought Awareness & Brain De-Cluttering"),
            Triple(2, "Day 2 • Stress Reset", "Nervous System Calming & Cortisol Reduction"),
            Triple(3, "Day 3 • Emotional Healing", "Forgiveness, Releasing Hurt & Self-Compassion"),
            Triple(4, "Day 4 • Confidence Reset", "Overcoming Imposter Syndrome & Identity Shift"),
            Triple(5, "Day 5 • Subconscious Mastery", "Neural Conditioning & Lifelong Mastery")
        )

        days.forEach { (dayNum, title, subtitle) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable {
                        if (isRegistered) {
                            onSelectDay(dayNum)
                        } else {
                            onOpenRegistration()
                        }
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BorderLight, BorderEmeraldLight)))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            color = BrandEmerald,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = subtitle,
                            color = TextSlate,
                            fontSize = 12.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isRegistered) MintSoft else BrandGold.copy(alpha = 0.2f))
                            .border(1.dp, if (isRegistered) BorderEmeraldLight else BrandGold, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (isRegistered) "Start Day $dayNum →" else "Join Free →",
                            color = if (isRegistered) MintText else BrandGoldDark,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BenefitsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BorderLight, BorderEmeraldLight)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "MEASURABLE OUTCOMES",
                color = BrandEmerald,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "What You Will Experience in 5 Days",
                color = TextEmerald950,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Our participants report deep emotional and mental shifts after just 120 hours.",
                color = TextSlate,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)
            )

            val benefits = listOf(
                "Quiet relentless mental chatter & overthinking in 90 seconds",
                "Lower cortisol & activate parasympathetic recovery instantly",
                "Break out of imposter syndrome & chronic career doubt",
                "Reclaim restful delta sleep without sleeping aids",
                "Permanent verifiable MindForge 360°™ completion certificate"
            )

            benefits.forEach { benefit ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = BrandEmerald,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = benefit,
                        color = TextEmerald950,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun TestimonialsSection(testimonials: List<TestimonialItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
            Text(
                text = "COMMUNITY VOICES",
                color = BrandEmerald,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Verified Seeker Testimonials",
                color = TextEmerald950,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Real transformations from our Antardarshan & community members.",
                color = TextSlate,
                fontSize = 12.sp
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(testimonials) { item ->
                Card(
                    modifier = Modifier
                        .width(280.dp)
                        .height(230.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardWhite),
                    border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(BorderLight, BorderEmeraldLight))),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            // Star rating
                            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                repeat(5) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = BrandGold,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "“${item.quote}”",
                                color = TextEmerald950,
                                fontSize = 13.sp,
                                fontStyle = FontStyle.Italic,
                                lineHeight = 18.sp
                            )
                        }

                        // Avatar & Name
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = item.avatarRes),
                                contentDescription = item.name,
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .border(1.5.dp, BrandGold, CircleShape),
                                contentScale = ContentScale.Crop
                            )

                            Column {
                                Text(
                                    text = item.name,
                                    color = TextEmerald950,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${item.role} • ${item.location}",
                                    color = TextSlate,
                                    fontSize = 11.sp
                                )
                            }
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
            text = "GOT QUESTIONS?",
            color = BrandEmerald,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(
            text = "Frequently Asked Questions",
            color = TextEmerald950,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
        )

        faqs.forEach { faq ->
            FaqCard(faq = faq)
        }
    }
}

@Composable
private fun FaqCard(faq: FaqItem) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BorderLight, BorderEmeraldLight)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = faq.question,
                    color = TextEmerald950,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = BrandEmerald
                )
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Text(
                    text = faq.answer,
                    color = TextSlate,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}

// 11. Bottom CTA Banner (Matching website's bottom CTA)
@Composable
private fun BottomCtaBannerSection(
    isRegistered: Boolean,
    onAction: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = EmeraldDeep),
        border = androidx.compose.foundation.BorderStroke(1.dp, BrandGold.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(BrandGold.copy(alpha = 0.2f))
                    .border(1.dp, BrandGold.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "★ START YOUR JOURNEY TODAY",
                    color = BrandGold,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Ready to Rewire Your Mind in 5 Days?",
                color = TextWhite,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Join 1,000+ seekers in the 5 Day Mind Reset Challenge and experience true inner calm.",
                color = Color(0xFFA7F3D0),
                fontSize = 12.sp,
                lineHeight = 17.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onAction,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("button_bottom_challenge_cta"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrandGold,
                    contentColor = EmeraldDeep
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = if (isRegistered) "Continue My Dashboard →" else "5 Day Mind Reset Challenge →",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "MindForge 360°™ Certification • 100% Free • Beginner Friendly",
                color = Color(0xFFD1FAE5),
                fontSize = 10.sp
            )
        }
    }
}
