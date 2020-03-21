package com.mainvey.craftplus.utility;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class RegrexAnalyzer {
    public abstract ArrayList<Object> analyze(String regrex);

    public boolean isObject(String value) {
        return value.contains(":");
    }

    public boolean isInteger(String value) {
        return value.charAt(0) <= '9' && value.charAt(0) >= '0';
    }

    public boolean isCharacter(String value) {
        return value.length() == 1;
    }

    public boolean isString(String value) {
        return value.length() > 1;
    }

    public boolean isEmpty(String value) {
        for(char c : value.toCharArray()) {
            if(c == '#')
                break;
            if(c != ' ')
                return false;
        }
        return true;
    }

    public Object listPop(ArrayList<Object> list) {
        Object object = list.get(0);
        list.remove(0);
        return object;
    }

    protected String trim(String value) {
        char[] values = value.toCharArray();
        //filter curly braces, whitespace and comment
        int count = 0;
        for(int i = 0; i < values.length; i++) {
            if(values[i] == '#') break;
            if(values[i] != ' ' && values[i] != '{' && values[i] != '}') {
                count++;
            } else {
                values[i] = '×';
            }
        }

        char[] chars = new char[count];
        for(int i = 0, j = 0; j < count; i++) {
            if(values[i] != '×')
                chars[j++] = values[i];
        }
        return new String(chars);
    }

    public void replaceEmpty(ArrayList<Object> list) {
        ArrayList<String> arecipes = new ArrayList<>();
        ArrayList<Character> achars = new ArrayList<>();

        for(Object o : list) {
            if(o instanceof String)
                arecipes.add((String)o);
            else if(o instanceof Character)
                achars.add((Character)o);
        }

        String schars = Arrays.toString(achars.toArray());
        String[] srecipes = new String[arecipes.size()];
        for(int i = 0; i < srecipes.length; i++)
            srecipes[i] = arecipes.get(i);

        for(int i = 0; i < srecipes.length; i++) {
            char[] crecipe = srecipes[i].toCharArray();
            boolean isChange = false;
            for(int j = 0; j < crecipe.length; j++) {
                if(schars.indexOf(crecipe[j]) < 0) {
                    if(!isChange) isChange = true;
                    crecipe[j] = ' ';
                }
            }
            if(isChange) list.set(i + 1 ,new String(crecipe));
        }
    }

    protected Item getItem(String identity) {
        return Item.REGISTRY.getObject(new ResourceLocation(identity));
    }
}
