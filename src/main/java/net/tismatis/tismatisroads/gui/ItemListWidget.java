package net.tismatis.tismatisroads.gui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ItemListWidget extends ElementListWidget<ItemListEntry> {
	private final List<ItemListEntry> items = Lists.newArrayList();
	@Nullable
	private String currentSearch;

	public ItemListWidget(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
		super(client, width, height, top, bottom, itemHeight);
		this.setRenderBackground(false);
		this.setRenderHorizontalShadows(false);
	}

	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		double d = this.client.getWindow().getScaleFactor();
		RenderSystem.enableScissor((int)((double)this.getRowLeft() * d), (int)((double)(this.height - this.bottom) * d), (int)((double)(this.getScrollbarPositionX() + 6) * d), (int)((double)(this.height - (this.height - this.bottom) - this.top - 4) * d));
		super.render(matrices, mouseX, mouseY, delta);
		RenderSystem.disableScissor();
	}

	public void refresh(Collection<ItemListEntry> items, double scrollAmount) {
		this.items.clear();
		this.items.addAll(items);
		this.filterItems();
		this.replaceEntries(this.items);
		this.setScrollAmount(scrollAmount);
	}

	private void filterItems() {
		if (this.currentSearch != null) {
			this.items.removeIf((items) -> {
				return !items.getName().toLowerCase(Locale.ROOT).contains(this.currentSearch);
			});
			this.replaceEntries(this.items);
		}

	}

	public void setCurrentSearch(String currentSearch) {
		this.currentSearch = currentSearch;
	}

	public boolean isEmpty() {
		return this.items.isEmpty();
	}
}