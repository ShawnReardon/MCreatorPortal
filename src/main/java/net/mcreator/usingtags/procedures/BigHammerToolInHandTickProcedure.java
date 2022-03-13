package net.mcreator.usingtags.procedures;

import net.minecraft.world.entity.Entity;

public class BigHammerToolInHandTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.fallDistance = 0;
	}
}
