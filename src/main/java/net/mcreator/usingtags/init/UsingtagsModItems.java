
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.usingtags.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.mcreator.usingtags.item.PowerUpSwordItem;
import net.mcreator.usingtags.item.PortalPlacerItem;
import net.mcreator.usingtags.item.PocketBridgeItem;
import net.mcreator.usingtags.item.CropSeedsItem;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UsingtagsModItems {
	private static final List<Item> REGISTRY = new ArrayList<>();
	public static final Item PLACE_AND_CHECK = register(UsingtagsModBlocks.PLACE_AND_CHECK, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final Item BREAKING_BLOCK = register(UsingtagsModBlocks.BREAKING_BLOCK, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final Item POCKET_BRIDGE = register(new PocketBridgeItem());
	public static final Item POWER_UP_SWORD = register(new PowerUpSwordItem());
	public static final Item ITS_A_TRAP = register(UsingtagsModBlocks.ITS_A_TRAP, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final Item I_MA_MOB = register(
			new SpawnEggItem(UsingtagsModEntities.I_MA_MOB, -1, -1, new Item.Properties().tab(CreativeModeTab.TAB_MISC))
					.setRegistryName("i_ma_mob_spawn_egg"));
	public static final Item CROP_SEEDS = register(new CropSeedsItem());
	public static final Item STAGE_0 = register(UsingtagsModBlocks.STAGE_0, null);
	public static final Item STAGE_1 = register(UsingtagsModBlocks.STAGE_1, null);
	public static final Item PORTAL_1 = register(UsingtagsModBlocks.PORTAL_1, null);
	public static final Item PORTAL_PLACER = register(new PortalPlacerItem());
	public static final Item PORTAL_2 = register(UsingtagsModBlocks.PORTAL_2, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final Item CAMERA_BLOCK = register(UsingtagsModBlocks.CAMERA_BLOCK, CreativeModeTab.TAB_BUILDING_BLOCKS);

	private static Item register(Item item) {
		REGISTRY.add(item);
		return item;
	}

	private static Item register(Block block, CreativeModeTab tab) {
		return register(new BlockItem(block, new Item.Properties().tab(tab)).setRegistryName(block.getRegistryName()));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new Item[0]));
	}
}
