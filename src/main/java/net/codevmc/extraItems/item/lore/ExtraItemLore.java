package net.codevmc.extraItems.item.lore;

import net.codevmc.extraItems.item.ExtraItem;
import net.codevmc.util.Item.lore.Lore;

public abstract class ExtraItemLore extends Lore {

    private final ExtraItem item;

    public ExtraItemLore(ExtraItem item) {
        this.item = item;
    }

    public ExtraItem getItem(){
        return item;
    }

}
