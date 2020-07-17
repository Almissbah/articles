package com.almissbah.articles.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class AppUtilsTest {
    val url1 = ""
    val url2 = "http://google.com"
    val url3 = "https://google.com"
    val url4 = "http:/google.com"
    val url5 = "http//google.com"

    @Test
    fun test_UrlValidation() {
        assertEquals(AppUtils.isValidUrl(url1), false)
        assertEquals(AppUtils.isValidUrl(url2), true)
        assertEquals(AppUtils.isValidUrl(url3), true)
        assertEquals(AppUtils.isValidUrl(url4), false)
        assertEquals(AppUtils.isValidUrl(url5), false)
    }
}