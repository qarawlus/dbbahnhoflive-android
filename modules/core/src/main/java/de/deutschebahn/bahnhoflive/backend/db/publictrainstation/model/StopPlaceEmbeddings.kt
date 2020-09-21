/*
 * SPDX-FileCopyrightText: 2020 DB Station&Service AG <bahnhoflive-opensource@deutschebahn.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package de.deutschebahn.bahnhoflive.backend.db.publictrainstation.model

class StopPlaceEmbeddings {
    var neighbours: List<Neighbour?>? = null
    var tripleSCenter: TripleSCenter? = null
    var travelCenters: List<EmbeddedTravelCenter>? = null
}
