package net.codevmc.extraItems.item;

import me.dpohvar.powernbt.PowerNBT;
import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;
import net.codevmc.util.Item.ItemUUID;
import net.codevmc.util.serialization.SerializationHelper;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class ExtraItemManager {

    private static NBTManager nbtManager = PowerNBT.getApi();
    private static final String EXTRA_ITEM_KEY = "ExtraItem";

    private static HashMap<UUID, ExtraItem> EXTRA_ITEM_MAP = new HashMap<>();

    public static Optional<ExtraItem> getExtraItem(ItemStack stack){
        Optional<UUID> uuid = ItemUUID.getUUID(stack);
        if(uuid.isPresent()){
            if(EXTRA_ITEM_MAP.containsKey(uuid.get()))
                return Optional.of(EXTRA_ITEM_MAP.get(uuid.get()));
            ExtraItem item = deserializeFromStack(stack);
            if(item==null)
                return Optional.empty();
            EXTRA_ITEM_MAP.put(uuid.get(),item);
            return Optional.of(item);
        }
        return Optional.empty();
    }

    private static ExtraItem deserializeFromStack(ItemStack stack) {
        NBTCompound compound = nbtManager.read(stack);
        if(compound==null)
            return null;
        String deserializeString = compound.getString(EXTRA_ITEM_KEY);
        if(deserializeString==null)
            return null;
        return SerializationHelper.deserialize(deserializeString);
    }

    public static void saveToItem(ExtraItem rpgItem, ItemStack stack){
        NBTCompound compound = nbtManager.read(stack);
        if(compound==null)
            compound = new NBTCompound();
        compound.put(EXTRA_ITEM_KEY, SerializationHelper.serialize(rpgItem));
        nbtManager.write(stack,compound);
        ItemUUID.addUUID(stack);
    }




}
