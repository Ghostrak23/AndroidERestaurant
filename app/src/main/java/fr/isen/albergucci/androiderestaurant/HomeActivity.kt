package fr.isen.albergucci.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast;


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val entreeButton = findViewById<Button>(R.id.button1)
        entreeButton.setOnClickListener {
            val intent = Intent(this, TemplateActivity::class.java)
            intent.putExtra("category_entrees", "Entrées")
            startActivity(intent)
        }

        val platButton = findViewById<Button>(R.id.button2)
        platButton.setOnClickListener {
            val intent = Intent(this, TemplateActivity::class.java)
            intent.putExtra("category_plats", "Plats")
            startActivity(intent)
        }

        val dessertButton = findViewById<Button>(R.id.button3)
        dessertButton.setOnClickListener {
            val intent = Intent(this, TemplateActivity::class.java)
            intent.putExtra("category_desserts", "Desserts")
            startActivity(intent)
        }
    }
}