package net.codevmc.extraItems.item;

import net.codevmc.extraItems.item.skill.Skill;
import net.codevmc.util.serialization.Serialization;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;
import java.util.stream.Collectors;

public class ExtraItem {

    @Serialization
    private List<Skill> skillList = new ArrayList<>();

    public ExtraItem(){}

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
        //a rpgItem can exist two or more same skill
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
