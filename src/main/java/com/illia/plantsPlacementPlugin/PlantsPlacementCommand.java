package com.illia.plantsPlacementPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;

public class PlantsPlacementCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        if (!hasWealthPermission(player)) {
            sender.sendMessage(ChatColor.RED + "Вам потрібна підписка " + ChatColor.AQUA + "Wealth " + ChatColor.RED + "для цього!");
            return false;
        }

        allowPlantsPlacement = !allowPlantsPlacement;

        if (allowPlantsPlacement) {
            player.sendMessage("Розміщення рослин на будь які блоки ввімкнено");
        } else {
            player.sendMessage("Розміщення рослин на будь які блоки вимкнено.");
        }

        return true;
    }

    private boolean allowPlantsPlacement = false;

    public boolean isAllowPlantsPlacement() {
        return allowPlantsPlacement;
    }

    public static boolean hasWealthPermission(Player player) {
        for (PermissionAttachmentInfo permissionInfo : player.getEffectivePermissions()) {
            String permission = permissionInfo.getPermission();
            if (permission.startsWith("Wealth")) {
                return true;
            }
        }
        return false;
    }

}