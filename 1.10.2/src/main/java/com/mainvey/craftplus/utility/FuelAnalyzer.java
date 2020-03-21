package com.mainvey.craftplus.utility;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class FuelAnalyzer extends RegrexAnalyzer {
    @Override
    public ArrayList<Object> analyze(String regrex) {
        String[] strings = this.trim(regrex).split(",");
        ArrayList<Object> list = new ArrayList<Object>();
        int tail = strings.length - 1;
        if(tail > 1) {
            if(tail > 2) {
                list.add(new ItemStack(this.getItem(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2])));
            } else {
                list.add(new ItemStack(this.getItem(strings[0]), Integer.parseInt(strings[1])));
            }
        } else {
            list.add(new ItemStack(this.getItem(strings[0])));
        }
        list.add(Integer.parseInt(strings[tail]));
        return list;
    }
}
