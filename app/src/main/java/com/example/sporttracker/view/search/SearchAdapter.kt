package com.example.sporttracker.view.search;


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sporttracker.R
import com.example.sporttracker.loadImage
import com.example.sporttracker.model.Team
import kotlinx.android.synthetic.main.team_cardview.view.*


class SearchAdapter(var searchData: MutableList<Team>,var teamChangeListener: TeamChangeListener) :
    RecyclerView.Adapter<SearchAdapter.myViewHolder>() {


    interface TeamChangeListener {
        fun updateTeam(id: Int?,poster_url:String?)
    }

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

    inner class myViewHolder(val v: View) : RecyclerView.ViewHolder(v) {


        fun bind(team: Team) {
            v.teamName_name.text = team.team_name
            v.sportName.text = team.sport
            v.logo.loadImage(team.logo)
            v.setOnClickListener {
                teamChangeListener.updateTeam(team.team_id,team.logo)
            }

        }
    }

}