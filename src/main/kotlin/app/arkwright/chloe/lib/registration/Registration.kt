package app.arkwright.chloe.lib.registration

import net.minecraft.core.Registry
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey

abstract class Registration<T : Any>(
	val modId: String,
	val registry: ResourceKey<Registry<T>>,
) {
	protected fun key(name: String): ResourceKey<T> = ResourceKey.create(registry, Identifier.fromNamespaceAndPath(modId, name))

	protected fun castKey(original: ResourceKey<*>): ResourceKey<T> = ResourceKey.create(registry, original.identifier())

	open fun init() = Unit // Classloading hook
}
