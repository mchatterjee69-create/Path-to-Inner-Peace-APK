package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MembershipPlan
import com.example.data.model.PillarItem
import com.example.ui.theme.BorderEmeraldLight
import com.example.ui.theme.BorderLight
import com.example.ui.theme.BrandEmerald
import com.example.ui.theme.BrandGold
import com.example.ui.theme.BrandGoldDark
import com.example.ui.theme.CardCream
import com.example.ui.theme.CardWhite
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.MintSoft
import com.example.ui.theme.MintText
import com.example.ui.theme.PageBackground
import com.example.ui.theme.TextEmerald900
import com.example.ui.theme.TextEmerald950
import com.example.ui.theme.TextSlate
import com.example.ui.theme.TextWhite

@Composable
fun InnerShiftScreen(
    plans: List<MembershipPlan>,
    pillars: List<PillarItem>,
    activeSound: String,
    isPlayingSound: Boolean,
    onToggleSound: () -> Unit,
    onSelectSound: (String) -> Unit,
    onOpenCoach: () -> Unit,
    onSelectPlan: (MembershipPlan) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val soundTracks = listOf(
        Pair("Gentle Rain with Theta Binaural Tones (6Hz)", "Cognitive Clearance & Mental Detox"),
        Pair("Deep Ocean Waves with Solfeggio 528Hz", "Vagus Nerve Reset & Cortisol Lowering"),
        Pair("Pine Forest Solitude with 639Hz Heart Harmony", "Heart Center & Emotional Forgiveness"),
        Pair("Morning Birdsong with 741Hz Awakening", "Radiant Self-Confidence & Inner Worth"),
        Pair("Sunrise Meadow with 852Hz Sacred Triad", "Subconscious Reprogramming & Mastery")
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(PageBackground),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(EmeraldDeep)
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Column {
                    Text(
                        text = "INNER SHIFT & SOUND THERAPY",
                        color = BrandGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Build Your Mental Fitness Foundation",
                        color = TextWhite,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Ambient Sound Therapy Player
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldDark)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Headphones,
                                contentDescription = null,
                                tint = BrandGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "SONIC THERAPY PLAYER",
                                color = BrandGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }

                        if (isPlayingSound) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MintSoft)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "PLAYING",
                                    color = MintText,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = activeSound,
                        color = TextWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onToggleSound,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandGold,
                            contentColor = EmeraldDeep
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlayingSound) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = if (isPlayingSound) "Pause Soundscape" else "Play Soundscape",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Select Frequencies:",
                        color = Color(0xFFA7F3D0),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )

                    soundTracks.forEach { (title, desc) ->
                        val isCurrent = title == activeSound
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isCurrent) EmeraldDeep else Color.Transparent)
                                .clickable { onSelectSound(title) }
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = title,
                                    color = if (isCurrent) BrandGold else TextWhite,
                                    fontSize = 12.sp,
                                    fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal
                                )
                                Text(
                                    text = desc,
                                    color = Color(0xFF94A3B8),
                                    fontSize = 10.sp
                                )
                            }
                            if (isCurrent && isPlayingSound) {
                                Icon(
                                    imageVector = Icons.Default.GraphicEq,
                                    contentDescription = null,
                                    tint = BrandGold,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Membership Tiers from www.pathtoinnerpeace.in
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "MEMBERSHIP PLANS",
                    color = BrandEmerald,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Choose Your Transformation Tier",
                    color = TextEmerald950,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 2.dp, bottom = 4.dp)
                )
                Text(
                    text = "From foundational daily mental fitness to comprehensive 1:1 VIP mentorship.",
                    color = TextSlate,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }

        items(plans) { plan ->
            MembershipCard(
                plan = plan,
                onSelectPlan = { onSelectPlan(plan) }
            )
        }

        // 10 Pillars Architecture
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "MINDFORGE 360°™ ARCHITECTURE",
                    color = BrandEmerald,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "The 10 Transformation Pillars",
                    color = TextEmerald950,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 2.dp, bottom = 4.dp)
                )
                Text(
                    text = "Evidence-informed cognitive, somatic, and neural rewiring modules.",
                    color = TextSlate,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }

        items(pillars) { pillar ->
            PillarCard(pillar = pillar)
        }
    }
}

@Composable
private fun MembershipCard(
    plan: MembershipPlan,
    onSelectPlan: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (plan.popular) EmeraldDeep else CardWhite
        ),
        border = if (plan.popular) {
            CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BrandGold, BrandGoldDark)))
        } else {
            CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BorderLight, BorderEmeraldLight)))
        },
        elevation = CardDefaults.cardElevation(defaultElevation = if (plan.popular) 4.dp else 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (plan.popular) BrandGold else MintSoft)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = plan.badge,
                        color = if (plan.popular) EmeraldDeep else MintText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = plan.period,
                    color = if (plan.popular) Color(0xFFA7F3D0) else TextSlate,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = plan.name,
                color = if (plan.popular) TextWhite else TextEmerald950,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = plan.tagline,
                color = if (plan.popular) BrandGold else BrandEmerald,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "₹${plan.priceINR}",
                    color = if (plan.popular) BrandGold else TextEmerald950,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " / month",
                    color = if (plan.popular) Color(0xFFCBD5E1) else TextSlate,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            plan.features.take(6).forEach { feat ->
                Row(
                    modifier = Modifier.padding(vertical = 2.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = if (plan.popular) BrandGold else BrandEmerald,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = feat,
                        color = if (plan.popular) Color(0xFFF1F5F9) else TextEmerald950,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onSelectPlan,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (plan.popular) BrandGold else BrandEmerald,
                    contentColor = if (plan.popular) EmeraldDeep else CardWhite
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = plan.buttonText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun PillarCard(pillar: PillarItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BorderLight, BorderEmeraldLight)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${pillar.number} • ${pillar.title}",
                    color = BrandEmerald,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(MintSoft)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = pillar.focus,
                        color = MintText,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = pillar.summary,
                color = TextSlate,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }
    }
}
