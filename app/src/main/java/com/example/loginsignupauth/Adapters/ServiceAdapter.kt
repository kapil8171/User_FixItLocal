package com.example.loginsignupauth.Adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.loginsignupauth.DataClasses.ServicePerson
import com.example.loginsignupauth.R
import com.example.loginsignupauth.Ui.BookingActivity
import com.example.loginsignupauth.Ui.ServiceActivity
import com.example.loginsignupauth.databinding.ItemServicemenLayoutBinding

class ServiceAdapter(private val context: ServiceActivity, private var list: List<ServicePerson>) :
    RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    private var originalList: List<ServicePerson> = ArrayList()

    init {
        // Store original list when the adapter is initialized
        originalList = ArrayList(list)
    }
    inner class ViewHolder(val binding: ItemServicemenLayoutBinding) :
        RecyclerView.ViewHolder(binding.root){
        init {
            binding.recyclerCallBtn.setOnClickListener {
                val phoneNumber = binding.recyclerMobno.text.toString()
                callPhoneNumber(phoneNumber)
            }

            // Add OnClickListener for recyclerName text view
            binding.recyclerSeeMore.setOnClickListener {
                val intent = Intent(context, BookingActivity::class.java)
                // Pass any necessary data to the BookActivity using intent extras if needed
                intent.putExtra("name",binding.recyclerName.text.toString())
                intent.putExtra("Image", list[adapterPosition].imageurl)
                intent.putExtra("location",binding.recyclerLocation.text.toString())
                intent.putExtra("service", list[adapterPosition].service)
                intent.putExtra("desc",list[adapterPosition].description)
                intent.putExtra("mobile_number",binding.recyclerMobno.text.toString())

                context.startActivity(intent)
            }
        }

        private fun callPhoneNumber(phoneNumber: String) {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemServicemenLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
//            recyclerImage.load(list[position].imageurl)
            recyclerImage.load(list[position].imageurl) {
                placeholder(R.drawable.profile_placeholder) // Set placeholder image resource
                error(R.drawable.pizza1) // Set error image resource if loading fails
                diskCachePolicy(CachePolicy.ENABLED) // Enable disk caching
            }
//            // Check if image URL is not null or empty
//            if (list[position].imageurl.isNotBlank()) {
//                recyclerImage.load(list[position].imageurl) {
//                    // Placeholder color or image, if needed
//                    placeholder(R.drawable.profile_placeholder)
//                }
//            } else {
//                // Set placeholder background when image URL is empty or null
//                recyclerImage.setBackgroundResource(R.drawable.placeholder_background)
//            }

            recyclerAge.text = list[position].age.toString()
            recyclerLocation.text = list[position].location
            recyclerMobno.text = list[position].number
            recyclerExperience.text = list[position].experience
            recyclerName.text = list[position].name
        }
    }

    fun filterByExperience(selectedFilter: String) {
        list = when (selectedFilter) {
            "Filter by Exp" -> ArrayList(originalList) // Reset to original list
            "(0-2)Years" -> originalList.filter { it.experience.toIntOrNull() in 0..2 }.toMutableList()
            "(2-7)Years" -> originalList.filter { it.experience.toIntOrNull() in 2..7 }.toMutableList()
            "(7+)Years" -> originalList.filter { it.experience.toIntOrNull() ?: Int.MAX_VALUE >= 7 }.toMutableList()
            else -> originalList.toMutableList() // Reset to original list
        }
        notifyDataSetChanged()
    }

    fun resetToOriginalList() {
        list = ArrayList(originalList)
        notifyDataSetChanged()
    }

    fun filterByLocation(locationQuery: String) {
        list = originalList.filter { it.location.contains(locationQuery, ignoreCase = true) }
        notifyDataSetChanged()
    }


}
