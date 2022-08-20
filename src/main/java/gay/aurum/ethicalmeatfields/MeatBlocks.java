package gay.aurum.ethicalmeatfields;

import gay.aurum.ethicalmeatfields.blocks.*;
import gay.aurum.ethicalmeatfields.blocks.crops.*;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import static gay.aurum.ethicalmeatfields.EthicalMeatFields.ID;

public class MeatBlocks {
	public static final Block MEAT_STALKS = Registry.register(Registry.BLOCK, ID("meat_stalks"),
			new MeatCropBase(QuiltBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.DULL_PINK).sounds(BlockSoundGroup.HONEY) ));
	public static final Item MEAT_SPORES = Registry.register(Registry.ITEM, ID("meat_spores"),
			new AliasedBlockItem(MEAT_STALKS, new QuiltItemSettings().group(ItemGroup.MISC)));
	public static final Item MEAT_TENDRIL = Registry.register(Registry.ITEM, ID("meat_tendril"),
			new Item(new QuiltItemSettings()
					.group(ItemGroup.FOOD)
					.food(new FoodComponent.Builder().meat().hunger(1).saturationModifier(0.1f)
							.statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 400, 2), 0.5F)
							.statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 1), 0.4F).build())));
	public static final Item COOKED_MEAT_TENDRIL = Registry.register(Registry.ITEM, ID("cooked_meat_tendril"),
			new Item(new QuiltItemSettings()
					.group(ItemGroup.FOOD)
					.food(new FoodComponent.Builder().meat().hunger(5).saturationModifier(0.3f)
							.statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 1), 0.2F).build()))); //add recipe
	public static final Block EYE_STALKS = Registry.register(Registry.BLOCK, ID("eye_stalks"),
			new EyeStalksBlock(QuiltBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.DULL_PINK).sounds(BlockSoundGroup.HONEY) ));
	public static final Item EYE_SPORES = Registry.register(Registry.ITEM, ID("eye_spores"),
			new AliasedBlockItem(EYE_STALKS, new QuiltItemSettings().group(ItemGroup.MISC)));

	public static final Block BEEF_ROOT = Registry.register(Registry.BLOCK, ID("beefroot"),
			new BeefrootBlock(QuiltBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.DULL_PINK).sounds(BlockSoundGroup.HONEY) ));
	public static final Item BEEF_ROOT_SPORES = Registry.register(Registry.ITEM, ID("beefroot_spores"),
			new AliasedBlockItem(BEEF_ROOT, new QuiltItemSettings().group(ItemGroup.MISC)));

	public static final Block LAMB_BUSH = Registry.register(Registry.BLOCK, ID("lamb_bush"),
			new LambBushBlock(QuiltBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.DULL_PINK).sounds(BlockSoundGroup.HONEY) ));
	public static final Item LAMB_BUSH_LEGS = Registry.register(Registry.ITEM, ID("lamb_bush_legs"),
			new AliasedBlockItem(LAMB_BUSH, new QuiltItemSettings().group(ItemGroup.MISC)));

	public static final Block CODGUS = Registry.register(Registry.BLOCK, ID("cod_fungi"),
			new CodgusBlock(QuiltBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.DULL_PINK).sounds(BlockSoundGroup.HONEY) ));
	public static final Item GODGUS_SPORES = Registry.register(Registry.ITEM, ID("cod_fungi_spores"),
			new AliasedBlockItem(CODGUS, new QuiltItemSettings().group(ItemGroup.MISC)));
	public static final Block WARPED_CODGUS = Registry.register(Registry.BLOCK, ID("salmon_fungi"),
			new CodgusBlock(QuiltBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.DULL_PINK).sounds(BlockSoundGroup.HONEY) ));
	public static final Item WARPED_CODGUS_SPORES = Registry.register(Registry.ITEM, ID("salmon_fungi_spores"),
			new AliasedBlockItem(WARPED_CODGUS, new QuiltItemSettings().group(ItemGroup.MISC)));
	public static final Block CHICKPEA = Registry.register(Registry.BLOCK, ID("chickpea"),
			new ChickpeasBlock(QuiltBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.DULL_PINK).sounds(BlockSoundGroup.HONEY) ));
	public static final Item CHICKPEAS = Registry.register(Registry.ITEM, ID("chickpea"),
			new AliasedBlockItem(CHICKPEA, new QuiltItemSettings().group(ItemGroup.MISC)));

	public static final Block INFESTATION_GRASS = Registry.register(Registry.BLOCK, ID("meat_soil"),
			new InfestationGrassBlock(QuiltBlockSettings.copyOf(Blocks.GRASS_BLOCK).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.HONEY) ));
	public static final Item INFESTATION_GRASS_ITEM = Registry.register(Registry.ITEM, ID("meat_soil"),
			new BlockItem(INFESTATION_GRASS, new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
	public static final InfestedFarmland TILLED_INFESTATON = Registry.register(Registry.BLOCK, ID("meat_farmland"),
			new InfestedFarmland(QuiltBlockSettings.copyOf(Blocks.FARMLAND).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.HONEY)));
	public static final Item TILLED_INFESTATON_ITEM = Registry.register(Registry.ITEM, ID("meat_farmland"),
			new BlockItem(TILLED_INFESTATON, new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

	public static final Block INFESTATION_CARPET = Registry.register(Registry.BLOCK,ID("infestation_carpet"),
			new CarpetBlock(QuiltBlockSettings.of(Material.PLANT, MapColor.DARK_DULL_PINK).strength(0.1F).sounds(BlockSoundGroup.HONEY)));
	public static final Item INFESTATION_CARPET_ITEM = Registry.register(Registry.ITEM, ID("infestation_carpet"),
			new BlockItem(INFESTATION_CARPET, new QuiltItemSettings().group(ItemGroup.DECORATIONS)));
	public static final Block ROT_PIT = Registry.register(Registry.BLOCK, ID("rot_pit"),
			new RotPitBlock(QuiltBlockSettings.copyOf(Blocks.GRASS).mapColor(MapColor.DULL_PINK).sounds(BlockSoundGroup.HONEY) ));
	public static final Item ROT_PIT_ITEM = Registry.register(Registry.ITEM, ID("rot_pit"),
			new BlockItem(ROT_PIT, new QuiltItemSettings().group(ItemGroup.DECORATIONS)));
	public static final Block VILE_STALKS = Registry.register(Registry.BLOCK, ID("vile_stalks"),
			new VileStalksBlock(QuiltBlockSettings.copyOf(Blocks.GRASS).mapColor(MapColor.DULL_PINK).sounds(BlockSoundGroup.HONEY) ));
	public static final Item VILE_STALKS_ITEM = Registry.register(Registry.ITEM, ID("vile_stalks"),
			new BlockItem(VILE_STALKS, new QuiltItemSettings().group(ItemGroup.DECORATIONS)));

	public static final Block ROT_MEAT = Registry.register(Registry.BLOCK, ID("rot_meat"),
			new Block(QuiltBlockSettings.copyOf(Blocks.DIRT).mapColor(MapColor.DULL_RED).sounds(BlockSoundGroup.HONEY) )); //actually inert
	public static final Item ROT_MEAT_ITEM = Registry.register(Registry.ITEM, ID("rot_meat"),
			new BlockItem(ROT_MEAT, new QuiltItemSettings().group(ItemGroup.DECORATIONS)));

	//add rotten features?
	//add stable flesh tendrils?
	//add leather?
	//add rabbit and pork

	public static void init(){}
}
