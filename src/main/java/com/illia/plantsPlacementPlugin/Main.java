package com.illia.plantsPlacementPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Main extends JavaPlugin {

    private Set<Location> authorizedPlantBlocks = new HashSet<>();
    private File configFile;
    private FileConfiguration config;

    public Set<Location> getAuthorizedPlantBlocks() {
        return authorizedPlantBlocks;
    }

    @Override
    public void onEnable() {
        PlantsPlacementCommand pluginCommandPlantsPlacement = new PlantsPlacementCommand();
        Objects.requireNonNull(this.getCommand("plantsplacement")).setExecutor(pluginCommandPlantsPlacement);
        getServer().getPluginManager().registerEvents(new PlantsPlacementListener(this), this);
        loadPlantsData();

        getLogger().info("PlantsPlacementPlugin is enabled!");
    }

    @Override
    public void onDisable() {
    }

    private void loadPlantsData() {
        configFile = new File(getDataFolder(), "plants.yml");
        if (!configFile.exists()) {
            saveResource("plants.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        authorizedPlantBlocks.clear();
        if (config.contains("plants")) {
            for (String key : Objects.requireNonNull(config.getConfigurationSection("plants")).getKeys(false)) {
                String[] parts = key.split(",");
                if (parts.length == 4) {
                    String worldName = parts[0];
                    World world = Bukkit.getWorld(worldName);
                    if (world == null) {
                        getLogger().warning("World " + worldName + " not found for location " + key);
                        continue;
                    }
                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);
                    int z = Integer.parseInt(parts[3]);

                    Location location = new Location(world, x, y, z);
                    authorizedPlantBlocks.add(location);
                }
            }
        }
    }

    public void savePlantsDataAsync() {
        final FileConfiguration configCopy = new YamlConfiguration();
        configCopy.set("plants", null);
        for (Location loc : authorizedPlantBlocks) {
            String key = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
            configCopy.set("plants." + key, true);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    configCopy.save(configFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(this);
    }

}
