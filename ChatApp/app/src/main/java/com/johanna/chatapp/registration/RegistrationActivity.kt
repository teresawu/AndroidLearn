package com.johanna.chatapp.registration

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.johanna.chatapp.R
import com.johanna.chatapp.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_create_account.*

class RegistrationActivity : AppCompatActivity(), RegistrationView {
    private val registrationPresenter = RegistrationPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        accountCreateButtonEt.setOnClickListener {
            val email = accountEmailEt.text.toString().trim()
            val password = accountPasswordEt.text.toString().trim()
            val displayName = accountDisplayNameEt.text.toString().trim()

            if (!TextUtils.isEmpty(displayName)
                    && !TextUtils.isEmpty(email)
                    && !TextUtils.isEmpty(password)) {
                registrationPresenter.createAccount(email, password, displayName)
            } else {
                Toast.makeText(this, R.string.fill_fields, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun registrationError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun updateDatabaseFail() {
        Toast.makeText(this, this.getString(R.string.user_not_created), Toast.LENGTH_LONG).show()
    }

    override fun updateDatabaseSuccess(displayName: String) {
        Toast.makeText(this, R.string.user_created, Toast.LENGTH_SHORT).show()
        val dashboardActivity = Intent(this, DashboardActivity::class.java)
        dashboardActivity.putExtra(DashboardActivity.name, displayName)
        startActivity(dashboardActivity)
        finish()
    }
}
