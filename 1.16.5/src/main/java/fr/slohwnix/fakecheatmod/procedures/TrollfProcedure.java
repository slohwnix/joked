package fr.slohwnix.fakecheatmod.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

import fr.slohwnix.fakecheatmod.block.GlisseadBlock;
import fr.slohwnix.fakecheatmod.JokedMod;

public class TrollfProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				JokedMod.LOGGER.warn("Failed to load dependency world for procedure Trollf!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				JokedMod.LOGGER.warn("Failed to load dependency x for procedure Trollf!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				JokedMod.LOGGER.warn("Failed to load dependency y for procedure Trollf!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				JokedMod.LOGGER.warn("Failed to load dependency z for procedure Trollf!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		world.setBlockState(new BlockPos(x, y, z), GlisseadBlock.block.getDefaultState(), 3);
	}
}
