import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.focusable
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import com.shifat.myhadis.ui.screens.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    val viewModel: AuthViewModel = hiltViewModel()

    var phoneNumber by remember { mutableStateOf("") }
    val loginError by viewModel.loginError.collectAsState()
    val isLoading by viewModel.isLoginLoading.collectAsState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var isTextFieldFocused by remember { mutableStateOf(false) }

    if (loginError) {
        LaunchedEffect(key1 = loginError) {
            Toast.makeText(context, "Couldn't Subscribe the User, Please Try Again!", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier.padding(
            top = if(isTextFieldFocused) 200.dp else 300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .animateContentSize(), // Add this modifier
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { newPhoneNumber: String -> phoneNumber = newPhoneNumber },
                label = { Text("Enter your mobile number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        isTextFieldFocused = focusState.isFocused
                    },
                keyboardActions = KeyboardActions(onDone = {
                    isTextFieldFocused = false
                    focusManager.clearFocus()
                }),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done)

            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.login(navController= navController, phoneNumber = phoneNumber)
                          },

                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Submit")
            }
            if (isLoading) {
                Spacer(modifier = Modifier.height(16.dp))
                LinearProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}