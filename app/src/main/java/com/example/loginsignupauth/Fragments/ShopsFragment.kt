    package com.example.loginsignupauth.Fragments

    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import com.example.loginsignupauth.databinding.FragmentShopsBinding



    class ShopsFragment : Fragment() {

        private var _binding: FragmentShopsBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentShopsBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Access views using the binding object and perform operations
    //        binding.textViewName.text = "Hello, World!"
            // Replace textViewName with your actual TextView ID.

        }



    }
