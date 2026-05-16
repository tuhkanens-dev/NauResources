package dev.nautilus.resources.manager

import dev.nautilus.devapi.core.NauDevAPI
import dev.nautilus.devapi.manager.file.api.FileAPI
import dev.nautilus.resources.data.ResourceData
import java.util.concurrent.ConcurrentHashMap

class ResourcesFileManager {

    private val resources: ConcurrentHashMap<String, ResourceData> = ConcurrentHashMap()

    fun loadResources() {

        NauDevAPI.getAPI<FileAPI>().getResource("resources.yml", "")

    }

}