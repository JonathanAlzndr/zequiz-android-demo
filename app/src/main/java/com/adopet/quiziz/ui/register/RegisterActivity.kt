package com.adopet.quiziz.ui.register

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.adopet.quiziz.R
import com.adopet.quiziz.databinding.ActivityRegisterBinding
import com.adopet.quiziz.ui.Result
import com.adopet.quiziz.ui.factory.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            val grade = binding.edtGrade.text.toString()

            if (username.isEmpty() || username.isBlank() ||
                password.isEmpty() || password.isBlank() ||
                grade.isBlank() || grade.isEmpty()
            ) {

            } else {
                viewModel.register(username, grade, password)
                    .observe(this@RegisterActivity) { response ->
                        when(response) {
                            is Result.Error -> {
                                //
                            }
                            Result.Loading -> {

                            }
                            is Result.Success -> {
                                //
                            }
                        }
                    }
            }
        }
    }
}