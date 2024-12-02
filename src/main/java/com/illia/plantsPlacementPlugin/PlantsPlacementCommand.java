package com.illia.plantsPlacementPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlantsPlacementCommand implements CommandExecutor {

    private boolean allowPlantsPlacement = false;

    public boolean isAllowPlantsPlacement() {
        return allowPlantsPlacement;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            allowPlantsPlacement = !allowPlantsPlacement;

            if (allowPlantsPlacement) {
                player.sendMessage("Plants placement enabled.");
            } else {
                player.sendMessage("Plants placement disabled.");
            }

            return true;
        }
        return false;
    }
}