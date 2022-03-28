package net.mcreator.usingtags.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.usingtags.init.UsingtagsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ReflectiveArmorExplosiveLeggingsProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			execute(event, entity.level, entity.getX(), entity.getY(), entity.getZ(), entity, event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
		execute(null, world, x, y, z, entity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
		if (entity == null)
			return;
		boolean found = false;
		double maxHP = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double amnt = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
				.getItem() == UsingtagsModItems.REFLECT_ARMOR_LEGGINGS) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
			for (int index0 = 0; index0 < (int) (6); index0++) {
				sx = -3;
				sy = -3;
				sz = -3;
				if (world instanceof Level _level && !_level.isClientSide())
					_level.explode(null, (x + sx), (y + sy), (z + sz), (float) amount, Explosion.BlockInteraction.BREAK);
				sz = sz + amount;
				sy = sy + amount;
				sx = sx + amount;
			}
		}
	}
}
