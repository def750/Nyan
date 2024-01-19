package xyz.seventwentyseven.nyan

import org.bukkit.plugin.java.JavaPlugin
import xyz.seventwentyseven.nyan.commands.MainCommand
import kotlin.jvm.internal.Intrinsics

class Main : JavaPlugin() {
    override fun onEnable() {
        logger.info("Nyan enabled!")
        // Register commands in MainCommand.kt class
        getCommand("nyan")?.setExecutor(MainCommand())
    }

    override fun onDisable() {
        logger.info("Nyan disabled!")

    }
}
