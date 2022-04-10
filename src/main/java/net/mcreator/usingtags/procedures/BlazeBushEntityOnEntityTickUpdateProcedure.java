package net.mcreator.usingtags.procedures;

import net.minecraft.world.entity.Entity;

public class BlazeBushEntityOnEntityTickUpdateProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.setDeltaMovement(0, 0, 0);
	}
}
