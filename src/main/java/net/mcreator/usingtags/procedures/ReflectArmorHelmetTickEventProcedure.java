package net.mcreator.usingtags.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;

import net.mcreator.usingtags.init.UsingtagsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ReflectArmorHelmetTickEventProcedure {
	@SubscribeEvent
	public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		Player sourceentity = event.getPlayer();
		if (event.getHand() != sourceentity.getUsedItemHand())
			return;
		execute(event, event.getTarget(), sourceentity);
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		boolean flip = false;
		for (Direction directioniterator : Direction.values()) {
			if ((sourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
					.getItem() == UsingtagsModItems.REFLECT_ARMOR_HELMET) {
				entity.setDeltaMovement(1, 1, 1);
			}
		}
	}
}
