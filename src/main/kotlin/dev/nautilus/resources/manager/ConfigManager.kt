package dev.nautilus.resources.manager

import org.bukkit.configuration.file.FileConfiguration

object ConfigManager {

    private val plugin = PluginManager.getPlugin()
    private val config = plugin.config

    fun loadConfig() {
        plugin.saveDefaultConfig()

        setCurrentLanguage()
    }

    private fun setCurrentLanguage() {
        val lang = config.getString("language")
            ?: throw IllegalStateException("'language' not found in config.yml!")

        FileLangManager.setCurrentLanguage(lang)
    }

    fun getConfig(): FileConfiguration {
        return config
    }

}