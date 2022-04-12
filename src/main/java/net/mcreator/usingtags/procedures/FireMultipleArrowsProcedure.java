package net.mcreator.usingtags.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class FireMultipleArrowsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands()
					.performCommand(
							new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
									_level.getServer(), null).withSuppressedOutput(),
							"summon minecraft:wither_skull ~ ~1 ~ {direction:[-0.5,0.1,-1.0],power:[-0.5,0.1,-1.0]}");
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, new Vec3((x + 1), y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
							_level.getServer(), null).withSuppressedOutput(),
					"summon minecraft:wither_skull ~ ~1 ~ {direction:[-0.5,0.1,-1.0],power:[-0.5,0.1,-1.0]}");
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, (z + 1)), Vec2.ZERO, _level, 4, "", new TextComponent(""),
							_level.getServer(), null).withSuppressedOutput(),
					"summon minecraft:wither_skull ~ ~1 ~ {direction:[-0.5,0.1,-1.0],power:[-0.5,0.1,-1.0]}");
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performCommand(
					new CommandSourceStack(CommandSource.NULL, new Vec3(x, (y + 1), z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
							_level.getServer(), null).withSuppressedOutput(),
					"summon minecraft:wither_skull ~ ~1 ~ {direction:[-0.5,0.1,-1.0],power:[-0.5,0.1,-1.0]}");
	}
}
