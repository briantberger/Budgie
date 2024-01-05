package com.example.budgetplanner

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_purchase.view.tvPurchaseAmount
import kotlinx.android.synthetic.main.item_purchase.view.tvPurchaseTitle

class PurchasesAdapter(
    private val purchases: Purchases
) : RecyclerView.Adapter<PurchasesAdapter.PurchasesViewHolder>() {
    class PurchasesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasesViewHolder {

        return PurchasesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_purchase,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addPurchase(purchase: Purchase) {
        purchases.addPurchase(purchase)
//        notifyItemInserted(purchases.purchases.size - 1)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return purchases.purchases.size
    }

    override fun onBindViewHolder(holder: PurchasesViewHolder, position: Int) {
        val currPurchase = purchases.purchases[position]
//        holder.itemView.apply{
//            tvPurchaseTitle.text = currPurchase.vendor
//            tvPurchaseAmount.text = currPurchase.price.toString()
//        }
        
    }
}