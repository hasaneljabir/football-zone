package id.hasaneljabir.footballzone.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.hasaneljabir.footballzone.R
import id.hasaneljabir.footballzone.activity.matchDetail.MatchDetailActivity
import id.hasaneljabir.footballzone.entity.event.Event
import id.hasaneljabir.footballzone.utils.DateHelper
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.startActivity

class MatchAdapter(val eventList: List<Event>, val context: Context?) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event) {
            if (event.intHomeScore == null) {
                itemView.tvDate.setTextColor(itemView.context.resources.getColor(R.color.colorDateNextMatch))
                itemView.tvTime.setTextColor(itemView.context.resources.getColor(R.color.colorDateNextMatch))
            }
            itemView.tvDate.text = event.dateEvent?.let { DateHelper.formatDateToMatch(it) }
            itemView.tvTime.text = event.strTime.take(5)
            itemView.tvHomeName.text = event.strHomeTeam
            itemView.tvHomeScore.text = event.intHomeScore
            itemView.tvAwayName.text = event.strAwayTeam
            itemView.tvAwayScore.text = event.intAwayScore

            itemView.setOnClickListener {
                itemView.context.startActivity<MatchDetailActivity>("match" to event)
            }
        }
    }
}