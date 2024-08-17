package virtuoel.statement.util;

import net.minecraft.util.Identifier;

import java.lang.invoke.MethodHandle;

public class IdentifierUtil {
//	public static final Optional<Class<?>> IDENTIFIER_CLASS;
	private static final MethodHandle OF_METHOD = FabricReflectionUtils.getMinecraftMethod(Identifier.class, "net.minecraft.class_2960", "method_60655", "(Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/class_2960;", String.class, String.class);
	private static final MethodHandle OF_METHOD_2 = FabricReflectionUtils.getMinecraftMethod(Identifier.class, "net.minecraft.class_2960", "method_60654", "(Ljava/lang/String;)Lnet/minecraft/class_2960;", String.class);
//	public static final Optional<Constructor<Identifier>> CONSTRUCTOR;

	public static <T> T of(String namespace, String path) {
        try {
            return (T) OF_METHOD.invoke(namespace, path);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
//		if (IDENTIFIER_CLASS.isPresent()) {
//			if (OF_METHOD.isPresent()) {
//                try {
//                    OF_METHOD.get().invoke(namespace, path);
//                } catch (IllegalAccessException | InvocationTargetException e) {
//                    throw new RuntimeException(e);
//                }
//            } else if (CONSTRUCTOR.isPresent()) {
//                try {
//                    return (T) CONSTRUCTOR.get().newInstance(namespace, path);
//                } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//		}
//        return null;
    }

	public static <T> T of(String path) {
        try {
            return (T) OF_METHOD_2.invoke(path);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
//		if (IDENTIFIER_CLASS.isPresent()) {
//			if (OF_METHOD.isPresent()) {
//                try {
//                    OF_METHOD.get().invoke(namespace, path);
//                } catch (IllegalAccessException | InvocationTargetException e) {
//                    throw new RuntimeException(e);
//                }
//            } else if (CONSTRUCTOR.isPresent()) {
//                try {
//                    return (T) CONSTRUCTOR.get().newInstance(namespace, path);
//                } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//		}
//        return null;
    }
}
