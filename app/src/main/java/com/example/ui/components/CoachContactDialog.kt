package com.example.ui.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.BorderEmeraldLight
import com.example.ui.theme.BrandEmerald
import com.example.ui.theme.BrandGold
import com.example.ui.theme.BrandGoldLight
import com.example.ui.theme.EmeraldCard
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.TextWhite

@Composable
fun CoachContactDialog(onClose: () -> Unit) {
    val context = LocalContext.current

    fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (_: Exception) {
            Toast.makeText(context, "Could not open link: $url", Toast.LENGTH_SHORT).show()
        }
    }

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 24.dp),
            shape = RoundedCornerShape(24.dp),
            color = EmeraldDeep
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Top Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .background(BrandGold.copy(alpha = 0.2f), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.SupportAgent,
                                contentDescription = null,
                                tint = BrandGold,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Connect with Mainak",
                                style = MaterialTheme.typography.titleMedium,
                                color = TextWhite,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Author & Mental Health Coach",
                                style = MaterialTheme.typography.bodySmall,
                                color = BrandGoldLight
                            )
                        }
                    }
                    IconButton(
                        onClick = onClose,
                        modifier = Modifier
                            .background(EmeraldDark, CircleShape)
                            .size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextWhite,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Bio Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(EmeraldDark, RoundedCornerShape(16.dp))
                        .border(1.dp, BorderEmeraldLight, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "Mainak Chatterjee is the founder of Path to Inner Peace, a Quantum Alchemist, Mental Health & Career Counselling Coach. He combines evidence-informed modern psychology, CBT techniques, and timeless mindfulness to guide individuals toward emotional sovereignty and deep mental stillness.",
                            fontSize = 13.sp,
                            lineHeight = 18.sp,
                            color = TextWhite.copy(alpha = 0.9f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "DIRECT CONTACT & CHANNELS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandGold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                ContactOptionRow(
                    icon = Icons.AutoMirrored.Filled.Chat,
                    title = "WhatsApp Coach Direct",
                    subtitle = "Join WhatsApp Community or ask questions",
                    badgeText = "Instant",
                    badgeColor = BrandGold,
                    onClick = {
                        openUrl("https://wa.me/?text=Hello%20Coach%20Mainak,%20I%20am%20participating%20in%20the%205-Day%20Mind%20Reset%20Challenge!")
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ContactOptionRow(
                    icon = Icons.Default.Email,
                    title = "Email Support",
                    subtitle = "connect@pathtoinnerpeace.in",
                    badgeText = "Official",
                    badgeColor = BrandEmerald,
                    onClick = {
                        openUrl("mailto:connect@pathtoinnerpeace.in?subject=Path%20to%20Inner%20Peace%20App%20Inquiry")
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ContactOptionRow(
                    icon = Icons.Default.Language,
                    title = "Official Website",
                    subtitle = "www.pathtoinnerpeace.in",
                    badgeText = "Portal",
                    badgeColor = BrandGold,
                    onClick = {
                        openUrl("https://www.pathtoinnerpeace.in")
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ContactOptionRow(
                    icon = Icons.Default.PlayArrow,
                    title = "YouTube Channel",
                    subtitle = "Daily live 30-min guided resets & meditations",
                    badgeText = "Live Stream",
                    badgeColor = Color(0xFFEF4444),
                    onClick = {
                        openUrl("https://www.youtube.com/@pathtoinnerpeace")
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onClose,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandGold,
                        contentColor = EmeraldDeep
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                ) {
                    Text(text = "Return to Practice", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun ContactOptionRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    badgeText: String,
    badgeColor: Color,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        color = EmeraldDark,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .background(EmeraldDeep, CircleShape)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = BrandGoldLight,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextWhite
                    )
                    Text(
                        text = subtitle,
                        fontSize = 11.sp,
                        color = TextWhite.copy(alpha = 0.65f)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .background(badgeColor.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = badgeText,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = badgeColor
                )
            }
        }
    }
}
