
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.usingtags.init;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.usingtags.entity.ZombieMobMobEntity;
import net.mcreator.usingtags.entity.LightiningStormEntity;
import net.mcreator.usingtags.entity.IMaMobEntity;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UsingtagsModEntities {
	private static final List<EntityType<?>> REGISTRY = new ArrayList<>();
	public static final EntityType<IMaMobEntity> I_MA_MOB = register("i_ma_mob",
			EntityType.Builder.<IMaMobEntity>of(IMaMobEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(3).setCustomClientFactory(IMaMobEntity::new).sized(0.4f, 0.3f));
	public static final EntityType<ZombieMobMobEntity> ZOMBIE_MOB_MOB = register("zombie_mob_mob",
			EntityType.Builder.<ZombieMobMobEntity>of(ZombieMobMobEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ZombieMobMobEntity::new).sized(0.6f, 1.8f));
	public static final EntityType<LightiningStormEntity> LIGHTINING_STORM = register("entitybulletlightining_storm",
			EntityType.Builder.<LightiningStormEntity>of(LightiningStormEntity::new, MobCategory.MISC)
					.setCustomClientFactory(LightiningStormEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));

	private static <T extends Entity> EntityType<T> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		EntityType<T> entityType = (EntityType<T>) entityTypeBuilder.build(registryname).setRegistryName(registryname);
		REGISTRY.add(entityType);
		return entityType;
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new EntityType[0]));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			IMaMobEntity.init();
			ZombieMobMobEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(I_MA_MOB, IMaMobEntity.createAttributes().build());
		event.put(ZOMBIE_MOB_MOB, ZombieMobMobEntity.createAttributes().build());
	}
}
