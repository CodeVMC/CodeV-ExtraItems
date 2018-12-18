package net.codevmc.extraItems.lore;

import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TemplateManager {
    private static ArrayList<Template> templateArrayList = new ArrayList<>();

    public static void registerTemplate(Template template){
        templateArrayList.add(template);
    }

    public static Template getTemplate(String templateName, ItemStack stack){
        Template template = templateArrayList
                .stream()
                .filter(template1 -> template1.getTemplateName().equals(templateName))
                .findFirst().orElse(null);
        if(template==null)
            return null;
        try {
            return template.getClass().getConstructor(ItemStack.class).newInstance(stack);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
