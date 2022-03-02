package com.fabledt5.authentication.screens.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import com.fabledt5.authentication.utils.isCorrectEmail
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MediumLateBlue
import com.fabledt5.common.theme.Proxima
import com.fabledt5.common.theme.Turquoise
import com.fabledt5.domain.model.Resource

@Composable
fun SignInPage(
    onForgotPasswordClicked: () -> Unit,
    onSignInClicked: (String, String) -> Unit,
    signInState: Resource<Boolean>
) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    var isEmailCorrect by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha = if (signInState is Resource.Loading) .5f else 1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(2.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.sign_in_message),
                    fontFamily = Proxima,
                    color = Color.Gray,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = userEmail,
                    onValueChange = {
                        isEmailCorrect = it.isCorrectEmail()
                        userEmail = it
                    },
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
                        backgroundColor = Color(0xFF111113),
                        cursorColor = Color.White,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        placeholderColor = Color.DarkGray
                    ),
                    isError = !isEmailCorrect
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = userPassword,
                    onValueChange = { userPassword = it },
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
                        TextButton(onClick = onForgotPasswordClicked) {
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
                        backgroundColor = Color(0xFF111113),
                        cursorColor = Color.White,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        placeholderColor = Color.DarkGray
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.social_networks),
                    fontFamily = Proxima,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF111113)),
                        shape = RoundedCornerShape(size = 3.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.facebook).uppercase(),
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.Gray,
                            fontFamily = Mark,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(.05f))
                    Button(
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF111113)),
                        shape = RoundedCornerShape(size = 3.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.twitter).uppercase(),
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.Gray,
                            fontFamily = Mark,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), contentAlignment = Alignment.Center
            ) {
                when (signInState) {
                    Resource.Loading -> CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MediumLateBlue
                    )
                    else -> OutlinedButton(
                        onClick = { onSignInClicked(userEmail, userPassword) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                        border = BorderStroke(width = 1.dp, color = Color.White),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color(0xFF0e0e0f)
                        ),
                        enabled = userEmail.isNotEmpty() && userPassword.isNotEmpty()
                    ) {
                        Text(
                            text = stringResource(id = R.string.sign_in).uppercase(),
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontFamily = Mark,
                            fontWeight = FontWeight.Bold,
                            color = Turquoise
                        )
                    }
                }
            }
        }

        if (signInState is Resource.Loading)
            CircularProgressIndicator(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center),
                color = MediumLateBlue
            )
    }
}