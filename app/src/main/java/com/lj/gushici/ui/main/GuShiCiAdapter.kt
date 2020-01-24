package com.lj.gushici.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.lj.gushici.R

/**
 * @author Li Jun
 * @date 2020/1/24 21:48
 * @filename  GuShiCiA
 */
class GuShiCiAdapter : RecyclerView.Adapter<GuShiCiAdapter.GuShiCiViewHolder>() {
    private var dataList: List<Int> = ArrayList()
    fun setList(list: List<Int>) {
        this.dataList = list

    }

    class GuShiCiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ll_background: LinearLayout = itemView.findViewById(R.id.ll_background)
        private val backGroundColorRes = arrayOf("#CCFF99", "#41F1E5", "#947A6D", "#FF99CC","#DDA52D","#90B44B")
        fun bindData(i: Int) {
            ll_background.setBackgroundColor(Color.parseColor(backGroundColorRes[i % 6]))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuShiCiViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gushici, parent, false)
        return GuShiCiViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: GuShiCiViewHolder, position: Int) {
        holder.bindData(position)
    }

}