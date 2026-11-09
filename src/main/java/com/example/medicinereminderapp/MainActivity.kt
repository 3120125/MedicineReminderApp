package com.example.medicinereminderapp

import android.os.Bundle
import android.content.Intent
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.DisposableEffect
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MedicineHomeScreen()
            }
        }
    }
}

@Composable
fun MedicineHomeScreen() {

    val context = LocalContext.current
    var savedMedication by remember {

        mutableStateOf(
            context.getSharedPreferences("medications", Context.MODE_PRIVATE)
                .getString("medicineName", "") ?: ""
        )
    }
    DisposableEffect(context) {
        val preferences = context.getSharedPreferences(
            "medications",
            Context.MODE_PRIVATE
        )

        savedMedication = preferences.getString(
            "medicineName",
            ""
        ) ?: ""

        onDispose { }
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Medicine Reminder",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Welcome to your Medicine Reminder App!",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(30.dp))


        Button(
            onClick = {
                val intent = Intent(
                    context,
                    AddMedicationActivity::class.java
                )
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Medication")
        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val intent = Intent(
                    context,
                    MedicationHistoryActivity::class.java
                )
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Medication History")
        }
        Button(
            onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "My medication reminder"
                    )
                }
                context.startActivity(
                    Intent.createChooser(shareIntent, "Share Medication")
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Share Medication")
        }
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "My Medications",
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = if (savedMedication.isNotBlank()) {
                savedMedication
            } else {
                "No medications added yet."
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MedicineHomeScreenPreview() {
    MaterialTheme {
        MedicineHomeScreen()
    }
}
