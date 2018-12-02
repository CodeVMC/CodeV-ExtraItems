package net.codevmc.lore;

import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public abstract class Template {

    private final ItemStack stack;

    public Template(ItemStack stack) {
        this.stack = stack;
    }

    public abstract String getTemplateName();
    public abstract boolean hasContent();
    public abstract Collection<String> getContent();

}
