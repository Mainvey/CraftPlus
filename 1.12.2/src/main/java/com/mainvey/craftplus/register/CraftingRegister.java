package com.mainvey.craftplus.register;

import com.mainvey.craftplus.CraftPlus;
import com.mainvey.craftplus.event.AnvilEventHandler;
import com.mainvey.craftplus.event.FurnaceEventHandler;

import com.mainvey.craftplus.parser.RegrexParser;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@EventBusSubscriber(modid = CraftPlus.ID)
public class CraftingRegister {
    public CraftingRegister() {
        RegrexParser parser = new RegrexParser();

        for(String regrex : ConfigRegister.Fuels) {
            if(parser.isEmpty(regrex)) continue;
            final ArrayList<Object> list = parser.parse(regrex);
            MinecraftForge.EVENT_BUS.register(
                    new FurnaceEventHandler() {
                        @Override
                        public void getFuelValue(FurnaceFuelBurnTimeEvent event) {
                            if(event.getItemStack().getItem() == ((ItemStack)list.get(0)).getItem()) {
                                event.setBurnTime((Integer)list.get(1));
                            }
                        }
                    });
        }

        for(String regrex : ConfigRegister.Smeltings) {
            if(parser.isEmpty(regrex)) continue;
            ArrayList<Object> list = parser.parse(regrex);
            FurnaceRecipes.instance().addSmeltingRecipe((ItemStack)list.get(1), (ItemStack)list.get(0), (float)list.get(2));
        }

        for(String regrex : ConfigRegister.Brewings) {
            if(parser.isEmpty(regrex)) continue;
            ArrayList<Object> list = parser.parse(regrex);
            BrewingRecipeRegistry.addRecipe((ItemStack)list.get(1), (ItemStack)list.get(2), (ItemStack)list.get(0));
        }

        for(String regrex : ConfigRegister.Forgings) {
            if(parser.isEmpty(regrex)) continue;
            final ArrayList<Object> list = parser.parse(regrex);
            MinecraftForge.EVENT_BUS.register(
                    new AnvilEventHandler() {
                        @Override
                        public void onAnvilUpdate(AnvilUpdateEvent event) {
                            if(event.getRight().getItem() == ((ItemStack)list.get(2)).getItem()
                                    && event.getLeft().getItem() == ((ItemStack)list.get(1)).getItem()
                                    && event.getRight().getCount() >= ((ItemStack)list.get(2)).getCount()) {
                                event.setOutput(((ItemStack)list.get(0)).copy());
                                event.setCost((Integer)list.get(3));
                                event.setMaterialCost(((ItemStack)list.get(2)).getCount());
                            }
                        }
                    });
        }
    }

    @SubscribeEvent
    public static void onRecipeRegistry(RegistryEvent.Register<IRecipe> event) {
        RegrexParser parser = new RegrexParser();
        for(String regrex : ConfigRegister.Composings) {
            if(parser.isEmpty(regrex)) continue;
            ArrayList<Object> list = parser.parse(regrex);
            ItemStack output = (ItemStack)list.get(0);
            if(list.get(1) instanceof NonNullList) {
                event.getRegistry().register(
                        new ShapedRecipes(
                                output.getUnlocalizedName(),
                                (int)list.get(2),
                                (int)list.get(3),
                                (NonNullList)list.get(1),
                                output
                        ).setRegistryName(output.getUnlocalizedName())
                );
            } else {
                NonNullList<Ingredient> ingredients = NonNullList.create();
                for (int index = 1; index < list.size(); index++) {
                    ingredients.add(Ingredient.fromStacks((ItemStack)list.get(index)));
                }
                event.getRegistry().register(
                        new ShapelessRecipes(
                                output.getUnlocalizedName(),
                                (ItemStack)list.get(0),
                                ingredients
                        ).setRegistryName(output.getUnlocalizedName())
                );
            }
        }
    }
}
