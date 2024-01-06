package com.example.budgetplanner

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetplanner.databinding.ActivityMainBinding
import com.example.budgetplanner.databinding.ItemPurchaseBinding
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var purchasesAdapter: PurchasesAdapter
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)


        purchasesAdapter = PurchasesAdapter(Purchases("Groceries"))

        binding.rvPurchases.adapter = purchasesAdapter
        binding.rvPurchases.layoutManager = LinearLayoutManager(this)

        binding.btnAddPurchase.setOnClickListener {
            val purchaseTitle = binding.etPurchaseName.text.toString()
            if (purchaseTitle.isNotEmpty()) {
                val purchase = Purchase(purchaseTitle, BigDecimal("9.99"))
                binding.etPurchaseName.text.clear()
                purchasesAdapter.addPurchase(purchase)
            }
        }
    }
}