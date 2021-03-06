/*
 * SPDX-FileCopyrightText: 2020 DB Station&Service AG <bahnhoflive-opensource@deutschebahn.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package de.deutschebahn.bahnhoflive.analytics;

import java.util.Map;

public interface Trackable {
    String getTrackingTag();

    Map<String, Object> getTrackingContextVariables();
}
