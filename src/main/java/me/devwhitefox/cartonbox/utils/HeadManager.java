package me.devwhitefox.cartonbox.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import static org.apache.commons.lang.Validate.notNull;

public class HeadManager {

    private static Method metaSetProfileMethod;
    private static Field metaProfileField;

    public static ItemStack itemWithBase64(ItemStack item, String base64) {
        try {
            notNull(item, "item");
            notNull(base64, "base64");

            if (!(item.getItemMeta() instanceof SkullMeta)) {
                return item;
            }

            SkullMeta meta = (SkullMeta) item.getItemMeta();
            mutateItemMeta(meta, base64);
            item.setItemMeta(meta);

            return item;
        } catch (NoClassDefFoundError e) {
            Bukkit.getConsoleSender().sendMessage("[Cartonbox] Missing required dependency: " + e.getCause());
            return item;
        }
    }

    private static void mutateItemMeta(SkullMeta meta, String b64) {
        try {
            if (metaSetProfileMethod == null) {
                metaSetProfileMethod = meta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
                metaSetProfileMethod.setAccessible(true);
            }
            metaSetProfileMethod.invoke(meta, makeProfile(b64));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            // if in an older API where there is no setProfile method,
            // we set the profile field directly.
            try {
                if (metaProfileField == null) {
                    metaProfileField = meta.getClass().getDeclaredField("profile");
                    metaProfileField.setAccessible(true);
                }
                metaProfileField.set(meta, makeProfile(b64));

            } catch (NoSuchFieldException | IllegalAccessException ex2) {
                ex2.printStackTrace();
            }
        }
    }

    private static @NotNull GameProfile makeProfile(String b64) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), "Player");
        profile.getProperties().put("textures", new Property("textures", b64));
        return profile;
    }
}
