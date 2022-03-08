
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.usingtags.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.usingtags.block.entity.Stage1BlockEntity;
import net.mcreator.usingtags.block.entity.Stage0BlockEntity;
import net.mcreator.usingtags.block.entity.Portal2BlockEntity;
import net.mcreator.usingtags.block.entity.Portal1BlockEntity;
import net.mcreator.usingtags.block.entity.PlaceAndCheckBlockEntity;
import net.mcreator.usingtags.block.entity.ItsATrapBlockEntity;
import net.mcreator.usingtags.block.entity.BreakingBlockBlockEntity;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UsingtagsModBlockEntities {
	private static final List<BlockEntityType<?>> REGISTRY = new ArrayList<>();
	public static final BlockEntityType<?> PLACE_AND_CHECK = register("usingtags:place_and_check", UsingtagsModBlocks.PLACE_AND_CHECK,
			PlaceAndCheckBlockEntity::new);
	public static final BlockEntityType<?> BREAKING_BLOCK = register("usingtags:breaking_block", UsingtagsModBlocks.BREAKING_BLOCK,
			BreakingBlockBlockEntity::new);
	public static final BlockEntityType<?> ITS_A_TRAP = register("usingtags:its_a_trap", UsingtagsModBlocks.ITS_A_TRAP, ItsATrapBlockEntity::new);
	public static final BlockEntityType<?> STAGE_0 = register("usingtags:stage_0", UsingtagsModBlocks.STAGE_0, Stage0BlockEntity::new);
	public static final BlockEntityType<?> STAGE_1 = register("usingtags:stage_1", UsingtagsModBlocks.STAGE_1, Stage1BlockEntity::new);
	public static final BlockEntityType<?> PORTAL_1 = register("usingtags:portal_1", UsingtagsModBlocks.PORTAL_1, Portal1BlockEntity::new);
	public static final BlockEntityType<?> PORTAL_2 = register("usingtags:portal_2", UsingtagsModBlocks.PORTAL_2, Portal2BlockEntity::new);

	private static BlockEntityType<?> register(String registryname, Block block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		BlockEntityType<?> blockEntityType = BlockEntityType.Builder.of(supplier, block).build(null).setRegistryName(registryname);
		REGISTRY.add(blockEntityType);
		return blockEntityType;
	}

	@SubscribeEvent
	public static void registerTileEntity(RegistryEvent.Register<BlockEntityType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new BlockEntityType[0]));
	}
}
