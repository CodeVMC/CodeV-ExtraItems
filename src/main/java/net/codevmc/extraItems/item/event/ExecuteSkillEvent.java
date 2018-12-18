package net.codevmc.extraItems.item.event;

import net.codevmc.extraItems.item.skill.Skill;
import org.bukkit.entity.Player;

public class ExecuteSkillEvent extends SkillEvent{
    public ExecuteSkillEvent(Player who, Skill skill) {
        super(who, skill);
    }
}
