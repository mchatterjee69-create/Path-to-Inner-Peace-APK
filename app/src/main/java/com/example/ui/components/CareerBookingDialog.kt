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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.BorderEmeraldLight
import com.example.ui.theme.BrandEmerald
import com.example.ui.theme.BrandGold
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.TextWhite
import java.net.URLEncoder

@Composable
fun CareerBookingDialog(
    initialType: String,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var organization by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(initialType) }
    var notes by remember { mutableStateOf("") }

    val categories = listOf(
        "College / Campus Alignment",
        "Gym & Fitness Center",
        "Corporate Wellness RFP",
        "1:1 Executive Clarity"
    )

    fun sendViaWhatsApp() {
        val finalOrg = if (organization.isNotBlank()) " | Org: $organization" else ""
        val finalNotes = if (notes.isNotBlank()) " | Notes: $notes" else ""
        val text = "Hello Coach Mainak! I would like to book an institutional partnership / clarity consultation for $selectedCategory. Name: $name$finalOrg$finalNotes."
        try {
            val encoded = URLEncoder.encode(text, "UTF-8")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/919163670300?text=$encoded"))
            context.startActivity(intent)
            onClose()
        } catch (_: Exception) {
            Toast.makeText(context, "Could not launch WhatsApp. Phone: +91 91636 70300", Toast.LENGTH_LONG).show()
        }
    }

    fun callCoach() {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919163670300"))
            context.startActivity(intent)
        } catch (_: Exception) {
            Toast.makeText(context, "Could not open dialer. Phone: +91 91636 70300", Toast.LENGTH_SHORT).show()
        }
    }

    fun emailProposal() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:connect@pathtoinnerpeace.in")
                putExtra(Intent.EXTRA_SUBJECT, "RFP / Partnership Request - $selectedCategory")
                putExtra(Intent.EXTRA_TEXT, "Hello Coach Mainak,\n\nI would like to request a proposal for $selectedCategory.\n\nName: $name\nPhone: $phone\nOrganization: $organization\nNotes: $notes")
            }
            context.startActivity(intent)
        } catch (_: Exception) {
            Toast.makeText(context, "Could not open email client", Toast.LENGTH_SHORT).show()
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
                // Top Header
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
                                text = "PARTNER SPHERE & CLARITY",
                                color = BrandGold,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Consultation & RFP Booking",
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
                            .testTag("button_close_career_booking")
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
                    text = "Book a direct facilitation session, college workshop, or enterprise wellness blueprint with Mainak Chatterjee.",
                    color = Color(0xFFA7F3D0),
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 14.dp)
                )

                // Category selector
                Text(
                    text = "SELECT CONSULTATION TYPE",
                    color = BrandGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))

                categories.forEach { cat ->
                    val isSelected = selectedCategory == cat
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) BrandGold else EmeraldDark)
                            .border(1.dp, if (isSelected) BrandGold else BorderEmeraldLight, RoundedCornerShape(10.dp))
                            .clickable { selectedCategory = cat }
                            .padding(horizontal = 12.dp, vertical = 9.dp)
                    ) {
                        Text(
                            text = cat,
                            color = if (isSelected) EmeraldDeep else TextWhite,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Your Name *", color = Color(0xFFA7F3D0)) },
                    placeholder = { Text("e.g. Dr. A. Sharma / Rahul Verma", color = Color.Gray) },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null, tint = BrandGold)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_booking_name"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedBorderColor = BrandGold,
                        unfocusedBorderColor = BorderEmeraldLight,
                        focusedContainerColor = EmeraldDark,
                        unfocusedContainerColor = EmeraldDark
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("WhatsApp / Phone *", color = Color(0xFFA7F3D0)) },
                    placeholder = { Text("+91 91636 70300", color = Color.Gray) },
                    leadingIcon = {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = BrandGold)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_booking_phone"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedBorderColor = BrandGold,
                        unfocusedBorderColor = BorderEmeraldLight,
                        focusedContainerColor = EmeraldDark,
                        unfocusedContainerColor = EmeraldDark
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = organization,
                    onValueChange = { organization = it },
                    label = { Text("College / Company / Studio Name", color = Color(0xFFA7F3D0)) },
                    leadingIcon = {
                        Icon(Icons.Default.Business, contentDescription = null, tint = BrandGold)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedBorderColor = BrandGold,
                        unfocusedBorderColor = BorderEmeraldLight,
                        focusedContainerColor = EmeraldDark,
                        unfocusedContainerColor = EmeraldDark
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Action Buttons
                Button(
                    onClick = {
                        if (name.isBlank() && phone.isBlank()) {
                            Toast.makeText(context, "Please enter your name or phone", Toast.LENGTH_SHORT).show()
                        } else {
                            sendViaWhatsApp()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("button_booking_whatsapp"),
                    shape = RoundedCornerShape(14.dp),
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
                            imageVector = Icons.AutoMirrored.Filled.Chat,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Connect on WhatsApp (+91 91636 70300) →",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { callCoach() },
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandEmerald,
                            contentColor = Color.White
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                            Text("Call Coach", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    OutlinedButton(
                        onClick = { emailProposal() },
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(16.dp))
                            Text("Email RFP", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
