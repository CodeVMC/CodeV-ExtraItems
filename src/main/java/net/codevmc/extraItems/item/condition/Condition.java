package net.codevmc.extraItems.item.condition;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public interface Condition extends ConfigurationSerializable {

    boolean meet();

    String conditionName();

}
