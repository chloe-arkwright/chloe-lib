package app.arkwright.chloe.lib.registration

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder

open class BlockEntityRegistration(modId: String) : Registration<BlockEntityType<*>>(modId, Registries.BLOCK_ENTITY_TYPE) {
	fun <T : BlockEntity> type(
		key: ResourceKey<BlockEntityType<*>>,
		blocks: Array<out Block>,
		factory: FabricBlockEntityTypeBuilder.Factory<T>
	): BlockEntityType<T> {
		val type = FabricBlockEntityTypeBuilder.create(factory, *blocks).build()

		type.builtInRegistryHolder().bindKey(key)

		return type
	}
}
