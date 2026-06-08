package com.github.cheesymcsqeaky.cobblemonantispawnbiome.mixin;

import dev.cudzer.cobblemonalphas.entity.spawner.AlphaSpawner;
import dev.cudzer.cobblemonalphas.entity.spawner.spawnData.safety.ISpawnCondition;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = AlphaSpawner.class, remap = false)
public class AlphaSpawnerMixin {

    @Shadow private List<ISpawnCondition> spawnConditions;

    // Cache the ResourceLocation cleanly
    @Unique
    private static final ResourceLocation SPAWNPROOF_BIOME =
            ResourceLocation.fromNamespaceAndPath("cobblemonantispawnbiome", "spawnproof_biome");

    // Static, leak-proof functional implementation of your spawn condition
    @Unique
    private static final ISpawnCondition BIOME_CONDITION = new ISpawnCondition() {
        @Override
        public boolean isSafe(Level level, BlockPos pos) {
            var biomeKeyOption = level.getBiome(pos).unwrapKey();
            if (biomeKeyOption.isPresent()) {
                // If it matches your target biome, fail the spawn!
                return !biomeKeyOption.get().location().equals(SPAWNPROOF_BIOME);
            }
            // For ALL other biomes, return true so vanilla spawns function normally
            return true;
        }
    };

    @Inject(method = "init", at = @At("TAIL"))
    private void injectCustomBiomeCondition(CallbackInfo ci) {
        // Double-check to prevent duplicate array appending if init() fires multiple times
        if (!this.spawnConditions.contains(BIOME_CONDITION)) {
            List<ISpawnCondition> mutableConditions = new ArrayList<>(this.spawnConditions);
            mutableConditions.add(BIOME_CONDITION);
            this.spawnConditions = mutableConditions;
        }
    }
}
