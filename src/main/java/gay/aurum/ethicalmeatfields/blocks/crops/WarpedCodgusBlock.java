package gay.aurum.ethicalmeatfields.blocks.crops;

import gay.aurum.ethicalmeatfields.MeatBlocks;
import gay.aurum.ethicalmeatfields.blocks.MeatCropBase;
import net.minecraft.item.ItemConvertible;

public class WarpedCodgusBlock extends MeatCropBase {
	public WarpedCodgusBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected ItemConvertible getSeedsItem() {
		return MeatBlocks.WARPED_CODGUS_SPORES;
	}
}
