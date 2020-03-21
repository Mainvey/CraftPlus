package com.mainvey.craftplus.register;

import com.mainvey.craftplus.event.AnvilEventHandler;
import com.mainvey.craftplus.utility.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class CraftingRegister {

    public CraftingRegister(ConfigRegister config){
        RegrexAnalyzer analyzer;

        analyzer = new RecipeAnalyzer();
        for(String regrex : config.Recipes) {
            if(analyzer.isEmpty(regrex)) continue;
            ArrayList<Object> list = analyzer.analyze(regrex);
            ItemStack itemStack = (ItemStack)analyzer.listPop(list);
            if(list.get(0) instanceof String) {
                GameRegistry.addShapedRecipe(itemStack, list.toArray());
            } else {
                GameRegistry.addShapelessRecipe(itemStack, list.toArray());
            }

        }

        analyzer = new FuelAnalyzer();
        for(String regrex : config.Fuels) {
            if (analyzer.isEmpty(regrex)) continue;
            final ArrayList<Object> list = analyzer.analyze(regrex);
            GameRegistry.registerFuelHandler(new IFuelHandler() {
                @Override
                public int getBurnTime(ItemStack fuel) {
                    return ((ItemStack)list.get(0)).getItem() != fuel.getItem() ? 0 : (Integer)list.get(1);
                }
            });
        }

        analyzer = new SmeltingAnalyzer();
        for(String regrex : config.Smeltings) {
            if(analyzer.isEmpty(regrex)) continue;
            ArrayList<Object> list = analyzer.analyze(regrex);
            GameRegistry.addSmelting((ItemStack)list.get(0), (ItemStack)list.get(1), (Float)list.get(2));
        }

        analyzer = new BrewingAnalyzer();
        for(String regrex : config.Brewings) {
            if(analyzer.isEmpty(regrex)) continue;
            ArrayList<Object> list = analyzer.analyze(regrex);
            BrewingRecipeRegistry.addRecipe((ItemStack)list.get(0), (ItemStack)list.get(1), (ItemStack)list.get(2));
        }

        analyzer = new ForgingAnalyzer();
        for(String regrex : config.Forgings) {
            if(analyzer.isEmpty(regrex)) continue;
            final ArrayList<Object> list = analyzer.analyze(regrex);
            MinecraftForge.EVENT_BUS.register(new AnvilEventHandler() {
                @Override
                public void onAnvilUpdate(AnvilUpdateEvent event) {
                    if(event.getLeft().getItem() == ((ItemStack)list.get(1)).getItem()) {
                        if(event.getRight().getItem() == ((ItemStack)list.get(2)).getItem()) {
                            if(event.getRight().stackSize >= ((ItemStack)list.get(2)).stackSize) {
                                event.setOutput(((ItemStack)list.get(0)).copy());
                                event.setCost((Integer)list.get(3));
                                event.setMaterialCost(((ItemStack)list.get(2)).stackSize);
                            }
                        }
                    }
                }
            });
        }
    }
}
