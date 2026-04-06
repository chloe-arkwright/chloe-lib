package app.arkwright.chloe.lib.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey

abstract class Registration<T : Any>(
	val modId: String,
	val registry: ResourceKey<Registry<T>>,
) {
	protected fun key(name: String): ResourceKey<T> = ResourceKey.create(registry, Identifier.fromNamespaceAndPath(modId, name))

	protected fun castKey(original: ResourceKey<*>): ResourceKey<T> = ResourceKey.create(registry, original.identifier())

	protected fun register(name: String, value: T): T {
		return Registry.register(findRegistry(registry), key(name), value)
	}

	protected fun register(key: ResourceKey<T>, value: T): T {
		return Registry.register(findRegistry(registry), key, value)
	}

	protected fun findRegistry(registry: ResourceKey<*>): Registry<T> {
		return when (registry) {
			Registries.ITEM -> BuiltInRegistries.ITEM
			Registries.BLOCK -> BuiltInRegistries.BLOCK
			Registries.MENU -> BuiltInRegistries.MENU
			Registries.ENTITY_TYPE -> BuiltInRegistries.ENTITY_TYPE
			Registries.BLOCK_ENTITY_TYPE -> BuiltInRegistries.BLOCK_ENTITY_TYPE
			else -> throw IllegalStateException("Unknown builtin registry: ${registry.registry()}")
		} as Registry<T>
	}

	open fun init() = Unit // Classloading hook
}
