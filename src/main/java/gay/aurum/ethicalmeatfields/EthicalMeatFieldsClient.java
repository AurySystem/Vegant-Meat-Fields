package gay.aurum.ethicalmeatfields;

import net.minecraft.client.render.RenderLayer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

import static gay.aurum.ethicalmeatfields.MeatBlocks.*;

public class EthicalMeatFieldsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		BlockRenderLayerMap.put(RenderLayer.getCutout(), VILE_STALKS, CHICKPEA, WARPED_CODGUS, CODGUS, LAMB_BUSH, BEEF_ROOT, EYE_STALKS, MEAT_STALKS);
	}
}
