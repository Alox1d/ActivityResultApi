package com.sumin.activityresultapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var getUsernameButton: Button
    private lateinit var usernameTextView: TextView
    private lateinit var getImageButton: Button
    private lateinit var imageFromGalleryImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getUsernameButton.setOnClickListener {
            UsernameActivity.newIntent(this).apply {
                startActivityForResult(this, RC_USERNAME)
            }
        }
        getImageButton.setOnClickListener {
            Intent(Intent.ACTION_PICK).apply {
                type = "image/*" // MIME types
                startActivityForResult(this, RC_IMAGE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_USERNAME && resultCode == RESULT_OK){
            val username = data?.getStringExtra(UsernameActivity.EXTRA_USERNAME) ?: ""
            usernameTextView.text = username
        }
        if (requestCode == RC_IMAGE && resultCode == RESULT_OK){
            val uri = data?.data
            imageFromGalleryImageView.setImageURI(uri)
        }
    }

    private fun initViews() {
        getUsernameButton = findViewById(R.id.get_username_button)
        usernameTextView = findViewById(R.id.username_textview)
        getImageButton = findViewById(R.id.get_image_button)
        imageFromGalleryImageView = findViewById(R.id.image_from_gallery_imageview)
    }

    companion object {
        private const val RC_USERNAME = 100
        private const val RC_IMAGE = 101
    }
}
