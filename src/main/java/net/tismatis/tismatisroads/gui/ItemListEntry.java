package net.tismatis.tismatisroads.gui;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import net.tismatis.tismatisroads.blockentities.CraftingMachineScreen;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class ItemListEntry extends ElementListWidget.Entry<ItemListEntry> {
	private final MinecraftClient client;
	private final ItemStack itemStack;
	public static final int BLACK_COLOR;
	public static final int GRAY_COLOR;
	public static final int DARK_GRAY_COLOR;
	public static final int WHITE_COLOR;
	public static final int LIGHT_GRAY_COLOR;
	@Nullable
	private ButtonWidget useButton;

	public ItemListEntry(MinecraftClient client, ItemStack itemStack) {
		this.client = client;
		this.itemStack = itemStack;

		this.useButton = new TexturedButtonWidget(0, 0, 20, 20, 0, 38, 20, CraftingMachineScreen.RECIPES_TEXTURE, 256, 256, (button) -> {
			System.out.println("YAAA");
		}, new ButtonWidget.TooltipSupplier() {
			public void onTooltip(ButtonWidget buttonWidget, MatrixStack matrixStack, int i, int j) {}
			public void supply(Consumer<Text> consumer) {}
		}, Text.of(""));
	}

	public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
		int i = x + 4;
		int j = y + (entryHeight - 24) / 2;
		int k = i + 24 + 4;
		int l;
		DrawableHelper.fill(matrices, x, y, x + entryWidth, y + entryHeight, GRAY_COLOR);
		l = y + (entryHeight - 9) / 2;

		this.client.getItemRenderer().renderGuiItemIcon(this.itemStack, i+4, j+4);
		this.client.textRenderer.draw(matrices, this.itemStack.getName(), (float)k, (float)l, WHITE_COLOR);
		this.useButton.render(matrices, mouseX, mouseY, tickDelta);
	}

	public List<? extends Element> children() {
		return ImmutableList.of(this.useButton);
	}

	public List<? extends Selectable> selectableChildren() {
		return ImmutableList.of(this.useButton);
	}

	public String getName() {
		return this.itemStack.getName().getString();
	}

	static {
		BLACK_COLOR = ColorHelper.Argb.getArgb(190, 0, 0, 0);
		GRAY_COLOR = ColorHelper.Argb.getArgb(255, 74, 74, 74);
		DARK_GRAY_COLOR = ColorHelper.Argb.getArgb(255, 48, 48, 48);
		WHITE_COLOR = ColorHelper.Argb.getArgb(255, 255, 255, 255);
		LIGHT_GRAY_COLOR = ColorHelper.Argb.getArgb(140, 255, 255, 255);
	}
}