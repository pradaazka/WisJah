package prada.habits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import prada.habits.ui.theme.HabitsTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitsTheme {
                var screen by remember { mutableStateOf("register") }

                when (screen) {
                    "register" -> RegisterScreen { screen = "login" }
                    "login" -> LoginScreen { screen = "mataram" }
                    "mataram" -> MataramChoiceScreen()
                }
            }
        }
    }
}

@Composable
fun RegisterScreen(onRegisterClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo_background), contentDescription = "Logo", modifier = Modifier.size(100.dp))
        Spacer(modifier = Modifier.height(20.dp))
        listOf("Email" to email, "Username" to username, "Password" to password).forEach { (label, value) ->
            OutlinedTextField(value = value, onValueChange = { if (label == "Email") email = it else if (label == "Username") username = it else password = it }, label = { Text(label) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
        }
        Button(onClick = onRegisterClick, modifier = Modifier.fillMaxWidth(), enabled = email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
            Text("Daftar")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Sudah punya akun?")
            Spacer(modifier = Modifier.width(4.dp))
            ClickableText(text = AnnotatedString("Masuk"), onClick = { onRegisterClick() }, style = MaterialTheme.typography.bodyMedium.copy(textDecoration = TextDecoration.Underline))
        }
    }
}

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo_background), contentDescription = "Logo", modifier = Modifier.size(100.dp))
        Spacer(modifier = Modifier.height(20.dp))
        listOf("Username" to username, "Password" to password).forEach { (label, value) ->
            OutlinedTextField(value = value, onValueChange = { if (label == "Username") username = it else password = it }, label = { Text(label) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
        }
        Button(onClick = onLoginClick, modifier = Modifier.fillMaxWidth()) {
            Text("Masuk")
        }
    }
}

@Composable
fun MataramChoiceScreen() {
    val options = listOf("Mataram Islam", "Mataram Hindu/Budha", "Masa Kolonial")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo_background), contentDescription = "Logo", modifier = Modifier.size(150.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text("Pilih destinasi wisata sejarah", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        options.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                RadioButton(selected = selectedOption == option, onClick = { selectedOption = option })
                Spacer(modifier = Modifier.width(8.dp))
                Text(option)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { /* START action */ }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
            Text("START")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    HabitsTheme { RegisterScreen { } }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    HabitsTheme { LoginScreen { } }
}

@Preview(showBackground = true)
@Composable
fun MataramChoiceScreenPreview() {
    HabitsTheme { MataramChoiceScreen() }
}
