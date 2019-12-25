package com.akakom175410002.myapplication

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hasil.*

class HasilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)
        getData()
        Log.d("Ijin ",intent.getStringExtra("ijin"))
        buttonMenu.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
        buttonFinish.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun getData() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(MainActivity.PREFERENCE_KEY, Context.MODE_PRIVATE)
        val data: Set<String>? = sharedPreferences.getStringSet(MainActivity.DATA_KEY, null)
        if(data == null) {
            Toast.makeText(this, "Data belum tersedia!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            val arrayData = mutableListOf<List<String>>()
            for(i in data) arrayData.add(i.split(", "))
            val selectedData = arrayData[intent.getIntExtra("namePosition", 0)]
            val gaji : Int
            val HitungIjin : Double
            val Ijin : Int = intent.getStringExtra("ijin").toInt()
            val Hitungalfa : Int
            val alfa : Int = intent.getStringExtra("alfa").toInt()
            val persenan : Int
            var gaji_akhir : Int = 0
            var potongan : Int = 0
            if (selectedData[2].equals("8")){
                val AlfaAkhir : Int
                val IjinAkhir : Int
                gaji = 1000000
                persenan = gaji/100
                Hitungalfa = persenan*3
                AlfaAkhir = Hitungalfa*alfa
                HitungIjin = persenan*0.5
                IjinAkhir = HitungIjin.toInt()*Ijin
                potongan = AlfaAkhir+IjinAkhir
                gaji_akhir = gaji-potongan
            }
            else if(selectedData[2].equals("9")){
                val AlfaAkhir : Int
                val IjinAkhir : Int
                gaji = 1500000
                persenan = gaji/100
                Hitungalfa = persenan*3
                AlfaAkhir = Hitungalfa*alfa
                HitungIjin = persenan*0.5
                IjinAkhir = HitungIjin.toInt()*Ijin
                potongan = AlfaAkhir+IjinAkhir
                gaji_akhir = gaji-potongan
                }
            else if(selectedData[2].equals("10")){
                val AlfaAkhir : Int
                val IjinAkhir : Int
                gaji = 2000000
                persenan = gaji/100
                Hitungalfa = persenan*3
                AlfaAkhir = Hitungalfa*alfa
                HitungIjin = persenan*0.5
                IjinAkhir = HitungIjin.toInt()*Ijin
                potongan = AlfaAkhir+IjinAkhir
                gaji_akhir = gaji-potongan
            }
            else if(selectedData[2].equals("11")){
                val AlfaAkhir : Int
                val IjinAkhir : Int
                gaji = 2500000
                persenan = gaji/100
                Hitungalfa = persenan*3
                AlfaAkhir = Hitungalfa*alfa
                HitungIjin = persenan*0.5
                IjinAkhir = HitungIjin.toInt()*Ijin
                potongan = AlfaAkhir+IjinAkhir
                gaji_akhir = gaji-potongan
            }
            textNama.text = selectedData[0]
            textPangkat.text = selectedData[1]
            textPotongan.text = potongan.toString()
            textTotal.text = gaji_akhir.toString()
            textGrade.text = resources.getStringArray(R.array.grade)[selectedData[2].toInt()]
        }
    }
}
