package net.mcreator.usingtags.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.mcreator.usingtags.init.UsingtagsModBlocks;

public class BorrowedUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double randomGrowth = 0;
		if (!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.FARMLAND)) {
			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_1
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK) {
				Stage0UpdateTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK) {
				Stage0UpdateTickProcedure.execute(world, x, y, z);
			}
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
		} else if ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.SAND
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.RED_SAND
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.GRAVEL
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.ANVIL
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.CHIPPED_ANVIL
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.DAMAGED_ANVIL
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.WHITE_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.ORANGE_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.MAGENTA_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.LIGHT_BLUE_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.YELLOW_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.LIME_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.PINK_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.GRAY_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.LIGHT_GRAY_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.CYAN_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.PURPLE_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.BLUE_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.BROWN_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.GREEN_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.RED_CONCRETE_POWDER
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.BLACK_CONCRETE_POWDER) {
			if (world instanceof Level) {
				Block.dropResources(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), (Level) world,
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) (y + 1), (int) z), false);
			}
		} else if (!world.isClientSide() && world.getMaxLocalRawBrightness(new BlockPos((int) x, (int) (y + 1), (int) z)) > 7) {
			if (!world.isClientSide()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getTileData().putDouble("growthTimer", (new Object() {
						public double getValue(LevelAccessor world, BlockPos pos, String tag) {
							BlockEntity blockEntity = world.getBlockEntity(pos);
							if (blockEntity != null)
								return blockEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "growthTimer") + 0.05));
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (new Object() {
				public double getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "growthTimer") >= 20) {
				randomGrowth = Math.random();
				if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.STAGE_1.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_1) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK) {
					if (randomGrowth >= 0.66) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					} else if (randomGrowth >= 0.33) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.PLACE_AND_CHECK) {
					if (randomGrowth >= 0.5) {
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
						world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.PLACE_AND_CHECK.defaultBlockState(), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getTileData().putDouble("growthTimer", 0);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
		}
	}
}
