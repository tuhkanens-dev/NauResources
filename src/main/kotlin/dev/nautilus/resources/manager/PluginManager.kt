package dev.nautilus.resources.manager

import dev.nautilus.resources.Main
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.configuration.file.FileConfiguration
import java.io.File

object PluginManager {

    private lateinit var instance: Main
    private val miniMessage: MiniMessage = MiniMessage.miniMessage()

    fun loadPlugin(instance: Main) {
        this.instance = instance
    }

    fun getPlugin(): Main {
        return instance
    }

    fun getMiniMessages(): MiniMessage {
        return miniMessage
    }

}