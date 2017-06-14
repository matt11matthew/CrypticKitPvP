package com.cryptickits.kitpvp;

import com.cryptickits.kitpvp.player.CrypticPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CrypticKitPvP extends JavaPlugin {

    private static CrypticKitPvP instance;

    public static CrypticKitPvP getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        registerListeners();
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(CrypticPlayerManager.getInstance(), this);
    }

    @Override
    public void onDisable() {

    }
}
