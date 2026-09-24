package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.JournalEntry
import com.example.data.model.StopStep
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun GuideScreen(
    stopSteps: List<StopStep>,
    journalEntries: List<JournalEntry>,
    onSaveJournal: (mood: String, stressBefore: Int, stressAfter: Int, reflection: String, gratitude: String, burden: String) -> Unit,
    onOpenBreathing: () -> Unit
) {
    var selectedMood by remember { mutableStateOf("Overwhelmed") }
    var stressBefore by remember { mutableFloatStateOf(7f) }
    var stressAfter by remember { mutableFloatStateOf(4f) }
    var reflection by remember { mutableStateOf("") }
    var gratitude by remember { mutableStateOf("") }
    var burden by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground)
            .padding(16.dp)
            .testTag("guide_screen")
    ) {
        // Header
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "TOOLKIT & THE S.T.O.P. TECHNIQUE",
                    color = BrandGoldDark,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Clinical Somatic Grounding",
                    color = TextDark,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "The core mental emergency protocol of Path to Inner Peace. Use this anytime anxiety, rumination, or emotional agitation overwhelms you.",
                    color = TextMuted,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // STOP Interactive Cards
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldDark),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "THE 4-STEP S.T.O.P. PROTOCOL",
                        color = BrandGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    stopSteps.forEach { step ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .background(BrandGold, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = step.letter,
                                    color = EmeraldDark,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = step.title,
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "• ${step.durationText}",
                                        color = Color(0xFFA7F3D0),
                                        fontSize = 10.sp
                                    )
                                }
                                Text(
                                    text = step.instruction,
                                    color = Color.White.copy(alpha = 0.85f),
                                    fontSize = 11.sp,
                                    lineHeight = 15.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onOpenBreathing,
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Air, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Practice Breathing Reset Now", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        // Daily Stress & MindReset Journal Form
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(16.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.EditNote, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Daily Mind Reset Journal Ledger",
                            color = TextDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("CURRENT MENTAL STATE / MOOD:", color = TextMuted, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))

                    val moods = listOf("Overwhelmed", "Anxious", "Neutral", "Centered", "Grateful")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        moods.forEach { mood ->
                            val isSelected = selectedMood == mood
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isSelected) EmeraldDark else EmeraldSoftBg)
                                    .clickable { selectedMood = mood }
                                    .padding(vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = mood,
                                    color = if (isSelected) BrandGold else EmeraldDark,
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text("STRESS LEVEL BEFORE RESET: ${stressBefore.toInt()}/10", color = TextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Slider(
                        value = stressBefore,
                        onValueChange = { stressBefore = it },
                        valueRange = 1f..10f,
                        steps = 8,
                        colors = SliderDefaults.colors(
                            thumbColor = BrandGold,
                            activeTrackColor = BrandGoldDark
                        )
                    )

                    Text("STRESS LEVEL AFTER RESET: ${stressAfter.toInt()}/10", color = TextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Slider(
                        value = stressAfter,
                        onValueChange = { stressAfter = it },
                        valueRange = 1f..10f,
                        steps = 8,
                        colors = SliderDefaults.colors(
                            thumbColor = EmeraldPrimary,
                            activeTrackColor = EmeraldPrimary
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = burden,
                        onValueChange = { burden = it },
                        label = { Text("What worry or emotional burden are you letting go of?", fontSize = 11.sp) },
                        minLines = 2,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EmeraldPrimary,
                            unfocusedBorderColor = BorderSubtle
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = gratitude,
                        onValueChange = { gratitude = it },
                        label = { Text("3 micro-blessings you are genuinely grateful for today", fontSize = 11.sp) },
                        minLines = 2,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EmeraldPrimary,
                            unfocusedBorderColor = BorderSubtle
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            onSaveJournal(
                                selectedMood,
                                stressBefore.toInt(),
                                stressAfter.toInt(),
                                reflection,
                                gratitude,
                                burden
                            )
                            isSubmitted = true
                            burden = ""
                            gratitude = ""
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldDark),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null, tint = BrandGold, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Save Entry to Local Journal", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    if (isSubmitted) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "✓ Journal entry securely saved to offline database!",
                            color = EmeraldPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        // Past Saved Journal Entries
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.History, contentDescription = null, tint = EmeraldDark, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "SAVED ENTRIES HISTORY (${journalEntries.size})",
                    color = TextDark,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        if (journalEntries.isEmpty()) {
            item {
                Text(
                    text = "No saved entries yet. Log your first stress reduction audit above!",
                    color = TextLight,
                    fontSize = 11.sp
                )
            }
        } else {
            items(journalEntries) { entry ->
                val dateStr = SimpleDateFormat("MMM dd, yyyy • hh:mm a", Locale.getDefault()).format(Date(entry.timestamp))
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .border(1.dp, BorderSubtle, RoundedCornerShape(10.dp))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(dateStr, color = TextMuted, fontSize = 10.sp)
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = EmeraldSoftBg
                            ) {
                                Text(
                                    text = entry.mood,
                                    color = EmeraldDark,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Stress Shift: ${entry.stressBefore}/10 → ${entry.stressAfter}/10",
                            color = BrandGoldDark,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )

                        if (entry.emotionalBurden.isNotBlank()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Released: \"${entry.emotionalBurden}\"",
                                color = TextDark,
                                fontSize = 11.sp
                            )
                        }

                        if (entry.gratitude.isNotBlank()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Gratitude: \"${entry.gratitude}\"",
                                color = EmeraldDark,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
