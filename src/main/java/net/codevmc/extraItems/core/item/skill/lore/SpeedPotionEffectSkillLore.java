package net.codevmc.extraItems.core.item.skill.lore;

import com.google.common.collect.Lists;
import net.codevmc.extraItems.core.item.skill.SpeedPotionEffectSkill;
import net.codevmc.util.Item.lore.annotation.AnnotationLore;

import java.util.List;

public class SpeedPotionEffectSkillLore extends AnnotationLore<SpeedPotionEffectSkill> {

    public SpeedPotionEffectSkillLore(SpeedPotionEffectSkill element) {
        super(element);
    }

    @Override
    public List<String> get() {
        return Lists.newArrayList(element.toString());
    }
}
