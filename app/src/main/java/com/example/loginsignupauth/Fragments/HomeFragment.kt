package com.example.loginsignupauth.Fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.loginsignupauth.R
import com.example.loginsignupauth.Ui.AllServicesActivity
import com.example.loginsignupauth.Ui.LoginActivity
import com.example.loginsignupauth.Ui.NavigationActivity
import com.example.loginsignupauth.Ui.ServiceActivity
import com.example.loginsignupauth.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : androidx.fragment.app.Fragment() {
    private val LOCATION_REQUEST_CODE = 123
    private lateinit var binding: FragmentHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var locationTextView: TextView
    private lateinit var dropDownImageView: ImageView
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        val locationTextView = binding.locationTextView
        val dropDownImageView = binding.dropDownImageView


        dropDownImageView.setOnClickListener {
            // Open Google Maps to select location
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="))
            startActivity(intent)
        }


        // find views
        drawerLayout = binding.drawerlayout
        navView = binding.navView


        binding.seeAllCategories.setOnClickListener {
            startActivity(Intent(requireContext(), AllServicesActivity::class.java))
        }

        // Set OnClickListener for the "menuBar" ImageView
        binding.menuBar.setOnClickListener {
            // Toggle the navigation drawer when the menuBar ImageView is clicked
            toggleDrawer()
        }

        // Set OnClickListener for the "Plumber" button
        binding.CarpenterLL.setOnClickListener {
            navigateToServiceActivity(binding.carpenterText.text.toString())
        }

        // Set OnClickListener for the other buttons similarly...
        binding.ElectricianLL.setOnClickListener {
            navigateToServiceActivity(binding.electricianText.text.toString())
        }

        binding.PainterLL.setOnClickListener {
            navigateToServiceActivity(binding.painterText.text.toString())
        }

        binding.ACServiceLL.setOnClickListener {
            navigateToServiceActivity(binding.ACServiceText.text.toString())
        }

        binding.OtherApplianceLL.setOnClickListener {
            navigateToServiceActivity(binding.applianceText.text.toString())
        }


        binding.plumberLL.setOnClickListener {
            navigateToServiceActivity(binding.plumberText.text.toString())
        }

        // Set OnClickListener for the "Cleaner" button
        binding.cleanerLL.setOnClickListener {
            navigateToServiceActivity(binding.cleanerText.text.toString())
        }



        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOCATION_REQUEST_CODE && resultCode == RESULT_OK) {
            val selectedLocation = data?.data // Get the selected location URI
            // Extract location details and update the TextView
            locationTextView.text = "Selected Location: $selectedLocation"
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == LOCATION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            val locationUri = data?.data
//            if (locationUri != null) {
//                val location = getLocationFromUri(locationUri)
//                locationTextView.text = location
//            }
//        }
//    }

    private fun getLocationFromUri(uri: Uri): String {
        // Extract location details from Uri
        val latitude = uri.getQueryParameter("lat")
        val longitude = uri.getQueryParameter("lon")
        return "Latitude: $latitude, Longitude: $longitude"
    }


    private fun toggleDrawer() {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawer(navView)
        } else {
            drawerLayout.openDrawer(navView)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //set up animation

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        val bounceAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.bounce)
        val Rotate_clockAnimation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_clockwise)
        val ScaleAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_up)
        val SlideInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in)
        val fadeOutAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)

//        // Set initial visibility and start animation
//        binding.locationtext.visibility = View.VISIBLE
//        binding.locationtext.startAnimation(ScaleAnimation)
//
//        binding.horLl1.visibility = View.VISIBLE
//        binding.horLl1.startAnimation(ScaleAnimation)
//
//        binding.cardView.visibility = View.VISIBLE
//        binding.cardView.startAnimation(ScaleAnimation)
//
//        binding.searchView.visibility = View.VISIBLE
//        binding.searchView.startAnimation(ScaleAnimation)
//
//        binding.HorLl2.visibility = View.VISIBLE
//        binding.HorLl2.startAnimation(ScaleAnimation)
//
//        binding.HorLl3.visibility = View.VISIBLE
//        binding.HorLl3.startAnimation(ScaleAnimation)
//
//        binding.HorLl4.visibility = View.VISIBLE
//        binding.HorLl4.startAnimation(ScaleAnimation)
//
//        binding.HorLl5.visibility = View.VISIBLE
//        binding.HorLl5.startAnimation(ScaleAnimation)

        // set image slider
        val imageList = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        imageList.add(SlideModel(R.drawable.plumber2, "Hire best Plumber", ScaleTypes.CENTER_CROP))
        imageList.add(
            SlideModel(
                R.drawable.electrician1,
                "Hire best Electrician",
                ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                R.drawable.wall_painter2,
                "Hire best painter",
                ScaleTypes.CENTER_CROP
            )
        )

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setSlideAnimation(AnimationTypes.FOREGROUND_TO_BACKGROUND)


        // Set up navigation drawer
        setupDrawer()
    }

    private fun setupDrawer() {
        drawerToggle = ActionBarDrawerToggle(
            requireActivity(),
            drawerLayout,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()



        navView.getHeaderView(0).findViewById<TextView>(R.id.edit_profile).setOnClickListener {
             startNavigationActivity(R.id.profileFragment)
        }

        // Handle navigation item clicks
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.setting -> {
                    // Handle navigation item 2 click
                    Toast.makeText(requireContext(), "setting is selected", Toast.LENGTH_SHORT)
                        .show()
                }

                R.id.notification_item -> {
                    // Handle navigation item click and start NavigationActivity with extra
                    startNavigationActivity(R.id.notificationFragment)
                }

                R.id.paymentHistory_Item -> {
                    startNavigationActivity(R.id.paymentHistoryFragment)
                }

                R.id.termsCondition_Item -> {
                    // Navigate to TermsConditionFragment
                    startNavigationActivity(R.id.termsConditionFragment)
                }

                R.id.AboutApp_Item -> {
                    // Navigate to AboutAppFragment
                    startNavigationActivity(R.id.aboutAppFragment)
                }
                R.id.edit_profile ->{
                    Log.d("hii","gfjsfgjd")
                    startNavigationActivity(R.id.profileFragment)
                }

                R.id.share -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT, "Hey Check out this Great app:")
                    intent.type = "text/plain"
                    startActivity(Intent.createChooser(intent, "Share To:"))
                }

                R.id.logout -> {
                    // Perform logout operation here
                    // For example, clear session data and navigate to the login screen
                    logoutUser()
                    true // Return true to indicate the menu item click is handled
                }

                // Add more cases for other navigation items if needed
            }
            true
            // Close the drawer after selecting an item
            // Close the drawer after selecting an item
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }


    }

    private fun startNavigationActivity(destinationId: Int) {
        val intent = Intent(requireContext(), NavigationActivity::class.java)
        intent.putExtra("destinationId", destinationId)
        startActivity(intent)
    }


    private fun logoutUser() {
        // Perform logout operations here, such as clearing session data, navigating to login screen, etc.
        // For example, if using Firebase Authentication:
        FirebaseAuth.getInstance().signOut()

        Toast.makeText(requireContext(), "Logout Successfully", Toast.LENGTH_SHORT).show()
        // After logout, navigate to the login screen
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)

        // If you want to close the activity that hosts the fragment, you can use getActivity() and call finish()
        drawerLayout.closeDrawer(GravityCompat.END)

    }

    private fun navigateToServiceActivity(serviceName: String) {
        val intent = Intent(requireContext(), ServiceActivity::class.java)
        intent.putExtra("key", serviceName)
        startActivity(intent)
    }

}
