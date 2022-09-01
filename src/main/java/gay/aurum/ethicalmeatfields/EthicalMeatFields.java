package gay.aurum.ethicalmeatfields;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EthicalMeatFields implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Vegan't Meat Fields");
	public static final String MODID = "ethicalmeatfields";
	public static final TagKey<Block> MEAT_VEGETATION = TagKey.of(Registry.BLOCK_KEY, ID("meat_vegetation"));

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("{} Loading, Enjoy cursed life from beyond!", mod.metadata().name());
		MeatBlocks.init();
		MeatFeatures.init();
	}

	public static Identifier ID(String name) {return new Identifier(MODID, name);}
}
