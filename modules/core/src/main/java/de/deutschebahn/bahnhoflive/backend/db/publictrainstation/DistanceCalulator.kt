/*
 * SPDX-FileCopyrightText: 2020 DB Station&Service AG <bahnhoflive-opensource@deutschebahn.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package de.deutschebahn.bahnhoflive.backend.db.publictrainstation

import android.location.Location

class DistanceCalulator(
    private val latitude: Double,
    private val longitude: Double
) {

    val distanceResults: FloatArray = floatArrayOf(0f)

    fun calculateDistance(latitude: Double, longitude: Double): Float {
        Location.distanceBetween(
            latitude,
            longitude,
            this.latitude,
            this.longitude,
            distanceResults
        )
        return distanceResults[0] / 1000
    }
}