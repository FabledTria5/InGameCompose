package com.fabledt5.authentication.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.fabledt5.authentication.model.AuthenticationFormEvent
import com.fabledt5.authentication.model.AuthenticationFormState
import com.fabledt5.common.theme.GradinentTextStyle
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima

@ExperimentalMaterial3Api
@Composable
fun SignInPage(
    onPasswordRecoveryClicked: () -> Unit,
    onFormEvent: (AuthenticationFormEvent) -> Unit,
    signInState: AuthenticationFormState
) {
    SignInContent(
        formState = signInState,
        onFormEvent = onFormEvent,
        onPasswordRecoveryClicked = onPasswordRecoveryClicked
    )
}

@ExperimentalMaterial3Api
@Composable
fun SignInContent(
    formState: AuthenticationFormState,
    onFormEvent: (AuthenticationFormEvent) -> Unit,
    onPasswordRecoveryClicked: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SignInDataInput(
            formState = formState,
            onPasswordRecoveryClicked = onPasswordRecoveryClicked,
            onFormEvent = onFormEvent,
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
                onClick = { onFormEvent(AuthenticationFormEvent.Submit) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(width = 1.dp, color = Color.White),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF0e0e0f)
                )
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
fun SignInDataInput(
    modifier: Modifier = Modifier,
    formState: AuthenticationFormState,
    onFormEvent: (AuthenticationFormEvent) -> Unit,
    onPasswordRecoveryClicked: () -> Unit
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
            value = formState.email,
            onValueChange = { onFormEvent(AuthenticationFormEvent.EmailChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            isError = formState.emailError != null,
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
        if (formState.emailError != null)
            Text(
                text = formState.emailError,
                modifier = Modifier.padding(top = 5.dp, bottom = 15.dp),
                color = MaterialTheme.colorScheme.error,
                fontFamily = Mark
            )

        OutlinedTextField(
            value = formState.password,
            onValueChange = { onFormEvent(AuthenticationFormEvent.PasswordChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            isError = formState.passwordError != null,
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
        if (formState.nicknameError != null)
            Text(
                text = formState.nicknameError,
                modifier = Modifier.padding(top = 5.dp),
                color = MaterialTheme.colorScheme.error,
                fontFamily = Mark
            )
    }
}