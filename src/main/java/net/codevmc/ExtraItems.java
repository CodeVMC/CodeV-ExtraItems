package net.codevmc;

import org.bukkit.plugin.java.JavaPlugin;

public class ExtraItems extends JavaPlugin {

    private static ExtraItems INSTANCE;

    public ExtraItems(){
        INSTANCE = this;
    }

    public static ExtraItems getInstance(){
        return INSTANCE;
    }

}
