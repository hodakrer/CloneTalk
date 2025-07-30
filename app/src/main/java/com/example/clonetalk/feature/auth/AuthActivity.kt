package com.example.clonetalk.feature.auth

import LoginFragment
import LoginScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clonetalk.R
import com.example.clonetalk.ui.theme.CloneTalkTheme

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            CloneTalkTheme {
                val viewModel: LoginViewModel = viewModel()
                LoginScreen(viewModel = viewModel)
            }
        }
        setContentView(R.layout.activity_auth)

        // 최초 진입 시 LoginFragment를 붙임
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.auth_fragment_container, LoginFragment())
                .commit()
        }
    }
}