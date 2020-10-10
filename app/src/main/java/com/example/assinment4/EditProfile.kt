package com.example.assinment4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.assinment4.Data.AppPreferance
import com.example.assinment4.Data.AppPreferenceImpl
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.et_age
import kotlinx.android.synthetic.main.activity_edit_profile.et_name
import kotlinx.android.synthetic.main.activity_edit_profile.et_weight
import kotlinx.android.synthetic.main.activity_edit_profile.notiEnable
import kotlinx.android.synthetic.main.activity_main.*

class EditProfile : AppCompatActivity() {
    private lateinit var appPreferance: AppPreferance

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        supportActionBar!!.setTitle("Profile")
        appPreferance = AppPreferenceImpl(this)
        showImage()
        showData()
        editBtn.setOnClickListener{


            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    fun  showData(){
        et_name.text = "Name: "+appPreferance.getString(AppPreferance.NAME)
        et_age.text = "Age: "+appPreferance.getInt(AppPreferance.AGE).toString()
        et_weight.text = "Weight: "+appPreferance.getFloat(AppPreferance.WEIGHT).toString()
        notiEnable.text = appPreferance.getBoolean(AppPreferance.NOTIFICATION).toString()
        gender_item.text = appPreferance.getString(AppPreferance.GENDER)

    }
    fun showImage(){
        val url = appPreferance.getString(AppPreferance.URL)
        Glide.with(this)
            .load(url)
            .override(300,200)
            .centerCrop()
            .placeholder(R.drawable.android)
            .error(R.drawable.imagenotfound)
            .into(imageView)
    }
}
