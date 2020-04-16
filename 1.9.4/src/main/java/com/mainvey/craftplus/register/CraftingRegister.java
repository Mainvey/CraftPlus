package com.mainvey.craftplus.register;

import com.mainvey.craftplus.event.AnvilEventHandler;
import com.mainvey.craftplus.parser.RegrexParser;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

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

        for(String regrex : config.Brewings) {
            if(parser.isEmpty(regrex)) continue;
            ArrayList<Object> list = parser.parse(regrex);
            BrewingRecipeRegistry.addRecipe((ItemStack)list.get(1), (ItemStack)list.get(2), (ItemStack)list.get(0));
        }

        for(String regrex : config.Forgings) {
            if(parser.isEmpty(regrex)) continue;
            final ArrayList<Object> list = parser.parse(regrex);
            MinecraftForge.EVENT_BUS.register(
                    new AnvilEventHandler() {
                        @Override
                        public void onAnvilUpdate(AnvilUpdateEvent event) {
                            if(event.getLeft().getItem() == ((ItemStack)list.get(1)).getItem()
                                    && event.getRight().getItem() == ((ItemStack)list.get(2)).getItem()
                                    && event.getRight().stackSize >= ((ItemStack)list.get(2)).stackSize) {
                                event.setOutput(((ItemStack)list.get(0)).copy());
                                event.setCost((Integer)list.get(3));
                                event.setMaterialCost(((ItemStack)list.get(2)).stackSize);
                            }
                        }
                    }
            );
        }
    }
}
