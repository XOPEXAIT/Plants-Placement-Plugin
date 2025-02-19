package com.illia.plantsPlacementPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PlantsPlacementCommand pluginCommandPlantsPlacement = new PlantsPlacementCommand();
        Objects.requireNonNull(this.getCommand("plantsplacement")).setExecutor(pluginCommandPlantsPlacement);
        getServer().getPluginManager().registerEvents(new PlantsPlacementListener(), this);

        getLogger().info("PlantsPlacementPlugin is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
