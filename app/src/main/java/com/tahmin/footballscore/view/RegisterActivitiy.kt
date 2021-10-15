package com.tahmin.footballscore.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tahmin.footballscore.model.user.RegisterUserItem
import com.tahmin.footballscore.pages.LoginScreen
import com.tahmin.footballscore.viewModel.RegisterFragmentViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.tahmin.footballscore.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivitiy : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var registerFragmentViewModel: RegisterFragmentViewModel

    var userMail: String? = ""
    var userPassword: String? = ""
    var userName: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        registerFragmentViewModel = ViewModelProviders.of(this).get(RegisterFragmentViewModel::class.java)

        sharedPreferences = getSharedPreferences("com.example.footballscore", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        userMail = sharedPreferences.getString("userMail", "")
        userPassword = sharedPreferences.getString("userPassword", "")
        userName = sharedPreferences.getString("userName", "")

        binding.txtKayitYaptiysaniz.setOnClickListener {
            intent = Intent(applicationContext, LoginScreen::class.java)
            startActivity(intent)
        }

        observeLiveData()
        binding.apply {

            btnKayitOl.setOnClickListener {
                if (isValid()) {
                    val MailAdresi: String = kayitMail.text.toString().trim()
                    val sifre: String = kayitSifre.text.toString().trim()
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(MailAdresi, sifre)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    registerFragmentViewModel.newUserRegister(RegisterUserItem(MailAdresi, kayitKullaniciAdi.text.toString(), sifre, firebaseUser.uid))
                                    Toast.makeText(this@RegisterActivitiy, "kayıt işlemini başarıyla tamamladınız.", Toast.LENGTH_LONG).show()
                                    val intent = Intent(this@RegisterActivitiy, LoginScreen::class.java)

                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("user_uid", firebaseUser.uid)
                                    intent.putExtra("email_id", MailAdresi)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@RegisterActivitiy, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                                }
                            })
                }
            }
        }
    }

    private fun isValid(): Boolean {
        var isCheckNull = true
        if (kayit_mail.text.isEmpty()) {
            Toast.makeText(this@RegisterActivitiy, "Lütfen Mail adresinizi giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        } else if (kayit_sifre.text.isEmpty()) {
            Toast.makeText(this@RegisterActivitiy, "Lütfen Sifrenizi giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        } else if (kayit_kullanici_adi.text.isEmpty()) {
            Toast.makeText(this@RegisterActivitiy, "Lütfen kullanıcı adınızı  giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        } else if (isEmailValid(kayit_mail.text.toString()) == false) {
            Toast.makeText(this@RegisterActivitiy, "Lütfen Mail Adresiniz doğru formatta giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        } else if (kayit_sifre.text.length < 6) {
            Toast.makeText(this@RegisterActivitiy, "Şifreniz en az 6 karakterden oluşmaktadır.", Toast.LENGTH_LONG).show()
            isCheckNull = false

        }

        return isCheckNull
    }

    fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    companion object {
        const val IS_CHECKED = "isChecked"
    }

    fun observeLiveData() {
        registerFragmentViewModel.kayitOlResponse.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })
    }
}
