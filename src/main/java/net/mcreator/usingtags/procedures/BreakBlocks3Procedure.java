package net.mcreator.usingtags.procedures;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class BreakBlocks3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double sz = 0;
		double count = 0;
		sz = entity.level.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)),
				ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ();
		count = 0;
		for (int index0 = 0; index0 < (int) (3); index0++) {
			count = 1 + count;
			if (world instanceof Level) {
				Block.dropResources(world.getBlockState(new BlockPos(
						(int) (entity.level
								.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)),
										ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
								.getBlockPos().getX() + count),
						(int) (y - 1), (int) z)), (Level) world, new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos(
						(int) (entity.level
								.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)),
										ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
								.getBlockPos().getX() + count),
						(int) (y - 1), (int) z), false);
			}
		}
		count = 0;
		for (int index1 = 0; index1 < (int) (3); index1++) {
			count = 1 + count;
			if (world instanceof Level) {
				Block.dropResources(world.getBlockState(new BlockPos(
						(int) (entity.level
								.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)),
										ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
								.getBlockPos().getX() - count),
						(int) (y - 1), (int) z)), (Level) world, new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos(
						(int) (entity.level
								.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(5)),
										ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
								.getBlockPos().getX() - count),
						(int) (y - 1), (int) z), false);
			}
		}
		count = sz + 1;
	}
}
