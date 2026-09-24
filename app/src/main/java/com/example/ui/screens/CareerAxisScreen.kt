package com.example.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.BrandGold
import com.example.ui.theme.BrandGoldDark
import com.example.ui.theme.CardSurface
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldSoftBg
import com.example.ui.theme.PageBackground
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextLight
import com.example.ui.theme.TextMuted

@Composable
fun CareerAxisScreen(
    onBookConsultation: () -> Unit,
    onOpenBooking: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground)
            .padding(16.dp)
            .testTag("career_axis_screen")
    ) {
        // Header
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "CAREER AXIS & PARTNER SPHERE",
                    color = BrandGoldDark,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "High-Performance Without Burnout",
                    color = TextDark,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Bridging deep inner serenity with aggressive professional impact. Designed by Mainak Chatterjee for college students entering placement cycles and corporate leaders managing high-stakes teams.",
                    color = TextMuted,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            }
        }

        item { Spacer(modifier = Modifier.height(18.dp)) }

        // Two Primary Spheres
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldDark),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.School, contentDescription = null, tint = BrandGold, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Campus Placement & Scholar Axis",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Students face crippling interview anxiety, comparison fatigue, and imposter syndrome. We deliver customized 3-day on-campus bootcamps helping final-year scholars stay emotionally centered and interview-ready.",
                        color = Color(0xFFA7F3D0),
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    val campusPoints = listOf(
                        "Interview Panic Grounding Protocols",
                        "Overcoming Placement Comparison Loops",
                        "Cognitive Stamina for 8-Hour Test Windows"
                    )

                    campusPoints.forEach { point ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = BrandGold, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(point, color = Color.White, fontSize = 11.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { onOpenBooking("College / Campus Alignment") },
                        colors = ButtonDefaults.buttonColors(containerColor = BrandGold),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Partner for Campus Cohort", color = EmeraldDark, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(14.dp)) }

        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Apartment, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Executive & Corporate Resilience",
                            color = TextDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Engineering leaders, executives, and founders navigate unrelenting pressure. We conduct quarterly offsites and confidential 1:1 audits to prevent burnout and foster emotional equilibrium in high-velocity teams.",
                        color = TextMuted,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    val corpPoints = listOf(
                        "Decision fatigue interruption during crunch times",
                        "Somatic calming before high-stakes board presentations",
                        "Team cognitive alignment & emotional safety culture"
                    )

                    corpPoints.forEach { point ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(point, color = TextDark, fontSize = 11.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { onOpenBooking("Corporate Resilience") },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Book Corporate Workshop Inquiry", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(18.dp)) }

        // 1:1 Deep Dive Consultation Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldSoftBg),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, EmeraldLight.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Psychology, contentDescription = null, tint = EmeraldDark, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Private 1:1 Clarity Intensive",
                            color = EmeraldDark,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "A dedicated 60-minute confidential audit with Mainak Chatterjee to identify your exact mental loops, burnout triggers, and craft your 90-day Career Axis roadmap.",
                        color = TextMuted,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { onOpenBooking("1:1 Executive Clarity") },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldDark),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Schedule 1:1 Clarity Session", color = BrandGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
