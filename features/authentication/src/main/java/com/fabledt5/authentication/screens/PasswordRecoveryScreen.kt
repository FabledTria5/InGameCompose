package com.fabledt5.authentication.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.authentication.AuthenticationViewModel
import com.fabledt5.authentication.R
import com.fabledt5.common.theme.GradinentTextStyle
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.common.theme.Turquoise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRecoveryScreen(authenticationViewModel: AuthenticationViewModel) {

    var userEmail by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.forgot_password).uppercase(),
            modifier = Modifier.align(Alignment.TopCenter),
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.password_recovery_message),
                modifier = Modifier.padding(bottom = 30.dp),
                fontFamily = Proxima,
                color = Color.Gray,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
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
            OutlinedButton(
                onClick = { authenticationViewModel.recoverPassword(userEmail) },
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
                    style = GradinentTextStyle()
                )
            }
        }
    }
}