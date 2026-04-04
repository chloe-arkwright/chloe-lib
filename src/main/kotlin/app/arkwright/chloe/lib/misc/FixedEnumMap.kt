package app.arkwright.chloe.lib.misc

import kotlin.enums.EnumEntries

class FixedEnumMap<K : Enum<K>, V> private constructor(
	keys: EnumEntries<K>,
	valueSelector: (K) -> V
) : Map<K, V> {
	private val _entries = keys.associateWith(valueSelector)
	override val entries = _entries.entries
	override val keys = keys.toSet()
	override val values = _entries.values
	override val size: Int = _entries.size
	override fun get(key: K): V = _entries[key]!!
	override fun isEmpty() = size == 0

	override fun containsKey(key: K) = keys.contains(key)
	override fun containsValue(value: V) = _entries.containsValue(value)

	companion object {
		fun <K : Enum<K>, V> create(entries: EnumEntries<K>, valueSelector: (K) -> V): FixedEnumMap<K, V> {
			return FixedEnumMap(entries, valueSelector)
		}
	}
}
