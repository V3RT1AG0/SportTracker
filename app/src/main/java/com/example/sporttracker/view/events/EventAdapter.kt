package com.example.sporttracker.view.events;


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sporttracker.R
import com.example.sporttracker.model.Event
import kotlinx.android.synthetic.main.event_cardview.view.*


class EventAdapter(var eventData: MutableList<Event>) :
    RecyclerView.Adapter<EventAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): myViewHolder =
        myViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.event_cardview,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = eventData.size

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(eventData[position])
    }

    fun refresh(newEvents: List<Event>) {
        eventData.clear()
        eventData.addAll(newEvents)
        notifyDataSetChanged()
    }

    class myViewHolder(val v: View) : RecyclerView.ViewHolder(v) {


        fun bind(event: Event) {
            v.event_name.text = "${event.home_team} (${event.home_score}) vs ${event.away_team} (${event.away_score})"
            v.event_scores.text = "${event.home_team} ${if (event.home_score > event.away_score)  "won" else "lost"}"
            v.event_league.text = event.league_name
        }
    }

}