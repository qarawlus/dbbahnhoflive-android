/*
 * SPDX-FileCopyrightText: 2020 DB Station&Service AG <bahnhoflive-opensource@deutschebahn.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package de.deutschebahn.bahnhoflive.repository

import com.google.android.gms.maps.model.LatLng

object StationPositions {

    val data by lazy {
        mapOf(
            "8325" to LatLng(48.791128, 11.406021), //Ingolstadt Audi
            "7433" to LatLng(48.473378, 8.482925) //Dornstetten-Aach
        )
    }
}