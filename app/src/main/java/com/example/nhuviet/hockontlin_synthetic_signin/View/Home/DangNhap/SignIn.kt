package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.DangNhap

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Account_SignIn
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.User
import com.example.nhuviet.hockontlin_synthetic_signin.Presenter.signIn_Account.Presenter_SignIn
import com.example.nhuviet.hockontlin_synthetic_signin.R
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.MainActivity_SyntheticKontlin
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.OptionalPendingResult
import kotlinx.android.synthetic.main.activity_main_signin.*
import org.json.JSONException
import java.util.*


/**
 * Created by nhuvi on 14/08/2017.
 */
class SignIn : AppCompatActivity(), View_SignIn, View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {


    var prensenter_SignIn: Presenter_SignIn? = null
    val kiemtra_sighIn: Boolean = false
    var callbackManager: CallbackManager? = null
    var account: Account? = null
    var type: Int = 0
    var mGoogleApiClient: GoogleApiClient? = null
    var RC_SIGN_IN = 111
    private val TAG = "SignInActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callbackManager = CallbackManager.Factory.create()
        setContentView(R.layout.activity_main_signin)


        bt_dangnhap.setOnClickListener(this)
        txt_dktaikhoan.setOnClickListener {
            val intent = Intent(this, Account::class.java)
            startActivity(intent)
        }
        prensenter_SignIn = Presenter_SignIn(this)

////////////////////////////Facebook//////////////////////////////////////////////
        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {

                    override fun onSuccess(result: LoginResult) {
                        val request = GraphRequest.newMeRequest(result.accessToken)
                        { _, response ->
                            Log.d("Response", response.toString())
                            try {
                                //    val tokenfirebase = FirebaseInstanceId.getInstance().token
                                val id = response.jsonObject.getString("id")
                                val url = "https://graph.facebook.com/$id/picture?type=large"
                                val user = User()
                                user.facebook!!.name = response.jsonObject.getString("name")
                                //     val fb = response.jsonObject.getString("name")
                                user.facebook!!.email = response.jsonObject.getString("email")
                                user.facebook!!.id = id
                                user.password = ""
                                user.facebook!!.photoprofile = (url)
                                //   user.tokenfirebase = (tokenfirebase)
                                user.facebook!!.token = AccessToken.getCurrentAccessToken().toString()
                                //  type = Constants.FACEBOOK
                                //  mLoginPresenter!!.register(user, type)
                                startActivity(Intent(this@SignIn, MainActivity_SyntheticKontlin::class.java))
                                Toast.makeText(this@SignIn, "Đăng Nhập thành công !", Toast.LENGTH_SHORT).show()
//                                Log.d("fb", url)

                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                        val parameters = Bundle()
                        parameters.putString("fields", "id,email,first_name,last_name,gender, birthday, name,picture")
                        request.parameters = parameters
                        request.executeAsync()

                    }

                    override fun onError(error: FacebookException) {
                        Log.d("Error", error.message)
                    }

                    override fun onCancel() {
                        println("loi cmnr")
                    }
                })
        bt_facebook.setOnClickListener(this)
/////////////////////////////////////GOOOGLE///////////////////////////////////////////////////////////////

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        bt_google.setOnClickListener(this)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this@SignIn, "Đăng nhập thất bại ", Toast.LENGTH_LONG).show()

    }

    //////////////////////////GOOGLE & FACEBOOK////////////////////////////////////////////////////////////////////////
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)

        }
    }

    fun handleSignInResult(result: GoogleSignInResult) {
        Log.d("gg", "handleSignInResult:" + result.isSuccess);
        if (result.isSuccess) {
            // Signed in successfully, show authenticated UI.
            val acct: GoogleSignInAccount? = result.signInAccount
            Log.e("gg", "display name: " + acct!!.displayName)
            //     val tokenfirebase = FirebaseInstanceId.getInstance().token
            val user = User()
            user.google!!.id = acct.id
            user.google!!.token = acct.idToken
            user.google!!.name = acct.displayName
            user.google!!.email = acct.email
            user.password = ""
            user.google!!.photoprofile = acct.photoUrl.toString()
            //      user.tokenfirebase = tokenfirebase
            //   type = Constants.GOOGLE
            //    mLoginPresenter!!.register(user, type)
            Log.d("gg", "Name: " + user.google!!.name + ",email: " + user.google!!.email
                    + ", Image: " + user.google!!.photoprofile)
            startActivity(Intent(this@SignIn, MainActivity_SyntheticKontlin::class.java))
            Toast.makeText(this@SignIn, "Đăng Nhập thành công !", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this@SignIn, "Đăng nhập thất bại ", Toast.LENGTH_LONG).show()
            // Signed out, show unauthenticated UI.

        }
    }

    override fun onStart() {
        super.onStart()
        val opr: OptionalPendingResult<GoogleSignInResult> = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient)
        if (opr.isDone) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d("gg", "Got cached sign-in");
            val result: GoogleSignInResult = opr.get()
            handleSignInResult(result)
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.

            opr.setResultCallback { googleSignInResult -> handleSignInResult(googleSignInResult); }
        }
    }

    override fun onClick(v: View?) {
        val id = v!!.id
        when (id) {
            R.id.bt_dangnhap -> {
                button_SignIn()
            }
            R.id.bt_facebook -> {
//                LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
            }
            R.id.bt_google -> {
                val google_Intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
                startActivityForResult(google_Intent, RC_SIGN_IN)
            }
        }
    }
//    override fun onPause() {
//        super.onPause()
//
//        // Your application logic
//        // ...
//        // ...
//
//        mGoogleApiClient?.disconnect()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        // stop GoogleApiClient
//        if (mGoogleApiClient?.isConnected!!) {
//            mGoogleApiClient?.disconnect()
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if (mGoogleApiClient?.isConnected!!) {
//            mGoogleApiClient?.disconnect()
//        }
//    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun button_SignIn() {

        val email = ed_diachiemail.text.toString().trim()
        val pass = ed_matkhau.text.toString().trim()

        if (!kiemtra_sighIn) {
            val account = Account_SignIn()
            account.email = email
            account.pass = pass
            prensenter_SignIn!!.Laythongtin_Sinin(this, account)
        } else {
            Log.d("kiemtra", "Đăng Nhập thất bại !")
            Toast.makeText(this, "Đăng Nhập thất bại !", Toast.LENGTH_SHORT).show();
        }
    }

    override fun dangki_thanhcong() {
        Toast.makeText(this, "Đăng Nhập thành công !", Toast.LENGTH_SHORT).show()
        val intentdangnhap = Intent(this, MainActivity_SyntheticKontlin::class.java)
        startActivity(intentdangnhap)
        Toast.makeText(this, "Đã đăng nhập !", Toast.LENGTH_SHORT).show()
    }

    override fun dangki_thatbai() {
        Toast.makeText(this, "Đăng Nhập thất bại !", Toast.LENGTH_SHORT).show()
    }
}