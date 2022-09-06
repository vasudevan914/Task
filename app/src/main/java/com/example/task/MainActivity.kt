package com.example.task

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task.databinding.ActivityMainBinding
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val adapter = ProfileListAdapter()
    var profileList: List<ProfileData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivThreeDots.setOnClickListener {

            val intent = Intent(this@MainActivity,GestureScreen::class.java)
            startActivity(intent)
        }



        val db = DatabaseHandler(this)
            db.addContact(ProfileData("Vijay", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
            db.addContact(ProfileData("Ajith", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
            db.addContact(ProfileData("Surya", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
            db.addContact(ProfileData("Rajini", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
            db.addContact(ProfileData("Kamal", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))


        Log.d("Reading: ", "Reading all contacts..")
        profileList = db.allContacts
        adapter.setMovieList(profileList)
        binding.rvProfileList
            .adapter =adapter


        val onItemClickListener: ProfileListAdapter.ItemClickListener = object : ProfileListAdapter.ItemClickListener {

            override fun onClick(view: View?, position: Int) {

                val intent  = Intent(this@MainActivity,ProfileDetailsScreen::class.java)
                intent.putExtra("name",profileList.get(position).name)
                intent.putExtra("address",profileList.get(position).desc)
                intent.putExtra("image",profileList.get(position).imageUrl)


                startActivity(intent)
            }
        }

        val onItemNewClickListener: ProfileListAdapter.ItemNewClickListener = object : ProfileListAdapter.ItemNewClickListener {


            override fun onNewClick(view: View?, position: Int, value: String) {
                if(value.equals("Yes")){
                    db.deleteContact(profileList[position].id)
                    Toast.makeText(this@MainActivity,"Deleted Record",Toast.LENGTH_SHORT).show()
                    adapter.notifyDataSetChanged()

                }

                else {
                    Toast.makeText(this@MainActivity,"Deleted Record",Toast.LENGTH_SHORT).show()

                    db.deleteContact(profileList[position].id)
                    adapter.notifyDataSetChanged()

                }
            }
        }
        adapter.setClickListener(onItemClickListener)
        adapter.setNewClickListener(onItemNewClickListener)


    }

}