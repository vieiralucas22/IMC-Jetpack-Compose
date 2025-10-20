package com.example.imc.view

import android.icu.lang.UCharacter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc.datasource.Calculations
import com.example.imc.ui.theme.Blue
import com.example.imc.ui.theme.Red
import com.example.imc.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var isTextFieldError by remember { mutableStateOf(false) }
    var resultMessage by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "IMC Calculator", fontSize = 18.sp, color = White) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Blue
            )
        )
    })
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = White)
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Height (cm)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(20.dp, 100.dp, 0.dp, 0.dp)
                )

                Text(
                    text = "Weight (kg)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(0.dp, 100.dp, 20.dp, 0.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                OutlinedTextField(
                    value = height,
                    onValueChange = {
                        if (it.length <= 3)
                            height = it
                    },
                    label = {
                        Text(text = "Ex: 165")
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(20.dp, 0.dp, 0.dp, 20.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        focusedLabelColor = Blue,
                        focusedIndicatorColor = Blue,
                        cursorColor = Blue,
                        errorIndicatorColor = Red,
                        errorContainerColor = White
                    ),
                    isError = isTextFieldError
                )

                OutlinedTextField(
                    value = weight,
                    onValueChange = {
                        if (it.length <= 7)
                            weight = it
                    },
                    label = {
                        Text(text = "Ex: 70.5")
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(0.dp, 0.dp, 20.dp, 20.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        focusedLabelColor = Blue,
                        focusedIndicatorColor = Blue,
                        cursorColor = Blue,
                        errorIndicatorColor = Red,
                        errorContainerColor = White
                    ),
                    isError = isTextFieldError
                )
            }

            Button(
                onClick = {
                    Calculations.CalculateIMC(height = height, weight = weight) { message, isError ->
                        resultMessage = message
                        isTextFieldError = isError
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(70.dp, 0.dp)
            ) {
                Text(
                    text = "Calculate",
                    fontSize = 16.sp,
                    color = White,
                )
            }

            Text(
                text = resultMessage,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),

            )
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen()
}