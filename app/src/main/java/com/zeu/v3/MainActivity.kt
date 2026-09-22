package com.zeu.v3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

data class Member(var name: String, var saved: Double=0.0, var fee: Double=0.0, var loan: Double=0.0)
data class Record(val date: String, val member: String, val type: String, val amount: Double)

class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContent { VillageV3() }
 }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VillageV3(){
 var members by remember { mutableStateOf(listOf(Member("Amina"), Member("James"), Member("Sarah"))) }
 var records by remember { mutableStateOf(listOf<Record>()) }
 var newName by remember { mutableStateOf("") }
 var feeAmount by remember { mutableStateOf("1000") }
 var isStarted by remember { mutableStateOf(false) }
 var isClosed by remember { mutableStateOf(false) }
 val today = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
 var box by remember { mutableStateOf(1250000.0) }
 var social by remember { mutableStateOf(320000.0) }
 val FIXED = 5000.0
 val LOAN_AMT = 150000.0
 val INTEREST = 0.10

 Scaffold(topBar={TopAppBar(title={Text("Village Savings V3 - $today")})}){pad->
  Column(Modifier.padding(pad).padding(12.dp)){
   // MEETING CONTROL - V3 RULE
   Card(colors=CardDefaults.cardColors(containerColor=if(isClosed) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primary