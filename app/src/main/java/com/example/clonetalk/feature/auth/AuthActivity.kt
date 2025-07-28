package com.example.clonetalk.feature.auth

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clonetalk.R
import com.example.clonetalk.ui.theme.CloneTalkTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloneTalkTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreen(modifier: Modifier = Modifier,
                viewModel: LoginViewModel = viewModel()) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var keepLoggedIn by remember { mutableStateOf(false) }
    // ViewModel 상태 구독 (예: 로그인 결과)
    val loginResult by viewModel.loginResult

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.fillMaxSize()
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
            TextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = { Text("전화번호") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next))
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = password, onValueChange = { password = it }, label = { Text("비밀번호") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done))
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = keepLoggedIn,
                    onClick = { keepLoggedIn = !keepLoggedIn }
                )
                Text("로그인 상태 유지")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* 로그인 처리 */
                viewModel.login(phoneNumber, password)

            }) {
                Text("로그인")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* 회원가입 화면 이동 */ }) {
                Text("회원가입")
            }

            // 로그인 결과 UI 표시 (선택사항)
            loginResult?.let { result ->
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "로그인 결과: $result", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
