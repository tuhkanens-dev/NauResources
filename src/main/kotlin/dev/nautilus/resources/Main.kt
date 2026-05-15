package dev.nautilus.resources

import dev.nautilus.resources.listener.PlayerListener
import dev.nautilus.resources.manager.ConfigManager
import dev.nautilus.resources.manager.FileLangManager
import dev.nautilus.resources.manager.PluginManager
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onEnable() {
        PluginManager.loadPlugin(this)
        ConfigManager.loadConfig()
        FileLangManager.loadLanguages()

        server.pluginManager.registerEvents(PlayerListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
