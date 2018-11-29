package net.codevmc.item;

import me.dpohvar.powernbt.PowerNBT;
import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;
import net.codevmc.item.condition.Condition;
import net.codevmc.item.skill.Skill;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RPGItemManager {

    private static NBTManager nbtManager = PowerNBT.getApi();
    private static final String RPG_ITEM_KEY = "RPGItem";

    private static HashMap<ItemStack,RPGItem> rpgItemHashMap = new HashMap<>();

    public static Optional<RPGItem> getRPGItem(ItemStack stack){
        if(rpgItemHashMap.containsKey(stack))
            return Optional.of(rpgItemHashMap.get(stack));
        NBTCompound compound = nbtManager.read(stack);
        if(!compound.containsKey(RPG_ITEM_KEY))
            return Optional.empty();
        Map<String,Object> deserializeMap = (Map<String, Object>) compound.get(RPG_ITEM_KEY);
        RPGItem rpgItem = new RPGItem(deserializeMap);
        rpgItemHashMap.put(stack,rpgItem);
        return Optional.of(rpgItem);
    }

    public static void saveToItem(RPGItem rpgItem,ItemStack stack){
        nbtManager.write(stack,new NBTCompound(getRPGMap(rpgItem.serialize())));
    }

    private static Map<String,Map<String,Object>> getRPGMap(Map<String,Object> map){
        HashMap<String,Map<String,Object>> rpgMap = new HashMap<>();
        rpgMap.put(RPG_ITEM_KEY,map);
        return rpgMap;
    }


}
