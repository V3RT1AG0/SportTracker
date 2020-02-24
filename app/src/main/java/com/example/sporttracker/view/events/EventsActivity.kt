package com.example.sporttracker.view.events

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.sporttracker.R
import com.example.sporttracker.viewmodel.SportViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sporttracker.loadImage
import com.example.sporttracker.model.Event
import com.example.sporttracker.view.search.SearchAdapter
import com.example.sporttracker.view.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem


class EventsActivity : AppCompatActivity(), SearchAdapter.TeamChangeListener, View.OnClickListener {


    lateinit var sportViewModel: SportViewModel
    var dialogFragment = SearchFragment()
    private val mylist = mutableListOf<Event>()
    private val eventAdapter = EventAdapter(mylist)
    var facebook_url: String? = ""
    var instagram_url: String? = ""
    var twitter_url: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sportViewModel = ViewModelProviders.of(this).get(SportViewModel::class.java)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
        }
        load_default()
        setObservers()
        setUpSearchFragment()
        setListeners()
    }

    private fun setListeners() {
        facebook.setOnClickListener(this)
        twitter.setOnClickListener(this)
        instagram.setOnClickListener(this)
    }

    private fun setUpSearchFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        dialogFragment.show(fragmentTransaction, "dialog")
    }

    private fun setObservers() {
        sportViewModel.teamDetails.observe(this, Observer { events ->
            events?.let {
                eventAdapter.refresh(it.events)
                val team = it.team
                t_name.text = "${team.team_name} (${team.alternate})"
                facebook.visibility =
                    if (!team.facebook.isNullOrEmpty()) View.VISIBLE else View.GONE
                twitter.visibility = if (!team.twitter.isNullOrEmpty()) View.VISIBLE else View.GONE
                instagram.visibility =
                    if (!team.instagram.isNullOrEmpty()) View.VISIBLE else View.GONE
                facebook_url = team.facebook
                twitter_url = team.twitter
                instagram_url = team.instagram
                poster.loadImage(team.logo)
                recyclerView.visibility = View.VISIBLE
            }
        })

        sportViewModel.loading.observe(this, Observer { loading ->
            loading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    recyclerView.visibility = View.GONE
                    error_view.visibility = View.GONE
                }
            }
        })

        sportViewModel.error.observe(this, Observer { errorOccoured ->
            errorOccoured?.let {
                error_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    recyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    fun load_default() {
        /*TODO loading some default data initially for now. Will be handled in shared preferences in real app to retain user's selected team.*/
        sportViewModel.fetchEventHistoryfor(133612)
        poster.loadImage("https://www.thesportsdb.com/images/media/team/badge/xzqdr11517660252.png")
    }

    override fun updateTeam(id: Int?, poster_url: String?) {
        id?.let {
            sportViewModel.fetchEventHistoryfor(it)
        }
        dialogFragment.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item -> {
                setUpSearchFragment()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.facebook -> startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse("https://" + facebook_url)
                )
            )
            R.id.twitter -> startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://" + twitter_url)
                )
            )
            R.id.instagram -> {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://" + instagram_url)
                    )
                )
            }
        }
    }
}
