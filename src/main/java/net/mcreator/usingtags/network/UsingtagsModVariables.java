package net.mcreator.usingtags.network;

import net.minecraftforge.fmllegacy.network.PacketDistributor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.usingtags.UsingtagsMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UsingtagsModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		UsingtagsMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new,
				SavedDataSyncMessage::handler);
		UsingtagsMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getPlayer().level.isClientSide())
				((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
						.syncPlayerVariables(event.getPlayer());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new PlayerVariables()));
			clone.portal_1_x = original.portal_1_x;
			clone.portal_1_y = original.portal_1_y;
			clone.portal_1_z = original.portal_1_z;
			clone.portal_2_x = original.portal_2_x;
			clone.portal_2_y = original.portal_2_y;
			clone.portal_2_z = original.portal_2_z;
			clone.bothPortalsPlaced = original.bothPortalsPlaced;
			clone.bisPortal1 = original.bisPortal1;
			clone.bisPortal2 = original.bisPortal2;
			clone.cam1_x = original.cam1_x;
			clone.cam1_y = original.cam1_y;
			clone.cam1_z = original.cam1_z;
			clone.bisBaseCam = original.bisBaseCam;
			clone.camop_x = original.camop_x;
			clone.camop_y = original.camop_y;
			clone.camop_z = original.camop_z;
			clone.portalDebounce = original.portalDebounce;
			clone.glb_portal_clock = original.glb_portal_clock;
			if (!event.isWasDeath()) {
				clone.globalPWR = original.globalPWR;
			}
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getPlayer().level.isClientSide()) {
				SavedData mapdata = MapVariables.get(event.getPlayer().level);
				SavedData worlddata = WorldVariables.get(event.getPlayer().level);
				if (mapdata != null)
					UsingtagsMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()),
							new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					UsingtagsMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()),
							new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getPlayer().level.isClientSide()) {
				SavedData worlddata = WorldVariables.get(event.getPlayer().level);
				if (worlddata != null)
					UsingtagsMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()),
							new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "usingtags_worldvars";

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide())
				UsingtagsMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "usingtags_mapvars";
		public double portalRange = 500.0;

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			portalRange = nbt.getDouble("portalRange");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putDouble("portalRange", portalRange);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				UsingtagsMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e),
						MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		public int type;
		public SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			if (this.data instanceof MapVariables _mapvars)
				_mapvars.read(buffer.readNbt());
			else if (this.data instanceof WorldVariables _worldvars)
				_worldvars.read(buffer.readNbt());
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("usingtags", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public double globalPWR = 0;
		public double portal_1_x = 0;
		public double portal_1_y = 0;
		public double portal_1_z = 0;
		public double portal_2_x = 0;
		public double portal_2_y = 0;
		public double portal_2_z = 0;
		public boolean bothPortalsPlaced = false;
		public boolean bisPortal1 = false;
		public boolean bisPortal2 = false;
		public double cam1_x = 0;
		public double cam1_y = 0;
		public double cam1_z = 0;
		public boolean bisBaseCam = false;
		public double camop_x = 0;
		public double camop_y = 0;
		public double camop_z = 0;
		public boolean portalDebounce = false;
		public double glb_portal_clock = 0;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				UsingtagsMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("globalPWR", globalPWR);
			nbt.putDouble("portal_1_x", portal_1_x);
			nbt.putDouble("portal_1_y", portal_1_y);
			nbt.putDouble("portal_1_z", portal_1_z);
			nbt.putDouble("portal_2_x", portal_2_x);
			nbt.putDouble("portal_2_y", portal_2_y);
			nbt.putDouble("portal_2_z", portal_2_z);
			nbt.putBoolean("bothPortalsPlaced", bothPortalsPlaced);
			nbt.putBoolean("bisPortal1", bisPortal1);
			nbt.putBoolean("bisPortal2", bisPortal2);
			nbt.putDouble("cam1_x", cam1_x);
			nbt.putDouble("cam1_y", cam1_y);
			nbt.putDouble("cam1_z", cam1_z);
			nbt.putBoolean("bisBaseCam", bisBaseCam);
			nbt.putDouble("camop_x", camop_x);
			nbt.putDouble("camop_y", camop_y);
			nbt.putDouble("camop_z", camop_z);
			nbt.putBoolean("portalDebounce", portalDebounce);
			nbt.putDouble("glb_portal_clock", glb_portal_clock);
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			globalPWR = nbt.getDouble("globalPWR");
			portal_1_x = nbt.getDouble("portal_1_x");
			portal_1_y = nbt.getDouble("portal_1_y");
			portal_1_z = nbt.getDouble("portal_1_z");
			portal_2_x = nbt.getDouble("portal_2_x");
			portal_2_y = nbt.getDouble("portal_2_y");
			portal_2_z = nbt.getDouble("portal_2_z");
			bothPortalsPlaced = nbt.getBoolean("bothPortalsPlaced");
			bisPortal1 = nbt.getBoolean("bisPortal1");
			bisPortal2 = nbt.getBoolean("bisPortal2");
			cam1_x = nbt.getDouble("cam1_x");
			cam1_y = nbt.getDouble("cam1_y");
			cam1_z = nbt.getDouble("cam1_z");
			bisBaseCam = nbt.getBoolean("bisBaseCam");
			camop_x = nbt.getDouble("camop_x");
			camop_y = nbt.getDouble("camop_y");
			camop_z = nbt.getDouble("camop_z");
			portalDebounce = nbt.getBoolean("portalDebounce");
			glb_portal_clock = nbt.getDouble("glb_portal_clock");
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.globalPWR = message.data.globalPWR;
					variables.portal_1_x = message.data.portal_1_x;
					variables.portal_1_y = message.data.portal_1_y;
					variables.portal_1_z = message.data.portal_1_z;
					variables.portal_2_x = message.data.portal_2_x;
					variables.portal_2_y = message.data.portal_2_y;
					variables.portal_2_z = message.data.portal_2_z;
					variables.bothPortalsPlaced = message.data.bothPortalsPlaced;
					variables.bisPortal1 = message.data.bisPortal1;
					variables.bisPortal2 = message.data.bisPortal2;
					variables.cam1_x = message.data.cam1_x;
					variables.cam1_y = message.data.cam1_y;
					variables.cam1_z = message.data.cam1_z;
					variables.bisBaseCam = message.data.bisBaseCam;
					variables.camop_x = message.data.camop_x;
					variables.camop_y = message.data.camop_y;
					variables.camop_z = message.data.camop_z;
					variables.portalDebounce = message.data.portalDebounce;
					variables.glb_portal_clock = message.data.glb_portal_clock;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
