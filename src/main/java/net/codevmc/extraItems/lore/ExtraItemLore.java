package net.codevmc.extraItems.lore;

import net.codevmc.extraItems.item.ExtraItem;
import net.codevmc.util.Item.lore.Lore;

import java.util.List;

public class ExtraItemLore extends Lore {

    private ExtraItem item;

    public ExtraItemLore(ExtraItem item) {
        this.item = item;
    }

    @Override
    public List<String> get() {
        return null;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void asyncUpdate() {
        super.asyncUpdate();
    }
}
