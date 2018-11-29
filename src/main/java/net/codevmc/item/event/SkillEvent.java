package net.codevmc.item.event;

import net.codevmc.item.skill.Skill;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public abstract class SkillEvent extends PlayerEvent implements Cancellable {

    private final Skill skill;
    private boolean cancel = false;

    public SkillEvent(Player who,Skill skill) {
        super(who);
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancel= b;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }


}
