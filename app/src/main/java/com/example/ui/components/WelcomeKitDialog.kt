package com.example.ui.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.BorderEmeraldLight
import com.example.ui.theme.BrandEmerald
import com.example.ui.theme.BrandGold
import com.example.ui.theme.EmeraldCard
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.TextWhite

data class WelcomeKitAsset(
    val id: String,
    val title: String,
    val category: String,
    val summary: String,
    val icon: ImageVector,
    val badge: String
)

@Composable
fun WelcomeKitDialog(
    onClose: () -> Unit
) {
    val context = LocalContext.current
    val portalUrl = "https://welcomekit-pathtoinnerpeace.vercel.app"

    val assets = listOf(
        WelcomeKitAsset(
            id = "assessment",
            title = "1. Mental Fitness Assessment",
            category = "Self-Evaluation Diagnostic",
            summary = "A calibrated 10-point assessment quantifying your baseline stress reactivity, mental clutter, and autonomic nervous system state.",
            icon = Icons.Default.Assessment,
            badge = "INTERACTIVE EVALUATION"
        ),
        WelcomeKitAsset(
            id = "report",
            title = "2. Personalised Mind Report",
            category = "Custom Neural Blueprint",
            summary = "Targeted action plan breaking down your primary cognitive hurdles with tailored breathwork sequences and focus anchors.",
            icon = Icons.Default.Description,
            badge = "TAILORED ROADMAP"
        ),
        WelcomeKitAsset(
            id = "stress-audio",
            title = "3. 5-Minute Stress Reset Audio",
            category = "Emergency Downshift Track",
            summary = "Fast-acting vagal nerve calming audio infused with 528Hz Solfeggio frequency to halt panic or workplace overwhelm in 300 seconds.",
            icon = Icons.Default.GraphicEq,
            badge = "EMERGENCY AUDIO"
        ),
        WelcomeKitAsset(
            id = "sleep-blueprint",
            title = "4. Better Sleep Blueprint",
            category = "Circadian Optimization",
            summary = "Scientifically designed digital sundown routine, delta-wave acoustic frequencies, and evening cognitive brain-dump protocols.",
            icon = Icons.Default.Bedtime,
            badge = "RESTORATIVE SLEEP"
        ),
        WelcomeKitAsset(
            id = "starter-guide",
            title = "5. Mental Reset Starter Guide",
            category = "Comprehensive Handbook",
            summary = "Step-by-step master handbook introducing the 10 Transformation Pillars, ANTs reframing worksheets, and daily habit anchors.",
            icon = Icons.Default.MenuBook,
            badge = "FOUNDATION HANDBOOK"
        )
    )

    fun launchPortal() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(portalUrl))
            context.startActivity(intent)
        } catch (_: Exception) {
            Toast.makeText(context, "Could not open link: $portalUrl", Toast.LENGTH_SHORT).show()
        }
    }

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(24.dp),
            color = EmeraldDeep,
            border = androidx.compose.foundation.BorderStroke(1.dp, BrandGold.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(BrandGold.copy(alpha = 0.2f))
                                .border(1.dp, BrandGold.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "FREE WELCOME KIT",
                                color = BrandGold,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "5 Transformation Assets",
                            color = TextWhite,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    IconButton(
                        onClick = onClose,
                        modifier = Modifier
                            .size(36.dp)
                            .background(EmeraldDark, CircleShape)
                            .testTag("button_close_welcome_kit")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextWhite,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Text(
                    text = "Included free for all 5-Day Mind Reset participants. Access your diagnostic report, sleep blueprint, and starter toolkit.",
                    color = Color(0xFFA7F3D0),
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                )

                // Launch External Portal CTA
                Button(
                    onClick = { launchPortal() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("button_launch_portal_top"),
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
                        Icon(
                            imageVector = Icons.Default.OpenInNew,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Open Portal (welcomekit-pathtoinnerpeace.vercel.app)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // The 5 Assets Cards
                assets.forEach { asset ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = EmeraldDark),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderEmeraldLight)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(34.dp)
                                            .clip(CircleShape)
                                            .background(BrandGold.copy(alpha = 0.15f))
                                            .border(1.dp, BrandGold.copy(alpha = 0.4f), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = asset.icon,
                                            contentDescription = null,
                                            tint = BrandGold,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }

                                    Column {
                                        Text(
                                            text = asset.title,
                                            color = TextWhite,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = asset.category,
                                            color = BrandGold,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(EmeraldDeep)
                                        .border(1.dp, BrandGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "FREE",
                                        color = BrandGold,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = asset.summary,
                                color = Color(0xFFD1FAE5),
                                fontSize = 11.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { launchPortal() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("button_launch_portal_bottom"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandGold,
                        contentColor = EmeraldDeep
                    )
                ) {
                    Text(
                        text = "Launch Welcome Kit Web Portal ↗",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onClose,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Text(text = "Close & Return to App", fontSize = 12.sp)
                }
            }
        }
    }
}
