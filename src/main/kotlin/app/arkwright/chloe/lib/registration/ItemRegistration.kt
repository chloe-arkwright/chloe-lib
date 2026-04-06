package app.arkwright.chloe.lib.registration

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

import app.arkwright.chloe.lib.holder

open class ItemRegistration(modId: String) : Registration<Item>(modId, Registries.ITEM) {
	protected typealias Properties = Item.Properties
	protected typealias ItemConstructor<T> = (Properties) -> T

	fun block(
		block: Block,
		properties: Properties = Properties(),
		makeItem: (Block) -> ItemConstructor<BlockItem> = { block -> { properties -> BlockItem(block, properties) } }
	): BlockItem {
		return item(castKey(block.holder().key()), properties.useBlockDescriptionPrefix(), makeItem(block))
	}

	fun <T : Item> item(name: String, properties: Properties = Properties(), makeItem: ItemConstructor<T>): T {
		return item(key(name), properties, makeItem)
	}

	fun <T : Item> item(key: ResourceKey<Item>, properties: Properties = Properties(), makeItem: ItemConstructor<T>): T {
		val item = makeItem(properties.setId(key))

		item.holder().bindKey(key)

		return item
	}
}
