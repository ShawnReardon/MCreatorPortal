package net.mcreator.usingtags.procedures;

import net.minecraft.world.level.LevelAccessor;

public class BigHammerMakeItemGlowProcedure {
	public static boolean execute(LevelAccessor world) {
		return world.getLevelData().isRaining();
	}
}
