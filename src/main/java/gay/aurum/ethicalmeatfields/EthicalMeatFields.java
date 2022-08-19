package gay.aurum.ethicalmeatfields;

import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EthicalMeatFields implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Vegan't Meat Fields");
	public static final String MODID = "ethicalmeatfields";

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("{} Loading, Enjoy cursed life from beyond!", mod.metadata().name());
		MeatBlocks.init();
		MeatFeatures.init();
	}

	public static Identifier ID(String name) {return new Identifier(MODID, name);}
}
