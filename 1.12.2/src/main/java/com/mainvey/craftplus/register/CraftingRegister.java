package com.mainvey.craftplus.register;

import com.mainvey.craftplus.event.AnvilEventHandler;
import com.mainvey.craftplus.event.FurnaceEventHandler;
import com.mainvey.craftplus.utility.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class CraftingRegister {

    public CraftingRegister(){
        RegrexAnalyzer analyzer;

        analyzer = new RecipeAnalyzer();
        for(String regrex : ConfigRegister.Recipes) {
            if(analyzer.isEmpty(regrex)) continue;
            ArrayList<Object> list = analyzer.analyze(regrex);
            ItemStack itemStack = (ItemStack)analyzer.listPop(list);
            if(list.get(0) instanceof String) {
                GameRegistry.addShapedRecipe(new ResourceLocation("craftplus:" + itemStack.getUnlocalizedName()), null, itemStack, list.toArray());
            } else {
                Ingredient[] ingredients = new Ingredient[list.size()];
                for(int i = 0; i < ingredients.length; i++)
                    ingredients[i] = (Ingredient)list.get(i);
                GameRegistry.addShapelessRecipe(new ResourceLocation("craftplus:" + itemStack.getUnlocalizedName()), null, itemStack, ingredients);
            }
        }

        analyzer = new FuelAnalyzer();
        for(String regrex : ConfigRegister.Fuels) {
            if (analyzer.isEmpty(regrex)) continue;
            final ArrayList<Object> list = analyzer.analyze(regrex);
            MinecraftForge.EVENT_BUS.register(new FurnaceEventHandler() {
                @Override
                public void getFuelValue(FurnaceFuelBurnTimeEvent event) {
                    if (event.getItemStack().getItem() == ((ItemStack)list.get(0)).getItem()) {
                        event.setBurnTime((Integer)list.get(1));
                    }
                }
            });
        }

        analyzer = new SmeltingAnalyzer();
        for(String regrex : ConfigRegister.Smeltings) {
            if(analyzer.isEmpty(regrex)) continue;
            ArrayList<Object> list = analyzer.analyze(regrex);
            GameRegistry.addSmelting((ItemStack)list.get(0), (ItemStack)list.get(1), (Float)list.get(2));
        }

        analyzer = new BrewingAnalyzer();
        for(String regrex : ConfigRegister.Brewings) {
            if(analyzer.isEmpty(regrex)) continue;
            ArrayList<Object> list = analyzer.analyze(regrex);
            BrewingRecipeRegistry.addRecipe((ItemStack)list.get(0), (ItemStack)list.get(1), (ItemStack)list.get(2));
        }

        analyzer = new ForgingAnalyzer();
        for(String regrex : ConfigRegister.Forgings) {
            if(analyzer.isEmpty(regrex)) continue;
            final ArrayList<Object> list = analyzer.analyze(regrex);
            MinecraftForge.EVENT_BUS.register(new AnvilEventHandler() {
                @Override
                public void onAnvilUpdate(AnvilUpdateEvent event) {
                    if(event.getRight().getItem() == ((ItemStack)list.get(2)).getItem()) {
                        if(event.getLeft().getItem() == ((ItemStack)list.get(1)).getItem()) {
                            if(event.getRight().getCount() >= ((ItemStack)list.get(2)).getCount()) {
                                event.setOutput(((ItemStack)list.get(0)).copy());
                                event.setCost((Integer)list.get(3));
                                event.setMaterialCost(((ItemStack)list.get(2)).getCount());
                            }
                        }
                    }
                }
            });
        }
    }
}
