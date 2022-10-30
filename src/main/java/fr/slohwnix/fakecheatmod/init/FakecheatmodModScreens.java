
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.slohwnix.fakecheatmod.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import fr.slohwnix.fakecheatmod.client.gui.ShopScreen;
import fr.slohwnix.fakecheatmod.client.gui.NameplayereditorScreen;
import fr.slohwnix.fakecheatmod.client.gui.IsnorealcheatguiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FakecheatmodModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(FakecheatmodModMenus.ISNOREALCHEATGUI, IsnorealcheatguiScreen::new);
			MenuScreens.register(FakecheatmodModMenus.NAMEPLAYEREDITOR, NameplayereditorScreen::new);
			MenuScreens.register(FakecheatmodModMenus.SHOP, ShopScreen::new);
		});
	}
}
