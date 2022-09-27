package com.tom.guess1to100

import android.app.AlertDialog
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.tom.guess1to100.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val guessVM: GuessViewModel by viewModels()
        guessVM.min.observe(this, Observer {
            binding.contentMain.min.text = it.toString()
        })
        guessVM.max.observe(this, Observer {
            binding.contentMain.max.text = it.toString()
        })
        binding.contentMain.guess.setOnClickListener {
            val num = binding.contentMain.number.text.toString().toInt()
            guessVM.guessNumber(num)
        }
        guessVM.result.observe(this, Observer {
            val message = when (it) {
                GuessViewModel.BIGGER -> "Bigger"
                GuessViewModel.SMALLER -> "Smaller"
                GuessViewModel.BINGO -> "Yes you get it"
                else -> return@Observer
            }
            AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        })

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }// onCreate()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }// onCreateOptionsMenu()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }// onOptionsItemSelected()

}