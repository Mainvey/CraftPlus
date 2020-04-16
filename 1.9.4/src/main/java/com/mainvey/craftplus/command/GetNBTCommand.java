package com.mainvey.craftplus.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class GetNBTCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "getnbt";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "getnbt";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if(sender instanceof EntityPlayer) {
            ItemStack stack = ((EntityPlayer)sender).getHeldItemMainhand();
            NBTTagCompound nbt = stack.getTagCompound();
            if(stack.getItem() == Item.getItemFromBlock(Blocks.AIR)) {
                ((EntityPlayer)sender).addChatComponentMessage(new TextComponentString(TextFormatting.RED + /*请手持物品执行此命令*/"\u8bf7\u624b\u6301\u7269\u54c1\u6267\u884c\u6b64\u547d\u4ee4"));
            } else if(nbt == null) {
                ((EntityPlayer)sender).addChatComponentMessage(new TextComponentString(TextFormatting.BOLD + stack.getUnlocalizedName() + ":{}"));
            } else {
                ((EntityPlayer)sender).addChatComponentMessage(new TextComponentString(TextFormatting.BOLD + stack.getUnlocalizedName() + ":" + nbt.toString()));
            }
        }
    }
}
