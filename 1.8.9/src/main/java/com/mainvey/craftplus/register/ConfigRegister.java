package com.mainvey.craftplus.register;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigRegister {

    public final String[] Recipes;
    public final String[] Fuels;
    public final String[] Smeltings;
    public final String[] Brewings;
    public final String[] Forgings;

    public ConfigRegister(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        config.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, "1.Use # character as comment sign and all after words are regarded as comment ignored by analyzer.\n2.When there is an object needs parameter of quantity or metadata, it needs in form of {domain:object, quantity(, metadata)}.\n3.Use 32767 to represent all objects that are the same kind but have different metadata. \n4.Press F3 + H key to see more information of objects.");

        Recipes = config.get(
                Configuration.CATEGORY_GENERAL,
                "recipes",
                new String[] {
                        "#Minecraft:Item.Combat",
                        "{minecraft:chainmail_helmet, {&&&, &*&}, {&, minecraft:iron_bars}} #Compose [Chain Helmet] with 5x[Iron Bars] in &&&,& & form.",
                        "{minecraft:chainmail_chestplate, {&*&, &&&, &&&}, {&, minecraft:iron_bars}} #Compose [Chain Chest Plate] with 8x[Iron Bars] in & &,&&&,&&& form.",
                        "{minecraft:chainmail_leggings, {&&&, &*&, &*&}, {&, minecraft:iron_bars}} #Compose [Chain Leggings] with 7x[Iron Bars] in &&&,& &,& & form.",
                        "{minecraft:chainmail_boots, {&*&, &*&}, {&, minecraft:iron_bars}} #Compose [Chain Boots] with 4x[Iron Bars] in & &,& & form.",
                        "#Minecraft:Block.Building",
                        "{{minecraft:dirt, 4, 2}, {&*, *&}, {&, {minecraft:dirt, 1, 0}}, {*, {minecraft:dye, 1, 15}}} #Compose 4x[Podzol(Dirt_2)] with 2x[Dirt(Dirt_0)] and 2x[Bone Meal(Dye_15)] in &*,*& form.",
                        "{minecraft:bedrock, {&&&, &*&, &&&}, {&, minecraft:obsidian}, {*, minecraft:stone}} #Compose [Bedrock] with 8x[Obsidian] and [Stone] in &&&,&*&,&&& form.",
                        "{{minecraft:sand, 4, 1}, {&*, *&}, {&, {minecraft:sand, 1, 0}, {*, {minecraft:dye, 1, 1}}}} #Compose 4x[Red Sand(Sand_1)] with 2x[Sand(Sand_0)] and 2x[Rose Red(Dye_1)] in &*,*& form.",
                        "{{minecraft:gravel, 4}, {&*, *&}, {&, {minecraft:sand, 1, 0}, {*, minecraft:cobblestone}}} #Compose 4x[Gravel] with 2x[Sand(Sand_0)] and 2x[Cobble Stone] in &*,*& form.",
                        "{minecraft:sponge, {&*&, *$*, &*&}, {&, {minecraft:wool, 1, 4}, {*, minecraft:string}, {$, minecraft:bucket}}} #Compose [Sponge] with [Yellow Wool(Wool_4)], [String] and [Bucket] in &*&,*$*,&*& form.",
                        "{minecraft:ice, {&&&, &*&, &&&}, {&, minecraft:snow}, {*, minecraft:water_bucket}} #Compose [Ice] with [Snow] and [Water Bucket] in &&&,&*&,&&& form.",
                        "{minecraft:pumpkin_seeds, minecraft:wheat_seeds, {minecraft:dye, 1, 15}} #Compose [Pumpkin Seeds] with [Wheat Seeds] and [Bone Meal(Dye_15)] in shapeless form.",
                        "{minecraft:melon_seeds, minecraft:wheat_seeds, {minecraft:dye, 1, 0}} #Compose [Melon Seeds] with [Wheat Seeds] and [Ink Sac(Dye_0)] in shapeless form.",
                        "{minecraft:mycelium, {&&&, &*&, &&&}, {&, minecraft:brown_mushroom}, {*, {minecraft:dirt, 1, 2}}} #Compose [Mycelium] with [Podzol] and 8x[Brown Mushroom] in &&&,&*&,&&& form.",
                        "{minecraft:mycelium, {&&&, &*&, &&&}, {&, minecraft:red_mushroom}, {*, {minecraft:dirt, 1, 2}}} #Compose [Mycelium] with [Podzol] and 8x[Red Mushroom] in &&&,&*&,&&& form.",
                        "#Minecraft:Block.Building.Ore",
                        "{minecraft:coal_ore, minecraft:coal, minecraft:stone} #Compose [Coal Ore] with [Coal] and [Stone] in shapeless form.",
                        "{minecraft:iron_ore, minecraft:iron_ingot, minecraft:stone} #Compose [Iron Ore] with [Iron Ingot] and [Stone] in shapeless form.",
                        "{minecraft:gold_ore, minecraft:gold_ingot, minecraft:stone} #Compose [Gold Ore] with [Gold Ingot] and [Stone] in shapeless form.",
                        "{minecraft:diamond_ore, minecraft:diamond, minecraft:stone} #Compose [Diamond Ore] with [Diamond] and [Stone] in shapeless form.",
                        "{minecraft:emerald_ore, minecraft:emerald, minecraft:stone} #Compose [Emerald Ore] with [Emerald] and [Stone] in shapeless form.",
                        "{minecraft:lapis_ore, {&*&, *&*, &*&}, {&, {minecraft:dye, 1, 4}}, {*, minecraft:stone}} #Compose [Lapis Lazuli Ore] with 5x[Lapis Lazuli(Dye_4)] and 4x[Stone] in &*&,*&*,&*& form.",
                        "{minecraft:redstone_ore, {&*&, *&*, &*&}, {&, minecraft:redstone}, {*, minecraft:stone}} #Compose [Red Stone Ore] with 5x[Red Stone] and 4x[Stone] in &*&,*&*,&*& form.",
                        "{minecraft:quartz_ore, {&*&, *&*, &*&}, {&, minecraft:quartz}, {*, minecraft:stone}} #Compose [Nether Quartz Ore] with 5x[Nether Quartz] and 4x[Stone] in &*&,*&*,&*& form.",
                        "#Minecraft:Block.Decoration",
                        "{minecraft:web, {&&&, &&&, &&&}, {&, minecraft:string}} #Compose [Web] with 9x[String] in &&&,&&&,&&& form.",
                        "{minecraft:waterlily, {&*&, &&&, &&&}, {&, minecraft:vine}} #Compose [Lily Pad] with 8x[Vines] in & &,&&&,&&& form.",
                        "{minecraft:end_portal_frame, {*$*, &&&}, {*, minecraft:ender_pearl}, {&, minecraft:end_stone}} #Compose [End Portal] with 2x[Ender Pearl] and 3x[End Stone] in * *,&&& form.",
                        "#Minecraft:Block.Decoration.Sapling",
                        "{{minecraft:sapling, 1, 1}, {minecraft:sapling, 1, 0}, {minecraft:dye, 1, 3}} #Compose [Spruce Sapling(Sapling_1)] with [Oak Sapling(Sapling_0)] and [Cocoa Beans(Dye_3)] in shapeless form.",
                        "{{minecraft:sapling, 1, 2}, {minecraft:sapling, 1, 0}, {minecraft:dye, 1, 15}} #Compose [Birch Sapling(Sapling_2)] with [Oak Sapling(Sapling_0)] and [Bone Meal(Dye_15)] in shapeless form.",
                        "{{minecraft:sapling, 1, 3}, {minecraft:sapling, 1, 0}, {minecraft:dye, 1, 14}} #Compose [Jungle Sapling(Sapling_3)] with [Oak Sapling(Sapling_0)] and [Orange Dye(Dye_14)] in shapeless form.",
                        "{{minecraft:sapling, 1, 4}, {minecraft:sapling, 1, 0}, {minecraft:dye, 1, 1}} #Compose [Acacia Sapling(Sapling_4)] with [Oak Sapling(Sapling_0)] and [Rose Red(Dye_1)] in shapeless form.",
                        "{{minecraft:sapling, 1, 5}, {minecraft:sapling, 1, 0}, {minecraft:dye, 1, 0}} #Compose [Dark Oak Sapling(Sapling_5)] with [Oak Sapling(Sapling_0)] and [Ink Sac(Dye_0)] in shapeless form.",
                        "#Minecraft:Item.Transportation",
                        "{minecraft:saddle, {&&&, &*&}, {&, minecraft:leather}, {*, minecraft:lead}} #Compose [Saddle] with [Lead] and 5x[Leather] in &&&,&*& form.",
                        "#Minecraft:Item.Miscellaneous",
                        "{minecraft:experience_bottle, {&&&, &*&, &&&}, {&, minecraft:gold_ingot}, {*, {minecraft:potion, 1, 0}}} #Compose [Bottle of Enchanting] with [Water Bottle(Potion_0)] and 8x[Gold Ingot] in &&&,&*&,&&& form.",
                        "#Minecraft:Item.Miscellaneous.HorseArmor",
                        "{minecraft:iron_horse_armor, {&$$, &*&, &&&}, {&, minecraft:iron_ingot}, {*, minecraft:saddle}} #Compose [Iron Horse Armor] with [Saddle] and 6x[Iron Ingot] in &  ,&*&,&&& form.",
                        "{minecraft:golden_horse_armor, {&$$, &*&, &&&}, {&, minecraft:gold_ingot}, {*, minecraft:saddle}} #Compose [Gold Horse Armor] with [Saddle] and 6x[Gold Ingot] in &  ,&*&,&&& form.",
                        "{minecraft:diamond_horse_armor, {&$$, &*&, &&&}, {&, minecraft:diamond}, {*, minecraft:saddle}} #Compose [Diamond Horse Armor] with [Saddle] and 6x[Diamond] in &  ,&*&,&&& form.",
                        "#Minecraft:Item.Tools",
                        "{minecraft:name_tag, minecraft:sign, minecraft:lead} #Compose [Name Tag] with [Sign] and [Lead] in shapeless form."
                },
                "1.Please customize recipes in shaped form of {domain:output, {1st_row_signs(, 2nd_row_signs, 3rd_row_signs)}, {sign1, domain:input1}(, {sign2, domain:input2}, ...)}\nor in shapeless form of {domain:output, domain:input1, domain:input2, ...}.\n2.For example, {{minecraft:gravel, 4}, {&*, *&}, {minecraft:sand, 1, 0}, {minecraft:cobblestone}} #Composing 4x[Gravel] with 2x[Sand(Sand_0)] and 2x[Cobble Stone] in &*,*& form.\nor {{minecraft:log, 1, 1}, {minecraft:log, 1, 0}, {minecraft:dye, 1, 3}} #Compose [Spruce Wood(Log_1)] with [Oak Wood(Log_0)] and [Cocoa Beans(Dye_3)] in shapeless form."
        ).getStringList();

        Fuels = config.get(
                Configuration.CATEGORY_GENERAL,
                "fuels",
                new String[] {
                        "#Minecraft:Block.Decoration",
                        "{{minecraft:leaves, 1, 32767}, 100} #[Leaves(Leaves)] can burn for five seconds.",
                        "{{minecraft:leaves2, 1, 32767}, 100} #[Leaves(Leaves2)] can burn for five seconds.",
                        "#Minecraft:Item.Miscellaneous",
                        "{minecraft:book, 200} #[Book] can burn for ten seconds.",
                        "{minecraft:writable_book, 200} #[Writable Book] can burn for ten seconds.",
                        "{minecraft:paper, 50} #[Paper] can burn for two fifths of second.",
                        "{minecraft:map, 50} #[Empty Map] can burn for two fifths of second.",
                        "{minecraft:filled_map, 50} #[Map] can burn for two fifths of second."
                },
                "1.Please customize fuels in form of {domain:object, gametick}\n2.one second equals twenty gameticks.\n3.For example, {minecraft:diamond, 200} #[Diamond] can burn for ten seconds."
        ).getStringList();

        Smeltings = config.get(
                Configuration.CATEGORY_GENERAL,
                "smeltings",
                new String[] {
                        "#Minecraft:Block.Decoration",
                        "{{minecraft:sapling, 1, 32767}, minecraft:deadbush, 0.5} #Smelt [Sapling] to [Dead Bush] and acquire 0.5 experience."
                },
                "1.Please customize smeltings in form of {domain:input, domain:output, acquirable_experience}.\n2.For example, {{minecraft:dirt, 1, 1}, {minecraft:dirt, 1, 2}, 0.5} #Smelt [Coarse Dirt(Dirt_1)] to [Podzol(Dirt_2)] acquiring 0.5 experience."
        ).getStringList();

        Brewings = config.get(
                Configuration.CATEGORY_GENERAL,
                "brewings",
                new String[] {
                        "{{minecraft:potion, 1, 32767}, minecraft:gold_block, minecraft:experience_bottle} #Brew 3x[Awkward Potion] to 3x[Bottle of Enchanting] with [Block of Gold]."
                },
                "1.Please customize brewings in form of {domain:input_potion, domain:input_material, domain:output}.\n2.For example, {{minecraft:potion, 1, 16}, minecraft:gold_block, minecraft:experience_bottle} #Brew 3x[Awkward Potion] to 3x[Bottle of Enchanting] with [Block of Gold]."
        ).getStringList();

        Forgings = config.get(
                Configuration.CATEGORY_GENERAL,
                "forgings",
                new String[] {
                        "#Minecraft:Item.Tool",
                        "#Minecraft:Item.Tool, Wood->Stone",
                        "{minecraft:stone_sword, minecraft:wooden_sword, {minecraft:cobblestone, 2}, 1} #Forge [Wooden Sword] to [Stone Sword] with 2x[Cobble Stone] requiring 1 experience.",
                        "{minecraft:stone_axe, minecraft:wooden_axe, {minecraft:cobblestone, 3}, 1} #Forge [Wooden Axe] to [Stone Axe] with 3x[Cobble Stone] requiring 1 experience.",
                        "{minecraft:stone_pickaxe, minecraft:wooden_pickaxe, {minecraft:cobblestone, 3}, 1} #Forge [Wooden Pickaxe] to [Stone Pickaxe] with 3x[Cobble Stone] requiring 1 experience.",
                        "{minecraft:stone_hoe, minecraft:wooden_hoe, {minecraft:cobblestone, 2}, 1} #Forge [Wooden Hoe] to [Stone Hoe] with 2x[Cobble Stone] requiring 1 experience.",
                        "{minecraft:stone_shovel, minecraft:wooden_shovel, minecraft:cobblestone, 1} #Forge [Wooden Shovel] to [Stone Shovel] with [Cobble Stone] requiring 1 experience.",
                        "#Minecraft:Item.Tool, Stone->Iron",
                        "{minecraft:iron_sword, minecraft:stone_sword, {minecraft:iron_ingot, 2}, 1} #Forge [Stone Sword] to [Iron Sword] with 2x[Iron Ingot] requiring 1 experience.",
                        "{minecraft:iron_axe, minecraft:stone_axe, {minecraft:iron_ingot, 3}, 1} #Forge [Stone Axe] to [Iron Axe] with 3x[Iron Ingot] requiring 1 experience.",
                        "{minecraft:iron_pickaxe, minecraft:stone_pickaxe, {minecraft:iron_ingot, 3}, 1} #Forge [Stone Pickaxe] to [Iron Pickaxe] with 3x[Iron Ingot] requiring 1 experience.",
                        "{minecraft:iron_hoe, minecraft:stone_hoe, {minecraft:iron_ingot, 2}, 1} #Forge [Stone Hoe] to [Iron Hoe] with 2x[Iron Ingot] requiring 1 experience.",
                        "{minecraft:iron_shovel, minecraft:stone_shovel, minecraft:iron_ingot, 1} #Forge [Stone Shovel] to [Iron Shovel] with [Iron Ingot] requiring 1 experience.",
                        "#Minecraft:Item.Tool, Stone->Gold",
                        "{minecraft:golden_sword, minecraft:stone_sword, {minecraft:gold_ingot, 2}, 1} #Forge [Stone Sword] to [Gold Sword] with 2x[Gold Ingot] requiring 1 experience.",
                        "{minecraft:golden_axe, minecraft:stone_axe, {minecraft:gold_ingot, 3}, 1} #Forge [Stone Axe] to [Gold Axe] with 3x[Gold Ingot] requiring 1 experience.",
                        "{minecraft:golden_pickaxe, minecraft:stone_pickaxe, {minecraft:gold_ingot, 3}, 1} #Forge [Stone Pickaxe] to [Gold Pickaxe] with 3x[Gold Ingot] requiring 1 experience.",
                        "{minecraft:golden_hoe, minecraft:stone_hoe, {minecraft:gold_ingot, 2}, 1} #Forge [Stone Hoe] to [Gold Hoe] with 2x[Gold Ingot] requiring 1 experience.",
                        "{minecraft:golden_shovel, minecraft:stone_shovel, minecraft:gold_ingot, 1} #Forge [Stone Shovel] to [Gold Shovel] with [Gold Ingot] requiring 1 experience.",
                        "#Minecraft:Item.Tool, Iron->Diamond",
                        "{minecraft:diamond_sword, minecraft:iron_sword, {minecraft:diamond, 2}, 1} #Forge [Iron Sword] to [Diamond Sword] with 2x[Diamond] requiring 1 experience.",
                        "{minecraft:diamond_axe, minecraft:iron_axe, {minecraft:diamond, 3}, 1} #Forge [Iron Axe] to [Diamond Axe] with 3x[Diamond] requiring 1 experience.",
                        "{minecraft:diamond_pickaxe, minecraft:iron_pickaxe, {minecraft:diamond, 3}, 1} #Forge [Iron Pickaxe] to [Diamond Pickaxe] with 3x[Diamond] requiring 1 experience.",
                        "{minecraft:diamond_hoe, minecraft:iron_hoe, {minecraft:diamond, 2}, 1} #Forge [Iron Hoe] to [Diamond Hoe] with 2x[Diamond] requiring 1 experience.",
                        "{minecraft:diamond_shovel, minecraft:iron_shovel, minecraft:diamond, 1} #Forge [Iron Shovel] to [Diamond Shovel] with [Diamond] requiring 1 experience.",
                        "#Minecraft:Item.Tool, Gold->Diamond",
                        "{minecraft:diamond_sword, minecraft:golden_sword, {minecraft:diamond, 2}, 1} #Forge [Gold Sword] to [Diamond Sword] with 2x[Diamond] requiring 1 experience.",
                        "{minecraft:diamond_axe, minecraft:golden_axe, {minecraft:diamond, 3}, 1} #Forge [Gold Axe] to [Diamond Axe] with 3x[Diamond] requiring 1 experience.",
                        "{minecraft:diamond_pickaxe, minecraft:golden_pickaxe, {minecraft:diamond, 3}, 1} #Forge [Gold Pickaxe] to [Diamond Pickaxe] with 3x[Diamond] requiring 1 experience.",
                        "{minecraft:diamond_hoe, minecraft:golden_hoe, {minecraft:diamond, 2}, 1} #Forge [Gold Hoe] to [Diamond Hoe] with 2x[Diamond] requiring 1 experience.",
                        "{minecraft:diamond_shovel, minecraft:golden_shovel, minecraft:diamond, 1} #Forge [Gold Shovel] to [Diamond Shovel] with [Diamond] requiring 1 experience."
                },
                "1.Please customize forgings in form of {domain:output, domain:input_object, domain:input_material, required_experience}.\n2.Required experience must be greater than or equal to 1, otherwise it won't be forged.\n3.For example, {minecraft:stone_sword, minecraft:wooden_sword, {minecraft:cobblestone, 2}, 1} #Forge [Wooden Sword] to [Stone Sword] with 2x[Cobble Stone] requiring 1 experience."
        ).getStringList();

        config.save();
    }
}
