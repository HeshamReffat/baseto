package com.hisham.baseto.presentation.fragments.auth

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hisham.baseto.R
import com.hisham.baseto.data.database.KelineDatabase
import com.hisham.baseto.databinding.FragmentRegisterBinding
import com.hisham.baseto.domain.repository.UserRepository
import com.hisham.baseto.domain.viewmodels.auth.AuthViewModel
import com.hisham.baseto.domain.viewmodels.auth.AuthViewModelFactory
import com.hisham.baseto.presentation.fragments.main.MainFragment
import java.io.IOException

class RegisterFragment : Fragment() {
    private val viewModel: AuthViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val database = KelineDatabase.initDatabase(application.applicationContext).dao
        val repo = UserRepository(database,requireActivity())
        val viewModelFactory = AuthViewModelFactory(repo, application.applicationContext)

        ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)
    }
    lateinit var binding: FragmentRegisterBinding
    lateinit var pickedMedia: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        pickedMedia = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                binding.roundImage.setImageURI(it.data?.data)
                val encodedBase64 =
                    it.data?.data?.let { it1 -> getBase64ForUriAndPossiblyCrash(it1) }
                Log.i("Base64", encodedBase64 ?: "")
            }
        }
        binding.roundImage.setOnClickListener {
            takePictureFromGallery()

        }
        viewModel.loggedIn.observe(viewLifecycleOwner, Observer {
            if(it == true){
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(this.id, MainFragment())
                transaction.disallowAddToBackStack()
                transaction.commit()
            }
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun takePictureFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        pickedMedia.launch(intent)
    }

    private fun getBase64ForUriAndPossiblyCrash(uri: Uri): String {
        var base64: String = ""
        try {
            val stream = context?.contentResolver?.openInputStream(uri)
            val bytes = stream?.readBytes()
            base64 = Base64.encodeToString(bytes, Base64.DEFAULT)
            stream?.close()
        } catch (error: IOException) {
            error.printStackTrace() // This exception always occurs
        }
        return base64
    }
}
