package com.example.loginsignupauth.Fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginsignupauth.Ui.DoneActivity
import com.example.loginsignupauth.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root

        // Find the button using view binding
        val payButton = binding.btnPay

        // Set onClickListener on the button
        payButton.setOnClickListener {
            // Handle button click here
            // For example, navigate to PaymentActivity
            val intent = Intent(requireContext(),DoneActivity ::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
