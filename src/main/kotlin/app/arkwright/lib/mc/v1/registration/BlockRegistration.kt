package app.arkwright.lib.mc.v1.registration

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour

import app.arkwright.lib.mc.v1.holder

open class BlockRegistration(modId: String) : Registration<Block>(modId, Registries.BLOCK) {
	protected typealias Properties = BlockBehaviour.Properties
	protected typealias BlockConstructor<T> = (Properties) -> T

	fun <B : Block> block(name: String, properties: Properties = Properties.of(), makeBlock: BlockConstructor<B>): B {
		return block(key(name), properties, makeBlock)
	}

	fun <B : Block> block(key: ResourceKey<Block>, properties: Properties = Properties.of(), makeBlock: BlockConstructor<B>): B {
		val block = makeBlock(properties.setId(key))

		block.holder().bindKey(key)

		return block
	}
}
