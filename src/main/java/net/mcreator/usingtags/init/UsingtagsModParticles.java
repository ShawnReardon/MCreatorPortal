
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.usingtags.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.Minecraft;

import net.mcreator.usingtags.client.particle.ShurikenParticle;
import net.mcreator.usingtags.client.particle.PowerParticleParticle;
import net.mcreator.usingtags.client.particle.PieDayParticleParticle;

import java.util.function.Function;
import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UsingtagsModParticles {
	private static final Map<ParticleType<?>, Function<SpriteSet, ParticleProvider<SimpleParticleType>>> REGISTRY = new HashMap<>();
	public static final SimpleParticleType POWER_PARTICLE = register(new SimpleParticleType(false).setRegistryName("power_particle"),
			PowerParticleParticle::provider);
	public static final SimpleParticleType SHURIKEN = register(new SimpleParticleType(false).setRegistryName("shuriken"), ShurikenParticle::provider);
	public static final SimpleParticleType PIE_DAY_PARTICLE = register(new SimpleParticleType(false).setRegistryName("pie_day_particle"),
			PieDayParticleParticle::provider);

	private static SimpleParticleType register(ParticleType<?> particle, Function<SpriteSet, ParticleProvider<SimpleParticleType>> provider) {
		REGISTRY.put(particle, provider);
		return (SimpleParticleType) particle;
	}

	@SubscribeEvent
	public static void registerParticleTypes(RegistryEvent.Register<ParticleType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.keySet().toArray(new ParticleType[0]));
	}

	@SubscribeEvent
	public static void registerParticles(ParticleFactoryRegisterEvent event) {
		REGISTRY.forEach((particle, provider) -> Minecraft.getInstance().particleEngine.register((SimpleParticleType) particle,
				spriteSet -> provider.apply(spriteSet)));
	}
}
