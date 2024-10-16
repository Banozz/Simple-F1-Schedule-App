package com.example.praktikumappf1.bottomnavigations.ui.race

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.praktikumappf1.bottomnavigations.R
import com.example.praktikumappf1.bottomnavigations.ui.Race

class DetailedRaceFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_race_fragment)

        val race = intent.getParcelableExtra<Race>("race_data")

        val raceNameTextView: TextView = findViewById(R.id.tvDetailedName)
        val raceRoundTextView: TextView = findViewById(R.id.tvDetailedDesc)
        val raceMoreDescTextView: TextView = findViewById(R.id.tvDetailedContent)
        val raceDateTextView: TextView = findViewById(R.id.tv_date)
        val raceImageView: ImageView = findViewById(R.id.ivDetailedImg)

        race?.let{
            raceNameTextView.text = it.fullName
            raceRoundTextView.text = it.name
            raceMoreDescTextView.text = it.desc
            raceDateTextView.text = it.date
            raceImageView.setImageResource(it.img)
        }

        val btnToWebsite: Button = findViewById(R.id.btnToWebsite)
        btnToWebsite.setOnClickListener {
            race?.let{
                openWebsite(it.web)
            }
        }
    }

    private fun openWebsite(url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}