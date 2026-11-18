package com.airplay.streamer.raop

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RaopCapabilitiesTest {

    @Test
    fun appleTv2and3_unencryptedOffered_isNotBlocked() {
        assertFalse(RaopCapabilities.requiresUnsupportedFairPlay(mapOf("et" to "0,3,5")))
    }

    @Test
    fun fairPlayOnly_isBlocked() {
        assertTrue(RaopCapabilities.requiresUnsupportedFairPlay(mapOf("et" to "3,5")))
        assertTrue(RaopCapabilities.requiresUnsupportedFairPlay(mapOf("et" to "5")))
    }

    @Test
    fun rsaOrNoEncryptionInfo_isNotBlocked() {
        assertFalse(RaopCapabilities.requiresUnsupportedFairPlay(mapOf("et" to "0,1")))
        assertFalse(RaopCapabilities.requiresUnsupportedFairPlay(mapOf("et" to "1,5")))
        assertFalse(RaopCapabilities.requiresUnsupportedFairPlay(emptyMap()))
    }
}
