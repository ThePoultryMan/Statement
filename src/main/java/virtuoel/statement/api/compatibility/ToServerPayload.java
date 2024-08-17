package virtuoel.statement.api.compatibility;

import net.minecraft.util.Identifier;

import java.util.UUID;

public class ToServerPayload extends AbstractCustomPayload {
	private String stateNbt;

	public ToServerPayload(Identifier packetId, UUID uuid, int length) {
		super(new Id<>(packetId), uuid, length);
	}

	public String getStateNbt() {
		return this.stateNbt;
	}

	public void setStateNbt(String stateNbt) {
		this.stateNbt = stateNbt;
	}
}
