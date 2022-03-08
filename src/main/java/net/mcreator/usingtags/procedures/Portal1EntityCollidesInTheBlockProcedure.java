package net.mcreator.usingtags.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.mcreator.usingtags.network.UsingtagsModVariables;

import java.util.Collections;

public class Portal1EntityCollidesInTheBlockProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		boolean currentTime = false;
		if ((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UsingtagsModVariables.PlayerVariables())).bothPortalsPlaced && !PortalDebounceProcedure.execute(world, entity)) {
			{
				Entity _ent = entity;
				_ent.teleportTo(
						((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).portal_2_x),
						((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).portal_2_y),
						((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).portal_2_z));
				if (_ent instanceof ServerPlayer _serverPlayer) {
					_serverPlayer.connection.teleport(
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).portal_2_x),
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).portal_2_y),
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).portal_2_z),
							_ent.getYRot(), _ent.getXRot(), Collections.emptySet());
				}
			}
		}
	}
}
