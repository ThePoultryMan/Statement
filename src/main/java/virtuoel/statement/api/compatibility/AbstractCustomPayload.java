package virtuoel.statement.api.compatibility;

import net.minecraft.network.packet.CustomPayload;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractCustomPayload implements CustomPayload {
	private final Id<?> id;
	private final UUID uuid;
	private final List<Integer> ints;

	public AbstractCustomPayload(Id<?> packetId, UUID uuid, int length) {
		this.id = packetId;
		this.uuid = uuid;
		this.ints = new ArrayList<>(length);
	}

	public UUID getUuid() {
		return this.uuid;
	}

	public void setInt(int i) {
		this.ints.add(i);
	}

	public int getInt(int i) {
		return this.ints.get(i);
	}

	@Override
	public Id<? extends CustomPayload> getId() {
		return this.id;
	}
}
