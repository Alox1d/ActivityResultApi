package com.sumin.activityresultapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class UsernameActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var saveUsernameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)
        initViews()
        saveUsernameButton.setOnClickListener {
            val username = usernameEditText.text.trim().toString()
            saveUsername(username)
            finish()
        }
    }

    private fun initViews() {
        usernameEditText = findViewById(R.id.username_edittext)
        saveUsernameButton = findViewById(R.id.save_username_button)
    }

    private fun saveUsername(username: String) {
        Intent().apply {
            putExtra(EXTRA_USERNAME, username)
            setResult(RESULT_OK, this)
        }
    }

    companion object {

        const val EXTRA_USERNAME = "username"

        fun newIntent(context: Context) = Intent(context, UsernameActivity::class.java)
    }
}
