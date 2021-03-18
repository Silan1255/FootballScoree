
package com.example.footballscore.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import com.example.footballscore.databinding.ActivityKayitOlBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_giris_ekrani.*
import kotlinx.android.synthetic.main.activity_kayit_ol.*


class KayitOl : AppCompatActivity() {
    lateinit var binding: ActivityKayitOlBinding
    lateinit var sharedPreferences: SharedPreferences

    var userMail: String? = ""
    var userPassword: String? = ""
    var userName: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKayitOlBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("com.example.footballscore", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        userMail = sharedPreferences.getString("userMail", "")
        userPassword = sharedPreferences.getString("userPassword", "")
        userName = sharedPreferences.getString("userName", "")

        binding.txtKayitYaptiysaniz.setOnClickListener {
            intent = Intent(applicationContext, GirisEkrani::class.java)
            startActivity(intent)
        }


        binding.apply {

            btn_kayit_ol.setOnClickListener {
                if (isValid()) {
                    val MailAdresi: String = kayitMail.text.toString().trim()
                    val sifre: String = kayitSifre.text.toString().trim()


                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(MailAdresi, sifre)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(this@KayitOl, "kayıt işlemini başarıyla tamamladınız.", Toast.LENGTH_LONG).show()
                                    val intent = Intent(this@KayitOl, GirisEkrani::class.java)

                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", MailAdresi)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@KayitOl, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                                }
                            })
                }
            }
        }
    }

    private fun isValid(): Boolean {
        var isCheckNull = true
        if (kayit_mail.text.isEmpty()) {
            Toast.makeText(this@KayitOl, "Lütfen Mail adresinizi giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
        else if  (kayit_sifre.text.isEmpty()) {
            Toast.makeText(this@KayitOl, "Lütfen Sifrenizi giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
        else if (kayit_kullanici_adi.text.isEmpty()) {
            Toast.makeText(this@KayitOl, "Lütfen kullanıcı adınızı  giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
        else if (isEmailValid(kayit_mail.text.toString()) == false) {
            Toast.makeText(this@KayitOl, "Lütfen Mail Adresiniz doğru formatta giriniz.", Toast.LENGTH_LONG).show()
            isCheckNull = false
        }
       else  if (kayit_sifre.text.length < 6) {
            Toast.makeText(this@KayitOl, "Şifreniz en az 6 karakterden oluşmaktadır.", Toast.LENGTH_LONG).show()
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
