package com.example.budgetplanner

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.btnAddPurchase
import kotlinx.android.synthetic.main.activity_main.etPurchaseName
import kotlinx.android.synthetic.main.activity_main.rvPurchases
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private lateinit var purchasesAdapter: PurchasesAdapter
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        purchasesAdapter = PurchasesAdapter(Purchases("Groceries"))

        rvPurchases.adapter = purchasesAdapter
        rvPurchases.layoutManager = LinearLayoutManager(this)

        btnAddPurchase.setOnClickListener {
            val purchaseTitle = etPurchaseName.text.toString()
            if (purchaseTitle.isNotEmpty()) {
                val purchase = Purchase(purchaseTitle, BigDecimal("9.99"))
                etPurchaseName.text.clear()
            }
        }
    }
}