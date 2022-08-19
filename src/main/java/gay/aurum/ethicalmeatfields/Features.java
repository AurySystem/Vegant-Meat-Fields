package gay.aurum.ethicalmeatfields;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Holder;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import static gay.aurum.ethicalmeatfields.EthicalMeatFields.ID;

public class Features {
	public static final Holder<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> FLESH_VEGETATION = register(
			"moss_vegetation",
			Feature.SIMPLE_BLOCK,
			new SimpleBlockFeatureConfig(
					new WeightedBlockStateProvider(
							DataPool.<BlockState>builder()
									.add(Blocks.FLOWERING_AZALEA.getDefaultState(), 4)
									.add(Blocks.AZALEA.getDefaultState(), 7)
									.add(Blocks.MOSS_CARPET.getDefaultState(), 25)
									.add(Blocks.GRASS.getDefaultState(), 50)
									.add(Blocks.TALL_GRASS.getDefaultState(), 10)
					)
			)
	);

	public static final Holder<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> FLESH_PATCH = register(
			"moss_patch",
			Feature.VEGETATION_PATCH,
			new VegetationPatchFeatureConfig(
					BlockTags.MOSS_REPLACEABLE,
					BlockStateProvider.of(Blocks.MOSS_BLOCK),
					PlacedFeatureUtil.placedInline(FLESH_VEGETATION),
					VerticalSurfaceType.FLOOR,
					ConstantIntProvider.create(1),
					0.0F,
					5,
					0.8F,
					UniformIntProvider.create(4, 7),
					0.3F
			)
	);

	public static <FC extends FeatureConfig, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, F feature, FC featureConfig) {
		return ConfiguredFeatureUtil.register(ID(id).toString(),  feature,  featureConfig);
	}
}
