package com.illia.plantsPlacement;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MainPlantsPlacement extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PlantsPlacementCommand pluginCommandPlantsPlacement = new PlantsPlacementCommand();
        Objects.requireNonNull(this.getCommand("plantsplacement")).setExecutor(pluginCommandPlantsPlacement);
        getServer().getPluginManager().registerEvents(new PlantsPlacementListener(pluginCommandPlantsPlacement), this);

        Bukkit.getLogger().info("Plugin PlantsPlacement is enabled!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Plugin PlantsPlacement is disabled!");
    }
}