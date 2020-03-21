package com.mainvey.craftplus.utility;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;



public class RecipeAnalyzer extends RegrexAnalyzer{
    @Override
    public ArrayList<Object> analyze(String regrex) {
        String[] strings = this.trim(regrex).split(",");
        ArrayList<Object> list = new ArrayList<>();
        boolean isShape = false, isFirst = true;
        for(int index = 0, length = strings.length; index < length; index++) {
            if(this.isObject(strings[index])) {
                int space = length - index - 1; //avoid causing out of index exception
                if(space >= 2) {
                    if(this.isInteger(strings[index + 1])) {
                        if(this.isInteger(strings[index + 2])) {
                            if(isShape || isFirst)
                                list.add(new ItemStack(this.getItem(strings[index]), Integer.parseInt(strings[index + 1]), Integer.parseInt(strings[index + 2])));
                            else
                                list.add(Ingredient.fromStacks(new ItemStack(this.getItem(strings[index]), Integer.parseInt(strings[index + 1]), Integer.parseInt(strings[index + 2]))));
                            index += 2;
                        } else {
                            if(isShape || isFirst)
                                list.add(new ItemStack(this.getItem(strings[index]), Integer.parseInt(strings[index + 1])));
                            else
                                list.add(Ingredient.fromStacks(new ItemStack(this.getItem(strings[index]), Integer.parseInt(strings[index + 1]))));
                            index += 1;
                        }
                        if(isFirst) isFirst = false;
                        continue;
                    }
                } else if(space == 1) {
                    if(this.isInteger(strings[index + 1])) {
                        if(isShape || isFirst)
                            list.add(new ItemStack(this.getItem(strings[index]), Integer.parseInt(strings[index + 1])));
                        else
                            list.add(Ingredient.fromStacks(new ItemStack(this.getItem(strings[index]), Integer.parseInt(strings[index + 1]))));
                        if(isFirst) isFirst = false;
                        index += 1;
                        continue;
                    }
                }
                if(isShape || isFirst)
                    list.add(new ItemStack(this.getItem(strings[index])));
                else
                    list.add(Ingredient.fromStacks(new ItemStack(this.getItem(strings[index]))));
                if(isFirst) isFirst = false;
            } else if(this.isString(strings[index])) {
                if(!isShape) isShape = true;
                list.add(strings[index]);
            } else if(this.isCharacter(strings[index])) {
                list.add(strings[index].charAt(0));
            }
        }
        if(isShape) this.replaceEmpty(list);
        return list;
    }
}
