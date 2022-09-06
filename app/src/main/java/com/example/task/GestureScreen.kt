package com.example.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.task.databinding.ActivityGestureScreenBinding

class GestureScreen : AppCompatActivity() {
    private lateinit var binding:ActivityGestureScreenBinding
    val adapter = GestureAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture_screen)
        binding = ActivityGestureScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

                val db = DatabaseHandler(this)
                db.addContact(ProfileData("Vijay", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
                db.addContact(ProfileData("Ajith", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
                db.addContact(ProfileData("Surya", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
                db.addContact(ProfileData("Rajini", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
                db.addContact(ProfileData("Kamal", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
                val profileList: List<ProfileData> = db.allContacts
                adapter.setMovieList(profileList)
                binding.rvProfileList
                    .adapter =adapter




        val onItemNewClickListener: GestureAdapter.ItemNewClickListener = object : GestureAdapter.ItemNewClickListener {


            override fun onNewClick(view: View?, position: Int, value: String) {
                if(value.equals("Yes")){
                    db.deleteContact(profileList[position].id)
                    Toast.makeText(this@GestureScreen,"Deleted Record", Toast.LENGTH_SHORT).show()
                    adapter.notifyDataSetChanged()
                    finish()

                }

                else {
                    Toast.makeText(this@GestureScreen,"Deleted Record", Toast.LENGTH_SHORT).show()

                    db.deleteContact(profileList[position].id)
                    adapter.notifyDataSetChanged()

                    finish()

                }
            }
        }
        adapter.setNewClickListener(onItemNewClickListener)


    }



}