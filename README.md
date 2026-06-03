# CobblemonAntispawnBiome

An addon mod specifically built for Minecraft **1.21.1** (Fabric) to manage wild encounters alongside the **Cobblemon Alpha Project** mod.

## 🌟 Overview
This mod introduces a dedicated safe-zone biome designed to have **zero** natural Pokémon spawns (including vanilla mobs and any other entity types unless forced by commands). It provides a peaceful region for server hubs, custom player towns, or community build projects without the clutter of wild entities.

## ⛔ Dependencies & Requirements
This mod **requires** the following dependencies to function properly:

* **Minecraft:** `1.21.1`
* **Fabric Loader:** `0.15.0` or higher
* **Cobblemon:** `1.7.2`+
* **Cobblemon Alpha Project:** any

## 🛠️ Important Configuration Fixes
By default, external side-mods like *Cobblemon Alpha Project* utilize secondary injection methods or custom spawn logic that can accidentally bypass standard biome spawn-pool exclusions. 

To ensure Alphas do not bypass these restrictions, this mod works directly with an aggressive global **Filter Rule** within Cobblemon Alpha Project's logic system to completely suppress unauthorized entity injections.

## ⚙️ Installation
No extra configuration or setup is required. Users simply need to download this mod along with **Cobblemon Alpha Project** and drop both files into the `mods` folder. 

* **For Multiplayer:** Must be installed on both the **Server** and the **Client**.
* **For Singleplayer:** Install directly into your client profile.
