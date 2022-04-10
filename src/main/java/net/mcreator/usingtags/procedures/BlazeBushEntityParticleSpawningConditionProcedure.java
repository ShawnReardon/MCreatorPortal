package net.mcreator.usingtags.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class BlazeBushEntityParticleSpawningConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		if (!((null) == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null))) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 7, 0, 3, 0, 0.5);
		}
		return !((null) == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null));
	}
}
