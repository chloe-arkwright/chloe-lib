package app.arkwright.chloe.lib.registration

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item

class ItemRegistration(modId: String) : Registration<Item>(modId, Registries.ITEM) {
	fun <T : Item> item(key: ResourceKey<Item>, createItem: (Item.Properties) -> T, properties: Item.Properties): T {
		val item = createItem(properties.setId(key))

		item.builtInRegistryHolder().bindKey(key)

		return item
	}
}
