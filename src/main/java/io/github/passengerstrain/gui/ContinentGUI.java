package io.github.passengerstrain.gui;

import io.github.passengerstrain.utils.ConfigUtils;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class ContinentGUI implements Listener {

    private final Inventory inventory;
    private final ConfigUtils configUtils;

    public ContinentGUI(ConfigUtils configUtils) {
        this.configUtils = configUtils;
        int guiSize = configUtils.getGuiConfiguration().getInt("gui.gui-size");
        String guiName = configUtils.getGuiConfiguration().getString("gui.gui-name");
        inventory = Bukkit.createInventory(null, guiSize, guiName != null ? guiName : "Default Name");

        initializeItems();
    }

    public void initializeItems() {
        for (int i = 0; i < 7; i++) {
            String name = configUtils.getGuiConfiguration().getString("continents." + i + ".name");
            String displayItem = configUtils.getGuiConfiguration().getString("continents." + i + ".displayitem");
            List<String> lore = configUtils.getGuiConfiguration().getStringList("continents." + i + ".lore");
            Material material = Material.getMaterial(displayItem != null ? displayItem.toLowerCase() : "E");
            inventory.addItem(createGuiItem(material, name, lore.toArray(new String[0])));
        }
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList(lore));
            item.setItemMeta(meta);
        }
        return item;
    }

    public void openInventory(final Player player) {
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getInventory().equals(inventory)) {
            return;
        }

        e.setCancelled(true);

        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) {
            return;
        }

        Player player = (Player) e.getWhoClicked();

        int slot = e.getRawSlot();
        if (slot >= 0 && slot < 7) {
            double x = configUtils.getGuiConfiguration().getDouble("continents." + slot + ".teleport.x");
            double y = configUtils.getGuiConfiguration().getDouble("continents." + slot + ".teleport.y");
            double z = configUtils.getGuiConfiguration().getDouble("continents." + slot + ".teleport.z");

            Location location = new Location(player.getWorld(), x, y, z);
            player.teleport(location);
        }
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent e) {
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);
        }
    }
}
