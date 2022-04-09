package net.mcreator.usingtags.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.usingtags.init.UsingtagsModEnchantments;
import net.mcreator.usingtags.init.UsingtagsModBlocks;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class SupriseIngotProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			execute(event, entity.level, entity.getX(), entity.getY(), entity.getZ(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		execute(null, world, x, y, z, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if (EnchantmentHelper.getItemEnchantmentLevel(UsingtagsModEnchantments.INGOT_SUPRISE,
				(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) {
			world.setBlock(new BlockPos((int) x, (int) y, (int) z), UsingtagsModBlocks.SUPRISE_LOOT_BLOCK.defaultBlockState(), 3);
		}
	}
}
