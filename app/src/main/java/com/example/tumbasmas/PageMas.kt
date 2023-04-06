package com.example.tumbasmas

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageMas : AppCompatActivity(), View.OnClickListener {
    lateinit var btn_grid:ImageButton
    lateinit var btn_list:ImageButton
    lateinit var rv_produk: RecyclerView
    var list: ArrayList<produk> = arrayListOf()
    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_mas)
        supportActionBar?.hide()

        rv_produk = findViewById(R.id.rv_produk)
        rv_produk.setHasFixedSize(true)

        btn_grid = findViewById(R.id.btn_grid)
        btn_grid.setOnClickListener(this)

        btn_list = findViewById(R.id.btn_list)
        btn_list.setOnClickListener(this)

        list.addAll(ListProduk.listData)
        showRecyclerGrid()
    }
    private fun showRecyclerList(){
        rv_produk.layoutManager = LinearLayoutManager(this)
        val listProductAdapter = ListProductAdapter(list)
        rv_produk.adapter = listProductAdapter

        listProductAdapter.setOnItemClickCallback(object : ListProductAdapter.OnItemClickCallback{
            override fun onItemClicked(data: produk) {
                showSelectedProduct(data)
            }
        })
    }
    private fun showRecyclerGrid(){
        rv_produk.layoutManager = GridLayoutManager(this,2)
        val gridProductAdapter = GridProductAdapter(list)
        rv_produk.adapter = gridProductAdapter

        gridProductAdapter.setOnItemClickCallback(object : GridProductAdapter.OnItemClickCallback{
            override fun onItemClicked(data: produk) {
                showSelectedProduct(data)
            }
        })
    }
    private fun showSelectedProduct(produk: produk){
        Toast.makeText(this,"Succesfull added "+ produk.name,Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.btn_grid ->{
                    showRecyclerGrid()
                }
                R.id.btn_list ->{
                    showRecyclerList()
                }
            }
        }
    }
}