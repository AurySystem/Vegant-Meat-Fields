package gay.aurum.ethicalmeatfields.blocks;

import gay.aurum.ethicalmeatfields.MeatBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class VileStalksBlock extends PlantBlock {
	public VileStalksBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return floor.isOf(MeatBlocks.INFESTATION_GRASS) || floor.isOf(MeatBlocks.TILLED_INFESTATON) || super.canPlantOnTop(floor, world, pos);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.damage(DamageSource.CACTUS, 1.0F);
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return true;
	}
}
