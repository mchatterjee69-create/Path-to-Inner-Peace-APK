package com.example.ui.components

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.BrandEmerald
import com.example.ui.theme.BrandGold
import com.example.ui.theme.EmeraldCard
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.TextWhite
import com.example.ui.viewmodel.BreathingPhase

@Composable
fun BreathingExerciseDialog(
    phase: BreathingPhase,
    count: Int,
    onClose: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(phase) {
        try {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            if (vibrator != null && vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION")
                    vibrator.vibrate(50)
                }
            }
        } catch (_: Exception) {}
    }

    val targetScale = when (phase) {
        BreathingPhase.INHALE -> 1.45f
        BreathingPhase.HOLD_IN -> 1.45f
        BreathingPhase.EXHALE -> 0.85f
        BreathingPhase.HOLD_OUT -> 0.85f
    }

    val animatedScale by animateFloatAsState(
        targetValue = targetScale,
        animationSpec = tween(durationMillis = 3800, easing = FastOutSlowInEasing),
        label = "breathingScale"
    )

    val (phaseText, phaseInstruction) = when (phase) {
        BreathingPhase.INHALE -> "Inhale Deeply (4s)" to "Breathe in calm and fresh life energy"
        BreathingPhase.HOLD_IN -> "Hold Breath (4s)" to "Keep soft chest, allow peace to settle in"
        BreathingPhase.EXHALE -> "Exhale Slowly (4s)" to "Release cortisol, worry & tension completely"
        BreathingPhase.HOLD_OUT -> "Rest in Stillness (4s)" to "Quiet the nervous system in serene presence"
    }

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = EmeraldDeep
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                BrandEmerald.copy(alpha = 0.35f),
                                EmeraldDeep
                            )
                        )
                    )
            ) {
                // Top Close Action
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Air,
                            contentDescription = null,
                            tint = BrandGold,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "4-4-4-4 Box Breathing Reset",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextWhite,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    IconButton(
                        onClick = onClose,
                        modifier = Modifier
                            .background(EmeraldDark, CircleShape)
                            .size(38.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextWhite
                        )
                    }
                }

                // Breathing Center Orb
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(260.dp)
                    ) {
                        // Outer Pulsing Glow
                        Box(
                            modifier = Modifier
                                .size(240.dp)
                                .scale(animatedScale)
                                .background(
                                    Brush.radialGradient(
                                        listOf(
                                            BrandEmerald.copy(alpha = 0.5f),
                                            BrandEmerald.copy(alpha = 0.15f),
                                            Color.Transparent
                                        )
                                    ),
                                    CircleShape
                                )
                        )

                        // Inner Ring
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(170.dp)
                                .border(2.dp, BrandGold, CircleShape)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(EmeraldCard, EmeraldDeep)
                                    ),
                                    CircleShape
                                )
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$count",
                                    fontSize = 46.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandGold
                                )
                                Text(
                                    text = "SECONDS",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    letterSpacing = 2.sp,
                                    color = Color.White.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(36.dp))

                    Text(
                        text = phaseText,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = phaseInstruction,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFA7F3D0),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(36.dp))

                    // Science Tip Badge
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(EmeraldDark, RoundedCornerShape(16.dp))
                            .border(1.dp, BrandGold.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            Text(
                                text = "COACH MAINAK'S VAGUS NERVE NOTE",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = BrandGold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Box breathing activates parasympathetic braking through the vagus nerve. 4 cycles will drop heart rate and downshift systemic cortisol significantly.",
                                fontSize = 13.sp,
                                color = TextWhite.copy(alpha = 0.9f),
                                lineHeight = 18.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Button(
                        onClick = onClose,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandGold,
                            contentColor = EmeraldDeep
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = "Finish Breathing Session",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
