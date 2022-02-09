package net.mcreator.usingtags.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.SilverfishModel;

import net.mcreator.usingtags.entity.IMaMobEntity;

public class IMaMobRenderer extends MobRenderer<IMaMobEntity, SilverfishModel<IMaMobEntity>> {
	public IMaMobRenderer(EntityRendererProvider.Context context) {
		super(context, new SilverfishModel(context.bakeLayer(ModelLayers.SILVERFISH)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(IMaMobEntity entity) {
		return new ResourceLocation("usingtags:textures/silverfish.png");
	}
}
