package com.example.signup2.ui.role

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.signup2.data.model.Role
import com.example.signup2.databinding.ItemRoleBinding

class RoleViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemRoleBinding.bind(view)
    fun bind(role: Role) {
        binding.tvRole.setText(role.name)
    }
}