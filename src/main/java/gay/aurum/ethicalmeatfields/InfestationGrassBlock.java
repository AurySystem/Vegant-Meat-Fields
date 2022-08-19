package gay.aurum.ethicalmeatfields;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

public class InfestationGrassBlock extends Block implements Fertilizable {
	public static final int MAX_AGE = 4;
	public static final IntProperty AGE = Properties.AGE_4;


	public InfestationGrassBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
	}

	public IntProperty getAgeProperty() {
		return AGE;
	}

	private static boolean perish(BlockState state, ServerWorld world, BlockPos pos) {
		BlockPos blockPos = pos.up();
		BlockState blockState = world.getBlockState(blockPos);
		if (blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) <= 5) {
			world.removeBlock(pos,false);
		}
		if (blockState.isAir()) {
			return false;
		} else {
			int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
			return i >= world.getMaxLightLevel();
		}
	}

	private static boolean canSpread(BlockState state, ServerWorld world, BlockPos pos) {
		BlockPos blockPos = pos.up();
		return !perish(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		int age = state.get(this.getAgeProperty());
		if (perish(state, world, pos)) {
			world.setBlockState(pos, Blocks.DIRT.getDefaultState());// turn into rotten infestation instead?
		} else if(age < MAX_AGE){
			if (random.nextInt(12) < 1) {
				world.setBlockState(pos, this.getDefaultState().with(this.getAgeProperty(), age+1), 2);
			}
			if(age == MAX_AGE-1){
				BlockState blockState = this.getDefaultState();

				for(int i = 0; i < 2; ++i) {
					BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					if (world.getBlockState(blockPos).isOf(Blocks.DIRT)||world.getBlockState(blockPos).isOf(Blocks.GRASS_BLOCK) && canSpread(blockState, world, blockPos)) { //actually just try to spawn a feature later
						world.setBlockState(blockPos, blockState.with(this.getAgeProperty(), 0));
					}
				}
			}
		}
	}

	@Override
	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
		return false;
	}

	@Override
	public boolean canGrow(World world, RandomGenerator random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld world, RandomGenerator random, BlockPos pos, BlockState state) {
		//use a feature to summon it in like moss
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}
}
