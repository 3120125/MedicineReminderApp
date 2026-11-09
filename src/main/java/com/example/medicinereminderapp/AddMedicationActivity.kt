package com.example.medicinereminderapp


import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AddMedicationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                AddMedicationScreen()
            }
        }
    }
}

@Composable
fun AddMedicationScreen() {

    var medicineName by rememberSaveable {
        mutableStateOf("")
    }

    var dosage by rememberSaveable {
        mutableStateOf("")
    }

    var instructions by rememberSaveable {
        mutableStateOf("")
    }

    var reminderTime by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Add Medication",
            fontSize = 28.sp
        )

        Text(
            text = "Enter your medication details"
        )

        OutlinedTextField(
            value = medicineName,
            onValueChange = { medicineName = it },
            label = { Text("Medicine Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dosage,
            onValueChange = { dosage = it },
            label = { Text("Dosage") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = instructions,
            onValueChange = { instructions = it },
            label = { Text("Instructions") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = reminderTime,
            onValueChange = { reminderTime = it },
            label = { Text("Reminder Time (e.g. 08:00)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (medicineName.isBlank() ||
                    dosage.isBlank() ||
                    reminderTime.isBlank()
                ) {
                    Toast.makeText(
                        context,
                        "Please complete the required fields",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val preferences = context.getSharedPreferences(
                        "medications" ,Context.MODE_PRIVATE
                    )
                    val previousMedicines = preferences.getString("medicineName", "") ?: ""

                    val allMedicines = if (previousMedicines.isBlank()) {
                        medicineName
                    } else {
                        "$previousMedicines\n$medicineName"
                    }

                    preferences.edit()
                        .putString("medicineName", allMedicines)
                        .apply()


                    Toast.makeText(
                        context,
                        "Medications details saved!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Medication")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddMedicationScreenPreview() {
    MaterialTheme {
        AddMedicationScreen()
    }
}
