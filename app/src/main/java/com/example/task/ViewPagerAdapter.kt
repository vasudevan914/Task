package com.example.task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import java.util.ArrayList

class ViewPagerAdapter(context: Context, var profileList:List<ProfileData>) : PagerAdapter() {
    private val context: Context
   // private var profileList:List<ProfileData> = ArrayList()
    private var layoutInflater: LayoutInflater? = null
    //private val images = arrayOf<Int>(R.drawable.slide1, R.drawable.slide2, R.drawable.slide3)
    override fun getCount(): Int {
        return profileList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater!!.inflate(R.layout.viewpager_layout, null)
        val imageView: ImageView = view.findViewById(R.id.imageView) as ImageView
        Glide.with(context).load(profileList[position].imageUrl).into(imageView)

        //imageView.setImageResource(profileList[position])
        val vp: ViewPager = container as ViewPager
        vp.addView(view, 0)
        return view
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp: ViewPager = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
    init {
        this.context = context
    }
}