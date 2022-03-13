
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.usingtags.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import net.mcreator.usingtags.block.Stage1Block;
import net.mcreator.usingtags.block.Stage0Block;
import net.mcreator.usingtags.block.RainbowPartyBlock;
import net.mcreator.usingtags.block.Portal2Block;
import net.mcreator.usingtags.block.Portal1Block;
import net.mcreator.usingtags.block.PlaceAndCheckBlock;
import net.mcreator.usingtags.block.ItsATrapBlock;
import net.mcreator.usingtags.block.CameraBlockBlock;
import net.mcreator.usingtags.block.BreakingBlockBlock;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UsingtagsModBlocks {
	private static final List<Block> REGISTRY = new ArrayList<>();
	public static final Block PLACE_AND_CHECK = register(new PlaceAndCheckBlock());
	public static final Block BREAKING_BLOCK = register(new BreakingBlockBlock());
	public static final Block ITS_A_TRAP = register(new ItsATrapBlock());
	public static final Block STAGE_0 = register(new Stage0Block());
	public static final Block STAGE_1 = register(new Stage1Block());
	public static final Block PORTAL_1 = register(new Portal1Block());
	public static final Block PORTAL_2 = register(new Portal2Block());
	public static final Block CAMERA_BLOCK = register(new CameraBlockBlock());
	public static final Block RAINBOW_PARTY = register(new RainbowPartyBlock());

	private static Block register(Block block) {
		REGISTRY.add(block);
		return block;
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new Block[0]));
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ItsATrapBlock.registerRenderLayer();
			Stage0Block.registerRenderLayer();
			Stage1Block.registerRenderLayer();
			Portal1Block.registerRenderLayer();
			Portal2Block.registerRenderLayer();
			CameraBlockBlock.registerRenderLayer();
		}
	}
}
