package net.codevmc.item.skill;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;

public abstract class PotionEffectSkill extends Skill{

    private PotionEffect potionEffect;

    private int duration=10;
    private int amplifier=1;
    private boolean ambient=true;
    private boolean particles=true;
    private boolean icon=true;

    private static final String DURATION_KEY = "duration";
    private static final String AMPLIFIER_KEY = "amplifier";
    private static final String AMBIENT_KEY = "ambient";
    private static final String PARTICLES_KEY = "particles";
    private static final String ICON_KEY = "icon";

    public abstract PotionEffectType getPotionEffectType();

    public PotionEffectSkill(){}

    public PotionEffectSkill(Map<String, Object> map) {
        super(map);
    }

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

    @Override
    public Map<String, Object> serialize() {
        Map<String,Object> map = super.serialize();
        map.put(DURATION_KEY,duration);
        map.put(AMPLIFIER_KEY,amplifier);
        map.put(AMBIENT_KEY,ambient);
        map.put(PARTICLES_KEY,particles);
        map.put(ICON_KEY,icon);
        return map;
    }
}
