package com.example.clonetalk.feature.auth

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.clonetalk.R

class SignupFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Composable
fun SignupScreen(viewModel: SignupViewModel){
    var phoneNumber by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var signupResult by viewModel.signupResult

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(painter = painterResource(id = R.drawable.kakaotalk_logo),
                contentDescription = "KakaoTalk Logo",
                modifier = Modifier.size(240.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = phoneNumber,
                onValueChange = {phoneNumber = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = password,
                onValueChange = {password = it},
                label = {Text("비밀번호")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {viewModel.signup(phoneNumber, password)}){ Text("회원가입")}
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}