package com.example.clonetalk.feature.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clonetalk.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //컴포즈와 frag 둘 다 호출하는 의미 없어서 컴포즈 코드 제거.
        setContentView(R.layout.activity_auth)


    }
}
//