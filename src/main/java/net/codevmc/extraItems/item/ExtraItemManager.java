package net.codevmc.extraItems.item;

import me.dpohvar.powernbt.PowerNBT;
import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExtraItemManager {

    private static NBTManager nbtManager = PowerNBT.getApi();
    private static final String EXTRA_ITEM_KEY = "ExtraItem";

    private static HashMap<ItemStack, ExtraItem> ExtraItemHashMap = new HashMap<>();

    public static Optional<ExtraItem> getRPGItem(ItemStack stack){
        if(ExtraItemHashMap.containsKey(stack))
            return Optional.of(ExtraItemHashMap.get(stack));
        NBTCompound compound = nbtManager.read(stack);
        if(!compound.containsKey(EXTRA_ITEM_KEY))
            return Optional.empty();
        Map<String,Object> deserializeMap = (Map<String, Object>) compound.get(EXTRA_ITEM_KEY);
        ExtraItem rpgItem = new ExtraItem(deserializeMap);
        ExtraItemHashMap.put(stack,rpgItem);
        return Optional.of(rpgItem);
    }

    public static void saveToItem(ExtraItem rpgItem, ItemStack stack){
//        nbtManager.write(stack,new NBTCompound(getRPGMap(rpgItem.serialize())));
    }

    private static Map<String,Map<String,Object>> getRPGMap(Map<String,Object> map){
        HashMap<String,Map<String,Object>> rpgMap = new HashMap<>();
        rpgMap.put(EXTRA_ITEM_KEY,map);
        return rpgMap;
    }



}
