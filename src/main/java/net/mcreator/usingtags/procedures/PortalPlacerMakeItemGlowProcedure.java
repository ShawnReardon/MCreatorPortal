package net.mcreator.usingtags.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.usingtags.network.UsingtagsModVariables;

public class PortalPlacerMakeItemGlowProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UsingtagsModVariables.PlayerVariables())).bothPortalsPlaced;
	}
}
