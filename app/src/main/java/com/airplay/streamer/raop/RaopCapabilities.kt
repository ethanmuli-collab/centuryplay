package com.airplay.streamer.raop

/**
 * Helpers for interpreting RAOP mDNS TXT capabilities.
 */
object RaopCapabilities {
    fun encryptionTypes(features: Map<String, String>): Set<Int> {
        return features["et"]
            ?.split(",")
            ?.mapNotNull { it.trim().toIntOrNull() }
            ?.toSet()
            ?: emptySet()
    }

    /**
     * Personal fork: a receiver that also offers unencrypted audio (et=0) is not blocked. A real
     * Apple TV 2/3 advertises et=0,3,5 and accepts unencrypted RAOP. FairPlay-only receivers with
     * the same TXT record (e.g. AirScreen) now fail at connect instead of being greyed out.
     */
    fun requiresUnsupportedFairPlay(features: Map<String, String>): Boolean {
        val encryptionTypes = encryptionTypes(features)
        return 5 in encryptionTypes && 1 !in encryptionTypes && 0 !in encryptionTypes
    }
}
