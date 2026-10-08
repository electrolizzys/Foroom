package com.example.foroom.Helper

/** Short time-based suffix that keeps names and messages from repeated runs apart. */
fun uniqueSuffix(): String = System.currentTimeMillis().toString(36)
