package net.codevmc.extraItems.item.lore;

import net.codevmc.extraItems.item.ExtraItemManager;
import net.codevmc.util.Item.lore.LoreManager;
import org.bukkit.inventory.ItemStack;

public class ExtraItemLoreManager {

    private static final LoreManager loreManager = LoreManager.getInstance();

    public static void bind(ItemStack stack){
        loreManager.bindLore(stack,new ExtraItemLore(ExtraItemManager.getExtraItem(stack).get()));
    }

    public static void unbind(ItemStack stack){
        loreManager.unbind(stack);
    }

}
