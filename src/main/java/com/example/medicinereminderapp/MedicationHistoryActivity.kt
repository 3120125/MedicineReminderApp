package com.example.medicinereminderapp

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.foundation.layout.*
    import androidx.compose.material3.*
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.unit.dp

    class MedicationHistoryActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContent {
                MedicationHistoryScreen()
            }
        }
    }

    @Composable
    fun MedicationHistoryScreen() {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Medication History",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Your medication history will appear here.")
        }
    }

