package net.codevmc.item.condition;

import net.codevmc.item.event.ExecuteSkillEvent;
import net.codevmc.item.serialization.SerializationHelper;
import net.codevmc.item.skill.Skill;
import org.bukkit.event.EventHandler;

import java.util.Map;
import java.util.Objects;

public class CoolDownCondition extends BaseCondition {

    private static final String CONDITION_NAME = "CoolDown";
    private static final String LAST_USE_TIME_KEY = "lastUseTime";
    private static final String COOL_DOWN_TIME_KEY = "coolDownTime";

    private long lastUseTime;
    //Tick
    private int coolDownTime;

    public CoolDownCondition(){}

    public CoolDownCondition(Map<String,Object> map){
        this.lastUseTime = (long) map.get(LAST_USE_TIME_KEY);
        this.coolDownTime= (int) map.get(COOL_DOWN_TIME_KEY);
    }

    @Override
    public boolean meet() {
        return coolDownTime< getPastTick();
    }

    @EventHandler
    public void onExecuteSkill(ExecuteSkillEvent event){
        long now = System.currentTimeMillis();
        Skill skill = event.getSkill();
        for(Condition condition : skill.getCondition(this.ConditionName())){
            CoolDownCondition coolDownCondition = (CoolDownCondition) condition;
            coolDownCondition.lastUseTime = now;
        }
    }

    public int getCoolDownTime() {
        return coolDownTime;
    }

    public int getLastCoolDownTime(){
        int coolDown = getCoolDownTime()- getPastTick();
        if(coolDown<=0)
            return 0;
        return coolDown;
    }

    public int getPastTick(){
        long passTime =System.currentTimeMillis()-lastUseTime;
        int passTick = (int) (passTime/50);
        return passTick;
    }

    @Override
    public String ConditionName() {
        return CONDITION_NAME;
    }

    @Override
    public Map<String, Object> serialize() {
        return SerializationHelper
                .helper()
                .put(LAST_USE_TIME_KEY,lastUseTime)
                .put(COOL_DOWN_TIME_KEY,coolDownTime)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CoolDownCondition that = (CoolDownCondition) o;
        return lastUseTime == that.lastUseTime &&
                coolDownTime == that.coolDownTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lastUseTime, coolDownTime);
    }
}
