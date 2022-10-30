
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.slohwnix.fakecheatmod.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import fr.slohwnix.fakecheatmod.network.ViewcheatMessage;
import fr.slohwnix.fakecheatmod.FakecheatmodMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class FakecheatmodModKeyMappings {
	public static final KeyMapping VIEWCHEAT = new KeyMapping("key.fakecheatmod.viewcheat", GLFW.GLFW_KEY_F1, "key.categories.cheat");

	@SubscribeEvent
	public static void registerKeyBindings(FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(VIEWCHEAT);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onKeyInput(InputEvent.KeyInputEvent event) {
			if (Minecraft.getInstance().screen == null) {
				if (event.getKey() == VIEWCHEAT.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						FakecheatmodMod.PACKET_HANDLER.sendToServer(new ViewcheatMessage(0, 0));
						ViewcheatMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
			}
		}
	}
}
