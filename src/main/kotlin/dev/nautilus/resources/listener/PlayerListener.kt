package dev.nautilus.resources.listener

import dev.nautilus.resources.manager.LangManager
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener : Listener {

    @Override
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        player.sendMessage(LangManager.getComponent("hello", Placeholder.parsed("player", player.name)))
    }

}