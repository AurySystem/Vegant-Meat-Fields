package gay.aurum.ethicalmeatfields.blocks;

import gay.aurum.ethicalmeatfields.MeatBlocks;
import gay.aurum.ethicalmeatfields.MeatFeatures;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.chunk.light.ChunkLightProvider;

public class InfestationGrassBlock extends Block  {
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

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		int age = state.get(this.getAgeProperty());
		if (perish(state, world, pos)) {
			world.setBlockState(pos, MeatBlocks.ROT_MEAT.getDefaultState());// turn into rotten infestation instead?
		} else if(age < MAX_AGE){
			if (random.nextInt(12) < 1) {
				world.setBlockState(pos, this.getDefaultState().with(this.getAgeProperty(), age+1), 2);
			}
			if(age == MAX_AGE-1){
				BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
				if(!world.getBlockState(blockPos).getBlock().equals(state.getBlock())){
					MeatFeatures.FLESH_PATCH.value().generate(world, world.getChunkManager().getChunkGenerator(), random, blockPos.up());
					world.setBlockState(pos, this.getDefaultState().with(this.getAgeProperty(), MAX_AGE), 2);
				}

			}
		}
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if(direction.getAxis() != Direction.UP.getAxis()){
			if(state.get(this.getAgeProperty()) == MAX_AGE && neighborState.isIn(BlockTags.DIRT)){
				return this.getDefaultState().with(this.getAgeProperty(), MAX_AGE-1);
			}
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}
}
