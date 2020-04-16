package com.mainvey.craftplus.parser;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;

public class RegrexParser {
    /**
     * 解析规则表达式
     * @param regrex 规则表达式
     * @return 配方
     */
    public ArrayList<Object> parse(String regrex) {
        ArrayList<Object> list = new ArrayList<>();

        String[] strings = this.split(this.trim(regrex));
        for(String string : strings) {
            if(this.isMap(string)) {
                String[] items = string.split("=");
                list.add(items[0].charAt(0));
                list.add(this.toItemStack(items[1]));
            } else if(this.isObject(string)) {
                list.add(this.toItemStack(string));
            } else if(this.isFloat(string)) {
                list.add(Float.parseFloat(string));
            } else if(this.isInteger(string)) {
                list.add(Integer.parseInt(string));
            } else {
                list.add(string);
            }
        }

        return list;
    }

    /**
     * 修剪字符串中的空格和注释
     * @param string 字符串
     * @return 剪去空格和注释的字符串
     */
    private String trim(String string) {
        char[] chars = string.toCharArray();
        //标记空格，略过注释及后续
        int count = 0;
        for(int index = 0; index < chars.length; index++) {
            if(chars[index] == '#') break;

            if(chars[index] != ' ') {
                count++;
            } else {
                chars[index] = '×';
            }
        }
        //过滤
        char[] result = new char[count];
        for(int i = 0, j = 0; j < count; i++) {
            if(chars[i] != '×') {
                result[j++] = chars[i];
            }
        }

        return new String(result);
    }

    /**
     * 拆分含有逗号字符的规则表达式，并略过中括号和大括号区域
     * @param regrex 规则表达式
     * @return 字符串数组
     */
    private String[] split(String regrex) {
        return this.split(regrex, ',', -1);
    }

    /**
     * 以特定次数拆分含有特定字符的规则表达式，并略过中括号和大括号区域
     * @param regrex 规则表达式
     * @param splitSign 拆分标识符
     * @param limit 拆分次数
     * @return 字符串数组
     */
    private String[] split(String regrex, char splitSign, int limit) {
        ArrayList<String> list = new ArrayList<>();
        for(int index = 0, head = 0, isSkip = 0, count = 0; index < regrex.length(); index++) {
            if(count == limit) {
                list.add(regrex.substring(head));
                break;
            }

            if(this.isExist("{[", regrex.charAt(index))) {
                isSkip++;
            } else if(isSkip > 0 && this.isExist("}]", regrex.charAt(index))) {
                isSkip--;
            }

            if(isSkip < 1 && regrex.charAt(index) == splitSign) {
                list.add(regrex.substring(head, index));
                head = index + 1;
                count++;
            }

            if(isSkip < 1 && index == regrex.length() - 1) {
                list.add(regrex.substring(head, index + 1));
                break;
            }
        }

        String[] result = new String[list.size()];
        for(int index = 0; index < list.size(); index++) {
            result[index] = list.get(index);
        }

        return result;
    }

    /**
     * 修剪字符串头尾的括号
     * @param string 字符串
     * @return 剪去头尾括号的字符串
     */
    private String unwrap(String string) {
        char rightSign = (char)(string.charAt(0) + 2);//{[转换为}]
        return string.substring(1, string.lastIndexOf(rightSign));
    }

    /**
     * 获取产物并在列表中删除
     * @param list 列表
     * @return 产物
     */
    public ItemStack popOutput(ArrayList<Object> list) {
        ItemStack stack = (ItemStack)list.get(0);
        list.remove(0);
        return stack;
    }

    /**
     * 把字符串转换为物品类型
     * @param identity 物品标识名
     * @return 物品
     */
    private Item toItem(String identity) {
        return Item.REGISTRY.getObject(new ResourceLocation(identity));
    }

    /**
     * 把字符串转换为物品栈类型
     * @param regrex 字符串
     * @return 物品栈
     */
    private ItemStack toItemStack(String regrex) {
        String[] subregrex = regrex.split("@");
        if(subregrex.length < 2) {//处理情况：minecraft:dirt
            return new ItemStack(this.toItem(regrex));
        } else if(this.isInteger(subregrex[1])) {//处理情况：minecraft:dirt@4
            return new ItemStack(this.toItem(subregrex[0]), Integer.parseInt(subregrex[1]));
        } else if(subregrex[1].charAt(0) != '{') {//处理情况：minecraft:dirt@tag:value
            ItemStack stack = new ItemStack(this.toItem(subregrex[0]));
            stack.setTagCompound(this.toNBT(new String[] {subregrex[1]}));
            return stack;
        }

        //处理情况：minecraft:dirt@{(,2,)tag:value,tag2:[value,...valueN],...,tagN:{value,...,subtag:{value,...,subtag:{...},...},...}}
        String[] items = this.split(this.unwrap(subregrex[1]));
        int[] data = {1, 0};
        NBTTagCompound nbt = null;
        for(int index = 0; index < items.length; index++) {
            if(index < 2 && this.isInteger(items[index])) {
                data[index] = Integer.parseInt(items[index]);
            } else if(this.isObject(items[index])) {
                nbt = this.toNBT(Arrays.copyOfRange(items, index, items.length));
                break;
            }
        }

        ItemStack stack = new ItemStack(this.toItem(subregrex[0]), data[0], data[1]);
        stack.setTagCompound(nbt);
        return stack;
    }

