package com.example.clonetalk.feature.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var loginResult = mutableStateOf<String?>(null)
        private set

    fun login(phoneNumber: String, password: String) {
        viewModelScope.launch {
            try {
                Log.d("LoginViewModel", "로그인 시도 중...") // ★ 로그 추가

                val response = RetrofitInstance.authService.login(
                    LoginRequest(phoneNumber = phoneNumber.trim(), password = password.trim())
                )

                Log.d("LoginViewModel", "응답 수신: ${response.code()}") // ★ 응답 로그

                if (response.isSuccessful) {
                    loginResult.value = response.body()?.token ?: "토큰 없음"
                } else {
                    loginResult.value = "로그인 실패: ${response.code()}"
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "오류 발생: ${e.localizedMessage}", e) // ★ 예외 로그
                loginResult.value = "오류: ${e.localizedMessage}"
            }
        }
    }
}