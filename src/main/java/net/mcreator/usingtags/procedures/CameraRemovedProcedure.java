package net.mcreator.usingtags.procedures;

import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.core.BlockPos;
import net.minecraft.Util;

import net.mcreator.usingtags.network.UsingtagsModVariables;

public class CameraRemovedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(world instanceof Level _lvl_isPow ? _lvl_isPow.hasNeighborSignal(new BlockPos((int) x, (int) y, (int) z)) : false)) {
			if ((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UsingtagsModVariables.PlayerVariables())).bisBaseCam) {
				{
					boolean _setval = false;
					entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.bisBaseCam = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (!world.isClientSide()) {
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().broadcastMessage(new TextComponent("CameraRemoved"), ChatType.SYSTEM, Util.NIL_UUID);
				}
			}
		}
	}
}
