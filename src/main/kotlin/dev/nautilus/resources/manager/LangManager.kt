package dev.nautilus.resources.manager

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

object LangManager {

    private val miniMessage = PluginManager.getMiniMessages()
    private val lang = FileLangManager.getCurrentLanguage()

    fun getString(key: String): String {
        return lang.getString(key) ?: ""
    }

    fun getStringList(key: String): String {
        val lines = lang.getStringList(key)

        if (lines.isEmpty()) {
            getString(key)
        }

        val line = lines.joinToString("\n")
        return line
    }

    fun getComponent(key: String, vararg placeholders: TagResolver): Component {
        val line = getString(key)

        return miniMessage.deserialize(line, TagResolver.resolver(*placeholders))
    }

    fun getComponentList(key: String, vararg placeholders: TagResolver): Component {
        val line = getStringList(key)

        if (line.isEmpty()) {
            getComponent(key, *placeholders)
        }

        return miniMessage.deserialize(line, TagResolver.resolver(*placeholders))
    }

}