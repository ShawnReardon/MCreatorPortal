package net.mcreator.usingtags.procedures;

import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.core.BlockPos;
import net.minecraft.Util;

import net.mcreator.usingtags.init.UsingtagsModBlocks;

public class Stage0UpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double randomChance = 0;
		if (!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.FARMLAND)) {
			if (!world.isClientSide()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().broadcastMessage(new TextComponent("Loop Stuck"), ChatType.SYSTEM, Util.NIL_UUID);
			}
			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0
					|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0) {
				Stage0BlockDestroyedByExplosionProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0) {
				Stage0BlockDestroyedByExplosionProcedure.execute(world, x, y, z);
			}
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);
		} else if ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.SAND
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.GRAVEL
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.RED_SAND
				|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.LIGHT_GRAY_WOOL) {
			world.setBlock(new BlockPos((int) x, (int) (y + 1), (int) z), Blocks.AIR.defaultBlockState(), 3);
		} else if (!world.isClientSide() && world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).getLightEmission(world,
				new BlockPos((int) x, (int) (y + 1), (int) z)) >= 7) {
			if (!world.isClientSide()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().broadcastMessage(new TextComponent(("LIGHT LEVEL W/ TICK AT : " + (new Object() {
						public double getValue(LevelAccessor world, BlockPos pos, String tag) {
							BlockEntity blockEntity = world.getBlockEntity(pos);
							if (blockEntity != null)
								return blockEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "growthTimer")))), ChatType.SYSTEM, Util.NIL_UUID);
			}
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
				randomChance = Math.random();
				if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == UsingtagsModBlocks.STAGE_0) {
					if (!world.isClientSide()) {
						MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
						if (mcserv != null)
							mcserv.getPlayerList().broadcastMessage(new TextComponent("CHANCE TO GROW"), ChatType.SYSTEM, Util.NIL_UUID);
					}
					if (randomChance >= 0.66) {
						if (!world.isClientSide()) {
							MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
							if (mcserv != null)
								mcserv.getPlayerList().broadcastMessage(new TextComponent("I GREW TO STAGE1"), ChatType.SYSTEM, Util.NIL_UUID);
						}
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
				}
			}
		}
		if (!world.isClientSide()) {
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().broadcastMessage(new TextComponent("broke"), ChatType.SYSTEM, Util.NIL_UUID);
		}
	}
}
