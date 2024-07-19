package com.hav.chat_app.components

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hav.chat_app.R
import com.hav.chat_app.model.Contact
import com.hav.chat_app.model.Message
import com.hav.chat_app.ui.theme.Message1
import com.hav.chat_app.ui.theme.Message2
import com.hav.chat_app.utils.Trans.Companion.timestampToTime


@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Black,
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 24.sp,
            fontWeight = Bold
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun NormalTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String

) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        modifier = Modifier
            .padding(8.dp)
    )
}

@Composable
fun IconTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    iconId: Int
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconId),
                modifier = Modifier.height(24.dp),
                contentDescription = null
            )
        }
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    iconId: Int
) {

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier.height(24.dp)
            )
        },
        trailingIcon = {
            val iconImage = if (passwordVisibility.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            Icon(
                imageVector = iconImage,
                contentDescription = null,
                modifier = Modifier.clickable {
                    passwordVisibility.value = !passwordVisibility.value
                }
            )
        },
        visualTransformation = if (passwordVisibility.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun CButton(login: () -> Unit, text: String) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Button(
        onClick = {
            keyboardController?.hide()
            login()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Red
        ),
    ) {
        Text(text = text)
    }
}

@Composable
fun MessageBox(message: Message, isOwned: Boolean) {
    Log.d("Vu", "isOwned: $isOwned")
    val textAlign = if (isOwned) TextAlign.End else TextAlign.Start

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (!isOwned) Alignment.Start else Alignment.End
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isOwned) Message1 else Message2,
                    RoundedCornerShape(100.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message.content, style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                ),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp)
            )
        }
        Text(
            text = timestampToTime(message.timestamp),
            style = TextStyle(
                color = Gray,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
        )
    }


}

@Composable
fun ChatBar(message: String, onMessageChange: (String) -> Unit, onSendMessage: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        NormalTextField(
            value = message,
            onValueChange = onMessageChange,
            label = "Type your message"
        )

        CButton(onSendMessage, "Send")
    }

}

//@Preview(showSystemUi = true)
@Composable
fun ChatBarPreview() {
    ChatBar(message = "hello", onMessageChange = {}, onSendMessage = {})

}

@Composable
fun ContactBox(contact: Contact, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 5.dp)
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.images),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Column() {
                    Text(
                        text = "Name 1", style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row() {
                        Text(text = "You: Last Message 1")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Time 1")
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Gray)
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewContactBox() {
    ContactBox(contact = Contact("1", 1), onClick = {})

}


@Composable
fun SignUpText(onSignUpClick: () -> Unit) {
    val annotationString = buildAnnotatedString {
        append("Don't have an account? ")
        pushStyle(
            SpanStyle(
                color = Color.Red,
                fontWeight = Bold,
                textDecoration = TextDecoration.Underline
            )
        )
        append("Sign up")
        pop()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        ClickableText(
            text = annotationString,
            onClick = {
                onSignUpClick()
            }
        )
    }
}


