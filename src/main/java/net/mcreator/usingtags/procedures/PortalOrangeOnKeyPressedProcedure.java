package net.mcreator.usingtags.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.usingtags.network.UsingtagsModVariables;
import net.mcreator.usingtags.init.UsingtagsModItems;
import net.mcreator.usingtags.init.UsingtagsModBlocks;

public class PortalOrangeOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == UsingtagsModItems.PORTAL_PLACER) {
			if ((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UsingtagsModVariables.PlayerVariables())).bisPortal2) {
				world.setBlock(new BlockPos(
						(int) ((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).portal_2_x),
						(int) ((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).portal_2_y),
						(int) ((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new UsingtagsModVariables.PlayerVariables())).portal_2_z)),
						Blocks.AIR.defaultBlockState(), 3);
				{
					boolean _setval = false;
					entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.bothPortalsPlaced = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			world.setBlock(
					new BlockPos(
							(int) (entity.level.clip(new ClipContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f)
											.add(entity.getViewVector(1f).scale(UsingtagsModVariables.MapVariables.get(world).portalRange)),
									ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
							(int) (entity.level
									.clip(new ClipContext(entity.getEyePosition(1f),
											entity.getEyePosition(1f)
													.add(entity.getViewVector(1f).scale(UsingtagsModVariables.MapVariables.get(world).portalRange)),
											ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))
									.getBlockPos().getY()),
							(int) (entity.level.clip(new ClipContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f)
											.add(entity.getViewVector(1f).scale(UsingtagsModVariables.MapVariables.get(world).portalRange)),
									ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())),
					UsingtagsModBlocks.PORTAL_2.defaultBlockState(), 3);
			{
				double _setval = entity.level.clip(new ClipContext(entity.getEyePosition(1f),
						entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(UsingtagsModVariables.MapVariables.get(world).portalRange)),
						ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX();
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.portal_2_x = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity.level.clip(new ClipContext(entity.getEyePosition(1f),
						entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(UsingtagsModVariables.MapVariables.get(world).portalRange)),
						ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY();
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.portal_2_y = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = entity.level.clip(new ClipContext(entity.getEyePosition(1f),
						entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(UsingtagsModVariables.MapVariables.get(world).portalRange)),
						ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ();
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.portal_2_z = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				boolean _setval = true;
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.bisPortal2 = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if ((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new UsingtagsModVariables.PlayerVariables())).bisPortal1) {
				{
					boolean _setval = true;
					entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.bothPortalsPlaced = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
