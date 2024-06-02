package com.shifat.myhadis.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shifat.myhadis.model.Contact
import com.shifat.myhadis.ui.common.BottomBar
import com.shifat.myhadis.ui.common.TopBar

@Composable
fun ContactScreen(navController: NavHostController) {
    val viewModel: ContactsViewModel = hiltViewModel()
    val contacts by viewModel.contactList.collectAsState()
    val listState = rememberLazyListState()
    val topBarVisibleState = remember { mutableStateOf(true) }

    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.error.collectAsState().value

    val context = LocalContext.current

    if(error!=""){
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        viewModel.resetError()
    }

    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 4.dp
            )
        }
    }

    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        topBarVisibleState.value = listState.firstVisibleItemScrollOffset <= 0
    }

    var isAddContactDialogOpen by remember { mutableStateOf(false) }
    var isEditContactDialogOpen by remember { mutableStateOf(false) }
    var selectedContact by remember { mutableStateOf<Contact?>(null) }
    Scaffold(
        topBar = {TopBar(topBarVisibleState = topBarVisibleState , ScreenName = "Contacts")},
        bottomBar = { BottomBar(navController, Screen.ContactsScreen.name) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isAddContactDialogOpen = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Contact")
            }
        }
    ) {it ->
        Surface(
            modifier = Modifier.padding(it),
            color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.padding(
                start = 15.dp,
                end= 15.dp
            )) {
                LazyColumn(state = listState) {
                    items(contacts) { contact ->
                        ContactCard(contact) {
                            selectedContact = it
                            isEditContactDialogOpen = true
                        }
                       // Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                if (isAddContactDialogOpen) {
                    AddContactDialog(onClose = { isAddContactDialogOpen = false }) { name, phoneNumber ->
                        viewModel.addContact(name, phoneNumber)
                    }
                }

                if (isEditContactDialogOpen) {
                    EditContactDialog(
                        contact = selectedContact!!,
                        onClose = { isEditContactDialogOpen = false },
                        onEdit = { name, phoneNumber ->
                            viewModel.updateContact(selectedContact!!, Contact(userNumber = selectedContact!!.userNumber, name = name, number = phoneNumber))
                        },
                        onRemove = {
                            viewModel.removeContact(selectedContact!!)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun ContactCard(contact: Contact, onClick: (Contact) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(contact) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Person, contentDescription = "Person Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Phone, contentDescription = "Phone Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = contact.number,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}



@Composable
fun EditContactDialog(contact: Contact, onClose: () -> Unit, onEdit: (String, String) -> Unit, onRemove: () -> Unit) {
    var name by remember { mutableStateOf(contact.name) }
    var phoneNumber by remember { mutableStateOf(contact.number) }

    Dialog(onDismissRequest = onClose) {
        Column(modifier = Modifier
            .background(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.surface)
            .padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(
                    onClick = {
                        onEdit(name, phoneNumber)
                        onClose()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        onRemove()
                        onClose()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Remove")
                }
            }
        }
    }
}

@Composable
fun AddContactDialog(onClose: () -> Unit, onAdd: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onClose) {
        Column(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surface
                )
                .padding(18.dp)

        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                onAdd(name, phoneNumber)
                onClose()
            }) {
                Text("Add Contact")
            }
        }
    }
}