package net.mcreator.usingtags.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class RainbowPartyBlockIsPlacedByProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.SMALL_FLAME, x, y, z, 5, 3, 3, 3, 1);
		if (world instanceof Level _level && !_level.isClientSide())
			_level.explode(null, x, y, z, 10, Explosion.BlockInteraction.BREAK);
	}
}
