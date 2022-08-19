package gay.aurum.ethicalmeatfields;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Holder;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;

import java.util.List;

import static gay.aurum.ethicalmeatfields.EthicalMeatFields.ID;

public class MeatFeatures {
	public static final Holder<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> FLESH_VEGETATION = register(
			"flesh_plants",
			Feature.SIMPLE_BLOCK,
			new SimpleBlockFeatureConfig(
					new WeightedBlockStateProvider(
							DataPool.<BlockState>builder()
									.add(MeatBlocks.INFESTATION_CARPET.getDefaultState(), 25)
									.add(MeatBlocks.MEAT_STALKS.getDefaultState(), 30)
									.add(MeatBlocks.BEEF_ROOT.getDefaultState(), 10)
									.add(MeatBlocks.CHICKPEA.getDefaultState(), 4)
									.add(MeatBlocks.CODGUS.getDefaultState(), 7)
									.add(MeatBlocks.WARPED_CODGUS.getDefaultState(), 10)
									.add(MeatBlocks.EYE_STALKS.getDefaultState(), 10)
									.add(MeatBlocks.LAMB_BUSH.getDefaultState(), 10)
									.add(MeatBlocks.VILE_STALKS.getDefaultState(), 33)
									.add(MeatBlocks.ROT_PIT.getDefaultState(), 33)
					)
			)
	);

	public static final Holder<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> FLESH_PATCH = register(
			"flesh_patch",
			Feature.VEGETATION_PATCH,
			new VegetationPatchFeatureConfig(
					BlockTags.DIRT,
					BlockStateProvider.of(MeatBlocks.INFESTATION_GRASS.getDefaultState()),
					PlacedFeatureUtil.placedInline(FLESH_VEGETATION),
					VerticalSurfaceType.FLOOR,
					ConstantIntProvider.create(1),
					0.0F,
					5,
					0.2F,
					UniformIntProvider.create(1, 3),
					0.3F
			)
	);

	public static final Holder<PlacedFeature> FLESH_INFESTATION = registerPlaced(
			"flesh_infestation",
			FLESH_PATCH,
			CountPlacementModifier.create(12),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
			EnvironmentScanPlacementModifier.create(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
			RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(1)),
			BiomePlacementModifier.getInstance()
	);

	public static Holder<PlacedFeature> registerPlaced(String id, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers) {
		return PlacedFeatureUtil.register(ID(id).toString(), configuredFeature, modifiers);
	}
	public static <FC extends FeatureConfig, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, F feature, FC featureConfig) {
		return ConfiguredFeatureUtil.register(ID(id).toString(),  feature,  featureConfig);
	}
	public static void init(){
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION,FLESH_INFESTATION.getKey().orElseThrow());
	}
}
