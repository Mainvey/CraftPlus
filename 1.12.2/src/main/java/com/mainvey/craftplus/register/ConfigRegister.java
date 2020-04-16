package com.mainvey.craftplus.register;

import com.mainvey.craftplus.CraftPlus;
import net.minecraftforge.common.config.Config;

@Config(modid = CraftPlus.ID, name = CraftPlus.ID)
public class ConfigRegister {
    /*
    1.按F3+H组合键来查看物品的详细信息
    2.只能使用除空格以外的无对应的字符来表示无物品，用#表示后续文字均为注释，在解析时会被全部过滤掉
    3.NBT格式：(1).标签名:标签值 (2).标签名:{子标签名1:子标签值1(, 子标签名2:子标签值2, ...)} (3).数组名:[数组值1(, 数组值2, ...)]
    4.例如：minecraft:diamond_sword@{display:{Name:全村的希望, Lore:[你知道吗, 这是个例子]}, Unbreakable:1} #一把名为“全村的希望”的钻石剑，附有第一行为“你知道吗”和第二行为“这是个例子”的信息，并且无法损坏。
    */
    @Config.LangKey("config.notice")
    @Config.Comment("1.\u6309F3+H\u7ec4\u5408\u952e\u6765\u67e5\u770b\u7269\u54c1\u7684\u8be6\u7ec6\u4fe1\u606f\n2.\u53ea\u80fd\u4f7f\u7528\u9664\u7a7a\u683c\u4ee5\u5916\u7684\u65e0\u5bf9\u5e94\u7684\u5b57\u7b26\u6765\u8868\u793a\u65e0\u7269\u54c1\uff0c\u7528#\u8868\u793a\u540e\u7eed\u6587\u5b57\u5747\u4e3a\u6ce8\u91ca\uff0c\u5728\u89e3\u6790\u65f6\u4f1a\u88ab\u5168\u90e8\u8fc7\u6ee4\u6389\n3.NBT\u683c\u5f0f\uff1a(1).\u6807\u7b7e\u540d:\u6807\u7b7e\u503c (2).\u6807\u7b7e\u540d:{\u5b50\u6807\u7b7e\u540d1:\u5b50\u6807\u7b7e\u503c1(, \u5b50\u6807\u7b7e\u540d2:\u5b50\u6807\u7b7e\u503c2, ...)} (3).\u6570\u7ec4\u540d:[\u6570\u7ec4\u503c1(, \u6570\u7ec4\u503c2, ...)]\n4.\u4f8b\u5982\uff1aminecraft:diamond_sword@{display:{Name:\u5168\u6751\u7684\u5e0c\u671b, Lore:[\u4f60\u77e5\u9053\u5417, \u8fd9\u662f\u4e2a\u4f8b\u5b50]}, Unbreakable:1} #\u4e00\u628a\u540d\u4e3a\u201c\u5168\u6751\u7684\u5e0c\u671b\u201d\u7684\u94bb\u77f3\u5251\uff0c\u9644\u6709\u7b2c\u4e00\u884c\u4e3a\u201c\u4f60\u77e5\u9053\u5417\u201d\u548c\u7b2c\u4e8c\u884c\u4e3a\u201c\u8fd9\u662f\u4e2a\u4f8b\u5b50\u201d\u7684\u4fe1\u606f\uff0c\u5e76\u4e14\u65e0\u6cd5\u635f\u574f\u3002\n5.\u6ce8\u610f\uff1a\u5b50\u6807\u7b7e\u4e00\u5b9a\u8981\u7528\u5927\u62ec\u53f7\u62ec\u8d77\u6765\uff0c\u6570\u7ec4\u503c\u4e00\u5b9a\u8981\u7528\u4e2d\u62ec\u53f7\u62ec\u8d77\u6765\u5e76\u4e14\u53ea\u80fd\u662f\u540c\u4e00\u6570\u636e\u7c7b\u578b\u3002")
    @Config.Name("NOTICE")
    public static boolean NOTICE = true;


    @Config.LangKey("config.getnbtcommand")
    @Config.Comment("\u662f\u5426\u542f\u7528\u83b7\u53d6NBT\u547d\u4ee4")
    @Config.Name("GetNBTCommand")
    public static boolean GetNBTCommand = true;

