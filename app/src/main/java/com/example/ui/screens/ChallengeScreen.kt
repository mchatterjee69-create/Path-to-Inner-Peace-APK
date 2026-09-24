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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ChallengeDay
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

@Composable
fun ChallengeScreen(
    days: List<ChallengeDay>,
    selectedDayNumber: Int,
    progress: UserProgress?,
    onSelectDay: (Int) -> Unit,
    onToggleTask: (Int, Int, Boolean) -> Unit,
    onSaveReflection: (Int, String) -> Unit,
    onOpenBreathing: () -> Unit,
    onOpenCertificate: () -> Unit
) {
    val activeDay = days.find { it.dayNumber == selectedDayNumber } ?: days.firstOrNull()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground)
            .padding(16.dp)
            .testTag("challenge_screen")
    ) {
        // Header
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "5-DAY MIND RESET",
                            color = BrandGoldDark,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Daily Transformation",
                            color = TextDark,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    Button(
                        onClick = onOpenCertificate,
                        colors = ButtonDefaults.buttonColors(containerColor = BrandGold),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = EmeraldDark, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Certificate", color = EmeraldDark, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Day Selector Tabs (Day 1 to Day 5)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                (1..5).forEach { dayNum ->
                    val isSelected = dayNum == selectedDayNumber
                    val dayData = days.find { it.dayNumber == dayNum }
                    val isCompleted = dayData?.completedTasks?.all { it } == true && dayData.completedTasks.isNotEmpty()

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) EmeraldDark else CardSurface)
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) BrandGold else BorderSubtle,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { onSelectDay(dayNum) }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "DAY $dayNum",
                                color = if (isSelected) BrandGold else TextMuted,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            if (isCompleted) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = EmeraldLight,
                                    modifier = Modifier.size(14.dp)
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .background(if (isSelected) BrandGold else BorderSubtle, CircleShape)
                                )
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Active Day Content
        if (activeDay != null) {
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldDark),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(BrandGold.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "DAY ${activeDay.dayNumber} OF 5",
                                    color = BrandGold,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Headphones, contentDescription = null, tint = Color(0xFFA7F3D0), modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = activeDay.audioDuration,
                                    color = Color(0xFFA7F3D0),
                                    fontSize = 11.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = activeDay.title,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = activeDay.subtitle,
                            color = BrandGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = activeDay.description,
                            color = Color(0xFFA7F3D0),
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Somatic Breathing Quick Trigger
                        Button(
                            onClick = onOpenBreathing,
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.Air, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Launch Guided Somatic Breath Reset", color = Color.White, fontSize = 13.sp)
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Micro-action Pill Card
            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldSoftBg),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, EmeraldLight.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "TODAY'S MICRO-ACTION CHALLENGE",
                            color = EmeraldDark,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = activeDay.microAction,
                            color = TextDark,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Action Checklist
            item {
                Text(
                    text = "ACTION CHECKLIST:",
                    color = TextMuted,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            item { Spacer(modifier = Modifier.height(6.dp)) }

            items(activeDay.tasks.size) { index ->
                val task = activeDay.tasks[index]
                val isChecked = if (index < activeDay.completedTasks.size) activeDay.completedTasks[index] else false

                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                        .border(1.dp, BorderSubtle, RoundedCornerShape(10.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { checked ->
                                onToggleTask(activeDay.dayNumber, index, checked)
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = EmeraldPrimary,
                                uncheckedColor = TextLight
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = task,
                            color = if (isChecked) TextMuted else TextDark,
                            fontSize = 13.sp,
                            fontWeight = if (isChecked) FontWeight.Normal else FontWeight.Medium
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Daily Reflection Box
            item {
                var reflectionText by remember(activeDay.dayNumber) {
                    mutableStateOf(activeDay.reflectionNotes)
                }
                var isSaved by remember { mutableStateOf(false) }

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Daily Reflection & Breakthrough Log",
                            color = TextDark,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "What was your main insight from today's exercise?",
                            color = TextMuted,
                            fontSize = 11.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = reflectionText,
                            onValueChange = {
                                reflectionText = it
                                isSaved = false
                            },
                            placeholder = { Text("Write your reflections here...", color = TextLight, fontSize = 12.sp) },
                            minLines = 3,
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = EmeraldPrimary,
                                unfocusedBorderColor = BorderSubtle
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isSaved) {
                                Text("Saved to your journal!", color = EmeraldPrimary, fontSize = 11.sp)
                            } else {
                                Spacer(modifier = Modifier.width(1.dp))
                            }

                            Button(
                                onClick = {
                                    onSaveReflection(activeDay.dayNumber, reflectionText)
                                    isSaved = true
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldDark),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Icon(Icons.Default.Save, contentDescription = null, tint = BrandGold, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Save Note", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
