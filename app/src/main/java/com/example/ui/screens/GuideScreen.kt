package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.JournalEntryEntity
import com.example.data.model.StopTechniqueStep
import com.example.ui.theme.BorderEmeraldLight
import com.example.ui.theme.BorderLight
import com.example.ui.theme.BrandEmerald
import com.example.ui.theme.BrandGold
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
import kotlin.math.roundToInt

@Composable
fun GuideScreen(
    stopSteps: List<StopTechniqueStep>,
    journalEntries: List<JournalEntryEntity>,
    onSaveJournal: (String, Int, Int, String, String, String) -> Unit,
    onOpenBreathing: () -> Unit,
    modifier: Modifier = Modifier
) {
    var activeStopStep by remember { mutableIntStateOf(0) }
    var selectedMood by remember { mutableStateOf("Peaceful") }
    var stressBefore by remember { mutableFloatStateOf(6f) }
    var stressAfter by remember { mutableFloatStateOf(2f) }
    var reflectionText by remember { mutableStateOf("") }
    var gratitudeText by remember { mutableStateOf("") }
    var releasedBurdenText by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    val moods = listOf("Peaceful", "Grounded", "Anxious", "Fatigued", "Empowered", "Reflective")

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
                        text = "INNER PEACE GUIDE & CBT TOOLS",
                        color = BrandGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "5 Techniques & Somatic Check-in",
                        color = TextWhite,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // 1. S-T-O-P Technique Interactive Tool
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
                        Text(
                            text = "EMERGENCY GROUNDING PROTOCOL",
                            color = BrandGold,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(MintSoft)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "90-SEC RESET",
                                color = MintText,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "The S-T-O-P Technique",
                        color = TextWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "A 4-step metacognitive tool to intercept fight-or-flight reactions instantly.",
                        color = Color(0xFFA7F3D0),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                    )

                    // Step Pills S - T - O - P
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        stopSteps.forEachIndexed { index, step ->
                            val isCurrent = index == activeStopStep
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (isCurrent) BrandGold else EmeraldDeep)
                                    .clickable { activeStopStep = index }
                                    .padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = step.letter,
                                    color = if (isCurrent) EmeraldDeep else BrandGold,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    val cur = stopSteps.getOrNull(activeStopStep)
                    if (cur != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(EmeraldDeep)
                                .border(1.dp, BorderEmeraldLight, RoundedCornerShape(12.dp))
                                .padding(14.dp)
                        ) {
                            Column {
                                Text(
                                    text = "${cur.letter} — ${cur.title}",
                                    color = BrandGold,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = cur.guidance,
                                    color = TextWhite,
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onOpenBreathing,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandGold,
                            contentColor = EmeraldDeep
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Air,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Launch 4-4-4-4 Box Breathing Tool",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // 2. Cognitive Behavioral Therapy (CBT) Thought Record Form
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BorderLight, BorderEmeraldLight)))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "SOMATIC & EMOTIONAL CHECK-IN",
                        color = BrandEmerald,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Daily Stress Reduction & Reflection",
                        color = TextEmerald950,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                    )

                    // Mood selector
                    Text(
                        text = "Current State of Mind:",
                        color = TextSlate,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(moods) { mood ->
                            val isSel = mood == selectedMood
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(if (isSel) BrandEmerald else CardCream)
                                    .border(
                                        1.dp,
                                        if (isSel) BrandGold else BorderLight,
                                        RoundedCornerShape(20.dp)
                                    )
                                    .clickable { selectedMood = mood }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = mood,
                                    color = if (isSel) CardWhite else TextEmerald950,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Stress Sliders
                    Text(
                        text = "Stress Level Before Practice: ${stressBefore.roundToInt()} / 10",
                        color = TextEmerald950,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Slider(
                        value = stressBefore,
                        onValueChange = { stressBefore = it },
                        valueRange = 1f..10f,
                        steps = 8,
                        colors = SliderDefaults.colors(
                            thumbColor = BrandEmerald,
                            activeTrackColor = BrandEmerald
                        )
                    )

                    Text(
                        text = "Stress Level After Practice: ${stressAfter.roundToInt()} / 10",
                        color = TextEmerald950,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Slider(
                        value = stressAfter,
                        onValueChange = { stressAfter = it },
                        valueRange = 1f..10f,
                        steps = 8,
                        colors = SliderDefaults.colors(
                            thumbColor = BrandGold,
                            activeTrackColor = BrandGold
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Text fields
                    OutlinedTextField(
                        value = reflectionText,
                        onValueChange = { reflectionText = it },
                        label = { Text("What did you observe in your mind today?", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BrandEmerald,
                            unfocusedBorderColor = BorderLight
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = gratitudeText,
                        onValueChange = { gratitudeText = it },
                        label = { Text("3 things you are grateful for right now:", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BrandEmerald,
                            unfocusedBorderColor = BorderLight
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = releasedBurdenText,
                        onValueChange = { releasedBurdenText = it },
                        label = { Text("What burden or grudge are you letting go of?", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BrandEmerald,
                            unfocusedBorderColor = BorderLight
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            onSaveJournal(
                                selectedMood,
                                stressBefore.roundToInt(),
                                stressAfter.roundToInt(),
                                reflectionText,
                                gratitudeText,
                                releasedBurdenText
                            )
                            isSubmitted = true
                            reflectionText = ""
                            gratitudeText = ""
                            releasedBurdenText = ""
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandEmerald,
                            contentColor = CardWhite
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = if (isSubmitted) Icons.Default.Check else Icons.Default.Save,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = if (isSubmitted) "Saved to Offline Journal" else "Save Journal Entry",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // 3. Saved Journal History
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = null,
                        tint = BrandEmerald,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Saved Offline Journal Entries",
                        color = TextEmerald950,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        items(journalEntries) { entry ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
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
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = entry.dateString,
                            color = BrandEmerald,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Stress: ${entry.stressBefore} → ${entry.stressAfter} / 10",
                            color = BrandGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (entry.reflection.isNotBlank()) {
                        Text(
                            text = entry.reflection,
                            color = TextEmerald950,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    if (entry.gratitude.isNotBlank()) {
                        Text(
                            text = "Grateful for: ${entry.gratitude}",
                            color = TextSlate,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    if (entry.releasedBurden.isNotBlank()) {
                        Text(
                            text = "Released: ${entry.releasedBurden}",
                            color = Color(0xFFEF4444),
                            fontSize = 11.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
