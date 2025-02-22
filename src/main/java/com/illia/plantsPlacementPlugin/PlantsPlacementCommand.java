package com.illia.plantsPlacementPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PlantsPlacementCommand implements CommandExecutor {

    private static final Map<String, Boolean> playerPlantsPlacement = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Цю команду можуть виконувати тільки гравці!");
            return false;
        }

        Player player = (Player) sender;

        if (!hasCommandPermission(player)) {
            sender.sendMessage(ChatColor.RED + "Вам потрібна підписка " + ChatColor.AQUA + "Wealth " + ChatColor.RED + "для цього!");
            return false;
        }

        boolean currentState = playerPlantsPlacement.getOrDefault(player.getName(), false);
        playerPlantsPlacement.put(player.getName(), !currentState);

        if (!currentState) {
            player.sendMessage("Розміщення рослин на будь-які блоки ввімкнено.");
        } else {
            player.sendMessage("Розміщення рослин на будь-які блоки вимкнено.");
        }

        return true;
    }

    public static boolean hasCommandPermission(Player player) {
        return player.hasPermission("plantsplacement.use");
    }

    public static boolean isAllowPlantsPlacement(Player player) {
        return playerPlantsPlacement.getOrDefault(player.getName(), false);
    }
}