
package fr.slohwnix.fakecheatmod.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import fr.slohwnix.fakecheatmod.JokedMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class CheatguiGuiWindow extends ContainerScreen<CheatguiGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = CheatguiGui.guistate;
	CheckboxButton autoactive;

	public CheatguiGuiWindow(CheatguiGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 86;
	}

	private static final ResourceLocation texture = new ResourceLocation("joked:textures/screens/cheatgui.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "spipercheat", 111, 68, -6750208);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		autoactive = new CheckboxButton(this.guiLeft + 31, this.guiTop + 26, 20, 20, new StringTextComponent("autoactive"), false);
		CheatguiGui.guistate.put("checkbox:autoactive", autoactive);
		this.addButton(autoactive);
		this.addButton(new Button(this.guiLeft + 119, this.guiTop + 3, 51, 20, new StringTextComponent("close"), e -> {
			if (true) {
				JokedMod.PACKET_HANDLER.sendToServer(new CheatguiGui.ButtonPressedMessage(0, x, y, z));
				CheatguiGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + -120, this.guiTop + -75, 82, 20, new StringTextComponent("name editor"), e -> {
			if (true) {
				JokedMod.PACKET_HANDLER.sendToServer(new CheatguiGui.ButtonPressedMessage(1, x, y, z));
				CheatguiGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + -120, this.guiTop + -54, 40, 20, new StringTextComponent("fly"), e -> {
			if (true) {
				JokedMod.PACKET_HANDLER.sendToServer(new CheatguiGui.ButtonPressedMessage(2, x, y, z));
				CheatguiGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + -120, this.guiTop + -31, 51, 20, new StringTextComponent("x-ray"), e -> {
			if (true) {
				JokedMod.PACKET_HANDLER.sendToServer(new CheatguiGui.ButtonPressedMessage(3, x, y, z));
				CheatguiGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + -120, this.guiTop + -10, 67, 20, new StringTextComponent("killaura"), e -> {
			if (true) {
				JokedMod.PACKET_HANDLER.sendToServer(new CheatguiGui.ButtonPressedMessage(4, x, y, z));
				CheatguiGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + -119, this.guiTop + 14, 56, 20, new StringTextComponent("aimbot"), e -> {
			if (true) {
				JokedMod.PACKET_HANDLER.sendToServer(new CheatguiGui.ButtonPressedMessage(5, x, y, z));
				CheatguiGui.handleButtonAction(entity, 5, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + -118, this.guiTop + 39, 35, 20, new StringTextComponent("tp"), e -> {
			if (true) {
				JokedMod.PACKET_HANDLER.sendToServer(new CheatguiGui.ButtonPressedMessage(6, x, y, z));
				CheatguiGui.handleButtonAction(entity, 6, x, y, z);
			}
		}));
	}
}
