package net.tismatis.tismatisroads.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.tismatis.tismatisroads.TismatisRoadsShared;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TismatisRoadsMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		TismatisRoadsShared.LOGGER.info("[TismatisRoads] The Mixin part has fully loaded!");
	}
}
