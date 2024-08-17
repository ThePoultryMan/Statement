package virtuoel.statement.mixin.compat116plus;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Table;
import com.mojang.serialization.MapCodec;

import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import virtuoel.statement.Statement;
import virtuoel.statement.util.HydrogenCompatibility;
import virtuoel.statement.util.StatementStateExtensions;

@Mixin(State.class)
public abstract class StateMixin<S> implements StatementStateExtensions<S>
{
	@Shadow private Table<Property<?>, Comparable<?>, S> withTable;
	@Shadow @Final @Mutable MapCodec<S> codec;
	
	@Inject(at = @At("HEAD"), method = "createWithTable")
	private void onCreateWithTable(Map<Map<Property<?>, Comparable<?>>, S> map, CallbackInfo info)
	{
		withTable = null;
	}
	
	@Shadow
	abstract void createWithTable(Map<Map<Property<?>, Comparable<?>>, ?> map);
	
	@Override
	public void statement_createWithTable(Map<Map<Property<?>, Comparable<?>>, ?> states)
	{
		createWithTable(states);
	}
	
	@Shadow
	abstract <T extends Comparable<T>, V extends T> S with(Property<T> property, V value);
	
	@Override
	public <T extends Comparable<T>, V extends T> S statement_with(Property<T> property, V value)
	{
		return with(property, value);
	}
	
	@Override
	public Object statement_getCodec()
	{
		return this.codec;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void statement_setCodec(Object codec)
	{
		this.codec = (MapCodec<S>) codec;
	}
}
