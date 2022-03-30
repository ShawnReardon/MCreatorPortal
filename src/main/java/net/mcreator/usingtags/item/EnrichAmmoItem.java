
package net.mcreator.usingtags.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

public class EnrichAmmoItem extends Item {
	public EnrichAmmoItem() {
		super(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(64).rarity(Rarity.RARE));
		setRegistryName("enrich_ammo");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
