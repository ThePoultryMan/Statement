package virtuoel.statement.mixin.compat1205plus;

import net.minecraft.registry.entry.RegistryEntryInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.serialization.Lifecycle;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleDefaultedRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import virtuoel.statement.util.RegistryKeyExtensions;

@Mixin(SimpleDefaultedRegistry.class)
public abstract class SimpleDefaultedRegistryMixin<T>
{
	@Shadow @Final Identifier defaultId;

	@Inject(method = "add", at = @At(value = "HEAD"))
	private void setDefault(RegistryKey<T> registryKey, T value, RegistryEntryInfo info, CallbackInfoReturnable<RegistryEntry.Reference<T>> cir)
	{
		if (defaultId.equals(registryKey.getValue()))
		{
			((RegistryKeyExtensions) registryKey).statement_setValue(defaultId);
		}
	}
}
