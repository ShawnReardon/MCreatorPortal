package net.mcreator.usingtags.procedures;

import net.minecraft.world.item.ItemStack;

public class PowerUpSwordMakeItemGlowProcedure {
	public static boolean execute(ItemStack itemstack) {
		return itemstack.getOrCreateTag().getDouble("dmg") > 5;
	}
}
