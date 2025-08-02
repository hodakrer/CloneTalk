package com.example.clonetalk.feature.auth

import RetrofitInstance
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    var signupResult = mutableStateOf<String?>(null)
        private set
    fun signup(phoneNumber: String, password: String){
        viewModelScope.launch{
            try{
                Log.d("SignupViewModel", "회원가입 시도 중")
                val response = RetrofitInstance.authService.signup(
                    SignupRequest(phoneNumber.trim(), password.trim())
                )
                Log.d("SignupViewModel", "회원가입 응답 수신: ${response.code()}")

                if(response.isSuccessful){
                    signupResult.value = response.body()?.token ?: "수신 없음"
                } else {
                    signupResult.value = "회원가입 실패: ${response.code()}"
                }
            } catch(e: Exception) {
                Log.e("SignupViewModel", "오류 발생: ${e.localizedMessage}", e)
                signupResult.value = "오류: ${e.localizedMessage}"
            }
        }
    }
}