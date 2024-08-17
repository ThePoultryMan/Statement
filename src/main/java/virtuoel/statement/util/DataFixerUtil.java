package virtuoel.statement.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.OptionalFieldCodec;
import net.minecraft.state.property.Property;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class DataFixerUtil {
	private static final Constructor<?>	OPTIONAL_FIELD_CODEC_CONSTRUCTOR = ReflectionUtils.getConstructor(Optional.of(OptionalFieldCodec.class), String.class, Codec.class, boolean.class).get();

	public static <T extends Property.Value<C>, C extends Comparable<C>> OptionalFieldCodec<T> newOptionalFieldCodec(String string, Codec<Property.Value<C>> codec) {
        try {
            return (OptionalFieldCodec<T>) OPTIONAL_FIELD_CODEC_CONSTRUCTOR.newInstance(string, codec, false);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
