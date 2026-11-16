package net.numericalk.meo.blocks.be.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.numericalk.meo.blocks.be.CampfireBlockEntity;
import org.jspecify.annotations.Nullable;

public class CampfireBlockEntityRenderer implements BlockEntityRenderer<CampfireBlockEntity, CampfireBlockEntityRenderState> {
    private static final Vec3[] SLOT_POSITIONS = {
            new Vec3(0.50, 1, 0.30),
            new Vec3(0.50, 1, 0.50),
            new Vec3(0.50, 1, 0.70)
    };

    private final BlockEntityRendererProvider.Context context;

    public CampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public CampfireBlockEntityRenderState createRenderState() {
        return new CampfireBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(CampfireBlockEntity blockEntity, CampfireBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        for (int slot = 0; slot < state.items.size(); slot++) {
            state.items.set(slot, blockEntity.getItem(slot).copy());
        }
    }

    @Override
    public void submit(CampfireBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        for (int slot = 0; slot < state.items.size(); slot++) {
            var stack = state.items.get(slot);
            if (stack.isEmpty()) continue;

            ItemStackRenderState itemState = new ItemStackRenderState();
            context.itemModelResolver().updateForTopItem(itemState, stack, ItemDisplayContext.FIXED, null, null, 0);

            Vec3 position = SLOT_POSITIONS[slot];
            poseStack.pushPose();
            poseStack.translate(position.x, position.y, position.z);
            poseStack.scale(0.5f, 0.5f, 0.5f);
            itemState.submit(
                    poseStack,
                    submitNodeCollector,
                    state.lightCoords,
                    0,
                    0
            );
            poseStack.popPose();
        }
    }
}
