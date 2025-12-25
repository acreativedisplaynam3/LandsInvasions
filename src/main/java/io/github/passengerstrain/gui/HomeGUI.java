package io.github.passengerstrain.gui;

import io.github.passengerstrain.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("unused")
public class HomeGUI implements Listener {

    private final Inventory inventory;
    private final ConfigUtils configUtils;

    public HomeGUI(ConfigUtils configUtils) {
        this.configUtils = configUtils;
        inventory = Bukkit.createInventory(null, 36, "Your Home");
        initializeItems();
    }

    public void initializeItems() {
        for (int i = 0; i < 5; i++) {
            inventory.setItem(i, createGuiItem("Your Home # " + (i + 1), "Set your current location as home!"));
        }
    }

    protected ItemStack createGuiItem(final String name, final String... lore) {
        final ItemStack item = new ItemStack(Material.GRAY_BED, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getInventory().equals(inventory)) {
            return;
        }

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) {
            return;
        }

        Player player = (Player) e.getWhoClicked();
        int homeIndex = e.getSlot();
        configUtils.set(player, homeIndex);
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent e) {
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);
        }
    }
}
