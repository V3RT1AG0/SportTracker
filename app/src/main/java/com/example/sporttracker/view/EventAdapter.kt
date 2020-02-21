package com.example.sporttracker.view;


import android.util.Log
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
            Log.d("manual",event.toString())
            v.event_name.text = event.event_name
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