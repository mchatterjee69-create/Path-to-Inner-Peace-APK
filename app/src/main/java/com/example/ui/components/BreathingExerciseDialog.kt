package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.BrandGold
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldMedium
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.viewmodel.BreathingPhase

@Composable
fun BreathingExerciseDialog(
    phase: BreathingPhase,
    count: Int,
    onClose: () -> Unit
) {
    Dialog(onDismissRequest = onClose) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = EmeraldDark,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = onClose,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }

                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "4-7-8 Somatic Breathing",
                            color = BrandGold,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Parasympathetic Nervous Reset",
                            color = Color(0xFFA7F3D0),
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                val targetScale = when (phase) {
                    BreathingPhase.INHALE -> 1.35f
                    BreathingPhase.HOLD -> 1.35f
                    BreathingPhase.EXHALE -> 0.85f
                    BreathingPhase.REST -> 1.0f
                }

                val animatedScale by animateFloatAsState(
                    targetValue = targetScale,
                    animationSpec = tween(durationMillis = 900),
                    label = "BreathingScale"
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(180.dp)
                        .scale(animatedScale)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                listOf(
                                    EmeraldLight.copy(alpha = 0.8f),
                                    EmeraldPrimary.copy(alpha = 0.4f),
                                    EmeraldMedium.copy(alpha = 0.2f)
                                )
                            )
                        )
                        .border(2.dp, BrandGold, CircleShape)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = when (phase) {
                                BreathingPhase.INHALE -> "INHALE"
                                BreathingPhase.HOLD -> "HOLD"
                                BreathingPhase.EXHALE -> "EXHALE"
                                BreathingPhase.REST -> "RESET"
                            },
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (phase == BreathingPhase.REST) "●" else "$count",
                            color = BrandGold,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = when (phase) {
                        BreathingPhase.INHALE -> "Breathe in deeply through nose, filling your diaphragm."
                        BreathingPhase.HOLD -> "Retain the breath with relaxed shoulders and jaw."
                        BreathingPhase.EXHALE -> "Release fully through mouth with a soothing whoosh sound."
                        BreathingPhase.REST -> "Resting before the next calming rhythm begins."
                    },
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onClose,
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Complete & Return", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
