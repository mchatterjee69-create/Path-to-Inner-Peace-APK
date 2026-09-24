package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MembershipPlan
import com.example.data.model.Pillar
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.BrandGold
import com.example.ui.theme.BrandGoldDark
import com.example.ui.theme.CardSurface
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldMedium
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoftBg
import com.example.ui.theme.PageBackground
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextLight
import com.example.ui.theme.TextMuted

@Composable
fun InnerShiftScreen(
    plans: List<MembershipPlan>,
    pillars: List<Pillar>,
    activeSound: String,
    isPlayingSound: Boolean,
    onToggleSound: () -> Unit,
    onSelectSound: (String) -> Unit,
    onOpenCoach: () -> Unit,
    onSelectPlan: (MembershipPlan) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground)
            .padding(16.dp)
            .testTag("inner_shift_screen")
    ) {
        // Header
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "THE INNER SHIFT CIRCLE",
                    color = BrandGoldDark,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Cognitive Mastery & Community",
                    color = TextDark,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Go beyond the 5-Day Challenge. Join our continuous mentorship circle for sustained psychological resilience, live coaching calls, and career clarity.",
                    color = TextMuted,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            }
        }

        item { Spacer(modifier = Modifier.height(18.dp)) }

        // MindForge 360 4 Core Pillars
        item {
            Text(
                text = "MINDFORGE 360°™ ARCHITECTURE:",
                color = TextDark,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        items(pillars.size) { index ->
            val p = pillars[index]
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(EmeraldSoftBg, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        val icon = when (p.iconName) {
                            "Air" -> Icons.Default.Air
                            "Psychology" -> Icons.Default.Psychology
                            "Work" -> Icons.Default.Work
                            else -> Icons.Default.SelfImprovement
                        }
                        Icon(imageVector = icon, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(20.dp))
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = p.title,
                            color = TextDark,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = p.subtitle,
                            color = BrandGoldDark,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = p.description,
                            color = TextMuted,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        // Ambient Sound Bar
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldDark),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.GraphicEq, contentDescription = null, tint = BrandGold, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "THETA BRAINWAVE GENERATOR",
                                    color = BrandGold,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = activeSound,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        IconButton(
                            onClick = onToggleSound,
                            modifier = Modifier
                                .size(40.dp)
                                .background(EmeraldPrimary, CircleShape)
                        ) {
                            Icon(
                                imageVector = if (isPlayingSound) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    val soundOptions = listOf(
                        "Gentle Rain with Theta Binaural Tones (6Hz)",
                        "Forest Canopy Deep Meditation (7Hz)",
                        "Tibetan Singing Bowl Coherence"
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        soundOptions.forEachIndexed { idx, sName ->
                            val isSelected = activeSound == sName
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isSelected) EmeraldPrimary else EmeraldMedium)
                                    .clickable { onSelectSound(sName) }
                                    .padding(vertical = 6.dp, horizontal = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Preset ${idx + 1}",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        // Membership Plans
        item {
            Text(
                text = "CHOOSE YOUR MEMBERSHIP CIRCLE:",
                color = TextDark,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        items(plans.size) { index ->
            val plan = plans[index]
            MembershipPlanCard(
                plan = plan,
                onEnroll = { onSelectPlan(plan) }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun MembershipPlanCard(
    plan: MembershipPlan,
    onEnroll: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (plan.isPopular) EmeraldDark else CardSurface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (plan.isPopular) 2.dp else 1.dp,
                color = if (plan.isPopular) BrandGold else BorderSubtle,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            plan.badge?.let { badge ->
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = BrandGold.copy(alpha = 0.2f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BrandGold),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = badge.uppercase(),
                        color = BrandGold,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Text(
                text = plan.name,
                color = if (plan.isPopular) Color.White else TextDark,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = plan.price,
                    color = if (plan.isPopular) BrandGold else EmeraldDark,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = plan.originalPrice,
                    color = if (plan.isPopular) Color.White.copy(alpha = 0.5f) else TextLight,
                    fontSize = 13.sp,
                    textDecoration = TextDecoration.LineThrough
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "• ${plan.period}",
                    color = if (plan.isPopular) Color(0xFFA7F3D0) else TextMuted,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = plan.description,
                color = if (plan.isPopular) Color.White.copy(alpha = 0.85f) else TextMuted,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                plan.features.forEach { f ->
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = if (plan.isPopular) BrandGold else EmeraldPrimary,
                            modifier = Modifier
                                .size(14.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = f,
                            color = if (plan.isPopular) Color.White else TextDark,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onEnroll,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (plan.isPopular) BrandGold else EmeraldPrimary
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (plan.price == "Custom") "Inquire Institutional Tier" else "Select & Enroll via Razorpay",
                    color = if (plan.isPopular) EmeraldDark else Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}
