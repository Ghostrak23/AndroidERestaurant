package fr.isen.albergucci.androiderestaurant

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class TemplateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template)

        val intent = intent
        val value = intent.getStringExtra("category_entree")

        val textView = findViewById<View>(R.id.titre) as TextView
        textView.text = value


    }
}