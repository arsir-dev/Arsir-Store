package com.arsir.dev.arsir.uikit.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.arsir.dev.arsir.uikit.theme.Typography

@Composable
fun AgreementText(modifier: Modifier = Modifier) {
    val baseColor = Color(0xFF9E9E9E)
    val linkColor = Color(0xFF4E7C3A)

    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        style = Typography.caption,
        text = buildAnnotatedString {
            withStyle(
                SpanStyle(color = baseColor)
            ) {
                append("By signing up, you agree to our ")
            }

            withStyle(
                SpanStyle(
                    color = linkColor,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append("Terms of service")
            }

            withStyle(
                SpanStyle(color = baseColor)
            ) {
                append("\nand ")
            }

            withStyle(
                SpanStyle(
                    color = linkColor,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append("Privacy policy")
            }
        }
    )
}
