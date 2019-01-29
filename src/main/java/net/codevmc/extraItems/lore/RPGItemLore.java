package net.codevmc.extraItems.lore;

import net.codevmc.extraItems.item.RPGItem;
import net.codevmc.util.lore.Lore;

import java.util.List;
import java.util.Map;

public class RPGItemLore implements Lore {

    private RPGItem item;

    public RPGItemLore(RPGItem item) {
        this.item = item;
    }

    @Override
    public List<String> get(Map<String, ?> map) {
        return null;
    }

}
