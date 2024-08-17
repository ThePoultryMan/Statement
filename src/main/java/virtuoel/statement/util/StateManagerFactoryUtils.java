package virtuoel.statement.util;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Reference2BooleanArrayMap;
import net.minecraft.state.State;
import net.minecraft.state.StateManager;

import java.lang.invoke.MethodHandle;
import java.util.Map;

public class StateManagerFactoryUtils {
	private static final MethodHandle TWENTY_ONE_CREATE = FabricReflectionUtils.getMinecraftMethod(StateManager.Factory.class, "net.minecraft.class_2689$class_2691", "create", "(Ljava/lang/Object;Lit/unimi/dsi/fastutil/objects/Reference2ObjectArrayMap;Lcom/mojang/serialization/MapCodec;)Ljava/lang/Object;", Object.class, Reference2BooleanArrayMap.class, MapCodec.class);

	public static <O, S extends State<O, S>> S create(StateManager.Factory<?, ?> factory, Object owner, Map<?, ?> propertyMap, MapCodec<?> mapCodec) {
        try {
            return (S) TWENTY_ONE_CREATE.invoke(factory, owner, propertyMap, mapCodec);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
