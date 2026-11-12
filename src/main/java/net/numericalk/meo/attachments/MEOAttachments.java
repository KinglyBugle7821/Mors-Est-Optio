package net.numericalk.meo.attachments;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.numericalk.meo.MorsEstOptio;

public class MEOAttachments {
    public static final AttachmentType<Float> TEMPERATURE =
            AttachmentRegistry.create(
                    MorsEstOptio.id("temperature"),
                    builder -> builder
                            .initializer(() -> 37F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );

    //NUTRITION
    public static final AttachmentType<Float> CARBOHYDRATE =
            AttachmentRegistry.create(
                    MorsEstOptio.id("carbohydrate"),
                    builder -> builder
                            .initializer(() -> 1.0F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    public static final AttachmentType<Float> PROTEIN =
            AttachmentRegistry.create(
                    MorsEstOptio.id("protein"),
                    builder -> builder
                            .initializer(() -> 1.0F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    public static final AttachmentType<Float> FAT =
            AttachmentRegistry.create(
                    MorsEstOptio.id("fat"),
                    builder -> builder
                            .initializer(() -> 1.0F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );

    public static final AttachmentType<Float> HYDRATION =
            AttachmentRegistry.create(
                    MorsEstOptio.id("hydration"),
                    builder -> builder
                            .initializer(() -> 1.0F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    public static final AttachmentType<Float> STAMINA =
            AttachmentRegistry.create(
                    MorsEstOptio.id("stamina"),
                    builder -> builder
                            .initializer(() -> 1F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );
    public static final AttachmentType<Float> FATIGUE =
            AttachmentRegistry.create(
                    MorsEstOptio.id("fatigue"),
                    builder -> builder
                            .initializer(() -> 0.0F)
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

    public static final AttachmentType<Float> WETNESS =
            AttachmentRegistry.create(
                    MorsEstOptio.id("wetness"),
                    builder -> builder
                            .initializer(() -> 0.0F)
                            .persistent(Codec.FLOAT)
                            .syncWith(
                                    ByteBufCodecs.FLOAT,
                                    AttachmentSyncPredicate.targetOnly()
                            )
            );

    //WORLD
    public static final AttachmentType<Integer> WIND_DIRECTION =
            AttachmentRegistry.create(
                    MorsEstOptio.id("wind_direction"),
                    builder -> builder
                            .initializer(() -> 0)
                            .persistent(Codec.INT)
            );

    public static final AttachmentType<Float> WIND_STRENGTH =
            AttachmentRegistry.create(
                    MorsEstOptio.id("wind_strength"),
                    builder -> builder
                            .initializer(() -> 0.05F)
                            .persistent(Codec.FLOAT)
            );
    public static final AttachmentType<Integer> WIND_DIRECTION_TARGET =
            AttachmentRegistry.create(
                    MorsEstOptio.id("wind_direction_target"),
                    builder -> builder
                            .initializer(() -> 0)
                            .persistent(Codec.INT)
            );

    public static final AttachmentType<Float> WIND_STRENGTH_TARGET =
            AttachmentRegistry.create(
                    MorsEstOptio.id("wind_strength_target"),
                    builder -> builder
                            .initializer(() -> 0.05F)
                            .persistent(Codec.FLOAT)
            );

    //SEASONS
    public static final AttachmentType<Integer> SEASON =
            AttachmentRegistry.create(
                    MorsEstOptio.id("season"),
                    builder -> builder
                            .initializer(() -> 0)
                            .persistent(Codec.INT)
            );
    public static void initialize(){}
}
