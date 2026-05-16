package dev.nautilus.resources

import dev.nautilus.devapi.core.NauDevAPI
import dev.nautilus.devapi.manager.config.api.ConfigAPI
import dev.nautilus.devapi.manager.instance.api.InstanceAPI
import dev.nautilus.devapi.manager.lang.api.LangFileAPI
import dev.nautilus.resources.listener.PlayerListener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onEnable() {
        NauDevAPI.setup()

        NauDevAPI.getAPI<InstanceAPI>().setInstance(this)
        NauDevAPI.getAPI<LangFileAPI>().loadLanguages()
        NauDevAPI.getAPI<ConfigAPI>().loadConfig()

        server.pluginManager.registerEvents(PlayerListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
