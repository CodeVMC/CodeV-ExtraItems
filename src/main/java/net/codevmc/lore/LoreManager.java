package net.codevmc.lore;

import net.codevmc.ExtraItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class LoreManager {

    static{

        new BukkitRunnable(){
            @Override
            public void run() {
                Bukkit.getOnlinePlayers()
                        .stream()
                        .filter(player -> player.getOpenInventory()==null)
                        .map(player->getListOfLore(player.getInventory()))
                        .filter(list->!list.isEmpty())
                        .forEach(list->list.forEach(Lore::flushLore));
            }
        }.runTaskTimer(ExtraItems.getInstance(),10,20);


        new BukkitRunnable(){
            @Override
            public void run() {
                Bukkit.getOnlinePlayers()
                        .stream()
                        .map(Player::getOpenInventory)
                        .map(inventoryView -> {
                            List<Lore> list = getListOfLore(inventoryView.getBottomInventory());
                            if(inventoryView.getTopInventory()!=null)
                                list.addAll(getListOfLore(inventoryView.getBottomInventory()));
                            return list;
                        })
                        .filter(list->!list.isEmpty())
                        .forEach(list->list.forEach(Lore::flushLore));
            }
        }.runTaskTimer(ExtraItems.getInstance(),10,1);


    }

    private static List<Lore> getListOfLore(Inventory inventory){
        ArrayList<Lore> listOfLore = new ArrayList<>();
        for(ItemStack stack : inventory){
            Lore lore = Lore.getLore(stack);
            if(!lore.templateIsEmpty())
                listOfLore.add(lore);
        }
        return listOfLore;
    }


}
