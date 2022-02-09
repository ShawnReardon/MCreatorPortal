package net.mcreator.usingtags.procedures;

import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.Util;

public class PowerUpSwordBasicProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (!entity.isAlive()) {
			itemstack.getOrCreateTag().putDouble("dmg", (itemstack.getOrCreateTag().getDouble("dmg") + 1));
			if (!world.isClientSide()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().broadcastMessage(new TextComponent("UPGRADED"), ChatType.SYSTEM, Util.NIL_UUID);
			}
		}
		entity.hurt(DamageSource.GENERIC, (float) (1 + itemstack.getOrCreateTag().getDouble("dmg") * 1));
		if (!world.isClientSide()) {
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().broadcastMessage(new TextComponent(("DMG: " + (1 + itemstack.getOrCreateTag().getDouble("dmg") * 1))),
						ChatType.SYSTEM, Util.NIL_UUID);
		}
	}
}
