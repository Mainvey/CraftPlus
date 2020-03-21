package com.mainvey.craftplus.register;

import com.mainvey.craftplus.utility.FuelAnalyzer;
import com.mainvey.craftplus.utility.RecipeAnalyzer;
import com.mainvey.craftplus.utility.RegrexAnalyzer;
import com.mainvey.craftplus.utility.SmeltingAnalyzer;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

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

        analyzer = new SmeltingAnalyzer();
        for(String regrex : config.Smeltings) {
            if(analyzer.isEmpty(regrex)) continue;
            ArrayList<Object> list = analyzer.analyze(regrex);
            GameRegistry.addSmelting((ItemStack)list.get(0), (ItemStack)list.get(1), (Float)list.get(2));
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
    }
}
