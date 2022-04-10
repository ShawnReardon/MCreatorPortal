package net.mcreator.usingtags.procedures;

import net.minecraft.world.entity.Entity;

public class BlazeBushMobplayerCollidesWithPlantProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.setSecondsOnFire(15);
	}
}
