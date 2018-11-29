package net.codevmc.item.event;

import net.codevmc.item.skill.Skill;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class ExecuteSkillEvent extends SkillEvent{
    public ExecuteSkillEvent(Player who, Skill skill) {
        super(who, skill);
    }
}
