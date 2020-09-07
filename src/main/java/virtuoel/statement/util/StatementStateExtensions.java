package virtuoel.statement.util;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.state.property.Property;

public interface StatementStateExtensions
{
	default <V extends Comparable<V>> boolean statement_addEntry(final Property<V> property, final V value)
	{
		return false;
	}
	
	default <V extends Comparable<V>> boolean statement_removeEntry(final Property<V> property)
	{
		return false;
	}
	
	default void statement_setEntries(ImmutableMap<Property<?>, Comparable<?>> entries)
	{
		
	}
	
	default void statement_createWithTable(Map<Map<Property<?>, Comparable<?>>, ?> states)
	{
		
	}
}
