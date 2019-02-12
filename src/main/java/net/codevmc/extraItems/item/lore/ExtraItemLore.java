package net.codevmc.extraItems.item.lore;

import net.codevmc.extraItems.item.ExtraItem;
import net.codevmc.util.Item.lore.Lore;
import net.codevmc.util.Item.lore.annotation.AnnotationManager;

import java.util.ArrayList;
import java.util.List;

public class ExtraItemLore extends Lore {

    private final ExtraItem extraItem;

    private List<Lore> skillLores = new ArrayList<>();

    public ExtraItemLore(ExtraItem item) {
        this.extraItem = item;
        extraItem.getSkills().forEach(skill -> skillLores.add(AnnotationManager.getLore(skill)));
    }

    public ExtraItem getExtraItem(){
        return extraItem;
    }

    @Override
    public List<String> get() {
        return skillLores
                .stream()
                .collect(ArrayList::new,(list,lore)->list.addAll(lore.get()),ArrayList::addAll);
    }
}
