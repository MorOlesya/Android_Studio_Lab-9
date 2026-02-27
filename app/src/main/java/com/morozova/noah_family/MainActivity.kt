package com.morozova.noah_family

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.morozova.noah_family.ui.theme.NoahFamilyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoahFamilyTheme {
                Construct()
            }
        }
    }
}



@Composable
fun Construct() {
    val personList = remember { Datasource().loadAnimeList() }
    var currentPage by remember { mutableIntStateOf(0) }
    val currentPerson = personList[currentPage]

    Image(currentPerson)
    Text(currentPerson)
    Buttons(
        currentIndex = currentPage,
        total = personList.size,
        previous = {
            currentPage = if (currentPage > 0) currentPage - 1 else personList.size - 1
        },
        next = {
            currentPage = if (currentPage < personList.size - 1) currentPage + 1 else 0
        }
    )
}

@Composable
fun Image(person: Person) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 130.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(person.imageResourceId),
            stringResource(id = person.titleResourceId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(230.dp)
                .width(230.dp)
        )
    }
}

@Composable
fun Text(person: Person) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 380.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = person.descriptionResourceId),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun Buttons(
    currentIndex: Int,
    total: Int,
    previous: () -> Unit,
    next: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 480.dp)
            .padding(horizontal = 16.dp)
    ) {
        Button(
            onClick = previous,
            enabled = true,
            modifier = Modifier
                .padding(start = 10.dp)
                .width(120.dp)
        ) {
            Text(
                text = "Назад",
                color = Color.Black
            )
        }
        Button(
            onClick = next,
            enabled = true,
            modifier = Modifier
                .padding(start = 80.dp)
                .width(120.dp)
        ) {
            Text(
                text = "Вперёд",
                color = Color.Black
            )
        }
    }
}

