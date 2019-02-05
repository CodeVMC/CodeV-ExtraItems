package net.codevmc.extraItems.item.skill;

import net.codevmc.extraItems.item.condition.Condition;
import net.codevmc.util.serialization.Serialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Skill{

    @Serialization
    private ArrayList<Condition> conditions = new ArrayList<>();

    public Skill(){}

    public void addCondition(Condition condition) {
        this.conditions.add(condition);
    }

    public boolean meetAllCondition() {
        for (Condition condition : conditions)
            if (!condition.meet())
                return false;
        return true;
    }

    public boolean hasCondition(String condition){
        return false;
    }

    public List<Condition> getConditionList(){
        return Collections.unmodifiableList(conditions);
    }

    public List<Condition> getCondition(String conditionName){
        return conditions
                .stream()
                .filter(condition -> condition.conditionName().equals(conditionName))
                .collect(Collectors.toList());
    }

    public void removeCondition(Condition condition){
        conditions.remove(condition);
    }

    public abstract String skillName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(conditions, skill.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditions);
    }
}
