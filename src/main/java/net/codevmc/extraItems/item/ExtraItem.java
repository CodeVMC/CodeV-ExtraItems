package net.codevmc.extraItems.item;

import net.codevmc.extraItems.item.serialization.SerializationHelper;
import net.codevmc.extraItems.item.skill.Skill;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;
import java.util.stream.Collectors;

public class ExtraItem {

    private List<Skill> skillList = new ArrayList<>();

    private static final String SKILL_LIST_KEY = "skillList";

    public ExtraItem(){}

    public ExtraItem(Map<String,Object> map){
        this.skillList = (List<Skill>) map.get(SKILL_LIST_KEY);
    }

    public void addSkill(Skill skill){
        skillList.add(skill);
    }

    public void removeSkill(Skill skill){
        this.skillList.remove(skill);
    }
    public Collection<Skill> getSkills(){
        return Collections.unmodifiableList(skillList);
    }

    public Collection<Skill> getSkill(String skillName){
        //a rpgItem can exist two same skill
        return skillList
                .stream()
                .filter(skill -> skill.skillName().equals(skillName))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraItem rpgItem = (ExtraItem) o;
        return Objects.equals(skillList, rpgItem.skillList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillList);
    }
}
