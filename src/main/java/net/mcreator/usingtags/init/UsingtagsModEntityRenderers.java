
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.usingtags.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.mcreator.usingtags.client.renderer.ZombieMobMobRenderer;
import net.mcreator.usingtags.client.renderer.IMaMobRenderer;
import net.mcreator.usingtags.client.renderer.BlazeBushEntityRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UsingtagsModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(UsingtagsModEntities.I_MA_MOB, IMaMobRenderer::new);
		event.registerEntityRenderer(UsingtagsModEntities.ZOMBIE_MOB_MOB, ZombieMobMobRenderer::new);
		event.registerEntityRenderer(UsingtagsModEntities.LIGHTINING_STORM, ThrownItemRenderer::new);
		event.registerEntityRenderer(UsingtagsModEntities.ENRICH_DEVICE, ThrownItemRenderer::new);
		event.registerEntityRenderer(UsingtagsModEntities.BLAZE_BUSH_ENTITY, BlazeBushEntityRenderer::new);
		event.registerEntityRenderer(UsingtagsModEntities.BLAZE_BUSH_RANGED_ITEM, ThrownItemRenderer::new);
	}
}
