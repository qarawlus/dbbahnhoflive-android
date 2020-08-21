package de.deutschebahn.bahnhoflive.ui.hub

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import de.deutschebahn.bahnhoflive.analytics.TrackingManager
import de.deutschebahn.bahnhoflive.backend.db.publictrainstation.model.StopPlace
import de.deutschebahn.bahnhoflive.backend.hafas.model.HafasStation
import de.deutschebahn.bahnhoflive.backend.toHafasStation
import de.deutschebahn.bahnhoflive.persistence.FavoriteStationsStore
import de.deutschebahn.bahnhoflive.persistence.RecentSearchesStore
import de.deutschebahn.bahnhoflive.repository.DbTimetableResource
import de.deutschebahn.bahnhoflive.repository.InternalStation
import de.deutschebahn.bahnhoflive.ui.ViewHolder
import de.deutschebahn.bahnhoflive.ui.search.DBStationSearchResult
import de.deutschebahn.bahnhoflive.ui.search.HafasStationSearchResult
import de.deutschebahn.bahnhoflive.ui.search.StopPlaceSearchResult
import de.deutschebahn.bahnhoflive.view.SingleSelectionManager
import java.util.*

internal class NearbyDeparturesAdapter(
        private val owner: LifecycleOwner,
        private val recentSearchesStore: RecentSearchesStore,
        private val favoriteHafasStationsStore: FavoriteStationsStore<HafasStation>,
        private val favoriteStationsStore: FavoriteStationsStore<InternalStation>,
        val trackingManager: TrackingManager
) : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder<*>>() {

    private val singleSelectionManager: SingleSelectionManager = SingleSelectionManager(this).apply {
        addListener(SingleSelectionManager.Listener { selectionManager ->
            val selection = selectionManager.selection

            if (selection == SingleSelectionManager.INVALID_SELECTION) {
                return@Listener
            }

            items?.get(selection)?.onLoadDetails()
        })

    }

    private var items: List<NearbyStationItem>? = null


    private val hafasTimetables = ArrayList<HafasStationSearchResult>()

    private var dbTimetables: MutableList<DBStationSearchResult>? = null

    private val dbStationCount: Int
        get() = Math.min(1, if (dbTimetables == null) 0 else dbTimetables!!.size)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> =
        when (viewType) {
            1 -> NearbyDeparturesViewHolder(parent, owner, singleSelectionManager, trackingManager)
            else -> NearbyDbDeparturesViewHolder(
                parent,
                singleSelectionManager,
                owner,
                trackingManager
            )
        }

    override fun onBindViewHolder(holder: ViewHolder<*>, position: Int) {
        items?.get(position)?.bindViewHolder(holder)
    }

    override fun getItemViewType(position: Int) = items?.get(position)?.type ?: 0

    override fun getItemCount() = Math.min(items?.size ?: 0, 20)

    fun notifyContentUpdated() {
        notifyItemRangeChanged(0, itemCount)
    }

    fun clearSelection() {
        singleSelectionManager.clearSelection()
    }

    fun setData(stopPlaces: List<StopPlace>, dbTimeTables: List<DbTimetableResource>) {
        clearSelection()

        items = stopPlaces.mapNotNull {
            when {
                it.isDbStation -> {
                    NearbyDbStationItem(
                        StopPlaceSearchResult(
                            it,
                            recentSearchesStore,
                            favoriteStationsStore
                        )
                    )
                }
                it.isLocalTransportStation -> {
                    NearbyHafasStationItem(
                        HafasStationSearchResult(
                            it.toHafasStation(),
                            recentSearchesStore,
                            favoriteHafasStationsStore
                        )
                    )
                }
                else -> null
            }
        }

        notifyDataSetChanged()

    }

    companion object {
        val TAG = NearbyDeparturesAdapter::class.java.simpleName
    }
}

interface NearbyStationItem {
    val type: Int

    val distance: Float

    fun bindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder)

    fun onLoadDetails()
}