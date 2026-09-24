package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.BrandGold
import com.example.ui.theme.BrandGoldDark
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldMedium
import com.example.ui.theme.EmeraldPrimary

@Composable
fun CertificateDialog(
    currentName: String,
    issuedDate: String,
    onClaimCertificate: (String) -> Unit,
    onClose: () -> Unit
) {
    var recipientName by remember { mutableStateOf(if (currentName == "Mindful Seeker") "" else currentName) }
    var isClaimed by remember { mutableStateOf(issuedDate.isNotBlank()) }
    val clipboardManager = LocalClipboardManager.current
    var copiedNotice by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onClose) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFF02201D),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
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

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = BrandGold,
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "MindForge 360°™ Certificate",
                            color = BrandGold,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Certificate parchment container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFFBEB), RoundedCornerShape(12.dp))
                        .border(3.dp, BrandGoldDark, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "PATH TO INNER PEACE",
                            color = EmeraldDark,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 2.sp
                        )
                        Text(
                            text = "CERTIFICATE OF COMPLETION",
                            color = BrandGoldDark,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "This proudly certifies that",
                            color = Color(0xFF475569),
                            fontSize = 11.sp,
                            fontStyle = FontStyle.Italic
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = if (recipientName.isBlank()) "Mindful Seeker" else recipientName,
                            color = EmeraldDark,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "has completed the 5-Day Mind Reset Challenge and demonstrated mastery in Somatic Nervous Regulation & The S.T.O.P. Protocol.",
                            color = Color(0xFF334155),
                            fontSize = 11.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 15.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
                                Text(
                                    text = if (issuedDate.isNotBlank()) issuedDate else "Issued upon claim",
                                    color = Color(0xFF64748B),
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = "Date Verified",
                                    color = Color(0xFF94A3B8),
                                    fontSize = 9.sp
                                )
                            }
                            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Mainak Chatterjee",
                                    color = EmeraldDark,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Cursive
                                )
                                Text(
                                    text = "Founder & Mentor",
                                    color = Color(0xFF94A3B8),
                                    fontSize = 9.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (!isClaimed) {
                    OutlinedTextField(
                        value = recipientName,
                        onValueChange = { recipientName = it },
                        label = { Text("Your Full Name (For Certificate)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            if (recipientName.isNotBlank()) {
                                isClaimed = true
                                onClaimCertificate(recipientName)
                            }
                        },
                        enabled = recipientName.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(containerColor = BrandGold),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Personalize & Claim Certificate",
                            color = EmeraldDark,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                val shareText = "I just completed the 5-Day Mind Reset Challenge by Mainak Chatterjee on Path to Inner Peace! Verified Certification earned."
                                clipboardManager.setText(AnnotatedString(shareText))
                                copiedNotice = true
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Share Achievement")
                        }
                    }

                    if (copiedNotice) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Copied certificate message to clipboard!",
                            color = Color(0xFFA7F3D0),
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}
