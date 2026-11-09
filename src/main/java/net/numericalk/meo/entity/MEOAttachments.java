package net.numericalk.meo.entity;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.numericalk.meo.MorsEstOptio;

public class MEOAttachments {
    public static final AttachmentType<Float> PLAYER_TEMPERATURE_ATTACHMENT =
            AttachmentRegistry.create(
                    MorsEstOptio.id("player_temperature_attachment"),
                    builder -> builder
                            .initializer(() -> 37F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );

    //NUTRITION
    public static final AttachmentType<Float> PLAYER_CARBOHYDRATE_ATTACHMENT =
            AttachmentRegistry.create(
                    MorsEstOptio.id("player_carbohydrate_attachment"),
                    builder -> builder
                            .initializer(() -> 0.5F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    public static final AttachmentType<Float> PLAYER_PROTEIN_ATTACHMENT =
            AttachmentRegistry.create(
                    MorsEstOptio.id("player_protein_attachment"),
                    builder -> builder
                            .initializer(() -> 0.5F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    public static final AttachmentType<Float> PLAYER_FAT_ATTACHMENT =
            AttachmentRegistry.create(
                    MorsEstOptio.id("player_fat_attachment"),
                    builder -> builder
                            .initializer(() -> 0.5F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    //HYDRATION
    public static final AttachmentType<Float> HYDRATION =
            AttachmentRegistry.create(
                    MorsEstOptio.id("hydration"),
                    builder -> builder
                            .initializer(() -> 0.5F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );

    //INJURY
    public static final AttachmentType<Float> PLAYER_HEAD_INJURY =
            AttachmentRegistry.create(
                    MorsEstOptio.id("player_head_injury"),
                    builder -> builder
                            .initializer(() -> 0.0F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    public static final AttachmentType<Float> PLAYER_BODY_INJURY =
            AttachmentRegistry.create(
                    MorsEstOptio.id("player_body_injury"),
                    builder -> builder
                            .initializer(() -> 0.0F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    public static final AttachmentType<Float> PLAYER_ARMS_INJURY =
            AttachmentRegistry.create(
                    MorsEstOptio.id("player_arms_injury"),
                    builder -> builder
                            .initializer(() -> 0.0F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    public static final AttachmentType<Float> PLAYER_LEGS_INJURY =
            AttachmentRegistry.create(
                    MorsEstOptio.id("player_legs_injury"),
                    builder -> builder
                            .initializer(() -> 0.0F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    public static void initialize(){}
}
