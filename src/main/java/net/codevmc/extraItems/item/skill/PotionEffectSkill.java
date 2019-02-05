package net.codevmc.extraItems.item.skill;

import net.codevmc.util.serialization.Serialization;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public abstract class PotionEffectSkill extends Skill{

    @Serialization
    private PotionEffect potionEffect;

    @Serialization
    private int duration=10;
    @Serialization
    private int amplifier=1;
    @Serialization
    private boolean ambient=true;
    @Serialization
    private boolean particles=true;
    @Serialization
    private boolean icon=true;

    private static final String DURATION_KEY = "duration";
    private static final String AMPLIFIER_KEY = "amplifier";
    private static final String AMBIENT_KEY = "ambient";
    private static final String PARTICLES_KEY = "particles";
    private static final String ICON_KEY = "icon";

    public abstract PotionEffectType getPotionEffectType();

    public PotionEffectSkill(){}

    public PotionEffect getPotionEffect(){
        if(potionEffect==null)
             potionEffect = getNew();
        return potionEffect;
    }

    public void flush(){
        potionEffect = getNew();
    }

    private PotionEffect getNew(){
        return new PotionEffect(getPotionEffectType(),duration,amplifier,ambient,particles,icon);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    public boolean isAmbient() {
        return ambient;
    }

    public void setAmbient(boolean ambient) {
        this.ambient = ambient;
    }

    public boolean isParticles() {
        return particles;
    }

    public void setParticles(boolean particles) {
        this.particles = particles;
    }

    public boolean isIcon() {
        return icon;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }

    public void applyPotionEffect(Player player){
        this.getPotionEffect().apply(player);
    }

}
