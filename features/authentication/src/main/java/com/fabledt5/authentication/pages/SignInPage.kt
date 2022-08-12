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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.authentication.R
import com.fabledt5.common.theme.*
import com.fabledt5.domain.model.Resource

@ExperimentalMaterial3Api
@Composable
fun SignInPage(
    onPasswordRecoveryClicked: () -> Unit,
    onSignInClicked: (String, String) -> Unit,
    signInState: Resource<Any>
) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        if (signInState is Resource.Loading)
            CircularProgressIndicator(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center),
                color = MediumLateBlue
            )
        else SignInContent(
            onEmailChanged = { userEmail = it },
            onPasswordChanges = { userPassword = it },
            signInState = signInState,
            userEmail = userEmail,
            userPassword = userPassword,
            onPasswordRecoveryClicked = onPasswordRecoveryClicked,
            onSignInClicked = { onSignInClicked(userEmail, userPassword) }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun SignInContent(
    userEmail: String,
    userPassword: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanges: (String) -> Unit,
    signInState: Resource<Any>,
    onPasswordRecoveryClicked: () -> Unit,
    onSignInClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha = if (signInState is Resource.Loading) .5f else 1f),
    ) {
        UserDataInput(
            userEmail = userEmail,
            userPassword = userPassword,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanges,
            onPasswordRecoveryClicked = onPasswordRecoveryClicked,
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
                onClick = onSignInClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(width = 1.dp, color = Color.White),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF0e0e0f)
                ),
                enabled = userEmail.isNotEmpty() && userPassword.isNotEmpty()
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in).uppercase(),
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
fun UserDataInput(
    userEmail: String,
    userPassword: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRecoveryClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
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
            onValueChange = {
                onEmailChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
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
            ),
        )
        OutlinedTextField(
            value = userPassword,
            onValueChange = { onPasswordChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.password_placeholder).uppercase(),
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            },
            trailingIcon = {
                TextButton(onClick = onPasswordRecoveryClicked) {
                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        fontFamily = Proxima,
                        color = Color.DarkGray,
                        fontSize = 11.sp
                    )
                }
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
            ),
            visualTransformation = PasswordVisualTransformation()
        )
    }
}