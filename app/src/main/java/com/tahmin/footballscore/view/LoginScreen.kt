package com.tahmin.footballscore.pages

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.tahmin.footballscore.view.RegisterActivitiy
import com.tahmin.footballscore.view.MainActivity
import com.tahmin.footballscore.viewModel.ShredPreferenc
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.tahmin.footballscore.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*


class LoginScreen : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    var userMail: String? = ""
    var userPassword: String? = ""
    var isRemembered: Boolean = false
    var mailAdresi = ""
    var sifre = ""
    var prefHelper = ShredPreferenc()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)


        sharedPreferences = getSharedPreferences("com.example.footballscore", Context.MODE_PRIVATE)

        isRemembered = sharedPreferences.getBoolean(IS_CHECKED, false)
        userMail = sharedPreferences.getString(USER_NAME, "")
        userPassword = sharedPreferences.getString(USER_PASSWORD, "")
        initScreen()
        binding.txtKayitYapmadiysaniz.setOnClickListener {
            intent = Intent(applicationContext, RegisterActivitiy::class.java)
            startActivity(intent)
        }
        binding.apply {
            btnGirisYapiniz.setOnClickListener {
                if (isValid()) {
                    mailAdresi = edtMailAdresi.text.toString().trim()
                    sifre =edtSifre.text.toString().trim()
                    control()

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(mailAdresi, sifre).addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            prefHelper.save(this@LoginScreen,"userId", firebaseUser.uid)

                            val intent = Intent(this@LoginScreen, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("email_id", mailAdresi)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginScreen, "Böyle bir kullanıcı bulunamadı, mail adresi veya şifrenizi doğru girdiğinizden emin olun.".toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
    private fun control() {
        sharedPreferences.edit {
            if (binding.cbBeniUnutma.isChecked) {
                this.putString(USER_NAME, mailAdresi)
                this.putString(USER_PASSWORD, sifre)
                this.putBoolean(IS_CHECKED, cb_beni_unutma.isChecked)
                this.apply()
            } else {
                this.putString(USER_NAME, "")
                this.putString(USER_PASSWORD, "")
                this.putBoolean(IS_CHECKED, cb_beni_unutma.isChecked)
                this.apply()
            }
        }
    }

    private fun initScreen() {
        binding.edtMailAdresi.setText(userMail)
        binding.edtSifre.setText(userPassword)
        binding.cbBeniUnutma.isChecked = isRemembered
    }

    private fun isValid(): Boolean {
        var isCheckNull = true
        if (edt_mail_adresi.text.isEmpty()) {
            Toast.makeText(this@LoginScreen, "Lütfen Mail adresinizi giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
        if (edt_sifre.text.isEmpty()) {
            Toast.makeText(this@LoginScreen, "Lütfen Sifrenizi giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
        if (isEmailValid(edt_mail_adresi.text.toString()) == false) {
            Toast.makeText(this@LoginScreen, "Lütfen Mail Adresiniz doğru formatta giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
        if (edt_sifre.text.length < 6) {
            Toast.makeText(this@LoginScreen, "Şifreniz en az 6 karakterden oluşmaktadır.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
        return isCheckNull
    }

    fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    companion object {
        const val IS_CHECKED = "isChecked"
        const val USER_NAME = "userMail"
        const val USER_PASSWORD = "userPassword"
    }
}