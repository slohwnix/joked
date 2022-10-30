
package fr.slohwnix.fakecheatmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import fr.slohwnix.fakecheatmod.world.inventory.IsnorealcheatguiMenu;
import fr.slohwnix.fakecheatmod.network.IsnorealcheatguiButtonMessage;
import fr.slohwnix.fakecheatmod.FakecheatmodMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class IsnorealcheatguiScreen extends AbstractContainerScreen<IsnorealcheatguiMenu> {
	private final static HashMap<String, Object> guistate = IsnorealcheatguiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Checkbox autoactive;

	public IsnorealcheatguiScreen(IsnorealcheatguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 153;
		this.imageHeight = 58;
	}

	private static final ResourceLocation texture = new ResourceLocation("fakecheatmod:textures/screens/isnorealcheatgui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
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
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "snipercheat", 88, 40, -6750157);
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
		this.addRenderableWidget(new Button(this.leftPos + -136, this.topPos + -70, 40, 20, new TextComponent("fly"), e -> {
		}));
		this.addRenderableWidget(new Button(this.leftPos + -136, this.topPos + -31, 56, 20, new TextComponent("aimbot"), e -> {
		}));
		this.addRenderableWidget(new Button(this.leftPos + -136, this.topPos + -12, 67, 20, new TextComponent("killaura"), e -> {
		}));
		this.addRenderableWidget(new Button(this.leftPos + -136, this.topPos + 8, 35, 20, new TextComponent("tp"), e -> {
		}));
		autoactive = new Checkbox(this.leftPos + 17, this.topPos + 16, 20, 20, new TextComponent("autoactive cheat"), false);
		guistate.put("checkbox:autoactive", autoactive);
		this.addRenderableWidget(autoactive);
		this.addRenderableWidget(new Button(this.leftPos + 96, this.topPos + -1, 51, 20, new TextComponent("close"), e -> {
			if (true) {
				FakecheatmodMod.PACKET_HANDLER.sendToServer(new IsnorealcheatguiButtonMessage(4, x, y, z));
				IsnorealcheatguiButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + -136, this.topPos + -51, 51, 20, new TextComponent("x-ray"), e -> {
		}));
		this.addRenderableWidget(new Button(this.leftPos + -136, this.topPos + -90, 82, 20, new TextComponent("name editor"), e -> {
			if (true) {
				FakecheatmodMod.PACKET_HANDLER.sendToServer(new IsnorealcheatguiButtonMessage(6, x, y, z));
				IsnorealcheatguiButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}));
	}
}
