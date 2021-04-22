package com.example.signup2.ui.gallery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.signup2.data.RoleDataSource
import com.example.signup2.data.model.Role
import com.example.signup2.data.Result
import com.example.signup2.databinding.FragmentGalleryBinding
import com.example.signup2.ui.role.RoleAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private fun context(): Context? = activity?.applicationContext
    private val binding get() = _binding!!
    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var adapter: RoleAdapter
    private val roles = mutableListOf<Role>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textGallery.text = it
        })
        return binding.root
    }
}