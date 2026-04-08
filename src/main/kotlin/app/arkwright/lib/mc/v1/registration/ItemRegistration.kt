package app.arkwright.lib.mc.v1.registration

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

import app.arkwright.lib.mc.v1.holder

open class ItemRegistration(modId: String) : Registration<Item>(modId, Registries.ITEM) {
	protected typealias Properties = Item.Properties
	protected typealias ItemConstructor<T> = (Properties) -> T

	fun block(
		block: Block,
		properties: Properties = Properties()
	): BlockItem {
		return block(block, properties) { block -> { properties -> BlockItem(block, properties) } }
	}

	fun <B : Block, I : BlockItem> block(
		block: B,
		properties: Properties = Properties(),
		makeItem: (B) -> ItemConstructor<I>
	): I {
		return item(castKey(block.holder().key()), properties.useBlockDescriptionPrefix(), makeItem(block))
	}

	fun <I : Item> item(name: String, properties: Properties = Properties(), makeItem: ItemConstructor<I>): I {
		return item(key(name), properties, makeItem)
	}

	fun <I : Item> item(key: ResourceKey<Item>, properties: Properties = Properties(), makeItem: ItemConstructor<I>): I {
		val item = makeItem(properties.setId(key))

		item.holder().bindKey(key)

		return item
	}
}
