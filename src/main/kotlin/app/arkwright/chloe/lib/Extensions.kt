package app.arkwright.chloe.lib

import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType

fun Block.registryKey(): ResourceKey<Block> {
	return this.properties().blockIdOrThrow()
}

fun <V : Any> ResourceKey<Registry<V>>.tagKey(id: Identifier): TagKey<V> = TagKey.create(this, id)

@Suppress("DEPRECATION")
fun Block.holder(): Holder.Reference<Block> {
	return this.builtInRegistryHolder()
}

@Suppress("DEPRECATION")
fun Item.holder(): Holder.Reference<Item> {
	return this.builtInRegistryHolder()
}

@Suppress("DEPRECATION")
fun BlockEntityType<*>.holder(): Holder.Reference<BlockEntityType<*>> {
	return this.builtInRegistryHolder()
}

fun <T : Item> T.register(): T {
	return Registry.register(BuiltInRegistries.ITEM, this.holder().key(), this)
}

fun <T : Block> T.register(): T {
	return Registry.register(BuiltInRegistries.BLOCK, this.registryKey(), this)
}

fun <T : BlockEntity> BlockEntityType<T>.register(): BlockEntityType<T> {
	return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, this.holder().key(), this)
}
