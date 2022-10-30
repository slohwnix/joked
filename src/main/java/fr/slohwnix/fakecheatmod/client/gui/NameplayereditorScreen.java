
package fr.slohwnix.fakecheatmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import fr.slohwnix.fakecheatmod.world.inventory.NameplayereditorMenu;
import fr.slohwnix.fakecheatmod.network.NameplayereditorButtonMessage;
import fr.slohwnix.fakecheatmod.FakecheatmodMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class NameplayereditorScreen extends AbstractContainerScreen<NameplayereditorMenu> {
	private final static HashMap<String, Object> guistate = NameplayereditorMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox naplayer;

	public NameplayereditorScreen(NameplayereditorMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("fakecheatmod:textures/screens/nameplayereditor.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		naplayer.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (naplayer.isFocused())
			return naplayer.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		naplayer.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "name player editor", 37, 24, -12829636);
		this.font.draw(poseStack, "snipercheat", 105, 143, -6750157);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		naplayer = new EditBox(this.font, this.leftPos + 28, this.topPos + 47, 120, 20, new TextComponent(""));
		guistate.put("text:naplayer", naplayer);
		naplayer.setMaxLength(32767);
		this.addWidget(this.naplayer);
		this.addRenderableWidget(new Button(this.leftPos + 59, this.topPos + 71, 51, 20, new TextComponent("valid"), e -> {
			if (true) {
				FakecheatmodMod.PACKET_HANDLER.sendToServer(new NameplayereditorButtonMessage(0, x, y, z));
				NameplayereditorButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
