package com.illia.plantsPlacementPlugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPhysicsEvent;

import java.util.EnumSet;
import java.util.Set;

public class PlantsPlacementListener implements Listener {

    private final Main main;

    public PlantsPlacementListener(Main main) {
            this.main = main;
        }

    private static final Set<Material> PLANT_MATERIALS = EnumSet.of(
            Material.SHORT_GRASS,
            Material.TALL_GRASS,
            Material.FERN,
            Material.LARGE_FERN,
            Material.OAK_SAPLING,
            Material.SPRUCE_SAPLING,
            Material.BIRCH_SAPLING,
            Material.JUNGLE_SAPLING,
            Material.ACACIA_SAPLING,
            Material.DARK_OAK_SAPLING,
            Material.MANGROVE_PROPAGULE,
            Material.CHERRY_SAPLING,
            Material.AZALEA,
            Material.FLOWERING_AZALEA,
            Material.BROWN_MUSHROOM,
            Material.RED_MUSHROOM,
            Material.CRIMSON_FUNGUS,
            Material.WARPED_FUNGUS,
            Material.DEAD_BUSH,
            Material.DANDELION,
            Material.POPPY,
            Material.BLUE_ORCHID,
            Material.ALLIUM,
            Material.AZURE_BLUET,
            Material.ORANGE_TULIP,
            Material.PINK_TULIP,
            Material.RED_TULIP,
            Material.WHITE_TULIP,
            Material.OXEYE_DAISY,
            Material.CORNFLOWER,
            Material.LILY_OF_THE_VALLEY,
            Material.TORCHFLOWER,
            Material.WITHER_ROSE,
            Material.PINK_PETALS,
            Material.SPORE_BLOSSOM,
            Material.BAMBOO,
            Material.SUGAR_CANE,
            Material.CACTUS,
            Material.CRIMSON_ROOTS,
            Material.WARPED_ROOTS,
            Material.NETHER_SPROUTS,
            Material.WEEPING_VINES,
            Material.TWISTING_VINES,
            Material.SUNFLOWER,
            Material.LILAC,
            Material.ROSE_BUSH,
            Material.PEONY,
            Material.PITCHER_PLANT,
            Material.BIG_DRIPLEAF,
            Material.SMALL_DRIPLEAF,
            Material.CHORUS_PLANT,
            Material.CHORUS_FLOWER,
            Material.GLOW_LICHEN,
            Material.HANGING_ROOTS
    );

    @EventHandler
    public void onBlockPlace(BlockCanBuildEvent event) {
        if (event.getPlayer() == null) return;
        if (!PlantsPlacementCommand.isAllowPlantsPlacement(event.getPlayer())) return;
        if (PLANT_MATERIALS.contains(event.getMaterial())) {
            event.setBuildable(true);
            main.getAuthorizedPlantBlocks().add(event.getBlock().getLocation());
            main.savePlantsDataAsync();
        }
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        if (main.getAuthorizedPlantBlocks().contains(event.getBlock().getLocation())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();
        if (main.getAuthorizedPlantBlocks().contains(location)) {
            main.getAuthorizedPlantBlocks().remove(location);
            main.savePlantsDataAsync();
        }
    }

}