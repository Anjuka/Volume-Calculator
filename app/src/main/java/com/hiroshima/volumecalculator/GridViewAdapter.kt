package com.hiroshima.volumecalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by AnjukaK on 11/06/24.
 */

class GridViewAdapter(
    private val context: Context,
    private val list: List<String>,
    private val screenWidth: Int
): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var gridView = convertView
        if (gridView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            gridView = inflater.inflate(R.layout.grid_item, parent, false)
        }

        val textView = gridView!!.findViewById<TextView>(R.id.tv_type)
        val ivType = gridView!!.findViewById<ImageView>(R.id.iv_type)
        textView.text = list[position]

        when(position){
            0 -> ivType.setBackgroundResource(R.drawable.sphere)
            1 -> ivType.setBackgroundResource(R.drawable.cone)
            2 -> ivType.setBackgroundResource(R.drawable.cube)
            3 -> ivType.setBackgroundResource(R.drawable.cylinder)
            4 -> ivType.setBackgroundResource(R.drawable.rectacgular)
            5 -> ivType.setBackgroundResource(R.drawable.capsule)
            6 -> ivType.setBackgroundResource(R.drawable.cap)
            7 -> ivType.setBackgroundResource(R.drawable.conical)
            8 -> ivType.setBackgroundResource(R.drawable.ellipsoid)
            9 -> ivType.setBackgroundResource(R.drawable.pyramid)
            10 -> ivType.setBackgroundResource(R.drawable.tube)
        }

        val layoutParams = gridView.layoutParams
        layoutParams.width = (screenWidth / 2) - 80
        //layoutParams.height = (screenWidth / 2) - 80
        gridView.layoutParams = layoutParams

        return gridView
    }
}