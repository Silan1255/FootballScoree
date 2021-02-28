package com.example.footballscore

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.footballscore.databinding.ActivityGirisEkraniBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_giris_ekrani.*
import kotlinx.android.synthetic.main.activity_kayit_ol.*


class GirisEkrani : AppCompatActivity() {

    lateinit var binding: ActivityGirisEkraniBinding
    lateinit var sharedPreferences: SharedPreferences
    var userMail: String? = ""
    var userPassword: String? = ""
    var isRemembered: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGirisEkraniBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("com.example.footballscore", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        isRemembered = sharedPreferences.getBoolean(IS_CHECKED, false)
        userMail = sharedPreferences.getString("userMail", "")
        userPassword = sharedPreferences.getString("userPassword", "")

        initScreen()

        binding.txtKayitYapmadiysaniz.setOnClickListener {
            intent = Intent(applicationContext, KayitOl::class.java)
            startActivity(intent)
        }

        binding.apply {
            btn_giris_yapiniz.setOnClickListener {
                if (isValid()) {
                    val MailAdresi: String = edt_mail_adresi.text.toString().trim()
                    val sifre: String = edt_sifre.text.toString().trim()

                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(MailAdresi, sifre)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                Toast.makeText(this@GirisEkrani, "kayıt işlemini başarıyla tamamladınız.", Toast.LENGTH_LONG).show()

                                val intent = Intent(this@GirisEkrani, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id", MailAdresi)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@GirisEkrani, "Böyle bir kullanıcı bulunamadı, mail adresi veya şifrenizi doğru girdiğinizden emin olun.".toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                }
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
            Toast.makeText(this@GirisEkrani, "Lütfen Mail adresinizi giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
        if (edt_sifre.text.isEmpty()) {
            Toast.makeText(this@GirisEkrani, "Lütfen Sifrenizi giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
        if (isEmailValid(edt_mail_adresi.text.toString()) == false) {
            Toast.makeText(this@GirisEkrani, "Lütfen Mail Adresiniz doğru formatta giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
        if (edt_sifre.text.length < 6) {
            Toast.makeText(this@GirisEkrani, "Şifreniz en az 6 karakterden oluşmaktadır.", Toast.LENGTH_LONG).show()
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
}


//Mail adresi aynı olduğu zaman kayıt olurken - Zaten var bu mail adresi
//Kullanıcı yoksa böyle bir kullanıcı bulunmamaktadır. Lütfen kayıt olunuz :)