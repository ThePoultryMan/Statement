package virtuoel.statement.mixin.compat1205plus;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import virtuoel.statement.Statement;
import virtuoel.statement.util.HydrogenCompatibility;
import virtuoel.statement.util.StatementStateExtensions;

import java.util.HashMap;
import java.util.Map;

@Mixin(State.class)
public abstract class StateMixin<O, S> implements StatementStateExtensions<S> {
	@Mutable
	@Shadow @Final private Reference2ObjectArrayMap<Property<?>, Comparable<?>> propertyMap;
	@Shadow @Final @Mutable protected O owner;
	@Unique
	String getMissingOwner = "";

	@Unique
	String withMissingOwner = "";
	@Unique String withDisallowedOwner = "";

	@Override
	public Map<Property<?>, Comparable<?>> statement_getEntries()
	{
		return getEntries();
	}

	@Override
	public <V extends Comparable<V>> boolean statement_addEntry(final Property<V> property, final V value)
	{
		if (!propertyMap.containsKey(property))
		{
			statement_setEntries(ImmutableMap.<Property<?>, Comparable<?>>builder().putAll(propertyMap).put(property, value).build());

			return true;
		}

		return false;
	}

	@Inject(method = "get", cancellable = true, at = @At(value = "INVOKE", target = "Ljava/lang/IllegalArgumentException;<init>(Ljava/lang/String;)V"))
	private <T extends Comparable<T>> void onGet(Property<T> property, CallbackInfoReturnable<T> info)
	{
		final String ownerString = this.owner.toString();

		if (!getMissingOwner.equals(ownerString))
		{
			Statement.LOGGER.info("Cannot get property {} as it does not exist in {}", property, this.owner);
			getMissingOwner = ownerString;
		}

		info.setReturnValue(cachedFallbacks.containsKey(property) ? property.getType().cast(cachedFallbacks.get(property)) : property.getValues().iterator().next());
	}

	@Inject(method = "with", cancellable = true, at = @At(value = "INVOKE", target = "Ljava/lang/IllegalArgumentException;<init>(Ljava/lang/String;)V"))
	private <T extends Comparable<T>, V extends T> void onWith(Property<T> property, V value, CallbackInfoReturnable<Object> info)
	{
		final String ownerString = this.owner.toString();

		if (this.propertyMap.get(property) == null)
		{
			if (!withMissingOwner.equals(ownerString))
			{
				Statement.LOGGER.info("Cannot set property {} as it does not exist in {}", property, this.owner);
				withMissingOwner = ownerString;
			}
		}
		else if (!withDisallowedOwner.equals(ownerString))
		{
			Statement.LOGGER.info("Cannot set property {} to {} on {}, it is not an allowed value", property, value, this.owner);
			withDisallowedOwner = ownerString;
		}

		info.setReturnValue(this);
	}

	@Unique final Map<Property<?>, Comparable<?>> cachedFallbacks = new HashMap<>();

	@Override
	public <V extends Comparable<V>> boolean statement_removeEntry(Property<V> property)
	{
		if (propertyMap.containsKey(property))
		{
			final ImmutableMap.Builder<Property<?>, Comparable<?>> builder = ImmutableMap.builder();

			for (final Map.Entry<Property<?>, Comparable<?>> entry : propertyMap.entrySet())
			{
				final Property<?> key = entry.getKey();

				if (key != property)
				{
					builder.put(key, entry.getValue());
				}
			}

			cachedFallbacks.put(property, propertyMap.get(property));

			statement_setEntries(builder.build());

			return true;
		}

		return false;
	}

	@Override
	public void statement_setEntries(Map<Property<?>, Comparable<?>> entries)
	{
		this.propertyMap = new Reference2ObjectArrayMap<>(HydrogenCompatibility.INSTANCE.wrapEntries(ImmutableMap.copyOf(entries)));
	}

	@Shadow abstract Map<Property<?>, Comparable<?>> getEntries();
}
