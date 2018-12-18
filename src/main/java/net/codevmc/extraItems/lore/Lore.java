package net.codevmc.extraItems.lore;

import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTList;
import me.dpohvar.powernbt.api.NBTManager;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Lore {

    private final ItemStack stack;
    private List<String> templateList = new ArrayList<>();

    public Lore(ItemStack stack){
        this.stack=stack;
    }

    public void addTemplate(Template template){
        templateList.add(template.getTemplateName());
    }

    public void removeTemplate(Template template){
        templateList.remove(template.getTemplateName());
    }

    public boolean templateIsEmpty(){
        return templateList.isEmpty();
    }

    public void flushLore(){
        if(templateIsEmpty())
            return;
        List<String> itemLore = templateList
                .stream()
                .map(templateName->TemplateManager.getTemplate(templateName,stack))
                .filter(Template::hasContent)
                .map(Template::getContent)
                .collect(ArrayList::new,(list,collection)->list.addAll(collection),List::addAll);
        NBTList nbtList = new NBTList(itemLore);
        setNBTLore(nbtList);
    }

    private void setNBTLore(NBTList list){
        NBTCompound stackCompound = NBTManager.getInstance().read(stack);
        NBTCompound displayCompound = getDisplay(stackCompound);
        displayCompound.bind("Lore",list);
        stackCompound.bind("display",displayCompound);
        NBTManager.getInstance().write(stack,stackCompound);
    }

    private NBTCompound getDisplay(NBTCompound nbtCompound){
        if(nbtCompound.containsKey("display"))
            return nbtCompound.getCompound("display");
        return new NBTCompound();
    }


    public static Lore getLore(ItemStack stack){
        return new Lore(stack);
    }

}
