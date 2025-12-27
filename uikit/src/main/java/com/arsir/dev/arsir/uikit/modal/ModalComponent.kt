package com.arsir.dev.arsir.uikit.modal

import androidx.compose.foundation.Image
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.arsir.dev.arsir.uikit.button.Button
import com.arsir.dev.arsir.uikit.button.ButtonAppearance
import com.arsir.dev.arsir.uikit.theme.Typography

@Composable
fun ModalGeneral(
    message: String,
    onDismiss: () -> Unit,
) {
    AppModal {
        Image(
            imageVector = Icons.Outlined.Warning,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color(0xFFFFAC27))
        )

        Text(
            text = "Oppss",
            style = Typography.subtitle1,
            lineHeight = 18.sp,
            letterSpacing = 0.1.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = message,
            style = Typography.caption,
            lineHeight = 18.sp,
            letterSpacing = 0.1.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Normal
        )

        Button(
            text = "Oke",
            appearance = ButtonAppearance.Primary,
            onClick = onDismiss
        )
    }
}

@Composable
fun ModalSuccess(
    message: String,
    onDismiss: () -> Unit,
) {
    AppModal {
        Image(
            imageVector = Icons.Outlined.Info,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color(0xFF36DA7F))
        )

        Text(
            text = "Success",
            style = Typography.subtitle1,
            lineHeight = 18.sp,
            letterSpacing = 0.1.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = message,
            style = Typography.caption,
            lineHeight = 18.sp,
            letterSpacing = 0.1.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Normal
        )

        Button(
            text = "Oke",
            appearance = ButtonAppearance.Primary,
            onClick = onDismiss
        )
    }
}

@Composable
fun ModalLoading(modifier: Modifier = Modifier) {
    AppModal(modifier = modifier) {
        CircularProgressIndicator(color = Color(0xFFB8E06E))
    }
}

@Preview
@Composable
private fun PreviewModelGeneralError() {
    ModalGeneral("Mohon maaf terjadi kesalahan") { }
}

@Preview
@Composable
private fun PreviewModelSuccess() {
    ModalSuccess("Berhasil") { }
}

@Preview
@Composable
private fun PreviewModelLoading() {
    ModalLoading()
}