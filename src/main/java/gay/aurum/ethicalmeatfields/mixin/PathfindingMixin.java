package gay.aurum.ethicalmeatfields.mixin;

import gay.aurum.ethicalmeatfields.MeatBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LandPathNodeMaker.class)
public class PathfindingMixin {
	@Inject(method = "getCommonNodeType", at = @At("HEAD"), cancellable = true)
	private static void getCommonNodeType(BlockView world, BlockPos pos, CallbackInfoReturnable<PathNodeType> cir) {
		BlockState blockState = world.getBlockState(pos);
		if(blockState.isOf(MeatBlocks.VILE_STALKS) || blockState.isOf(MeatBlocks.ROT_PIT)){
			cir.setReturnValue(PathNodeType.DAMAGE_OTHER);
			cir.cancel();
		}
	}

	@Inject(method = "getNodeTypeFromNeighbors", at = @At("HEAD"), cancellable = true) // so very hacky but argg
	private static void getNodeTypeFromNeighbors(BlockView world, BlockPos.Mutable pos, PathNodeType nodeType, CallbackInfoReturnable<PathNodeType> cir) {
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();

		for(int l = -1; l <= 1; ++l) {
			for (int m = -1; m <= 1; ++m) {
				for (int n = -1; n <= 1; ++n) {
					if (l != 0 || n != 0) {
						pos.set(i + l, j + m, k + n);
						BlockState blockState = world.getBlockState(pos);
						if (blockState.isOf(MeatBlocks.VILE_STALKS) || blockState.isOf(MeatBlocks.ROT_PIT)) {
							cir.setReturnValue(PathNodeType.DAMAGE_OTHER);
							cir.cancel();
						}
					}
				}
			}
		}
	}
}
