package org.cardboardpowered.mixin;

import com.javazilla.bukkitfabric.BukkitFabricMod;
import org.spongepowered.asm.mixin.Mixin;

import org.cardboardpowered.impl.CardboardPotionEffectType;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.registry.Registry;

@Mixin(StatusEffects.class)
public class MixinStatusEffects {

    static {
        for (Object effect : Registry.STATUS_EFFECT) {
            CardboardPotionEffectType cardboardPotionEffectType = new CardboardPotionEffectType((StatusEffect) effect);
            try {
                org.bukkit.potion.PotionEffectType.registerPotionEffectType(cardboardPotionEffectType);
            } catch (ArrayIndexOutOfBoundsException ex) {
                BukkitFabricMod.LOGGER.warning("Error in Bukkit PotionEffectType#registerPotionEffectType for " + cardboardPotionEffectType.getName() + " [" + cardboardPotionEffectType.getId() + "]");
            }
        }
    }

}