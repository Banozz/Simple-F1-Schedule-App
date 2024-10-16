package com.example.praktikumappf1.bottomnavigations.ui.race

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikumappf1.bottomnavigations.R
import com.example.praktikumappf1.bottomnavigations.databinding.FragmentRaceBinding
import com.example.praktikumappf1.bottomnavigations.ui.Race

class RaceFragment : Fragment() {

    private var _binding: FragmentRaceBinding? = null
    private lateinit var rvRaces: RecyclerView
    private val list = ArrayList<Race>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val raceViewModel =
            ViewModelProvider(this).get(RaceViewModel::class.java)

        _binding = FragmentRaceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        raceViewModel.text.observe(viewLifecycleOwner) {
            //textView.text = it
        }

        initRecyclerView(root)

        return root
    }

    private fun initRecyclerView(root: View) {
        rvRaces = root.findViewById(R.id.rv_Races)
        rvRaces.setHasFixedSize(true)

        list.addAll(getListRaces())
        showRecyclerList()

        root.setOnApplyWindowInsetsListener { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getListRaces(): ArrayList<Race> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataFullName = resources.getStringArray(R.array.data_full_name)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val dataDate = resources.getStringArray(R.array.data_date)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val dataWeb = resources.getStringArray(R.array.data_web)
        val listTrack = ArrayList<Race>()
        for (i in dataName.indices) {
            val track = Race(dataName[i], dataFullName[i], dataImg.getResourceId(i, -1), dataDate[i], dataDesc[i], dataWeb[i])
            listTrack.add(track)
        }
        dataImg.recycle()
        return listTrack
    }

    private fun showRecyclerList() {
        rvRaces.layoutManager = LinearLayoutManager(requireContext())
        rvRaces.adapter = object : RecyclerView.Adapter<RaceViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_races, parent, false)
                return RaceViewHolder(view)
            }

            override fun getItemCount(): Int = list.size

            override fun onBindViewHolder(holder: RaceViewHolder, position: Int) {
                val (name, fullName, img, date) = list[position]
                holder.tvName.text = name
                holder.tvShortDescription.text = fullName
                holder.imgPhoto.setImageResource(img)
                holder.tvDate.text = date
                holder.itemView.setOnClickListener {
                    val intent = Intent(holder.itemView.context, DetailedRaceFragment::class.java)
                    intent.putExtra("race_data", list[position])
                    holder.itemView.context.startActivity(intent)
                }
            }
        }
    }

    inner class RaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPhoto: ImageView = view.findViewById(R.id.img_item_photo)
        val tvName: TextView = view.findViewById(R.id.tv_item_name)
        val tvShortDescription: TextView = view.findViewById(R.id.tv_item_description)
        val tvDate: TextView = view.findViewById(R.id.tv_item_date)
    }
}
