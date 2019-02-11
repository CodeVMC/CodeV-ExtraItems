package net.codevmc.extraItems.item.skill;

import org.bukkit.potion.PotionEffectType;

import java.util.Map;

public class SpeedPotionEffecthSkill extends PotionEffectSkill{

    @Override
    public PotionEffectType getPotionEffectType() {
        return PotionEffectType.SPEED;
    }

    @Override
    public String skillName() {
        return "speed effect";
    }

}
