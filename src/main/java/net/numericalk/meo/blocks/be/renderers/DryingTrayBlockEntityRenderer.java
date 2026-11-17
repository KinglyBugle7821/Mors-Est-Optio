package net.numericalk.meo.blocks.be.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.numericalk.meo.blocks.be.DryingTrayBlockEntity;
import org.joml.Quaternionfc;
import org.jspecify.annotations.Nullable;

public class DryingTrayBlockEntityRenderer implements BlockEntityRenderer<DryingTrayBlockEntity, DryingTrayBlockEntityRenderState> {
    private static final Vec3 SLOT_POSITION = new Vec3(0.50, 0.1, 0.50);

    private final BlockEntityRendererProvider.Context context;

    public DryingTrayBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public DryingTrayBlockEntityRenderState createRenderState() {
        return new DryingTrayBlockEntityRenderState();
    }
    @Override
    public void extractRenderState(DryingTrayBlockEntity blockEntity, DryingTrayBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.items.set(0, blockEntity.getItem(0).copy());
    }
    @Override
    public void submit(DryingTrayBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        var stack = state.items.getFirst();
        if (stack.isEmpty()) return;

        ItemStackRenderState itemState = new ItemStackRenderState();
        context.itemModelResolver().updateForTopItem(itemState, stack, ItemDisplayContext.FIXED, null, null, 0);

        Vec3 position = SLOT_POSITION;
        poseStack.pushPose();
        poseStack.translate(position.x, position.y, position.z);
        poseStack.scale(0.8f, 0.8f, 0.8f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        itemState.submit(
                poseStack,
                submitNodeCollector,
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                0
        );
        poseStack.popPose();
    }
}
