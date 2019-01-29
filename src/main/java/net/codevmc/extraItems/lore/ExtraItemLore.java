package net.codevmc.extraItems.lore;

import net.codevmc.extraItems.item.ExtraItem;
import net.codevmc.util.lore.Lore;

import java.util.List;
import java.util.Map;

public class ExtraItemLore implements Lore {

    private ExtraItem item;

    public ExtraItemLore(ExtraItem item) {
        this.item = item;
    }

    @Override
    public List<String> get(Map<String, ?> map) {
        return null;
    }

}
