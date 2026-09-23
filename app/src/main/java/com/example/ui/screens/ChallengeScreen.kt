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
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ChallengeDayEntity
import com.example.data.model.UserProgressEntity
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
fun ChallengeScreen(
    days: List<ChallengeDayEntity>,
    selectedDayNumber: Int,
    progress: UserProgressEntity?,
    onSelectDay: (Int) -> Unit,
    onToggleTask: (ChallengeDayEntity, Int, Boolean) -> Unit,
    onSaveReflection: (Int, String) -> Unit,
    onOpenBreathing: () -> Unit,
    onOpenCertificate: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentDay = days.find { it.dayNumber == selectedDayNumber } ?: days.firstOrNull()

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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "5-DAY MIND RESET",
                            color = BrandGold,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Daily Neuroscience Curriculum",
                            color = TextWhite,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = onOpenCertificate,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandGold,
                            contentColor = EmeraldDeep
                        ),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CardMembership,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Certificate",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Day Selector Pills
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(days) { day ->
                    val isSelected = day.dayNumber == selectedDayNumber
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) BrandEmerald else CardWhite)
                            .border(
                                1.dp,
                                if (isSelected) BrandGold else BorderLight,
                                RoundedCornerShape(12.dp)
                            )
                            .clickable { onSelectDay(day.dayNumber) }
                            .padding(horizontal = 14.dp, vertical = 10.dp)
                            .testTag("day_selector_${day.dayNumber}")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            if (day.isCompleted) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = if (isSelected) BrandGold else BrandEmerald,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                            Text(
                                text = "Day ${day.dayNumber}",
                                color = if (isSelected) TextWhite else TextEmerald950,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        if (currentDay != null) {
            // Day Hero Card
            item {
                DayHeroCard(day = currentDay, onOpenBreathing = onOpenBreathing)
            }

            // Audio Meditation Track
            item {
                AudioTrackCard(day = currentDay)
            }

            // Lesson Core Principle
            item {
                LessonCard(day = currentDay)
            }

            // 3 Tasks Checklist
            item {
                TasksChecklistCard(
                    day = currentDay,
                    onToggleTask = onToggleTask,
                    onOpenBreathing = onOpenBreathing
                )
            }

            // Sovereign Affirmation Card
            item {
                AffirmationCard(affirmation = currentDay.affirmation)
            }

            // Journal Prompt & Notes
            item {
                DayReflectionCard(
                    day = currentDay,
                    onSave = { note -> onSaveReflection(currentDay.dayNumber, note) }
                )
            }
        }
    }
}

@Composable
private fun DayHeroCard(
    day: ChallengeDayEntity,
    onOpenBreathing: () -> Unit
) {
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
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(EmeraldDeep)
                        .border(1.dp, BrandGold.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "DAY ${day.dayNumber} OF 5",
                        color = BrandGold,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MintSoft)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${day.durationMinutes} Min Practice",
                        color = MintText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = day.title,
                color = TextWhite,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = day.subtitle,
                color = BrandGold,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 2.dp, bottom = 10.dp)
            )

            Text(
                text = day.summary,
                color = Color(0xFFD1FAE5),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = onOpenBreathing,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandGold,
                        contentColor = EmeraldDeep
                    )
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
                            text = "${day.breathingGoalMinutes}m Box Breathing",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AudioTrackCard(day: ChallengeDayEntity) {
    var isPlaying by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(BrandEmerald)
                            .clickable { isPlaying = !isPlaying },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Play Audio Track",
                            tint = BrandGold,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column {
                        Text(
                            text = day.meditationTrackTitle,
                            color = TextEmerald950,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${day.meditationTrackDuration} • ${day.meditationTrackGenre}",
                            color = TextSlate,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = day.audioDescription,
                color = TextSlate,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(CardCream)
                    .padding(10.dp)
            )
        }
    }
}

@Composable
private fun LessonCard(day: ChallengeDayEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BorderLight, BorderEmeraldLight)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "LESSON & PRINCIPLE",
                color = BrandEmerald,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = day.lessonContent,
                color = TextEmerald950,
                fontSize = 13.sp,
                lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Key Takeaways:",
                color = TextEmerald900,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            val takeaways = listOf(day.takeaway1, day.takeaway2, day.takeaway3)
            takeaways.forEach { t ->
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = BrandEmerald,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = t,
                        color = TextSlate,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun TasksChecklistCard(
    day: ChallengeDayEntity,
    onToggleTask: (ChallengeDayEntity, Int, Boolean) -> Unit,
    onOpenBreathing: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BorderLight, BorderEmeraldLight)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "DAILY 3-TASK COMPLETION",
                color = BrandEmerald,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Check off all 3 tasks to unlock Day ${day.dayNumber} completion badge.",
                color = TextSlate,
                fontSize = 11.sp,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
            )

            TaskItem(
                title = "1. Listen to Guided Audio Track (${day.meditationTrackDuration})",
                isChecked = day.isTask1Done,
                onCheckedChange = { onToggleTask(day, 1, it) }
            )

            TaskItem(
                title = "2. Practice ${day.breathingGoalMinutes}m Box Breathing Tool",
                isChecked = day.isTask2Done,
                onCheckedChange = { onToggleTask(day, 2, it) }
            )

            TaskItem(
                title = "3. Complete Daily Reflection & Affirmation",
                isChecked = day.isTask3Done,
                onCheckedChange = { onToggleTask(day, 3, it) }
            )
        }
    }
}

@Composable
private fun TaskItem(
    title: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!isChecked) }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = BrandEmerald,
                checkmarkColor = CardWhite
            )
        )
        Text(
            text = title,
            color = if (isChecked) TextSlate else TextEmerald950,
            fontSize = 13.sp,
            fontWeight = if (isChecked) FontWeight.Normal else FontWeight.Medium
        )
    }
}

@Composable
private fun AffirmationCard(affirmation: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = EmeraldDeep)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SOVEREIGN DAILY AFFIRMATION",
                color = BrandGold,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "“$affirmation”",
                color = TextWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif,
                lineHeight = 21.sp
            )
        }
    }
}

@Composable
private fun DayReflectionCard(
    day: ChallengeDayEntity,
    onSave: (String) -> Unit
) {
    var note by remember(day.dayNumber) { mutableStateOf(day.reflectionNotes) }
    var isSaved by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(BorderLight, BorderEmeraldLight)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "DAY ${day.dayNumber} JOURNAL PROMPT",
                color = BrandEmerald,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = day.journalPrompt1,
                color = TextEmerald950,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = note,
                onValueChange = {
                    note = it
                    isSaved = false
                },
                placeholder = {
                    Text(
                        text = "Write your reflections and discoveries here (saved offline)...",
                        color = TextSlate,
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrandEmerald,
                    unfocusedBorderColor = BorderLight
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    onSave(note)
                    isSaved = true
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSaved) BrandEmerald else BrandGold,
                    contentColor = if (isSaved) CardWhite else EmeraldDeep
                ),
                modifier = Modifier.align(Alignment.End)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Default.Check else Icons.Default.Save,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = if (isSaved) "Saved to Journal" else "Save Reflection",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
