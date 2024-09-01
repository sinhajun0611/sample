package io.github.sinhajun.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin enable");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disable");
    }
}
