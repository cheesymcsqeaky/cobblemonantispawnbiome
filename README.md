# CobblemonAntispawnBiome

An addon mod specifically built for Minecraft **1.21.1** (Fabric) to manage wild encounters alongside the **Cobblemon Alpha Project** mod.

## 🌟 Overview
This mod introduces a dedicated safe-zone biome designed to have **zero** natural Pokémon spawns (including vanilla mobs and any other entity types unless forced by commands). It provides a peaceful region for server hubs, custom player towns, or community build projects without the clutter of wild entities.

## ⛔ Dependencies & Requirements
This mod **requires** the following dependencies to function. It will prevent the game from booting if they are missing, ensuring that custom spawn logic does not crash your environment:

* **Minecraft:** `1.21.1`
* **Fabric Loader:** `0.15.0` or higher
* **Cobblemon:** `1.5.0`+
* **Cobblemon Alpha Project:** `1.0.0`+ (Hard Dependency)

## 🛠️ Important Configuration Fixes
By default, external side-mods like *Cobblemon Alpha Project* utilize secondary injection methods or custom spawn logic that can accidentally bypass standard biome spawn-pool exclusions. 

To ensure Alphas do not bypass these restrictions, this mod works directly with an aggressive global **Filter Rule** within Cobblemon Alpha Project's logic system to completely suppress unauthorized entity injections.

## ⚙️ Setup
For setup instructions and IDE configuration, please refer to the official [Fabric Documentation page](https://fabricmc.net) related to the environment you are using. Ensure you define `cobblemon-alpha-project` inside your `fabric.mod.json` file's `"depends"` block.
