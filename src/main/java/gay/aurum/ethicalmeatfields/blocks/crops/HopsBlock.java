package gay.aurum.ethicalmeatfields.blocks.crops;

import gay.aurum.ethicalmeatfields.MeatBlocks;
import gay.aurum.ethicalmeatfields.blocks.MeatCropBase;
import net.minecraft.item.ItemConvertible;

public class HopsBlock extends MeatCropBase {
	public HopsBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected ItemConvertible getSeedsItem() {
		return MeatBlocks.HOPS_SEEDS;
	}
}
