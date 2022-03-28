
package net.mcreator.usingtags.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

public class ReflectorShieldItem extends Item {
	public ReflectorShieldItem() {
		super(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(64).rarity(Rarity.COMMON));
		setRegistryName("reflector_shield");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
