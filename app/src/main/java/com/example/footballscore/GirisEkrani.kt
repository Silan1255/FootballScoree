package com.example.footballscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.inputmethod.InputBinding
import android.widget.Toast
import com.example.footballscore.databinding.ActivityGirisEkraniBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_giris_ekrani.*


class GirisEkrani : AppCompatActivity() {

    lateinit var binding: ActivityGirisEkraniBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGirisEkraniBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        binding.btnGirisYapiniz.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        binding.txtKayitYapmadiysaniz.setOnClickListener {
            intent = Intent(applicationContext, KayitOl::class.java)
            startActivity(intent)
        }

        binding.apply {
            btn_giris_yapiniz.setOnClickListener {
                when {
                    TextUtils.isEmpty(edt_mail_adresi.text.toString().trim {
                        it <= ' '
                    }) -> {
                        Toast.makeText(
                            this@GirisEkrani,
                            "Lütfen Mail adresinizi giriniz.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    TextUtils.isEmpty(edt_sifre.text.toString().trim {
                        it <= ' '
                    }) -> {
                        Toast.makeText(
                            this@GirisEkrani,
                            "Lütfen şifre giriniz.",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    else -> {
                        val MailAdresi: String = edt_mail_adresi.text.toString().trim {
                            it <= ' '
                        }
                        val sifre: String = edt_sifre.text.toString().trim {
                            it <= ' '
                        }


                        FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(MailAdresi, sifre)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this@GirisEkrani,
                                        "kayıt işlemini başarıyla tamamladınız.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val intent = Intent(this@GirisEkrani, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra(
                                        "user_id",
                                        FirebaseAuth.getInstance().currentUser!!.uid
                                    )
                                    intent.putExtra("email_id", MailAdresi)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@GirisEkrani,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    }
                }
            }
        }

    }
}