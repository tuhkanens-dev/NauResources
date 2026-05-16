package dev.nautilus.resources.data

import java.net.URI

data class ResourceData(
    val url: URI,
    val sha256: String
)