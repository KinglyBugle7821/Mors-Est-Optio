package net.numericalk.meo.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorType;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.entity.MEOEffects;

public class MEOInjuryEvent {
    public ServerLivingEntityEvents.AfterDamage afterDamage = (livingEntity, damageSource, baseDamage, damageTaken, blocked) -> {
        if (livingEntity instanceof Player player){
            ResourceKey<DamageType> damageType = damageSource.typeHolder().unwrapKey().orElse(null);

            if (damageType == null) return;

            setInjury(DamageTypes.ARROW, 0.5f, 1, 1, 0.5f, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.BAD_RESPAWN_POINT, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.CACTUS, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.CAMPFIRE, 0.1f, 1, 0.1f, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.ENDER_PEARL, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.EXPLOSION, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.FALL, 0, 0, 0, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.FALLING_ANVIL, 1, 1, 0.5f, 0.5f, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.FALLING_STALACTITE, 1, 0.5f, 0.2f, 0f, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.FIREBALL, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.FIREWORKS, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.FLY_INTO_WALL, 1, 1, 0.5f, 0.5f, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.GENERIC, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.GENERIC_KILL, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.HOT_FLOOR, 0, 0, 0, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.IN_FIRE, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.IN_WALL, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.INDIRECT_MAGIC, 0.5f, 1, 1, 0.5f, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.LAVA, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.LIGHTNING_BOLT, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.MACE_SMASH, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.MAGIC, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.MOB_ATTACK, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.MOB_ATTACK_NO_AGGRO, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.MOB_PROJECTILE, 1, 1, 1, 0.5f, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.ON_FIRE, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.PLAYER_ATTACK, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.PLAYER_EXPLOSION, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.SPEAR, 0.5f, 1, 0.7f, 0.2f, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.STALAGMITE, 0, 0.2f, 0.2f, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.SULFUR_CUBE_HOT, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.SWEET_BERRY_BUSH, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.THORNS, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.TRIDENT, 0.8f, 1, 0.5f, 0.5f, damageTaken, baseDamage, player, damageType);
            setInjury(DamageTypes.UNATTRIBUTED_FIREBALL, 1, 1, 1, 1, damageTaken, baseDamage, player, damageType);

        }
    };

    public static void serverTick(ServerPlayer player) {
        float headInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_HEAD_INJURY, 0.0F);
        float bodyInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_BODY_INJURY, 0.0F);
        float armsInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_ARMS_INJURY, 0.0F);
        float legsInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_LEGS_INJURY, 0.0F);

        MobEffectInstance blindness = new MobEffectInstance(MobEffects.BLINDNESS, 3 * 20, 0, false, false, false);
        MobEffectInstance slowness = new MobEffectInstance(MobEffects.SLOWNESS, 3 * 20, 5, false, false, false);
        MobEffectInstance miningFatigue = new MobEffectInstance(MobEffects.MINING_FATIGUE, 3 * 20, 2, false, false, false);
        MobEffectInstance bleeding = new MobEffectInstance(MEOEffects.BLEEDING, 3 * 20, 0, false, false, false);

        if (headInjury < 0) player.setAttached(MEOAttachments.PLAYER_HEAD_INJURY, 0F);
        if (bodyInjury < 0) player.setAttached(MEOAttachments.PLAYER_BODY_INJURY, 0F);
        if (armsInjury < 0) player.setAttached(MEOAttachments.PLAYER_ARMS_INJURY, 0F);
        if (legsInjury < 0) player.setAttached(MEOAttachments.PLAYER_LEGS_INJURY, 0F);

        if (headInjury > 0.8F){
            player.addEffect(blindness);
            player.addEffect(bleeding);
        }
        if (bodyInjury > 0.8F) {
            player.addEffect(bleeding);
        }
        if (armsInjury > 0.8F) {
            player.addEffect(miningFatigue);
            player.addEffect(bleeding);
        }
        if (legsInjury > 0.8F) {
            player.addEffect(slowness);
            player.addEffect(bleeding);
        }
    }

    private void setInjury(ResourceKey<DamageType> damageType,
                           float headModifier, float bodyModifier,
                           float armsModifier, float legsModifier,
                           float damageTaken, float baseDamage,
                           Player player, ResourceKey<DamageType> playerDamageType){
        if (!playerDamageType.equals(damageType)) return;

        float injuryMultiplier = damageTaken / 20F;

        player.setAttached(MEOAttachments.PLAYER_HEAD_INJURY,
                player.getAttachedOrElse(MEOAttachments.PLAYER_HEAD_INJURY, 0F)
                        + injuryMultiplier * headModifier / 10F
        );
        player.setAttached(MEOAttachments.PLAYER_BODY_INJURY,
                player.getAttachedOrElse(MEOAttachments.PLAYER_BODY_INJURY, 0F)
                        + injuryMultiplier * bodyModifier / 10F
        );
        player.setAttached(MEOAttachments.PLAYER_ARMS_INJURY,
                player.getAttachedOrElse(MEOAttachments.PLAYER_ARMS_INJURY, 0F)
                        + injuryMultiplier * armsModifier / 10F
        );
        player.setAttached(MEOAttachments.PLAYER_LEGS_INJURY,
                player.getAttachedOrElse(MEOAttachments.PLAYER_LEGS_INJURY, 0F)
                        + injuryMultiplier * legsModifier / 10F
        );
    }
}
