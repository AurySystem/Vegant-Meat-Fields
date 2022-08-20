package gay.aurum.ethicalmeatfields.blocks;

import gay.aurum.ethicalmeatfields.MeatBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class InfestedFarmland extends FarmlandBlock {
	public InfestedFarmland(Settings settings) {
		super(settings);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		int i = state.get(MOISTURE);
		if (!isWaterNearby(world, pos) && !world.hasRain(pos.up())) {
			if (i > 0) {
				world.setBlockState(pos, state.with(MOISTURE, i - 1), 2);
			}
		} else if (i < 7) {
			world.setBlockState(pos, state.with(MOISTURE, 7), 2);
		}
		if (random.nextInt(7) < 1) {
			for (int r = 0; r < 2; ++r) {
				BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
				if (world.getBlockState(blockPos).isOf(Blocks.FARMLAND)) {
					world.setBlockState(blockPos, this.getDefaultState(), 2);
				}
			}
		}

	}

	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (!world.isClient
				&& world.random.nextFloat() < fallDistance - 0.5F
				&& entity instanceof LivingEntity
				&& (entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING))
				&& entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F) {
			setToDirt(state, world, pos);
		}

		entity.handleFallDamage(fallDistance, 0.8F, DamageSource.FALL);
	}
	public static void setToDirt(BlockState state, World world, BlockPos pos) {
		world.setBlockState(pos, pushEntitiesUpBeforeBlockChange(state, MeatBlocks.INFESTATION_GRASS.getDefaultState(), world, pos));
	}

	private static boolean hasCrop(BlockView world, BlockPos pos) {
		Block block = world.getBlockState(pos.up()).getBlock();
		return block instanceof CropBlock || block instanceof StemBlock || block instanceof AttachedStemBlock;
	}

	private static boolean isWaterNearby(WorldView world, BlockPos pos) {
		for(BlockPos blockPos : BlockPos.iterate(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
			if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
				return true;
			}
		}

		return false;
	}

}
