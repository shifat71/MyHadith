package com.shifat.myhadis.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun OtpScreen(navController: NavHostController) {

    Box(
        modifier = Modifier.fillMaxSize(),
        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth().padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = "",
                label = { Text(text = "Enter your OTP") },
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Submit")
            }
        }
    }

}
