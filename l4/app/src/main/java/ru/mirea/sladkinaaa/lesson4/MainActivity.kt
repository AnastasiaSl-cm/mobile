package ru.mirea.sladkinaaa.lesson4


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.sladkinaaa.lesson4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.textSongTitle.text = "My Favorite Song"

        binding!!.buttonPlay.setOnClickListener {
            Log.d("MainActivity", "Play clicked")
            binding!!.textSongTitle.text = "Playing..."
        }

        binding!!.buttonPause.setOnClickListener {
            Log.d("MainActivity", "Pause clicked")
            binding!!.textSongTitle.text = "Paused."
        }
    }
}