    /*
    1.规则配方的格式：模组ID:合成的物品ID, 第一行标识符(, 第二行标识符, 第三行标识符), 标识符1=模组ID:放入的物品ID1(, 标识符2=模组ID:放入的物品ID2, ...)
    或者无规则配方的格式：模组ID:产出的物品ID, 模组ID:放入的物品ID1, 模组ID:放入的物品ID2(, 模组ID:放入的物品ID3, ...)
    2.例如：minecraft:end_portal_frame, *$*, &&&, *=minecraft:ender_pearl, &=minecraft:end_stone #用2个[末影珍珠]和3个[末地石]以* *,&&&的格式合成[末地传送门框架]。
    或者minecraft:coal_ore, minecraft:coal, minecraft:stone #用[煤]和[石头]以无规则的格式合成[煤矿]。
    3.注意：当物品需要数量、元数据(Metadata)或NBT时，格式为模组ID:物品ID@{数量, 元数据, NBT}，只需要数量或NBT时可以省略大括号，只需要元数据时可以省略数量但不能缺省逗号，元数据可以填写为32767来代表所有相同种类却不同元数据的物品。
    */
    @Config.RequiresMcRestart
    @Config.LangKey("config.composings")
    @Config.Comment("1.\u89c4\u5219\u914d\u65b9\u7684\u683c\u5f0f\uff1a\u6a21\u7ec4ID:\u5408\u6210\u7684\u7269\u54c1ID, \u7b2c\u4e00\u884c\u6807\u8bc6\u7b26(, \u7b2c\u4e8c\u884c\u6807\u8bc6\u7b26, \u7b2c\u4e09\u884c\u6807\u8bc6\u7b26), \u6807\u8bc6\u7b261=\u6a21\u7ec4ID:\u653e\u5165\u7684\u7269\u54c1ID1(, \u6807\u8bc6\u7b262=\u6a21\u7ec4ID:\u653e\u5165\u7684\u7269\u54c1ID2, ...)\n\u6216\u8005\u65e0\u89c4\u5219\u914d\u65b9\u7684\u683c\u5f0f\uff1a\u6a21\u7ec4ID:\u4ea7\u51fa\u7684\u7269\u54c1ID, \u6a21\u7ec4ID:\u653e\u5165\u7684\u7269\u54c1ID1, \u6a21\u7ec4ID:\u653e\u5165\u7684\u7269\u54c1ID2(, \u6a21\u7ec4ID:\u653e\u5165\u7684\u7269\u54c1ID3, ...)\n2.\u4f8b\u5982\uff1aminecraft:end_portal_frame, *$*, &&&, *=minecraft:ender_pearl, &=minecraft:end_stone #\u75282\u4e2a[\u672b\u5f71\u73cd\u73e0]\u548c3\u4e2a[\u672b\u5730\u77f3]\u4ee5* *,&&&\u7684\u683c\u5f0f\u5408\u6210[\u672b\u5730\u4f20\u9001\u95e8\u6846\u67b6]\u3002\n\u6216\u8005minecraft:coal_ore, minecraft:coal, minecraft:stone #\u7528[\u7164]\u548c[\u77f3\u5934]\u4ee5\u65e0\u89c4\u5219\u7684\u683c\u5f0f\u5408\u6210[\u7164\u77ff]\u3002\n3.\u6ce8\u610f\uff1a\u5f53\u7269\u54c1\u9700\u8981\u6570\u91cf\u3001\u5143\u6570\u636e(Metadata)\u6216NBT\u65f6\uff0c\u683c\u5f0f\u4e3a\u6a21\u7ec4ID:\u7269\u54c1ID@{\u6570\u91cf, \u5143\u6570\u636e, NBT}\uff0c\u53ea\u9700\u8981\u6570\u91cf\u6216NBT\u65f6\u53ef\u4ee5\u7701\u7565\u5927\u62ec\u53f7\uff0c\u53ea\u9700\u8981\u5143\u6570\u636e\u65f6\u53ef\u4ee5\u7701\u7565\u6570\u91cf\u4f46\u4e0d\u80fd\u7f3a\u7701\u9017\u53f7\uff0c\u5143\u6570\u636e\u53ef\u4ee5\u586b\u5199\u4e3a32767\u6765\u4ee3\u8868\u6240\u6709\u76f8\u540c\u79cd\u7c7b\u5374\u4e0d\u540c\u5143\u6570\u636e\u7684\u7269\u54c1\u3002")
    @Config.Name("Composings")
    public static String[] Composings = new String[] {"#\u5de5\u4f5c\u53f0\u914d\u65b9"};

    /*
    1.格式：模组ID:作为燃料的物品ID, 燃烧时间
    2.例如：minecraft:book, 200 #[书]可供燃烧10秒。
    3.注意：燃烧时间的单位为游戏时(Gametick)，1秒等于20游戏时
    */
    @Config.RequiresMcRestart
    @Config.LangKey("config.fuels")
    @Config.Comment("1.\u683c\u5f0f\uff1a\u6a21\u7ec4ID:\u4f5c\u4e3a\u71c3\u6599\u7684\u7269\u54c1ID, \u71c3\u70e7\u65f6\u95f4\n2.\u4f8b\u5982\uff1aminecraft:book, 200 #[\u4e66]\u53ef\u4f9b\u71c3\u70e710\u79d2\u3002\n3.\u6ce8\u610f\uff1a\u71c3\u70e7\u65f6\u95f4\u7684\u5355\u4f4d\u4e3a\u6e38\u620f\u65f6(Gametick)\uff0c1\u79d2\u7b49\u4e8e20\u6e38\u620f\u65f6")
    @Config.Name("Fuels")
    public static String[] Fuels = new String[] {"#\u7194\u7089\u71c3\u6599"};

