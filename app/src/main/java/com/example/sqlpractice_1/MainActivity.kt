package com.example.sqlpractice_1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val db = DBHelper(this, null)
    private var listProductAdapter: ListAdapter<Product>? = null

    private lateinit var nameProductET: EditText
    private lateinit var priceProductET: EditText
    private lateinit var weightProductET: EditText
    private lateinit var saveBTN: Button
    private lateinit var listViewLW: ListView
    private lateinit var toolbar: Toolbar

    private var productList: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = ""

        init()

        listProductAdapter = ListAdapter(this@MainActivity, productList)
        listViewLW.adapter = listProductAdapter
        listProductAdapter?.notifyDataSetChanged()

        saveBTN.setOnClickListener {
            val name = nameProductET.text.toString()
            val price = priceProductET.text.toString()
            val weight = weightProductET.text.toString()

            val product = Product(1,name, price.toDouble(), weight.toDouble())
            db.addProduct(product)
            Toast.makeText(this, "$name добавлен в чек", Toast.LENGTH_SHORT).show()
            nameProductET.text.clear()
            priceProductET.text.clear()
            weightProductET.text.clear()

            productList = db.readProduct()
            listProductAdapter = ListAdapter(this@MainActivity, productList)
            listViewLW.adapter = listProductAdapter
            listProductAdapter?.notifyDataSetChanged()
        }



    }

    private fun init(){
        nameProductET = findViewById(R.id.nameProductET)
        priceProductET = findViewById(R.id.priceProductET)
        weightProductET = findViewById(R.id.weightProductET)
        saveBTN = findViewById(R.id.saveBTN)
        listViewLW = findViewById(R.id.listViewLW)
    }
}