    /**
     * 把处理为NBT集的字符串数组转换为NBT集类型
     * @param tags 处理为NBT集的字符串数组
     * @return NBT集
     */
    private NBTTagCompound toNBT(String[] tags) {
        NBTTagCompound nbt = new NBTTagCompound();
        for(String tag : tags) {
            String[] items = this.split(tag, ':', 1);
            if(items[1].charAt(0) == '{') {
                String[] subtags = this.split(this.unwrap(items[1]));
                nbt.setTag(items[0], this.toNBT(subtags));
            } else if(items[1].charAt(0) == '[') {
                String[] subtags = this.split(this.unwrap(items[1]));
                nbt.setTag(items[0], this.toTagList(subtags));
            }else if(this.isFloat(items[1])) {
                nbt.setFloat(items[0], Float.parseFloat(items[1]));
            } else if(this.isInteger(items[1])) {
                nbt.setInteger(items[0], Integer.parseInt(items[1]));
            } else {
                nbt.setString(items[0], items[1]);
            }
        }
        return nbt;
    }

    /**
     * 把处理为标签的字符串数组转换为NBT标签列表类型
     * @param tags 处理为标签的字符串数组
     * @return NBT标签列表
     */
    private NBTTagList toTagList(String[] tags) {
        NBTTagList list = new NBTTagList();
        ArrayList<String> subtags = new ArrayList<>();
        for(String tag : tags) {
            if(this.isObject(tag)) {
                subtags.add(tag);
            } else if(this.isFloat(tag)) {
                list.appendTag(new NBTTagFloat(Float.parseFloat(tag)));
            } else if(this.isInteger(tag)) {
                list.appendTag(new NBTTagInt(Integer.parseInt(tag)));
            } else {
                list.appendTag(new NBTTagString(tag));
            }
        }

        if(!subtags.isEmpty()) {
            String[] items = new String[subtags.size()];
            for(int index = 0; index < items.length; index++) {
                items[index] = subtags.get(index);
            }

            NBTTagCompound nbt = this.toNBT(items);
            list.appendTag(nbt);
        }

        return list;
    }

    /**
     * 判断规则表达式是否为空（纯注释）
     * @param regrex 规则表达式
     * @return 是否为空
     */
    public boolean isEmpty(String regrex) {
        for(char character : regrex.toCharArray()) {
            if(character == '#') break;
            else if(character != ' ')
                return false;
        }
        return true;
    }

    /**
     * 判断字符串中是否存在某个字符
     * @param string 字符串
     * @param key 字符
     * @return 是否存在某个字符
     */
    private boolean isExist(String string, char key) {
        for(char character : string.toCharArray()) {
            if(character == key)
                return true;
        }
        return false;
    }

    /**
     * 判断是否为物品
     * @param string 字符串
     * @return 是否为物品
     */
    private boolean isObject(String string) {
        return string.contains(":");
    }

    /**
     * 判断是否为标识符与物品的映射项
     * @param string 字符串
     * @return 是否为映射项
     */
    private boolean isMap(String string) {
        return string.contains("=");
    }

    /**
     * 判断是否为浮点数
     * @param string 字符串
     * @return 是否为浮点数
     */
    private boolean isFloat(String string) {
        if(!string.contains("."))
            return false;

        String[] strings = string.split("\\.");
        return this.isIntegerArray(strings);
    }

    /**
     * 判断是否为整数
     * @param string 字符串
     * @return 是否为整数
     */
    private boolean isInteger(String string) {
        if(string.isEmpty())
            return false;

        for(char character : string.toCharArray()) {
            if(character < '0' || character > '9')
                return false;
        }
        return true;
    }

    /**
     * 判断字符串数组是否为整数数组
     * @param strings 字符串数组
     * @return 是否为整数数组
     */
    private boolean isIntegerArray(String[] strings) {
        for(String string : strings) {
            if(!this.isInteger(string))
                return false;
        }
        return true;
    }
}