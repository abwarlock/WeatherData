package com.abdev.weatherdata.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.abdev.weatherdata.R
import com.abdev.weatherdata.data.DataFactory
import com.abdev.weatherdata.data.models.CityData
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_citydata_list_dialog.*
import kotlinx.android.synthetic.main.fragment_citydata_list_dialog_item.view.*


class CityDataListDialogFragment : BottomSheetDialogFragment() {
    private var mListener: Listener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_citydata_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.adapter = CityDataAdapter(ArrayList(DataFactory.getInstance(context!!).cityDataDao.getAllCities()))
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val dialog = dialog as BottomSheetDialog?
            val bottomSheet = dialog!!.findViewById(R.id.design_bottom_sheet) as FrameLayout?
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = 0
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        mListener = if (parent != null) {
            parent as Listener
        } else {
            context as Listener
        }
    }

    override fun onDetach() {
        mListener = null
        super.onDetach()
    }

    interface Listener {
        fun onCityDataClicked(cityData: CityData)
    }

    private inner class CityDataAdapter internal constructor(private val listData: ArrayList<CityData>) :
        RecyclerView.Adapter<CityDataAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val (cityName, isSelected) = this.listData[position]
            holder.cityCheckBox.text = cityName
            holder.cityCheckBox.isChecked = isSelected
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        inner class ViewHolder internal constructor(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_citydata_list_dialog_item, parent, false)) {

            internal val cityCheckBox: CheckBox = itemView.cityView

            init {
                cityCheckBox.setOnClickListener {
                    mListener?.let {
                        it.onCityDataClicked(listData[adapterPosition])
                        dismiss()
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(): CityDataListDialogFragment = CityDataListDialogFragment()
    }
}
