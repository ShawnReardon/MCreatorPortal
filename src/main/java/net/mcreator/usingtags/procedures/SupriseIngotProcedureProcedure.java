package net.mcreator.usingtags.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.usingtags.init.UsingtagsModEnchantments;
import net.mcreator.usingtags.init.UsingtagsModBlocks;

public class SupriseIngotProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if (EnchantmentHelper.getItemEnchantmentLevel(UsingtagsModEnchantments.INGOT_SUPRISE,
				(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) {
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.SUPRISE_LOOT_BLOCK.defaultBlockState(), 3);
		}
	}
}
