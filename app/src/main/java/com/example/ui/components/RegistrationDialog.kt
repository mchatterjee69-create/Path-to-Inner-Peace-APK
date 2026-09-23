package com.example.ui.components

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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
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
import com.example.ui.theme.EmeraldCard
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.TextWhite

@Composable
fun RegistrationDialog(
    initialBatch: String,
    onRegister: (fullName: String, email: String, whatsapp: String, country: String, batch: String) -> Unit,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    var fullName by remember { mutableStateOf("") }
    var whatsappNumber by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("India") }
    var selectedBatch by remember { mutableStateOf(initialBatch) }
    var agreementChecked by remember { mutableStateOf(true) }

    val morningSlots = listOf("6:30 AM", "7:30 AM", "8:30 AM")
    val eveningSlots = listOf("5:00 PM", "6:00 PM", "7:00 PM")

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 20.dp),
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
                // Top Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(BrandGold.copy(alpha = 0.2f))
                                .border(1.dp, BrandGold.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "100% FREE CHALLENGE",
                                color = BrandGold,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "5-Day Mind Reset Registration",
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
                            .testTag("button_close_registration")
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
                    text = "Reserve your spot in the next batch. Instant access to Day 1 Mental Detox, guided audio tracks & Welcome Kit.",
                    color = Color(0xFFA7F3D0),
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                )

                // Batch Time Selection
                Text(
                    text = "SELECT YOUR DAILY 30-MIN BATCH (IST)",
                    color = BrandGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Morning Slots
                Text(
                    text = "🌤️ Morning Batches",
                    color = TextWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    morningSlots.forEach { slot ->
                        val isSelected = selectedBatch == slot
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (isSelected) BrandGold else EmeraldDark
                                )
                                .border(
                                    1.dp,
                                    if (isSelected) BrandGold else BorderEmeraldLight,
                                    RoundedCornerShape(10.dp)
                                )
                                .clickable { selectedBatch = slot }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = slot,
                                color = if (isSelected) EmeraldDeep else TextWhite,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Evening Slots
                Text(
                    text = "🌙 Evening Batches",
                    color = TextWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    eveningSlots.forEach { slot ->
                        val isSelected = selectedBatch == slot
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (isSelected) BrandGold else EmeraldDark
                                )
                                .border(
                                    1.dp,
                                    if (isSelected) BrandGold else BorderEmeraldLight,
                                    RoundedCornerShape(10.dp)
                                )
                                .clickable { selectedBatch = slot }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = slot,
                                color = if (isSelected) EmeraldDeep else TextWhite,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Input Fields
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name *", color = Color(0xFFA7F3D0)) },
                    placeholder = { Text("e.g. Mainak Chatterjee", color = Color.Gray) },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null, tint = BrandGold)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_reg_fullname"),
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

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = whatsappNumber,
                    onValueChange = { whatsappNumber = it },
                    label = { Text("WhatsApp Number *", color = Color(0xFFA7F3D0)) },
                    placeholder = { Text("+91 91636 70300", color = Color.Gray) },
                    leadingIcon = {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = BrandGold)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_reg_whatsapp"),
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

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = emailAddress,
                    onValueChange = { emailAddress = it },
                    label = { Text("Email Address (Optional)", color = Color(0xFFA7F3D0)) },
                    placeholder = { Text("seeker@pathtoinnerpeace.in", color = Color.Gray) },
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null, tint = BrandGold)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_reg_email"),
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

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = country,
                    onValueChange = { country = it },
                    label = { Text("Country", color = Color(0xFFA7F3D0)) },
                    leadingIcon = {
                        Icon(Icons.Default.Public, contentDescription = null, tint = BrandGold)
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

                Spacer(modifier = Modifier.height(14.dp))

                // Agreement Checkbox
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(EmeraldDark.copy(alpha = 0.6f))
                        .clickable { agreementChecked = !agreementChecked }
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = agreementChecked,
                        onCheckedChange = { agreementChecked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = BrandGold,
                            checkmarkColor = EmeraldDeep,
                            uncheckedColor = Color(0xFFA7F3D0)
                        )
                    )
                    Text(
                        text = "I agree to receive daily WhatsApp notifications, lesson updates, and my certificate alert.",
                        color = Color(0xFFD1FAE5),
                        fontSize = 11.sp,
                        lineHeight = 15.sp,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Submit Button
                Button(
                    onClick = {
                        if (fullName.isBlank()) {
                            Toast.makeText(context, "Please enter your Full Name", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        if (whatsappNumber.isBlank()) {
                            Toast.makeText(context, "Please enter your WhatsApp Number", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        Toast.makeText(context, "Registration Successful! Welcome to the Challenge.", Toast.LENGTH_LONG).show()
                        onRegister(fullName, emailAddress, whatsappNumber, country, selectedBatch)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("button_complete_registration"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandGold,
                        contentColor = EmeraldDeep
                    )
                ) {
                    Text(
                        text = "Complete Free Registration →",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = BrandGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "MindForge 360°™ Certification & Welcome Kit included.",
                        color = Color(0xFFA7F3D0),
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
