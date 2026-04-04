package app.arkwright.chloe.lib.registration

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour

class BlockRegistration(modId: String) : Registration<Block>(modId, Registries.BLOCK) {
	fun <T : Block> block(key: ResourceKey<Block>, createBlock: (BlockBehaviour.Properties) -> T, properties: BlockBehaviour.Properties): T {
		val block = createBlock(properties.setId(key))

		block.builtInRegistryHolder().bindKey(key)

		return block
	}
}
