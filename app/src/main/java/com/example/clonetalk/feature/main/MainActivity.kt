package com.example.clonetalk.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.clonetalk.feature.auth.AuthActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first


class MainActivity : ComponentActivity() {
    private val USER_PREFERENCES_NAME = "user_preferences"
    private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)
    private val JWT_TOKEN_KEY = stringPreferencesKey("jwtToken")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            val prefs = dataStore.data.first()
            val jwtToken = prefs[JWT_TOKEN_KEY] ?: ""

            if (jwtToken.isEmpty()) {
                // 토큰 없으면 AuthActivity로 화면 전환
                val intent = Intent(this@MainActivity, AuthActivity::class.java)
                startActivity(intent)
                finish() // MainActivity 종료해서 뒤로가기 방지
            } else {
                // 토큰이 있으면 정상 화면 보여주기
                setContent {
                    mainScreen(Modifier)
                }
            }
        }
    }
}

@Composable
fun mainScreen(modifier: Modifier){
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "Main Activity", modifier = modifier.padding(24.dp))
    }
}

/*
@Composable
@Preview
fun LoginScreen(modifier: Modifier = Modifier){
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)){
            Image(value = "", onValueChange = /*main image of kakaotalk*/)
            TextField(value = "Id", onValueChange = )
            TextField(value = "Password", onValueChange = )
            Button(onClick = { /*Login -> move to MainActivity*/ }) {

            }
            Button(onClick = {/*move to Signup Fragment*/}){}
            RadioButton(selected = , onClick = { /*로그인 유지/ 끄기*/ })
        }
    }
}*/