package view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.virtusa.com.poc_virtusa.R

class VirtusaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.virtusaFrameLayout, VirtusaFragment()).commit()
        }
    }
}
