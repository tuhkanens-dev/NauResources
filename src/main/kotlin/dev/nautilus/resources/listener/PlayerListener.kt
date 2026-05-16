package dev.nautilus.resources.listener

import dev.nautilus.devapi.core.NauDevAPI
import dev.nautilus.devapi.manager.lang.api.LangAPI
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener : Listener {

    @Override
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        player.sendMessage(NauDevAPI.getAPI<LangAPI>().getComponent("hello", Placeholder.parsed("player", player.name)))
    }

}