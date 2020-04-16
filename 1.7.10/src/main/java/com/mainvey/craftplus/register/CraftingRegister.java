package com.mainvey.craftplus.register;

import com.mainvey.craftplus.parser.RegrexParser;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class CraftingRegister {
    public CraftingRegister(ConfigRegister config){
        RegrexParser parser = new RegrexParser();

        for(String regrex : config.Composings) {
            if(parser.isEmpty(regrex)) continue;
            ArrayList<Object> list = parser.parse(regrex);
            ItemStack output = parser.popOutput(list);
            if(list.get(1) instanceof String) {
                GameRegistry.addShapedRecipe(output, list.toArray());
            } else {
                GameRegistry.addShapelessRecipe(output, list.toArray());
            }
        }

        for(String regrex : config.Fuels) {
            if (parser.isEmpty(regrex)) continue;
            final ArrayList<Object> list = parser.parse(regrex);
            GameRegistry.registerFuelHandler(
                    new IFuelHandler() {
                        @Override
                        public int getBurnTime(ItemStack fuel) {
                            return ((ItemStack)list.get(0)).getItem() != fuel.getItem() ? 0 : (Integer)list.get(1);
                        }
                    }
            );
        }

        for(String regrex : config.Smeltings) {
            if(parser.isEmpty(regrex)) continue;
            ArrayList<Object> list = parser.parse(regrex);
            GameRegistry.addSmelting((ItemStack)list.get(1), (ItemStack)list.get(0), (Float)list.get(2));
        }

        /*for(String regrex : config.Brewings) {
            //TODO
        }*/

        /*for(String regrex : config.Forgings) {
            //TODO
        }*/
    }
}
