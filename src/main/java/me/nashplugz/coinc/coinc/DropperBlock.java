package me.nashplugz.coinc;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class DropperBlock {

    public static ItemStack createDropperBlock() {
        ItemStack dropperBlock = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = dropperBlock.getItemMeta();
        meta.setDisplayName("§6CoinCraze Dropper");
        meta.setLore(Arrays.asList("§ePlace this block to start earning coins!", "§7Drops sunflowers at regular intervals"));
        dropperBlock.setItemMeta(meta);
        return dropperBlock;
    }
}
