package com.fabledt5.authentication.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.authentication.R
import com.fabledt5.common.theme.GradinentTextStyle
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MediumLateBlue
import com.fabledt5.common.theme.Proxima
import com.fabledt5.domain.model.Resource

@ExperimentalMaterial3Api
@Composable
fun SignUpPage(onSignUpClicked: (String, String, String) -> Unit, signUpState: Resource<Any>) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var userNickName by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        if (signUpState is Resource.Loading)
            CircularProgressIndicator(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center),
                color = MediumLateBlue
            )
        else SignUpContent(
            userEmail = userEmail,
            userPassword = userPassword,
            userNickName = userNickName,
            onEmailChanged = { userEmail = it },
            onPasswordChanged = { userPassword = it },
            onNicknameChanged = { userNickName = it },
            signUpState = signUpState,
            onSignUpClicked = onSignUpClicked
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun SignUpContent(
    userEmail: String,
    userPassword: String,
    userNickName: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onNicknameChanged: (String) -> Unit,
    signUpState: Resource<Any>,
    onSignUpClicked: (String, String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha = if (signUpState is Resource.Loading) .5f else 1f),
    ) {
        USerDataInput(
            userEmail = userEmail,
            userPassword = userPassword,
            userNickName = userNickName,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onNicknameChanged = onNicknameChanged,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            OutlinedButton(
                onClick = { onSignUpClicked(userEmail, userPassword, userNickName) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(width = 1.dp, color = Color.White),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF0e0e0f)
                ),
                enabled = userEmail.isNotEmpty()
                        && userPassword.isNotEmpty()
                        && userNickName.isNotEmpty()
            ) {
                Text(
                    text = stringResource(R.string.sign_up).uppercase(),
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    style = GradinentTextStyle()
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun USerDataInput(
    userEmail: String,
    userPassword: String,
    userNickName: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onNicknameChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.sign_in_message),
            modifier = Modifier.padding(bottom = 15.dp),
            fontFamily = Proxima,
            color = Color.Gray,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            value = userEmail,
            onValueChange = { onEmailChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
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
        OutlinedTextField(
            value = userPassword,
            onValueChange = { onPasswordChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            placeholder = {
                Text(
                    text = stringResource(R.string.password_placeholder).uppercase(),
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
        OutlinedTextField(
            value = userNickName,
            onValueChange = { onNicknameChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.nickname_placeholder).uppercase(),
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
    }
}