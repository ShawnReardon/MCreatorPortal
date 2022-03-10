package net.mcreator.usingtags.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.usingtags.network.UsingtagsModVariables;
import net.mcreator.usingtags.init.UsingtagsModEntities;
import net.mcreator.usingtags.entity.ZombieMobMobEntity;

import javax.annotation.Nullable;

import java.util.Comparator;

@Mod.EventBusSubscriber
public class WhenEquippedWithTagProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			execute(event, entity.level, entity.getX(), entity.getY(), entity.getZ(), entity);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean isDefenderPresent = false;
		if (!(entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UsingtagsModVariables.PlayerVariables())).isDefenderSpawned
				&& ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("usingtags:weapons"))
						.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem())) {
			{
				boolean _setval = true;
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isDefenderSpawned = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = new ZombieMobMobEntity(UsingtagsModEntities.ZOMBIE_MOB_MOB, _level);
				entityToSpawn.moveTo(x, y, z, 0, 0);
				entityToSpawn.setYBodyRot(0);
				entityToSpawn.setYHeadRot(0);
				entityToSpawn.setDeltaMovement(0, 0, 0);
				if (entityToSpawn instanceof Mob _mobToSpawn)
					_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null,
							null);
				world.addFreshEntity(entityToSpawn);
			}
		} else if ((entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new UsingtagsModVariables.PlayerVariables())).isDefenderSpawned
				&& !(ItemTags.getAllTags().getTagOrEmpty(new ResourceLocation("usingtags:weapons"))
						.contains((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()))
				&& ((Entity) world.getEntitiesOfClass(ZombieMobMobEntity.class, AABB.ofSize(new Vec3(x, y, z), 50, 50, 50), e -> true).stream()
						.sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof ZombieMobMobEntity) {
			if (!((Entity) world.getEntitiesOfClass(ZombieMobMobEntity.class, AABB.ofSize(new Vec3(x, y, z), 50, 50, 50), e -> true).stream()
					.sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)).level.isClientSide())
				((Entity) world.getEntitiesOfClass(ZombieMobMobEntity.class, AABB.ofSize(new Vec3(x, y, z), 50, 50, 50), e -> true).stream()
						.sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).discard();
			{
				boolean _setval = false;
				entity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isDefenderSpawned = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
