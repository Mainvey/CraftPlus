package com.mainvey.craftplus.utility;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class ForgingAnalyzer extends RegrexAnalyzer{
    @Override
    public ArrayList<Object> analyze(String regrex) {
        String[] strings = this.trim(regrex).split(",");
        ArrayList<Object> list = new ArrayList<Object>();
        int tail = strings.length - 1;
        for(int index = 0; index < tail; index++) {
            int space = tail - index - 1; //avoid causing out of index exception
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
        }
        list.add(Integer.parseInt(strings[tail]));
        return list;
    }
}
