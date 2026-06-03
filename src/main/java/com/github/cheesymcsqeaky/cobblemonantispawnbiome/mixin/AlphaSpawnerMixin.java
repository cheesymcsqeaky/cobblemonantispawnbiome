package com.github.cheesymcsqeaky.cobblemonantispawnbiome.mixin;

import dev.cudzer.cobblemonalphas.entity.spawner.AlphaSpawner;
import dev.cudzer.cobblemonalphas.entity.spawner.spawnData.safety.ISpawnCondition;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = AlphaSpawner.class, remap = false)
public class AlphaSpawnerMixin {

    @Shadow private List<ISpawnCondition> spawnConditions;

    @Inject(method = "init", at = @At("TAIL"))
    private void injectCustomBiomeCondition(CallbackInfo ci) {
        List<ISpawnCondition> mutableConditions = new ArrayList<>(this.spawnConditions);

        mutableConditions.add(new ISpawnCondition() {
            @Override
            public boolean isSafe(Level level, BlockPos pos) {
                var biomeKeyOption = level.getBiome(pos).unwrapKey();

                if (biomeKeyOption.isPresent()) {
                    ResourceLocation biomeLocation = biomeKeyOption.get().location();

                    if (biomeLocation.getNamespace().equals("cobblemonantispawnbiome") &&
                            biomeLocation.getPath().equals("spawnproof_biome")) {
                        return false;
                    }
                }
                return true;
            }
        });

        this.spawnConditions = mutableConditions;
    }
}
