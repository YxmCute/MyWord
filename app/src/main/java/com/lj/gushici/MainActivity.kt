package com.lj.gushici

import android.os.Build
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.lj.gushici.ui.main.GuShiCiAdapter
import com.lj.gushici.ui.main.SectionsPagerAdapter
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myPager2 = findViewById<ViewPager2>(R.id.view_pager)
        myPager2.orientation=ViewPager2.ORIENTATION_VERTICAL
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(ScaleInTransformer())
        compositePageTransformer.addTransformer(MarginPageTransformer(resources.getDimension(R.dimen.fab_margin).toInt()))
        myPager2.setPageTransformer(compositePageTransformer)
        val dataAdapter = GuShiCiAdapter()
        dataAdapter.setList(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        myPager2.adapter = dataAdapter
        myPager2.apply {
            offscreenPageLimit = 1
            val recycleview = getChildAt(0) as RecyclerView
            recycleview.apply {
                val padding = resources.getDimensionPixelOffset(R.dimen.fab_margin)
                setPadding(padding, padding, padding, padding)
                clipToPadding = false
            }
        }
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    class ScaleInTransformer : ViewPager2.PageTransformer {
        private val mMinScale = DEFAULT_MIN_SCALE
        override fun transformPage(view: View, position: Float) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.elevation = -abs(position)
            }
            val pageWidth = view.width
            val pageHeight = view.height

            view.pivotY = (pageHeight / 2).toFloat()
            view.pivotX = (pageWidth / 2).toFloat()
            if (position < -1) {
                view.scaleX = mMinScale
                view.scaleY = mMinScale
                view.pivotX = pageWidth.toFloat()
            } else if (position <= 1) {
                if (position < 0) {
                    val scaleFactor = (1 + position) * (1 - mMinScale) + mMinScale
                    view.scaleX = scaleFactor
                    view.scaleY = scaleFactor
                    view.pivotX = pageWidth * (DEFAULT_CENTER + DEFAULT_CENTER * -position)
                } else {
                    val scaleFactor = (1 - position) * (1 - mMinScale) + mMinScale
                    view.scaleX = scaleFactor
                    view.scaleY = scaleFactor
                    view.pivotX = pageWidth * ((1 - position) * DEFAULT_CENTER)
                }
            } else {
                view.pivotX = 0f
                view.scaleX = mMinScale
                view.scaleY = mMinScale
            }
        }

        companion object {

            const val DEFAULT_MIN_SCALE = 0.85f
            const val DEFAULT_CENTER = 0.5f
        }
    }
}