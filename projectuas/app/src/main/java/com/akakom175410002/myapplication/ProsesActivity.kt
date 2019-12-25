package com.akakom175410002.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_proses.*

class ProsesActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_HASIL = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proses)
        getData()
        buttonBack.setOnClickListener { finish() }
    }

    private fun getData() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(MainActivity.PREFERENCE_KEY, Context.MODE_PRIVATE)
        val data: Set<String>? = sharedPreferences.getStringSet(MainActivity.DATA_KEY, null)
        Log.e(this.javaClass.simpleName, data.toString())
        if(data == null) {
            Toast.makeText(this, "Data belum tersedia!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            val arrayNama = mutableListOf<String>()
            for(i in data) arrayNama.add(i.split(", ")[0])
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayNama).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerNama.adapter = adapter
            }
            buttonProses.setOnClickListener {
                if(inputIjin.text.isNotEmpty() && inputAlfa.text.isNotEmpty()) {
                    val intent = Intent(this, HasilActivity::class.java).apply {
                        putExtra("namePosition", spinnerNama.selectedItemPosition)
                        putExtra("ijin", inputIjin.text.toString().toInt())
                        putExtra("alfa", inputAlfa.text.toString().toInt())
                    }
                    startActivityForResult(intent, REQUEST_HASIL)
                } else {
                    Toast.makeText(this, "Mohon masukkan jumlah ijin dan alfa!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_HASIL) finish()
    }
}
