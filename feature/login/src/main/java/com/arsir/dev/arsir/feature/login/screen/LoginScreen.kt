package com.arsir.dev.arsir.feature.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsir.dev.arsir.uikit.button.Button
import com.arsir.dev.arsir.uikit.button.ButtonAppearance
import com.arsir.dev.arsir.uikit.event.OnAction
import com.arsir.dev.arsir.uikit.text.AgreementText
import com.arsir.dev.arsir.uikit.textfield.AppTextField
import com.arsir.dev.arsir.uikit.textfield.TextFieldAppearance
import com.arsir.dev.arsir.uikit.theme.Typography
import com.arsir.dev.arsir.uikit.R as RUIKit

@Composable
internal fun LoginScreen(
    state: LoginState,
    onAction: OnAction,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Color(0xFFFFFFFE),
        topBar = {
            Row(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 4.dp,
                    alignment = Alignment.CenterHorizontally,
                ),
            ) {
                Image(
                    painter = painterResource(RUIKit.drawable.ic_store),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Ar Store",
                    style = Typography.h5,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 18.sp,
                    letterSpacing = 0.08.sp
                )
            }
        },
        bottomBar = {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(color = Color(0xFFFFFFFE))
                    .padding(16.dp)
            ) {
                Button(
                    text = "Login",
                    isLoading = state.isLoading,
                    appearance = ButtonAppearance.Primary,
                    onClick = {
                        onAction(
                            LoginEvent.OnLogin(
                                username = state.username,
                                password = state.password,
                            )
                        )
                    },
                )
                AgreementText()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome to Ar Store",
                    style = Typography.h6,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 12.sp,
                    letterSpacing = 0.1.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )

                Text(
                    text = "Sign up or login below to manage your project, task, and productivity",
                    style = Typography.caption,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.sp,
                    letterSpacing = 0.1.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(fraction = 0.6f)
                        .align(alignment = Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Login",
                    style = Typography.caption,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 18.sp,
                    letterSpacing = 0.1.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                )
                Divider(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .height(5.dp)
                        .fillMaxWidth()
                        .background(color = Color(0xFF94B759))
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .weight(4f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterVertically,
                )
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(space = 4.dp)) {
                    AppTextField(
                        value = state.username,
                        onValueChange = { username ->
                            onAction(LoginEvent.OnUsername(username = username))
                        },
                        isError = !state.isUsernameBlank,
                        leadingIcon = Icons.Outlined.Email,
                        appearance = TextFieldAppearance.Normal,
                        placeholder = "Enter your username",
                    )
                    if (state.isUsernameBlank) {
                        Text(
                            text = state.usernameError.orEmpty(),
                            style = Typography.caption,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 18.sp,
                            letterSpacing = 0.1.sp,
                            color = Color.Red,
                            modifier = Modifier
                                .alpha(0.7f)
                                .fillMaxWidth(),
                        )
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(space = 4.dp)) {
                    AppTextField(
                        value = state.password,
                        onValueChange = { password ->
                            onAction(LoginEvent.OnPassword(password = password))
                        },
                        isError = !state.isPasswordBlank,
                        leadingIcon = Icons.Outlined.Lock,
                        appearance = TextFieldAppearance.Password,
                        placeholder = "Enter your password",
                    )
                    if (state.isPasswordBlank) {
                        Text(
                            text = state.passwordError.orEmpty(),
                            style = Typography.caption,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 18.sp,
                            letterSpacing = 0.1.sp,
                            color = Color.Red,
                            modifier = Modifier
                                .alpha(0.7f)
                                .fillMaxWidth(),
                        )
                    }
                }
                Text(
                    text = "Forgot Password?",
                    style = Typography.caption,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 18.sp,
                    letterSpacing = 0.1.sp,
                    modifier = Modifier
                        .alpha(0.7f)
                        .fillMaxWidth(),
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLogin() {
    LoginScreen(
        state = LoginState(
            username = "username",
            password = "password",
            usernameError = null,
            passwordError = null,
        ),
        onAction = {}
    )
}