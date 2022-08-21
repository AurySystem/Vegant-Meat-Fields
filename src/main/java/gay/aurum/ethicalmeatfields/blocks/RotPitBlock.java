package gay.aurum.ethicalmeatfields.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class RotPitBlock extends VileStalksBlock{
	private static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 3.0, 14.0, 4.0, 15.0);
	public RotPitBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!world.isClient && world.getDifficulty() != Difficulty.PEACEFUL) {
			if (entity instanceof LivingEntity livingEntity) {
				livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 30, 2));
			}

		}
	}
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}
}
