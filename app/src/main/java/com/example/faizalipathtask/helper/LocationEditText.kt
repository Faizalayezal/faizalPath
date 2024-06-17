package com.example.faizalipathtask.helper

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationEditText(
    hint: String,
    onDataChanged: (String) -> Unit,
    onclick: () -> Unit,
    value: String
) {
    onDataChanged.invoke(value)

    OutlinedTextField(
        placeholder = { Text(text = hint) },
        value = value,
        onValueChange = {

        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        enabled = false,
        readOnly = true,
        textStyle = TextStyle(color = Color.Black),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            disabledTextColor = Color.Black,
            disabledPlaceholderColor = Color.Black,
            disabledBorderColor = Color.Transparent,

            ),
        modifier = Modifier
            .clickable {
                onclick.invoke()
            }
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = MaterialTheme.shapes.small.copy(CornerSize(10.dp))
            )
            .width(300.dp)
    )


}