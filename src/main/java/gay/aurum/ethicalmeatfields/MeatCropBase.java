package gay.aurum.ethicalmeatfields;

import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;


public class MeatCropBase extends CropBlock {
	public MeatCropBase(Settings settings) {
		super(settings);
	}

	protected ItemConvertible getSeedsItem() {
		return EthicalMeatFields.MEAT_SPORES;
	}
	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return floor.isOf(Blocks.FARMLAND) || floor.isOf(EthicalMeatFields.TILLED_INFESTATON);
	}
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		if (world.getBaseLightLevel(pos, 0) >= 9) {
			int i = this.getAge(state);
			if (i < this.getMaxAge()) {
				float f = getAvailableMoisture(this, world, pos);
				if (random.nextInt((int)(25.0F / f) + 1) == 0) {
					world.setBlockState(pos, this.withAge(i + 1), 2);
					if(random.nextInt(12) < 1 && world.getBlockState(pos.down()).isOf(Blocks.FARMLAND)){
						world.setBlockState(pos.down(), EthicalMeatFields.TILLED_INFESTATON.getDefaultState(), 2);
					}
				}
			}
		}

	}
	protected static float getAvailableMoisture(Block block, BlockView world, BlockPos pos) {
		float fin = 1.0F;
		BlockPos blockPos = pos.down();

		for(int x = -1; x <= 1; ++x) {
			for(int z = -1; z <= 1; ++z) {
				float water = 0.0F;
				BlockState blockState = world.getBlockState(blockPos.add(x, 0, z));
				if (blockState.isOf(Blocks.FARMLAND) || blockState.isOf(EthicalMeatFields.TILLED_INFESTATON)) {
					water = 1.0F;
					if (blockState.get(FarmlandBlock.MOISTURE) > 0) {
						water = 3.0F;
						if(blockState.isOf(EthicalMeatFields.TILLED_INFESTATON)) water = 4.0F;
					}
				}

				if (x != 0 || z != 0) {
					water /= 4.0F;
				}

				fin += water;
			}
		}

		BlockPos blockPos2 = pos.north();
		BlockPos blockPos3 = pos.south();
		BlockPos blockPos4 = pos.west();
		BlockPos blockPos5 = pos.east();
		boolean bl = world.getBlockState(blockPos4).isOf(block) || world.getBlockState(blockPos5).isOf(block);
		boolean bl2 = world.getBlockState(blockPos2).isOf(block) || world.getBlockState(blockPos3).isOf(block);
		if (bl || bl2) {
			fin /= 2.0F;
		}

		return fin;
	}
	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return (world.getBlockState(pos.down()).isOf(EthicalMeatFields.TILLED_INFESTATON) || world.getBlockState(pos.down()).isOf(EthicalMeatFields.INFESTATION_GRASS) || super.canPlaceAt(state, world, pos));
	}

}
