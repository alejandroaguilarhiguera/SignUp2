package com.example.signup2.ui.role

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.signup2.R
import com.example.signup2.data.model.Role

class RoleAdapter (private val roles: MutableList<Role>): RecyclerView.Adapter<RoleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        return RoleViewHolder(layoutInflater.inflate(R.layout.item_role, parent, false))
    }

    override fun onBindViewHolder(holder: RoleViewHolder, position: Int) {
        val item = roles[position];
        holder.bind(item);
    }

    override fun getItemCount(): Int {
        return roles.size;
    }
}