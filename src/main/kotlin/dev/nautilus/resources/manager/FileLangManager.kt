package dev.nautilus.resources.manager

import dev.nautilus.resources.data.LangData
import org.bukkit.configuration.file.YamlConfiguration

object FileLangManager {

    private val plugin = PluginManager.getPlugin()
    private val dataFolder = plugin.dataFolder

    private lateinit var CURRENT_LANG_FILE: LangData
    private lateinit var langFiles: MutableList<LangData>

    private const val LANG_FOLDER = "lang"

    fun loadLanguages() {

        langFiles = mutableListOf()

        if (!dataFolder.exists()) {
            dataFolder.mkdir()
        }

        val loader = Thread.currentThread().contextClassLoader
        val targetLangFolder = dataFolder.resolve(LANG_FOLDER).apply { mkdir() }

        // ['en.yml', 'ru.yml']
        val fileNames = loader.getResourceAsStream(LANG_FOLDER)
            ?.bufferedReader()?.readLines() ?: return

        fileNames.forEach { fileName ->
            val targetFile = targetLangFolder.resolve(fileName)
            if (!targetFile.exists()) {
                loader.getResourceAsStream("$LANG_FOLDER/$fileName")?.use { input ->
                    targetFile.writeBytes(input.readBytes())
                }
            }

            val yamlConfiguration: YamlConfiguration = YamlConfiguration.loadConfiguration(targetFile)
            val langName = fileName.removeSuffix(".yml")

            langFiles.add(LangData(name = langName, yaml = yamlConfiguration))
        }

    }

    fun getLanguage(name: String): LangData? {
        return langFiles.firstOrNull { it.name == name }
    }

    fun getLanguages(): List<LangData> {
        return langFiles
    }

    fun getCurrentLanguage(): YamlConfiguration {
        if (!::CURRENT_LANG_FILE.isInitialized) {
            CURRENT_LANG_FILE = langFiles.firstOrNull() ?: throw IllegalStateException("No languages loaded!")
        }
        return CURRENT_LANG_FILE.yaml
    }

    fun setCurrentLanguage(name: String) {
        CURRENT_LANG_FILE = getLanguage(name) ?: return
    }

}