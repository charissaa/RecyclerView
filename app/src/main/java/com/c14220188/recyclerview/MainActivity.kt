package com.c14220188.recyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _rvWayang =findViewById<RecyclerView>(R.id.rvWayang)

        fun SiapkanData() {
            _nama = resources.getStringArray(R.array.namaWayang)
            _deskripsi = resources.getStringArray(R.array.deskripsiWayang)
            _karakter = resources.getStringArray(R.array.karakterUtamaWayang)
            _gambar = resources.getStringArray(R.array.gambarWayang)
        }

        fun TambahData() {
            for (position in _nama.indices) {
                val data = wayang(
                    _gambar[position],
                    _nama[position],
                    _karakter[position],
                    _deskripsi[position]
                )
                arWayang.add(data)
            }
        }

        fun TampilkanData() {
            _rvWayang.layoutManager = LinearLayoutManager(this)

            val adapterWayang = adapterRecView(arWayang)
            _rvWayang.adapter = adapterWayang

            adapterWayang.setOnItemClickCallback(object : adapterRecView.OnItemClickCallback {
                override fun onItemClicked(data: wayang) {
//                    Toast.makeText(this@MainActivity,data.nama,Toast.LENGTH_LONG).show()
                    val intent = Intent(this@MainActivity,detWayang::class.java)
                    intent.putExtra("kirimData", data)
                    startActivity(intent)
                }
            })
        }

        //panggil function
        _rvWayang = findViewById<RecyclerView>(R.id.rvWayang)
        SiapkanData()
        TambahData()
        TampilkanData()
    }

    private lateinit var _nama : Array<String>
    private lateinit var _karakter : Array<String>
    private lateinit var _deskripsi : Array<String>
    private lateinit var _gambar : Array<String>

    private var arWayang = arrayListOf<wayang>()

    private lateinit var _rvWayang : RecyclerView
}