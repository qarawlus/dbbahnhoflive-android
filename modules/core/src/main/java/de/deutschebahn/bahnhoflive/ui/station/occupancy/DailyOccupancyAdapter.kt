package de.deutschebahn.bahnhoflive.ui.station.occupancy

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.deutschebahn.bahnhoflive.repository.occupancy.model.Occupancy

class DailyOccupancyAdapter(
    private val timeTextWidth: Float,
    val onItemClickListener: View.OnClickListener?
) :
    RecyclerView.Adapter<DailyOccupancyViewHolder>() {

    var occupancy: Occupancy? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = DailyOccupancyViewHolder(parent, timeTextWidth, onItemClickListener)

    override fun getItemCount() = occupancy?.dailyOccupancies?.size ?: 0

    override fun onBindViewHolder(holder: DailyOccupancyViewHolder, position: Int) {
        holder.bind(occupancy?.let { it to position })
    }
}