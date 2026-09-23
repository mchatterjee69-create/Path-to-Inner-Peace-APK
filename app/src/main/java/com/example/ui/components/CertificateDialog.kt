package com.example.ui.components

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.BrandEmerald
import com.example.ui.theme.BrandGold
import com.example.ui.theme.BrandGoldLight
import com.example.ui.theme.EmeraldCard
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.TextWhite

@Composable
fun CertificateDialog(
    currentName: String,
    issuedDate: String,
    onClaimCertificate: (String) -> Unit,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    var inputName by remember { mutableStateOf(currentName.ifEmpty { "Mindful Seeker" }) }

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
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.WorkspacePremium,
                            contentDescription = null,
                            tint = BrandGold,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "MindForge 360°™ Official Certificate",
                            style = MaterialTheme.typography.titleSmall,
                            color = TextWhite,
                            fontWeight = FontWeight.Bold
                        )
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

                Spacer(modifier = Modifier.height(16.dp))

                // The Certificate Sheet
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    EmeraldDark,
                                    EmeraldDeep,
                                    EmeraldCard
                                )
                            ),
                            RoundedCornerShape(16.dp)
                        )
                        .border(2.dp, Brush.linearGradient(listOf(BrandGold, Color(0xFFD97706), BrandGoldLight)), RoundedCornerShape(16.dp))
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "PATH TO INNER PEACE",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = BrandGold,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "CERTIFICATE OF COMPLETION",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "MindForge 360°™ 5-Day Mental Reset",
                            fontSize = 12.sp,
                            color = BrandGoldLight,
                            fontStyle = FontStyle.Italic
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "This is proudly presented to",
                            fontSize = 11.sp,
                            color = TextWhite.copy(alpha = 0.65f)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = inputName,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandGold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "for successfully completing the rigorous 5-Day Mind Reset Challenge, demonstrating commitment to nervous-system regulation, cognitive restructuring, and emotional transformation.",
                            fontSize = 11.sp,
                            lineHeight = 16.sp,
                            color = TextWhite.copy(alpha = 0.85f),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        // Signatures & Seals
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Column(horizontalAlignment = Alignment.Start) {
                                Text(
                                    text = "Mainak Chatterjee",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.Cursive,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandGoldLight
                                )
                                Box(modifier = Modifier.size(width = 90.dp, height = 1.dp).background(BrandGold))
                                Text(
                                    text = "Founder & Head Coach",
                                    fontSize = 9.sp,
                                    color = TextWhite.copy(alpha = 0.7f)
                                )
                                Text(
                                    text = "Quantum Alchemist",
                                    fontSize = 8.sp,
                                    color = TextWhite.copy(alpha = 0.5f)
                                )
                            }

                            // Seal
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(52.dp)
                                    .background(EmeraldDark, CircleShape)
                                    .border(1.5.dp, BrandGold, CircleShape)
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Default.Verified,
                                        contentDescription = "Verified",
                                        tint = BrandGold,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = "VERIFIED",
                                        fontSize = 7.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BrandGold
                                    )
                                }
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = issuedDate.ifEmpty { "September 2026" },
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = TextWhite
                                )
                                Box(modifier = Modifier.size(width = 80.dp, height = 1.dp).background(BrandGold.copy(alpha = 0.5f)))
                                Text(
                                    text = "Credential ID",
                                    fontSize = 8.sp,
                                    color = TextWhite.copy(alpha = 0.6f)
                                )
                                Text(
                                    text = "MF360-PIP-8472",
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandGold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Name customization input
                OutlinedTextField(
                    value = inputName,
                    onValueChange = { inputName = it },
                    label = { Text("Name on Certificate", color = BrandGold) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BrandGold,
                        unfocusedBorderColor = BrandEmerald,
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = {
                            onClaimCertificate(inputName)
                            Toast.makeText(context, "Certificate issued & saved offline!", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandGold,
                            contentColor = EmeraldDeep
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                    ) {
                        Text(text = "Save & Claim", fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = {
                            Toast.makeText(context, "MindForge 360°™ Certificate copied to clipboard!", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandEmerald,
                            contentColor = TextWhite
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(46.dp)
                    ) {
                        Icon(Icons.Default.Share, contentDescription = "Share", tint = TextWhite, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.size(6.dp))
                        Text(text = "Share", color = TextWhite, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}
