package app.arkwright.chloe.lib.registration

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour

import app.arkwright.chloe.lib.holder

open class BlockRegistration(modId: String) : Registration<Block>(modId, Registries.BLOCK) {
	protected typealias Properties = BlockBehaviour.Properties
	protected typealias BlockConstructor<T> = (Properties) -> T

	fun <T : Block> block(name: String, properties: Properties, makeBlock: BlockConstructor<T>): T {
		return block(key(name), properties, makeBlock)
	}

	fun <T : Block> block(key: ResourceKey<Block>, properties: Properties, makeBlock: BlockConstructor<T>): T {
		val block = makeBlock(properties.setId(key))

		block.holder().bindKey(key)

		return block
	}
}
