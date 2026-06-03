# CobblemonAntispawnBiome

An addon mod specifically built for our server to manage wild encounters alongside the **Cobblemon Alpha Project** mod.

## 🌟 Overview
This mod introduces a dedicated safe-zone biome designed to have **zero** natural Pokémon spawns. It provides a peaceful region for building, server hubs, or custom player towns without wild entity clutter. 

## 🛠️ Important Configuration Fixes
By default, some external side-mods like *Cobblemon Alpha Project* utilize secondary injection methods or custom spawn logic that can accidentally bypass standard biome spawn-pool exclusions. 

To ensure Alphas do not bypass the restrictions, we have implemented a aggressive global **Location Filter Rule** within Cobblemon's logic system rather than just blanking out the base `spawn_pool_world` entries.

## ⚙️ Setup
For setup instructions, please see the [Fabric Documentation page](https://docs.fabricmc.net/develop/getting-started/creating-a-project#setting-up) related to the IDE that you are using.

## 📄 License
This template is available under the CC0 license. Feel free to learn from it and incorporate it in your own projects.
