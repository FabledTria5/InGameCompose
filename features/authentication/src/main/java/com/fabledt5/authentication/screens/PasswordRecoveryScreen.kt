package com.fabledt5.authentication.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.authentication.R
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.common.theme.Turquoise

@Composable
fun PasswordRecoveryScreen() {

    var userEmail by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.4f),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = stringResource(id = R.string.forgot_password).uppercase(),
                fontFamily = Mark,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.password_recovery_message),
                fontFamily = Proxima,
                color = Color.Gray,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(R.string.email_placeholder).uppercase(),
                        fontFamily = Mark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                },
                textStyle = TextStyle(fontSize = 12.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                shape = RoundedCornerShape(size = 3.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    containerColor = Color(0xFF111113),
                    cursorColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    placeholderColor = Color.DarkGray
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(width = 1.dp, color = Color.White),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF0e0e0f)
                )
            ) {
                Text(
                    text = stringResource(R.string.send).uppercase(),
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    color = Turquoise
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF18181C)
@Composable
fun PasswordRecoveryScreenPreview() {
    PasswordRecoveryScreen()
}