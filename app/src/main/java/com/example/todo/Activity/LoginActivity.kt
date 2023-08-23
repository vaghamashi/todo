package com.example.todo.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.todo.MainActivity
import com.example.todo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)


        if (isLoggedIn()) {
            startMainActivity()
        }

        val db = SqliteDatabase(requireContext())
        val list = db.showData()

        binding.btnSignIn.setOnClickListener {

            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPass.text.toString()

            var flag = false

            for (i in 0 until list.size) {
                if (list[i].email == email && list[i].password == pass) {
                    flag = true
                    break
                }
            }

            if (flag) {
                saveLoginState(true)
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                startMainActivity()
            } else {
                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun saveLoginState(isLoggedIn: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    private fun startMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }



    }


}