package com.akakom175410002.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val PREFERENCE_KEY = "PREFERENCE_MAIN_ACTIVITY"
        const val DATA_KEY = "DATA_MAIN_ACTIVITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGrade.adapter = adapter
        }
        buttonInput.setOnClickListener {
            if(inputNama.text.isNotEmpty() && inputPangkat.text.isNotEmpty()) {
                saveData(inputNama.text.toString().trim(), inputPangkat.text.toString().trim(), spinnerGrade.selectedItemPosition)
            } else {
                Toast.makeText(this, "Mohon masukkan nama dan grade", Toast.LENGTH_SHORT).show()
            }
        }
        buttonProses.setOnClickListener {
            startActivity(Intent(this, ProsesActivity::class.java))
        }
        buttonExit.setOnClickListener {
            finish()
        }
    }

    private fun saveData(nama: String, pangkat: String, gradeId: Int) {
        val sharedPreferences: SharedPreferences = getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
        val oldSet: Set<String>? = sharedPreferences.getStringSet(DATA_KEY, null)
        val newSet = mutableSetOf<String>()
        if(oldSet == null) newSet.add("$nama, $pangkat, $gradeId")
        else {
            newSet.addAll(oldSet)
            newSet.add("$nama, $pangkat, $gradeId")
        }
        Log.e(this.javaClass.simpleName, newSet.toString())
        with(sharedPreferences.edit()) {
            putStringSet(DATA_KEY, newSet)
            commit()
        }
        Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
    }
}
