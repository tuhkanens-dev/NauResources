package dev.nautilus.resources.manager

import dev.nautilus.resources.data.LangData
import org.bukkit.configuration.file.YamlConfiguration
import java.util.jar.JarFile

object FileLangManager {

    private val plugin = PluginManager.getPlugin()
    private val dataFolder = plugin.dataFolder

    private lateinit var CURRENT_LANG_FILE: LangData
    private var langFiles: MutableList<LangData> = mutableListOf()

    private const val LANG_FOLDER = "lang"

    fun loadLanguages() {

        val targetLangFolder = dataFolder.resolve(LANG_FOLDER).apply { mkdirs() }
        val jarPath = plugin.javaClass.protectionDomain.codeSource.location.toURI().path

        JarFile(jarPath).use { jar ->
            jar.entries().asSequence()
                .filter { !it.isDirectory && it.name.startsWith("$LANG_FOLDER/") }
                .forEach { entry ->
                    val targetFile = targetLangFolder.resolve(entry.name.removePrefix("$LANG_FOLDER/"))

                    if (!targetFile.exists()) {
                        jar.getInputStream(entry).use { input ->
                            targetFile.writeBytes(input.readBytes())
                        }
                    }

                    val yaml = YamlConfiguration.loadConfiguration(targetFile)
                    langFiles.add(LangData(name = targetFile.nameWithoutExtension, yaml = yaml))
                }
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