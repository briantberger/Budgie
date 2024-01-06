package com.example.budgetplanner

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetplanner.databinding.ItemPurchaseBinding

class PurchasesAdapter(
    private val purchases: Purchases
) : RecyclerView.Adapter<PurchasesAdapter.PurchasesViewHolder>() {
    class PurchasesViewHolder(val binding: ItemPurchaseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasesViewHolder {

        val binding = ItemPurchaseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PurchasesViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addPurchase(purchase: Purchase) {
        purchases.addPurchase(purchase)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return purchases.purchases.size
    }

    override fun onBindViewHolder(holder: PurchasesViewHolder, position: Int) {
        with(holder){
            with(purchases.purchases[position]){
                binding.tvPurchaseAmount.text = price.toString()
                binding.tvPurchaseTitle.text = vendor
            }
        }
    }
}