package net.codevmc.item.skill;

import net.codevmc.item.RPGItemManager;
import net.codevmc.item.event.ExecuteSkillEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.Map;

public class SpeedPotionEffecthSkill extends PotionEffectSkill{

    public SpeedPotionEffecthSkill() {
    }

    public SpeedPotionEffecthSkill(Map<String, Object> map) {
        super(map);
    }

    @Override
    public PotionEffectType getPotionEffectType() {
        return PotionEffectType.SPEED;
    }

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event){
        if(!event.isSneaking())
            return;
        if(!super.meetAllCondition())
            return;
        RPGItemManager.getRPGItem(event.getPlayer().getInventory().getChestplate()).ifPresent(rpgItem -> {
            Collection<Skill> skills = rpgItem.getSkill(getSkillName());
            for(Skill skill : skills){
                SpeedPotionEffecthSkill speedPotionEffecthSkill = (SpeedPotionEffecthSkill) skill;
                ExecuteSkillEvent skillEvent = new ExecuteSkillEvent(event.getPlayer(),speedPotionEffecthSkill);
                Bukkit.getServer().getPluginManager().callEvent(skillEvent);
                if(skillEvent.isCancelled())
                    continue;
                speedPotionEffecthSkill.applyPotionEffect(event.getPlayer());
            }
        });
    }


    @Override
    public String getSkillName() {
        return "speed effect";
    }
}
