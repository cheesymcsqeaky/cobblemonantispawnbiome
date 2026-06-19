package com.github.cheesymcsqeaky.cobblemonantispawnbiome.mixin;

import dev.cudzer.cobblemonalphas.entity.spawner.AlphaSpawner;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = AlphaSpawner.class, remap = false)
public class AlphaSpawnerMixin {

    @Shadow private MinecraftServer server;

    @Unique
    private static final ResourceLocation SPAWNPROOF_BIOME =
            ResourceLocation.fromNamespaceAndPath("cobblemonantispawnbiome", "spawnproof_biome");

    @Inject(method = "attemptSpawn", at = @At("HEAD"), cancellable = true)
    private void cleanPlayersBeforeSpawn(CallbackInfo ci) {
        if (this.server == null) return;

        List<ServerPlayer> players = this.server.getPlayerList().getPlayers();
        if (players.isEmpty()) return;

        // Check if ALL online players are currently inside your custom safe zone
        boolean allPlayersInSafeZone = true;
        for (ServerPlayer player : players) {
            var biomeHandle = player.level().getBiomeManager().getBiome(player.blockPosition());
            var biomeKeyOption = biomeHandle.unwrapKey();

            if (biomeKeyOption.isEmpty() || !biomeKeyOption.get().location().equals(SPAWNPROOF_BIOME)) {
                // Found at least one player in a normal biome, so alpha spawning can proceed normally
                allPlayersInSafeZone = false;
                break;
            }
        }

        // If every single player is inside the custom biome, cancel the global spawn attempt entirely
        if (allPlayersInSafeZone) {
            ci.cancel();
        }
    }
}
