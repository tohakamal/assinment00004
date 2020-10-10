package com.example.assinment4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.assinment4.Data.AppPreferance
import com.example.assinment4.Data.AppPreferenceImpl
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.et_age
import kotlinx.android.synthetic.main.activity_main.et_name
import kotlinx.android.synthetic.main.activity_main.et_weight
import kotlinx.android.synthetic.main.activity_main.notiEnable

class MainActivity : AppCompatActivity() {

 private lateinit var appPreferance: AppPreferance
    var checkValue = false
    var gender =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title="Profile Settings"
        appPreferance = AppPreferenceImpl(this)

        //checkBox
        checkBoxImp()

        //radioButton
        radioButtonImp()

        // Retrieve Data
        retrieveData()


        btn_save.setOnClickListener {
            checkEmpty()
        }
    }


    fun retrieveData(){
        et_name.setText(appPreferance.getString(AppPreferance.NAME))
        et_age.setText(appPreferance.getInt(AppPreferance.AGE).toString())
        et_weight.setText(appPreferance.getFloat(AppPreferance.WEIGHT).toString())
        et_url.setText(appPreferance.getString(AppPreferance.URL))

    }


    fun saveData(){


        //add sharePreferences
        appPreferance.setString(AppPreferance.NAME,et_name.text.toString())
        appPreferance.setInt(AppPreferance.AGE,et_age.text.toString().toInt())
        appPreferance.setFloat(AppPreferance.WEIGHT,et_weight.text.toString().toFloat())
        appPreferance.setString(AppPreferance.URL, et_url.text.toString())
        appPreferance.setBoolean(AppPreferance.NOTIFICATION,checkValue)
        appPreferance.setString(AppPreferance.GENDER,gender)

        //intent after save
        val intent = Intent(this,EditProfile::class.java)
        startActivity(intent)
    }

    fun checkBoxImp(){
        notification_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(buttonView.isChecked){
                notiEnable.text="Enable"
                checkValue = true
            }else
            {
                notiEnable.text="Not Enable"
                checkValue = false
            }
        }

    }

    fun radioButtonImp(){
        radio_group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.male)
            {
                gender = male.text.toString()
            }
            if (checkedId == R.id.female)
            {
                gender = female.text.toString()
            }

        }
    }


    fun checkEmpty(){

        if (TextUtils.isEmpty(et_name.text.toString())){
            et_name.error = "Please Enter Name"
        }
        else if (TextUtils.isEmpty(et_age.text.toString())){
            et_age.error = "Please Enter Age"
        }


        else if (TextUtils.isEmpty(et_weight.text.toString())){
            et_weight.error = "Please Enter Weight"
        }

        else if (TextUtils.isEmpty(et_url.text.toString())){
            et_url.error = "Please Enter Url"
        }

        else
            saveData()
        Toast.makeText(this,"Data Are Saved", Toast.LENGTH_SHORT)

       }




}
