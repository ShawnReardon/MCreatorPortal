package net.mcreator.usingtags.procedures;

import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.Util;

import net.mcreator.usingtags.network.UsingtagsModVariables;

public class PortalDebounceProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		double currentTime = 0;
		if (!world.isClientSide()) {
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().broadcastMessage(
						new TextComponent(((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).glb_portal_clock
								+ " || "
								+ ((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new UsingtagsModVariables.PlayerVariables())).glb_portal_clock + 50))),
						ChatType.SYSTEM, Util.NIL_UUID);
		}
		if (!(entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UsingtagsModVariables.PlayerVariables())).portalDebounce) {
			{
				boolean _setval = true;
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.portalDebounce = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = world.dayTime();
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.glb_portal_clock = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if (world.dayTime() > (entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UsingtagsModVariables.PlayerVariables())).glb_portal_clock + 50) {
			{
				boolean _setval = false;
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.portalDebounce = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		return (entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UsingtagsModVariables.PlayerVariables())).portalDebounce;
	}
}
