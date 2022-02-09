package net.mcreator.usingtags.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.mcreator.usingtags.network.UsingtagsModVariables;

import java.util.Collections;

public class Portal2EntityCollideProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UsingtagsModVariables.PlayerVariables())).bothPortalsPlaced) {
			{
				Entity _ent = entity;
				_ent.teleportTo(
						((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).portal_1_x + 3),
						((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).portal_1_y),
						((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).portal_1_z));
				if (_ent instanceof ServerPlayer _serverPlayer) {
					_serverPlayer.connection.teleport(
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).portal_1_x + 3),
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).portal_1_y),
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).portal_1_z),
							_ent.getYRot(), _ent.getXRot(), Collections.emptySet());
				}
			}
		}
	}
}
