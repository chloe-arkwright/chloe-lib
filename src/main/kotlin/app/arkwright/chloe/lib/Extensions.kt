package app.arkwright.chloe.lib

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType

fun <T : Item> T.register(): T {
	return Registry.register(BuiltInRegistries.ITEM, this.builtInRegistryHolder().key(), this)
}

fun <T : Block> T.register(): T {
	return Registry.register(BuiltInRegistries.BLOCK, this.builtInRegistryHolder().key(), this)
}

fun <T : BlockEntity> BlockEntityType<T>.register(): BlockEntityType<T> {
	return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, this.builtInRegistryHolder().key(), this)
}
