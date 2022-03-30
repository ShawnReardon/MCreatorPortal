package net.mcreator.usingtags.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.world.BlockEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.usingtags.init.UsingtagsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BlockBreakEnrichAmmoProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		execute(null, world, x, y, z, blockstate);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double i = 0;
		i = 0;
		if (BlockTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:ores_tag")).contains(blockstate.getBlock())) {
			if (Math.random() < 0.7) {
				for (int index0 = 0; index0 < (int) (4); index0++) {
					if (world instanceof Level _level && !_level.isClientSide()) {
						ItemEntity entityToSpawn = new ItemEntity(_level, (x + i), y, z, new ItemStack(UsingtagsModItems.ENRICH_AMMO));
						entityToSpawn.setPickUpDelay(10);
						_level.addFreshEntity(entityToSpawn);
					}
					i = i + 1;
				}
			}
		}
	}
}
