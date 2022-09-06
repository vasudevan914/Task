package com.example.task

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.task.databinding.ActivityProfileDetailsScreenBinding


class ProfileDetailsScreen : AppCompatActivity() {
    private lateinit var binding: ActivityProfileDetailsScreenBinding
    var profileList: List<ProfileData> = ArrayList()
    var dotscount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_details_screen)
        binding = ActivityProfileDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        var name :String = intent.getStringExtra("name")!!
        var address :String = intent.getStringExtra("address")!!
        var imageUrl :String = intent.getStringExtra("image")!!

        binding.tvProfileName.text = name
        binding.tvProfileAddress.text = address

        val db = DatabaseHandler(this)
        db.addContact(ProfileData("Vijay", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
        db.addContact(ProfileData("Ajith", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
        db.addContact(ProfileData("Surya", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
        db.addContact(ProfileData("Rajini", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))
        db.addContact(ProfileData("Kamal", "https://i.pinimg.com/564x/58/54/c2/5854c27cac8a2a3d5a4c50fccc3871d4.jpg","vijayanagar 6th street,velachery,chennai - 600005"))


        Log.d("Reading: ", "Reading all contacts..")
        profileList = db.allContacts

        val adapter = ViewPagerAdapter(this,profileList)

        binding.photosViewpager
            .adapter =adapter

        dotscount = adapter.count
        val dots = arrayOfNulls<ImageView>(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.tab_indicator_default
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            binding.SliderDots!!.addView(dots[i], params)
        }

        dots[0]!!.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.tab_indicator_selected
            )
        )

        binding.photosViewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.tab_indicator_default
                        )
                    )
                }
                dots[position]!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.tab_indicator_selected
                    )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }


}