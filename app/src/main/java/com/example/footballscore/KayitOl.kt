package com.example.footballscore

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import com.example.footballscore.databinding.ActivityGirisEkraniBinding
import com.example.footballscore.databinding.ActivityKayitOlBinding
import com.example.footballscore.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_kayit_ol.*


class KayitOl : AppCompatActivity() {
    lateinit var binding: ActivityKayitOlBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKayitOlBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        binding.apply {
            btn_kayit_ol.setOnClickListener {
                when {
                    TextUtils.isEmpty(kayit_mail.text.toString().trim {
                        it <= ' '
                    }) -> {
                        Toast.makeText(
                            this@KayitOl,
                            "Lütfen Mail adresinizi giriniz.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    TextUtils.isEmpty(kayit_sifre.text.toString().trim {
                        it <= ' '
                    }) -> {
                        Toast.makeText(
                            this@KayitOl,
                            "Lütfen şifre giriniz.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    TextUtils.isEmpty(kayit_kullanici_adi.text.toString().trim {
                        it <= ' '
                    }) -> {
                        Toast.makeText(
                            this@KayitOl,
                            "Lütfen kullanıcı adınızı  giriniz.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        val MailAdresi: String = kayit_mail.text.toString().trim {
                            it <= ' '
                        }
                        val KullaniciAdi: String = kayit_kullanici_adi.text.toString().trim {
                            it <= ' '
                        }
                        val sifre: String = kayit_sifre.text.toString().trim {
                            it <= ' '
                        }


                        FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(MailAdresi, sifre)
                            .addOnCompleteListener(
                                OnCompleteListener<AuthResult> { task ->
                                    if (task.isSuccessful) {
                                        val firebaseUser: FirebaseUser = task.result!!.user!!
                                        Toast.makeText(
                                            this@KayitOl,
                                            "kayıt işlemini başarıyla tamamladınız.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        val intent = Intent(this@KayitOl, MainActivity::class.java)
                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        intent.putExtra("user_id", firebaseUser.uid)
                                        intent.putExtra("email_id", MailAdresi)
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            this@KayitOl,
                                            task.exception!!.message.toString(),
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }

                                })
                    }
                }
            }
        }


    }
}