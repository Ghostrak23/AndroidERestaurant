package fr.isen.albergucci.androiderestaurant

import CustomAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.albergucci.androiderestaurant.databinding.ActivityTemplateBinding
import fr.isen.albergucci.androiderestaurant.model.ResultFood
import org.json.JSONObject

class TemplateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTemplateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        binding = ActivityTemplateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = binding.titleCategory
        val recyclerView = binding.recyclerCategory

        title.text = intent.extras?.getString("titleCategory")?:"No title available"

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = CustomAdapter(arrayListOf()){
            val intent = Intent(this@TemplateActivity, DetailsActivity::class.java)
            intent.putExtra("mealTitle", it)
            startActivity(intent)
        }

        loadDishesFromAPI()
    }
    private fun loadDishesFromAPI() {
        Volley.newRequestQueue(this)

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject, {
            Log.w("CategoryActivity", "response : $it")
            handleAPIData(it.toString())
        }, {
            Log.w("CategoryActivity", "erreur : $it")
            Toast.makeText(this@TemplateActivity, "Erreur API", Toast.LENGTH_SHORT).show()
        })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String) {
        val dishesResult = Gson().fromJson(data, ResultFood::class.java)
        val dishes = dishesResult.data.firstOrNull() {
            it.nameFr == (intent.extras?.getString("titleCategory") ?: "No title available")
        }

        val adapter = binding.recyclerCategory.adapter as CustomAdapter
        if (dishes != null) {
            adapter.refreshList(dishes.items)
        }
    }
}

