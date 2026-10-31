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
}
