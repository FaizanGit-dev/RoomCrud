package com.example.room.mvvm.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.mvvm.R
import com.example.room.mvvm.model.LoginTableModel
import javax.inject.Inject
import android.content.Context

class LoginAdapter @Inject constructor (private val loginList: MutableList<LoginTableModel>) :
    RecyclerView.Adapter<LoginAdapter.LoginViewHolder>() {

    var onEditClickListener: ((LoginTableModel) -> Unit)? = null
    var onDeleteClickListener: ((LoginTableModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list, parent, false)
        return LoginViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LoginViewHolder, position: Int) {
        val currentItem = loginList[position]
        holder.nameTextView.text = currentItem.Name
        holder.classTextView.text = currentItem.SClass
        holder.rollnoTextView.text=currentItem.RollNo
        holder.schoolTextView.text=currentItem.School
        holder.ageTextView.text=currentItem.Age
        holder.genderTextView.text=currentItem.gender
        holder.editButton.setOnClickListener {
            showEditDialog(holder.itemView.context, currentItem)
        }
        holder.deleteButton.setOnClickListener {
            onDeleteClickListener?.invoke(currentItem)
        }
    }

    override fun getItemCount() = loginList.size

    fun updateData(newLoginList: List<LoginTableModel>) {
        loginList.clear()
        loginList.addAll(newLoginList)
        notifyDataSetChanged()
    }

    class LoginViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameView)
        val classTextView: TextView = itemView.findViewById(R.id.classView)
        val rollnoTextView: TextView = itemView.findViewById(R.id.rollnoView)
        val schoolTextView: TextView = itemView.findViewById(R.id.schoolView)
        val ageTextView: TextView = itemView.findViewById(R.id.ageView)
        val genderTextView: TextView = itemView.findViewById(R.id.genderView)
        val editButton: ImageButton = itemView.findViewById(R.id.editButton)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    private fun showEditDialog(context: Context, item: LoginTableModel) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_item, null)


        val nameEditText = dialogView.findViewById<EditText>(R.id.editTextName)
        val classEditText = dialogView.findViewById<EditText>(R.id.editTextClass)
        val rollNoEditText = dialogView.findViewById<EditText>(R.id.editTextRollNo)
        val schoolEditText = dialogView.findViewById<EditText>(R.id.editTextSchool)
        val ageEditText = dialogView.findViewById<EditText>(R.id.editTextAge)
        val genderEditText = dialogView.findViewById<EditText>(R.id.editTextGender)


        nameEditText.setText(item.Name)
        classEditText.setText(item.SClass)
        rollNoEditText.setText(item.RollNo)
        schoolEditText.setText(item.School)
        ageEditText.setText(item.Age)
        genderEditText.setText(item.gender)

        val dialogBuilder = AlertDialog.Builder(dialogView.context)
            .setTitle("Edit Item")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                item.Name = nameEditText.text.toString()
                item.SClass = classEditText.text.toString()
                item.RollNo = rollNoEditText.text.toString()
                item.School = schoolEditText.text.toString()
                item.Age = ageEditText.text.toString()
                item.gender = genderEditText.text.toString()


                notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)

        val dialog = dialogBuilder.create()
        dialog.show()
    }
}

data class ItemType(
    var Name: String,
    var SClass: String,
    var RollNo: String,
    var School: String,
    var Age: String,
    var gender: String
)
