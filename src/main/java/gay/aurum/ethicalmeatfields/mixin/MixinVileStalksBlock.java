package gay.aurum.ethicalmeatfields.mixin;

import gay.aurum.ethicalmeatfields.EthicalMeatFields;
import gay.aurum.ethicalmeatfields.blocks.VileStalksBlock;
import me.jellysquid.mods.lithium.api.pathing.BlockPathingBehavior;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.PathNodeType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VileStalksBlock.class)
public class MixinVileStalksBlock implements BlockPathingBehavior {

	@Override
	public PathNodeType getPathNodeType(BlockState blockState) {
		return PathNodeType.DAMAGE_OTHER;
	}

	@Override
	public PathNodeType getPathNodeTypeAsNeighbor(BlockState blockState) {
		return PathNodeType.DAMAGE_OTHER;
	}
}
