package com.example.arduinofb.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arduinofb.Models.Dato
import com.example.arduinofb.R

class AdapterPorton(private val dataList: ArrayList<Dato>) :
    RecyclerView.Adapter<AdapterPorton.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idText: TextView = view.findViewById(R.id.textId)
        val valorText: TextView = view.findViewById(R.id.textValor)
        val timestampText: TextView = view.findViewById(R.id.textTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dato, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dato = dataList[position]
        holder.idText.text = "ID: ${dato.id}"
        holder.valorText.text = "Valor: ${dato.valor}"
        holder.timestampText.text = "Timestamp: ${dato.timestamp}"
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
