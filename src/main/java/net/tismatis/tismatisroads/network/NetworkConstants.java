package net.tismatis.tismatisroads.network;

import net.minecraft.util.Identifier;
import net.tismatis.tismatisroads.TismatisRoadsShared;

public class NetworkConstants {
	public static Identifier OPEN_MESSAGE_BOARD_S2C = new Identifier(
			TismatisRoadsShared.MODID,
			"open_msgboard_s2c"
	);

	public static Identifier UPDATE_MESSAGE_BOARD_C2S = new Identifier(
			TismatisRoadsShared.MODID,
			"update_msgboard_c2s"
	);
}
