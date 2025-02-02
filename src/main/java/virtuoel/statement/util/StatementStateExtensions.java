package virtuoel.statement.util;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.state.State;
import net.minecraft.state.property.Property;

public interface StatementStateExtensions<S>
{
	default <V extends Comparable<V>> boolean statement_addEntry(final Property<V> property, final V value)
	{
		return false;
	}
	
	default <V extends Comparable<V>> boolean statement_removeEntry(final Property<V> property)
	{
		return false;
	}
	
	default void statement_setEntries(Map<Property<?>, Comparable<?>> entries)
	{
		
	}
	
	default void statement_createWithTable(Map<Map<Property<?>, Comparable<?>>, ?> states)
	{
		
	}
	
	default Object statement_getCodec()
	{
		return null;
	}
	
	default void statement_setCodec(Object codec)
	{
		
	}
	
	default Map<Property<?>, Comparable<?>> statement_getEntries()
	{
		return ImmutableMap.of();
	}
	
	default <T extends Comparable<T>, V extends T> S statement_with(Property<T> property, V value)
	{
		return null;
	}
	
	default void statement_initShapeCache()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public static <S> StatementStateExtensions<S> statement_cast(State<?, S> state)
	{
		return (StatementStateExtensions<S>) state;
	}
}
