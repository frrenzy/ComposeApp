package com.frrenzy.composeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactCard(contact: Contact) {
    val name = contact.surname?.let { "${contact.name} $it" } ?: contact.name
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .height(50.dp)
        ) {
            when (contact.imageRes) {
                null -> RoundInitials(contact)
                else -> Image(
                    painter = painterResource(contact.imageRes),
                    contentDescription = stringResource(R.string.user_avatar),
                )
            }
        }
        Text(text = name, fontSize = 20.sp, fontWeight = FontWeight(500))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = contact.familyName, fontSize = 22.sp)
            if (contact.isFavorite) {
                Icon(
                    painter = painterResource(android.R.drawable.star_big_on),
                    contentDescription = null,
                )
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            InfoRow(R.string.phone, contact.phone)
            InfoRow(R.string.address, contact.address)
            contact.email?.let { InfoRow(R.string.email, it) }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactCardPreview1() {
    Column {
        Text(
            text = stringResource(R.string.title),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(5.dp, 30.dp, 5.dp, 10.dp),
            color = Color.White,
            fontSize = 16.sp,
        )
        ContactCard(
            Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                email = "ELukashin@practicum.ru"
            )
        )
    }
}

@Preview(
    showSystemUi = true, device = "spec:width=673dp,height=841dp,orientation=landscape",
    showBackground = false
)
@Composable
fun ContactCardPreview2() {
    Column {
        Text(
            text = stringResource(R.string.title),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(5.dp, 30.dp, 5.dp, 10.dp),
            color = Color.White,
            fontSize = 16.sp,
        )
        ContactCard(
            Contact(
                name = "Василий",
                familyName = "Кузякин",
                isFavorite = false,
                imageRes = R.drawable.shrek,
                phone = "---",
                address = "Ивановская область, дер. Крутово, д. 4",
            )
        )
    }
}
