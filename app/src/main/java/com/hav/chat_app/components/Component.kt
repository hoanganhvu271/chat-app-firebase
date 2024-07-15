package com.hav.chat_app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hav.chat_app.model.Contact
import com.hav.chat_app.model.Message
import com.hav.chat_app.ui.theme.Message1
import com.hav.chat_app.ui.theme.Message2


@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Black,
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 24.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        ),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center
    )
}

@Composable
fun CTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        modifier = Modifier
            .padding(8.dp)
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
            contentColor = Color.White
        ),
    ) {
        Text(text = text)
    }
}

@Composable
fun MessageBox(message: Message, isOwned : Boolean) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isOwned) Message1 else Message2
        )
    ) {
        Text(
            text = message.content,
            textAlign = if (isOwned) TextAlign.End else TextAlign.Start,
            modifier = Modifier.padding(8.dp),
            color = Color.Black
        )
    }

}

@Composable
fun ChatBar(message : String, onMessageChange: (String) -> Unit, onSendMessage: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        CTextField(
            value = message,
            onValueChange = onMessageChange,
            label = "Type your message",
            keyboardType = KeyboardType.Text
        )

        CButton(onSendMessage, "Send")
    }

}

@Preview(showSystemUi = true)
@Composable
fun ChatBarPreview() {
    ChatBar(message = "hello", onMessageChange = {}, onSendMessage = {})

}

@Composable
fun  ContactBox(contact : Contact, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },

        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Text(
            text = contact.userId,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(8.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = Bold
            ),
            color = Color.Black
        )

        Text(
            text = contact.lastContactTimeStamp.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(8.dp),
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = Bold
            ),
            color = Color.Gray
        )
    }
}


//@Preview(showSystemUi = true)
//@Composable
//fun ContactPreview() {
//    ContactBox(Contact("123456", 12345))
//}

@Preview(showSystemUi = true)
@Composable
fun ChatPreview() {
//    MessageBox(message = Message("1", "1", "Hello", 1, true), isOwned = false)
    MessageBox(message =
    Message("1",
        "1",
        "Hello",
        1,
        true),
        isOwned = true)
}
