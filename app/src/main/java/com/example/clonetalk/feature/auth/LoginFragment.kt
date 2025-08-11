package com.example.clonetalk.feature.auth


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.clonetalk.R
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.example.clonetalk.ui.theme.CloneTalkTheme
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        (view as ComposeView).setContent {
            CloneTalkTheme {
                LoginScreen(viewModel = viewModel, navController = navController)
            }
        }
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel,
                navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var keepLoggedIn by remember { mutableStateOf(false) }
    val loginResult by viewModel.loginResult

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.kakaotalk_logo),
                contentDescription = "KakaoTalk Logo",
                modifier = Modifier.size(240.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("전화번호") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("비밀번호") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = keepLoggedIn,
                    onClick = { keepLoggedIn = !keepLoggedIn }
                )
                Text("로그인 상태 유지")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.login(phoneNumber, password)
            }) {
                Text("로그인")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                navController.navigate(R.id.action_login_to_signup)
            }) {
                Text("회원가입")
            }

            loginResult?.let { result ->
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "로그인 결과: $result", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}