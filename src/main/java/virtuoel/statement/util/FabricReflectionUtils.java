package virtuoel.statement.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public class FabricReflectionUtils {
	private static final MappingResolver MAPPING_RESOLVER = FabricLoader.getInstance().getMappingResolver();
	private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();

	public static MethodHandle getMinecraftMethod(Class<?> clazz, String className, String method, String methodDescription, Class<?>... params) {
		String name = MAPPING_RESOLVER.mapMethodName("intermediary", className, method, methodDescription);
        try {
			return LOOKUP.unreflect(clazz.getMethod(name, params));
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

	public static MethodHandle getMethod(Class<?> clazz, String method, Class<?>... params) {
        try {
			return LOOKUP.unreflect(clazz.getMethod(method, params));
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