    /*
    1.格式：模组ID:产出的物品ID, 模组ID:放入的物品ID, 获得的经验
    2.例如：minecraft:deadbush, minecraft:sapling, 0.5 #把[橡树树苗]煅烧成[枯死的灌木]可获得0.5经验。
    */
    @Config.RequiresMcRestart
    @Config.LangKey("config.smeltings")
    @Config.Comment("1.\u683c\u5f0f\uff1a\u6a21\u7ec4ID:\u4ea7\u51fa\u7684\u7269\u54c1ID, \u6a21\u7ec4ID:\u653e\u5165\u7684\u7269\u54c1ID, \u83b7\u5f97\u7684\u7ecf\u9a8c\n2.\u4f8b\u5982\uff1aminecraft:deadbush, minecraft:sapling, 0.5 #\u628a[\u6a61\u6811\u6811\u82d7]\u7145\u70e7\u6210[\u67af\u6b7b\u7684\u704c\u6728]\u53ef\u83b7\u5f970.5\u7ecf\u9a8c\u3002")
    @Config.Name("Smeltings")
    public static String[] Smeltings = new String[] {"#\u7194\u7089\u914d\u65b9"};

    /*
    1.格式：模组ID:产出的物品ID, 模组ID:放入的药水ID,模组ID:作为材料的物品ID
    2.例如：minecraft:experience_bottle, minecraft:potion@Potion:minecraft:awkward, minecraft:gold_block,  #以[金块]作为材料，把[粗制的药水]酿造成[经验瓶]。
    */
    @Config.RequiresMcRestart
    @Config.LangKey("config.brewings")
    @Config.Comment("1.\u683c\u5f0f\uff1a\u6a21\u7ec4ID:\u4ea7\u51fa\u7684\u7269\u54c1ID, \u6a21\u7ec4ID:\u653e\u5165\u7684\u836f\u6c34ID,\u6a21\u7ec4ID:\u4f5c\u4e3a\u6750\u6599\u7684\u7269\u54c1ID\n2.\u4f8b\u5982\uff1aminecraft:experience_bottle, minecraft:potion@potion:minecraft:awkward, minecraft:gold_block,  #\u4ee5[\u91d1\u5757]\u4f5c\u4e3a\u6750\u6599\uff0c\u628a[\u7c97\u5236\u7684\u836f\u6c34]\u917f\u9020\u6210[\u7ecf\u9a8c\u74f6]\u3002")
    @Config.Name("Brewings")
    public static String[] Brewings = new String[] {"#\u917f\u9020\u53f0\u914d\u65b9"};

    /*
    1.格式：模组ID:产出的物品ID, 模组ID:放入的物品ID, 模组ID:作为材料的物品ID, 需要的经验
    2.例如：minecraft:stone_sword, minecraft:wooden_sword, minecraft:cobblestone@2, 1 #以2个[圆石]作为材料，把[木剑]锻造成[石剑]需要1经验。
    3.注意：需要的经验必须大于或等于1，否则将无法锻造
    */
    @Config.RequiresMcRestart
    @Config.LangKey("config.forgings")
    @Config.Comment("1.\u683c\u5f0f\uff1a\u6a21\u7ec4ID:\u4ea7\u51fa\u7684\u7269\u54c1ID, \u6a21\u7ec4ID:\u653e\u5165\u7684\u7269\u54c1ID, \u6a21\u7ec4ID:\u4f5c\u4e3a\u6750\u6599\u7684\u7269\u54c1ID, \u9700\u8981\u7684\u7ecf\u9a8c\n2.\u4f8b\u5982\uff1aminecraft:stone_sword, minecraft:wooden_sword, minecraft:cobblestone@2, 1 #\u4ee52\u4e2a[\u5706\u77f3]\u4f5c\u4e3a\u6750\u6599\uff0c\u628a[\u6728\u5251]\u953b\u9020\u6210[\u77f3\u5251]\u9700\u89811\u7ecf\u9a8c\u3002\n3.\u6ce8\u610f\uff1a\u9700\u8981\u7684\u7ecf\u9a8c\u5fc5\u987b\u5927\u4e8e\u6216\u7b49\u4e8e1\uff0c\u5426\u5219\u5c06\u65e0\u6cd5\u953b\u9020")
    @Config.Name("Forgings")
    public static String[] Forgings = new String[] {"#\u94c1\u7827\u914d\u65b9"};
}