package com.mainvey.craftplus.utility;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class RecipeAnalyzer extends RegrexAnalyzer{
    @Override
    public ArrayList<Object> analyze(String regrex) {
        String[] strings = this.trim(regrex).split(",");
        ArrayList<Object> list = new ArrayList<Object>();
        for(int index = 0, length = strings.length; index < length; index++) {
            if(this.isObject(strings[index])) {
                int space = length - index - 1; //avoid causing out of index exception
                if(space >= 2) {
                    if(this.isInteger(strings[index + 1])) {
                        if(this.isInteger(strings[index + 2])) {
                            list.add(new ItemStack(this.getItem(strings[index]), Integer.parseInt(strings[index + 1]), Integer.parseInt(strings[index + 2])));
                            index += 2;
                        } else {
                            list.add(new ItemStack(this.getItem(strings[index]), Integer.parseInt(strings[index + 1])));
                            index += 1;
                        }
                        continue;
                    }
                } else if(space == 1) {
                    if(this.isInteger(strings[index + 1])) {
                        list.add(new ItemStack(this.getItem(strings[index]), Integer.parseInt(strings[index + 1])));
                        index += 1;
                        continue;
                    }
                }
                list.add(new ItemStack(this.getItem(strings[index])));
            } else if(this.isString(strings[index])) {
                list.add(strings[index]);
            } else if(this.isCharacter(strings[index])) {
                list.add(strings[index].charAt(0));
            }
        }
        return list;
    }
}
