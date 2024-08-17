package virtuoel.statement.api.compatibility;

import net.minecraft.util.Identifier;

import java.util.UUID;

public class ToClientPayload extends AbstractCustomPayload {
	public ToClientPayload(Identifier packetId, UUID uuid, int length) {
		super(new Id<>(packetId), uuid, length);
	}
}
