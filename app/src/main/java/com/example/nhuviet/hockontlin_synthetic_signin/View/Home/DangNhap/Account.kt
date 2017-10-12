package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.DangNhap

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Account_SignIn
import com.example.nhuviet.hockontlin_synthetic_signin.Presenter.signIn_Account.Presenter_Account
import com.example.nhuviet.hockontlin_synthetic_signin.R
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.MainActivity_SyntheticKontlin
import kotlinx.android.synthetic.main.account.*


/**
 * Created by nhuvi on 14/08/2017.
 */
class Account : AppCompatActivity(), View_Account, View.OnClickListener {


    var prensenter_acount: Presenter_Account? = null
    var kiemtrathongtin: Boolean = false
    //    val account = Account_SignIn()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account)
        prensenter_acount = Presenter_Account(this)
        bt_dangki.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        val id = v!!.id
        when (id) {
            R.id.bt_dangki -> {
                Bt_DangKy()
            }
        }
    }
    fun Bt_DangKy() {
        var dk_email: String = ed_dkdiachiemail.text.toString()
        var dk_pass: String = ed_dkmatkhau.text.toString()

        if (!kiemtrathongtin) {
            val account = Account_SignIn()
            account.email = dk_email
            account.pass = dk_pass
            prensenter_acount!!.Thuchien_Account(this, account)
        } else {
            Log.d("kiemtra", "Dk that bai")
            Toast.makeText(this, "Đăng ký thất bại !", Toast.LENGTH_SHORT).show();
        }
    }
    override fun dangki_thanhcong() {
        Toast.makeText(this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show()
        val intentdangnhap = Intent(this, MainActivity_SyntheticKontlin::class.java)
        startActivity(intentdangnhap)
        Toast.makeText(this, "Đã đăng nhập !", Toast.LENGTH_SHORT).show()
    }
    override fun dangki_thatbai() {
        Toast.makeText(this, "Đăng ký thất bại !", Toast.LENGTH_SHORT).show()
    }
    override fun email_datontai() {
        Toast.makeText(this, "Email đã tồn tại..! !", Toast.LENGTH_SHORT).show()
    }
}