package net.codevmc.extraItems.item;

import me.dpohvar.powernbt.api.NBTCompound;
import net.codevmc.extraItems.item.lore.ExtraItemLoreManager;
import net.codevmc.util.Item.ItemUUID;
import net.codevmc.util.nbt.NBTHelper;
import net.codevmc.util.serialization.SerializationHelper;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class ExtraItemManager {

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
        NBTCompound compound = NBTHelper.getNBT(stack);
        String deserializeString = compound.getString(EXTRA_ITEM_KEY);
        if(deserializeString==null)
            return null;
        return SerializationHelper.deserialize(deserializeString);
    }

    public static void saveToItem(ItemStack stack,ExtraItem extraItem){
        NBTCompound compound = NBTHelper.getNBT(stack);
        compound.put(EXTRA_ITEM_KEY, SerializationHelper.serialize(extraItem));
        NBTHelper.write(stack,compound);
        ItemUUID.addUUID(stack);
    }

    public static void saveToItemAndBindLore(ItemStack stack,ExtraItem extraItem){
        saveToItem(stack,extraItem);
        ExtraItemLoreManager.bind(stack);
    }

}
