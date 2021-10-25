package com.begicim.clearscoredemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.begicim.clearscoredemo.R
import com.begicim.clearscoredemo.ui.CreditFragment.Companion.CREDIT_FRAGMENT_TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val creditFragment = if (savedInstanceState == null) {
            val creditFragment = CreditFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, creditFragment, CREDIT_FRAGMENT_TAG)
                .commit()

            creditFragment
        } else {
            supportFragmentManager.findFragmentByTag(CREDIT_FRAGMENT_TAG)
        }
    }
}