package net.codevmc.item;

import net.codevmc.lore.Template;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class RPGItemTemplate extends Template {

    public RPGItemTemplate(ItemStack stack) {
        super(stack);
    }

    @Override
    public String getTemplateName() {
        return "RPGItem";
    }

    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public Collection<String> getContent() {
        return null;
    }

}
