/*
 * SPDX-FileCopyrightText: 2020 DB Station&Service AG <bahnhoflive-opensource@deutschebahn.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package de.deutschebahn.bahnhoflive.ui.hub;

import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;

import de.deutschebahn.bahnhoflive.R;
import de.deutschebahn.bahnhoflive.analytics.TrackingManager;
import de.deutschebahn.bahnhoflive.ui.search.DBStationSearchResult;
import de.deutschebahn.bahnhoflive.view.SingleSelectionManager;

class NearbyDbDeparturesViewHolder extends DbDeparturesViewHolder {
    private final DistanceViewHolder distanceViewHolder;

    public NearbyDbDeparturesViewHolder(ViewGroup parent, SingleSelectionManager singleSelectionManager, LifecycleOwner owner, TrackingManager trackingManager) {
        super(parent, R.layout.card_nearby_departures, singleSelectionManager, owner, trackingManager, null, TrackingManager.UiElement.ABFAHRT_NAEHE_BHF);
        distanceViewHolder = new DistanceViewHolder(itemView);
    }

    @Override
    protected void onBind(DBStationSearchResult item) {
        super.onBind(item);

        distanceViewHolder.bind(item.getTimetable().getDistanceInKm());
    }


}
