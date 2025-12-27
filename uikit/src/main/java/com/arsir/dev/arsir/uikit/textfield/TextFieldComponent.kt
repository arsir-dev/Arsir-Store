package com.arsir.dev.arsir.uikit.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isError: Boolean= false,
    leadingIcon: ImageVector? = null,
    appearance: TextFieldAppearance = TextFieldAppearance.Normal,
    enabled: Boolean = true,
    isReadOnly: Boolean = false,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val backgroundColor = Color(0xFFF6F6F6)
    val iconColor = Color(0xFF9E9E9E)
    val textColor = Color(0xFF1C1C1C)
    val placeholderColor = Color(0xFF9E9E9E)

    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        isError = isError,
        readOnly = isReadOnly,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        singleLine = true,
        shape = RoundedCornerShape(30.dp),
        textStyle = LocalTextStyle.current.copy(color = textColor),
        placeholder = {
            Text(
                text = placeholder,
                color = placeholderColor
            )
        },
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor
                )
            }
        },
        trailingIcon = when (appearance) {
            TextFieldAppearance.Password -> {
                {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Icon(
                            imageVector =
                            if (passwordVisible)
                                Icons.Outlined.Visibility
                            else
                                Icons.Outlined.VisibilityOff,
                            contentDescription = null,
                            tint = iconColor
                        )
                    }
                }
            }

            TextFieldAppearance.Search -> {
                if (value.isNotEmpty()) {
                    {
                        IconButton(
                            onClick = { onValueChange("") }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null,
                                tint = iconColor
                            )
                        }
                    }
                } else null
            }

            else -> null
        },
        visualTransformation =
        if (appearance == TextFieldAppearance.Password && !passwordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.primary
        )
    )
}