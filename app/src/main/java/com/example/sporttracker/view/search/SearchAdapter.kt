package com.example.sporttracker.view.search;


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sporttracker.R
import com.example.sporttracker.model.Team
import kotlinx.android.synthetic.main.event_cardview.view.*
import kotlinx.android.synthetic.main.team_cardview.view.*


class SearchAdapter(var searchData: MutableList<Team>) :
    RecyclerView.Adapter<SearchAdapter.myViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): myViewHolder =
        myViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.team_cardview,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = searchData.size

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(searchData[position])
    }

    fun refresh(newEvents: List<Team>) {
        searchData.clear()
        searchData.addAll(newEvents)
        notifyDataSetChanged()
    }

    class myViewHolder(val v: View) : RecyclerView.ViewHolder(v) {


        fun bind(team: Team) {
            Log.d("manual",team.toString())
            v.teamName_name.text = team.team_name
//            v.anime_summary.text = anime.summary
//            v.poster.loadImage(anime.image_url)
//            v.setOnClickListener { v ->
//                val intent = Intent(v.context, AnimeMain::class.java)
//                intent.putExtra("anime", anime)
//
//                v.context.startActivity(intent)
//            }
        }
    }

}