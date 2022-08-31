package com.example.animalroom

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    modifier: Modifier,
    text: String,
    label: String,
    onTextChange: (String) -> Unit,
    imeAction: ImeAction,
    keyboardActions: KeyboardActions,
    iconId: Int,
    iconModifier: Modifier
) {
    TextField(
        modifier = modifier.padding(6.dp) ,
        value = text,
        onValueChange = { onTextChange(it) },
        label = { Text(text = label) },
        singleLine = true,
        leadingIcon = {
            Icon(
                painterResource(id = iconId),
                "Tf icon",
                modifier = iconModifier.size(40.dp)
            )
        } ,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = KeyboardType.Text),
        keyboardActions = keyboardActions
    )
}
