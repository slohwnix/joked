
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.slohwnix.fakecheatmod.init;

import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.List;
import java.util.ArrayList;

import fr.slohwnix.fakecheatmod.world.inventory.ShopMenu;
import fr.slohwnix.fakecheatmod.world.inventory.NameplayereditorMenu;
import fr.slohwnix.fakecheatmod.world.inventory.IsnorealcheatguiMenu;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FakecheatmodModMenus {
	private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
	public static final MenuType<IsnorealcheatguiMenu> ISNOREALCHEATGUI = register("isnorealcheatgui",
			(id, inv, extraData) -> new IsnorealcheatguiMenu(id, inv, extraData));
	public static final MenuType<NameplayereditorMenu> NAMEPLAYEREDITOR = register("nameplayereditor",
			(id, inv, extraData) -> new NameplayereditorMenu(id, inv, extraData));
	public static final MenuType<ShopMenu> SHOP = register("shop", (id, inv, extraData) -> new ShopMenu(id, inv, extraData));

	private static <T extends AbstractContainerMenu> MenuType<T> register(String registryname, IContainerFactory<T> containerFactory) {
		MenuType<T> menuType = new MenuType<T>(containerFactory);
		menuType.setRegistryName(registryname);
		REGISTRY.add(menuType);
		return menuType;
	}

	@SubscribeEvent
	public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
	}
}
