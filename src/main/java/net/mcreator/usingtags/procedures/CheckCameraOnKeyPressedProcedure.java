package net.mcreator.usingtags.procedures;

import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import net.mcreator.usingtags.network.UsingtagsModVariables;

import java.util.Collections;

public class CheckCameraOnKeyPressedProcedure {
	public static void execute(double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
				} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance()
							.getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR;
				}
				return false;
			}
		}.checkGamemode(entity)) && (entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UsingtagsModVariables.PlayerVariables())).bisBaseCam) {
			{
				double _setval = x;
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.camop_x = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = y;
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.camop_y = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = z;
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.camop_z = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof ServerPlayer _player)
				_player.setGameMode(GameType.SPECTATOR);
			{
				Entity _ent = entity;
				_ent.teleportTo(
						((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).cam1_x),
						((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).cam1_y),
						((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).cam1_z));
				if (_ent instanceof ServerPlayer _serverPlayer) {
					_serverPlayer.connection.teleport(
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).cam1_x),
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).cam1_y),
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).cam1_z),
							_ent.getYRot(), _ent.getXRot(), Collections.emptySet());
				}
			}
		} else {
			if (new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayer _serverPlayer) {
						return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
					} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
						return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft
								.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR;
					}
					return false;
				}
			}.checkGamemode(entity) && (entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UsingtagsModVariables.PlayerVariables())).bisBaseCam) {
				{
					Entity _ent = entity;
					_ent.teleportTo(
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).camop_x),
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).camop_y),
							((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new UsingtagsModVariables.PlayerVariables())).camop_z));
					if (_ent instanceof ServerPlayer _serverPlayer) {
						_serverPlayer.connection.teleport(
								((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new UsingtagsModVariables.PlayerVariables())).camop_x),
								((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new UsingtagsModVariables.PlayerVariables())).camop_y),
								((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new UsingtagsModVariables.PlayerVariables())).camop_z),
								_ent.getYRot(), _ent.getXRot(), Collections.emptySet());
					}
				}
				if (entity instanceof ServerPlayer _player)
					_player.setGameMode(GameType.SURVIVAL);
			}
		}
	}
}
