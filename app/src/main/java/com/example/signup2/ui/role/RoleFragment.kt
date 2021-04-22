package com.example.signup2.ui.role

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.signup2.data.Result
import com.example.signup2.data.RoleDataSource
import com.example.signup2.data.model.Role
import com.example.signup2.databinding.FragmentRoleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var _binding: FragmentRoleBinding
    private val binding get() = _binding!!
    private fun context(): Context? = activity?.applicationContext
    private lateinit var adapter: RoleAdapter
    private val roles = mutableListOf<Role>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoleBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        initRecyclerView()
        refreshData()
        return binding.root
    }
    private fun initRecyclerView() {
        adapter = RoleAdapter(roles)
        binding.rvRoles.layoutManager = LinearLayoutManager(context())
        binding.rvRoles.adapter = adapter
    }
    private fun refreshData() {
        MainScope().launch {
            val result = withContext(Dispatchers.Default) { RoleDataSource().getAll(context()!!) }
            if (result is Result.Success) {
                roles.clear()
                roles.addAll(result.data)
                adapter.notifyDataSetChanged()
            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RoleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}