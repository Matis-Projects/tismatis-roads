package net.tismatis.tismatisroads.blockentities;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsPlayerListWidget;
import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tismatis.tismatisroads.TismatisRoadsShared;
import net.tismatis.tismatisroads.gui.ItemListEntry;
import net.tismatis.tismatisroads.gui.ItemListWidget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class CraftingMachineScreen extends HandledScreen<CraftingMachineScreenHandler> {
	private static final Identifier TEXTURE = new Identifier("textures/gui/container/dispenser.png");
	public static final Identifier RECIPES_TEXTURE = new Identifier("textures/gui/social_interactions.png");
	private ItemListWidget itemListWidget;
	private TextFieldWidget searchBox;
	private static final Text SEARCH_TEXT;
	private static final Text EMPTY_SEARCH_TEXT;
	private String currentSearch = "";
	private boolean initialized;
	private final Collection<ItemListEntry> itemStacks = new ArrayList<>();

	public CraftingMachineScreen(CraftingMachineScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);

		for (Identifier identifier: TismatisRoadsShared.items) {
			itemStacks.add(new ItemListEntry(
					MinecraftClient.getInstance(),
					new ItemStack(Registry.ITEM.get(identifier))
			));
		}
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		this.searchBox.tick();

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

		int i = this.getSearchBoxX() + 3;
		RenderSystem.setShaderTexture(0, RECIPES_TEXTURE);
		this.drawTexture(matrices, i, 64, 1, 1, 236, 8);
		int j = this.getRowCount();

		for(int k = 0; k < j; ++k) {
			this.drawTexture(matrices, i, 72 + 16 * k, 1, 10, 236, 16);
		}

		this.drawTexture(matrices, i, 72 + 16 * j, 1, 27, 236, 8);
		this.drawTexture(matrices, i + 10, 76, 243, 1, 12, 12);
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		renderBackground(matrices);

		if (!this.searchBox.isFocused() && this.searchBox.getText().isEmpty()) {
			drawTextWithShadow(matrices, this.client.textRenderer, SEARCH_TEXT, this.searchBox.x, this.searchBox.y, -1);
		} else {
			this.searchBox.render(matrices, mouseX, mouseY, delta);
		}

		super.render(matrices, mouseX, mouseY, delta);
		drawMouseoverTooltip(matrices, mouseX, mouseY);

		this.itemListWidget.render(matrices, mouseX, mouseY, delta);
	}

	@Override
	protected void init() {
		super.init();
		// Center the title
		titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;

		if (this.initialized) {
			this.itemListWidget.updateSize(196-28, this.height, 88, this.getItemListBottom());
		} else {
			this.itemListWidget = new ItemListWidget(this.client, 196-28, this.height, 88, this.getItemListBottom(), 36);
		}
		this.itemListWidget.setLeftPos(28*2);

		String string = this.searchBox != null ? this.searchBox.getText() : "";
		this.searchBox = new TextFieldWidget(this.textRenderer, this.getSearchBoxX() + 28, 78, 196, 16, SEARCH_TEXT) {
			protected MutableText getNarrationMessage() {
				return !CraftingMachineScreen.this.searchBox.getText().isEmpty() && CraftingMachineScreen.this.itemListWidget.isEmpty() ? super.getNarrationMessage().append(", ").append(CraftingMachineScreen.EMPTY_SEARCH_TEXT) : super.getNarrationMessage();
			}
		};
		this.searchBox.setMaxLength(16);
		this.searchBox.setDrawsBackground(false);
		this.searchBox.setVisible(true);
		this.searchBox.setEditableColor(16777215);
		this.searchBox.setText(string);
		this.searchBox.setChangedListener(this::onSearchChange);
		this.addSelectableChild(this.searchBox);
		this.addSelectableChild(this.itemListWidget);

		this.itemListWidget.refresh(itemStacks, this.itemListWidget.getScrollAmount());

		this.initialized = true;
	}

	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (this.searchBox.isFocused()) {
			this.searchBox.mouseClicked(mouseX, mouseY, button);
		}

		return super.mouseClicked(mouseX, mouseY, button) || this.itemListWidget.mouseClicked(mouseX, mouseY, button);
	}

	private void onSearchChange(String currentSearch) {
		currentSearch = currentSearch.toLowerCase(Locale.ROOT);
		if (!currentSearch.equals(this.currentSearch)) {
			this.itemListWidget.setCurrentSearch(currentSearch);
			this.currentSearch = currentSearch;
		}
		this.itemListWidget.refresh(itemStacks, this.itemListWidget.getScrollAmount());
	}


	public void removed() {
		this.client.keyboard.setRepeatEvents(false);
	}

	private int getScreenHeight() {
		return Math.max(52, this.height - 128 - 16);
	}

	private int getRowCount() {
		return this.getScreenHeight() / 16;
	}

	private int getItemListBottom() {
		return 80 + this.getRowCount() * 16 - 8;
	}

	private int getSearchBoxX() {
		return 0;
	}

	static {
		SEARCH_TEXT = Text.translatable("gui.socialInteractions.search_hint").formatted(Formatting.ITALIC).formatted(Formatting.GRAY);
		EMPTY_SEARCH_TEXT = Text.translatable("gui.socialInteractions.search_empty").formatted(Formatting.GRAY);
	}
}