package net.codevmc.extraItems.core.item.skill;

import net.codevmc.extraItems.core.item.skill.lore.SpeedPotionEffectSkillLore;
import net.codevmc.util.Item.lore.annotation.Lore;
import org.bukkit.potion.PotionEffectType;

@Lore(SpeedPotionEffectSkillLore.class)
public class SpeedPotionEffectSkill extends PotionEffectSkill {

    @Override
    public PotionEffectType getPotionEffectType() {
        return PotionEffectType.SPEED;
    }

    @Override
    public String skillName() {
        return "speed effect";
    }

}
