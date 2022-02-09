
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.usingtags.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fmlclient.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.usingtags.network.PortalOrangeMessage;
import net.mcreator.usingtags.network.PortalBlueMessage;
import net.mcreator.usingtags.network.CheckCameraMessage;
import net.mcreator.usingtags.UsingtagsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class UsingtagsModKeyMappings {
	public static final KeyMapping PORTAL_BLUE = new KeyMapping("key.usingtags.portal_blue", GLFW.GLFW_KEY_F, "key.categories.misc");
	public static final KeyMapping PORTAL_ORANGE = new KeyMapping("key.usingtags.portal_orange", GLFW.GLFW_KEY_G, "key.categories.misc");
	public static final KeyMapping CHECK_CAMERA = new KeyMapping("key.usingtags.check_camera", GLFW.GLFW_KEY_U, "key.categories.misc");

	@SubscribeEvent
	public static void registerKeyBindings(FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(PORTAL_BLUE);
		ClientRegistry.registerKeyBinding(PORTAL_ORANGE);
		ClientRegistry.registerKeyBinding(CHECK_CAMERA);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onKeyInput(InputEvent.KeyInputEvent event) {
			if (Minecraft.getInstance().screen == null) {
				if (event.getKey() == PORTAL_BLUE.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						UsingtagsMod.PACKET_HANDLER.sendToServer(new PortalBlueMessage(0, 0));
						PortalBlueMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
				if (event.getKey() == PORTAL_ORANGE.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						UsingtagsMod.PACKET_HANDLER.sendToServer(new PortalOrangeMessage(0, 0));
						PortalOrangeMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
				if (event.getKey() == CHECK_CAMERA.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						UsingtagsMod.PACKET_HANDLER.sendToServer(new CheckCameraMessage(0, 0));
						CheckCameraMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
			}
		}
	}
}
