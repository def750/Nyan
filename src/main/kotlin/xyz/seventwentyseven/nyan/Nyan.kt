package xyz.seventwentyseven.nyan

import org.bukkit.plugin.java.JavaPlugin

// register our command

class Nyan : JavaPlugin() {
    override fun onEnable() {
        logger.info("Nyan enabled!")
    }

    override fun onDisable() {
        logger.info("Nyan disabled!")
    }
}
