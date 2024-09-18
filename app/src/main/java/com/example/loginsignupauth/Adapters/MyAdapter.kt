package com.example.loginsignupauth.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginsignupauth.DataClasses.Person
import com.example.loginsignupauth.R
import com.google.android.material.button.MaterialButton

class MyAdapter(private val context: Context, private val arr: List<Person>,
) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val image = itemView.findViewById<ImageView>(R.id.imageView3)
        val name = itemView.findViewById<TextView>(R.id.recyclertext2)
        val category = itemView.findViewById<TextView>(R.id.recyclerCategory)
        val location = itemView.findViewById<TextView>(R.id.recyclerLocation)
        val image  = itemView.findViewById<ImageView>(R.id.recyclerimage)
        val callButton = itemView.findViewById<MaterialButton>(R.id.recyclerbutton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = arr[position].name
        holder.location.text = arr[position].location
        holder.category.text = arr[position].serviceCategory

        holder.callButton.setOnClickListener {
            val phoneNumber = arr[position].phone // Replace with the desired phone number
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            context.startActivity(intent)
        }


//        holder.callButton.setOnClickListener {
//            val phoneNumber = "9119071696" // Replace with the desired phone number
//            val intent = Intent(Intent.ACTION_DIAL)
//            intent.data = Uri.parse("tel:$phoneNumber")
//            startActivity(intent)
//
//        }


        Glide.with(context)
            .load(arr[position].image)
            .into(holder.image)

    }

    private fun startActivity(intent: Intent) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return arr!!.size
    }
}