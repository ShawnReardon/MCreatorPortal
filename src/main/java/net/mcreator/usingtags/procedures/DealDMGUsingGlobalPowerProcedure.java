package net.mcreator.usingtags.procedures;

import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.Util;

import net.mcreator.usingtags.network.UsingtagsModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class DealDMGUsingGlobalPowerProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			execute(event, entity.level, entity.getX(), entity.getY(), entity.getZ(), entity, event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 6, 6, 6), e -> true).isEmpty()) {
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private LevelAccessor world;

				public void start(LevelAccessor world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					entity.hurt(DamageSource.GENERIC, (float) (sourceentity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new UsingtagsModVariables.PlayerVariables())).globalPWR);
					if (entity instanceof LivingEntity _entity)
						_entity.hurt(new DamageSource("BAAAH GLOBAL PWR HURTS").bypassArmor(),
								(float) (sourceentity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new UsingtagsModVariables.PlayerVariables())).globalPWR);
					if (!world.isClientSide()) {
						MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
						if (mcserv != null)
							mcserv.getPlayerList().broadcastMessage(
									new TextComponent(
											("GLBL DMG DEALT: " + (sourceentity.getCapability(UsingtagsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
													.orElse(new UsingtagsModVariables.PlayerVariables())).globalPWR)),
									ChatType.SYSTEM, Util.NIL_UUID);
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, 5);
		}
	}
}
