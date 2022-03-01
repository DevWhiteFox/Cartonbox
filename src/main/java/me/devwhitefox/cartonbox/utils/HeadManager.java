package me.devwhitefox.cartonbox.utils;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTList;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class HeadManager {

    public static ItemStack GetCustomHead(final String name, final String textureValue){
        //"textureValue" Pulled from the head link, scroll to the bottom and the "Other Value" field has this texture id.

        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1); // Creating the ItemStack, your input may vary.
        NBTItem nbti = new NBTItem(head); // Creating the wrapper.

        NBTCompound disp = nbti.addCompound("display");
        disp.setString("Name", "Testitem"); // Setting the name of the Item

        NBTList<String> l = disp.getStringList("Lore");
        l.add("Some lore"); // Adding a bit of lore.

        NBTCompound skull = nbti.addCompound("SkullOwner"); // Getting the compound, that way we can set the skin information
        skull.setString("Name", name); // Owner's name
        skull.setString("Id", UUID.randomUUID().toString());
        // The UUID, note that skulls with the same UUID but different textures will misbehave and only one texture will load
        // (They'll share it), if skulls have different UUIDs and same textures they won't stack. See UUID.randomUUID();

        NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
        texture.setString("Value",  textureValue);

        head = nbti.getItem(); // Refresh the ItemStack
        return head;
    }
}
