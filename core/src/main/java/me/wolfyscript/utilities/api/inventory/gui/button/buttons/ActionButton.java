package me.wolfyscript.utilities.api.inventory.gui.button.buttons;

import me.wolfyscript.utilities.api.WolfyUtilities;
import me.wolfyscript.utilities.api.inventory.gui.GuiHandler;
import me.wolfyscript.utilities.api.inventory.gui.GuiUpdate;
import me.wolfyscript.utilities.api.inventory.gui.GuiWindow;
import me.wolfyscript.utilities.api.inventory.gui.button.*;
import me.wolfyscript.utilities.api.inventory.gui.cache.CustomCache;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

/**
 * Button that executes the action method and renders the item as set in the render method of the ButtonState.
 *
 * action - these methods are executed when the button is clicked.
 *
 * render - these methods are executed when the button is rendered in one of
 * the possible render methods: {@link GuiWindow#onUpdateAsync(GuiUpdate)}, {@link GuiWindow#onUpdateSync(GuiUpdate)}
 *
 * You can set them directly using the constructor and the id of the button is passed into the ButtonState.
 * If the ButtonState requires another key (e.g. when using global item names from lang file) you need to create an ButtonState instance and use
 * {@link ActionButton#ActionButton(String, ButtonState)}
 *
 */
public class ActionButton<C extends CustomCache> extends Button<C> {

    private final String id;
    private final ButtonType type;
    private final ButtonState<C> state;

    protected ActionButton(String id, ButtonType type, ButtonState<C> state) {
        super(id, type);
        this.id = id;
        this.type = type;
        this.state = state;
    }

    public ActionButton(String id, ButtonState<C> state) {
        this(id, ButtonType.NORMAL, state);
    }

    public ActionButton(String id, ItemStack itemStack) {
        this(id, new ButtonState<>(id, itemStack));
    }

    public ActionButton(String id, ItemStack itemStack, ButtonAction<C> action) {
        this(id, itemStack, action, null);
    }

    public ActionButton(String id, ItemStack itemStack, ButtonRender<C> render) {
        this(id, itemStack, null, render);
    }

    public ActionButton(String id, ItemStack itemStack, ButtonAction<C> action, ButtonRender<C> render) {
        this(id, new ButtonState<>(id, itemStack, action, render));
    }

    public ActionButton(String id, Material material) {
        this(id, new ButtonState<>(id, material));
    }

    public ActionButton(String id, Material material, ButtonAction<C> action) {
        this(id, material, action, null);
    }

    public ActionButton(String id, Material material, ButtonRender<C> render) {
        this(id, material, null, render);
    }

    public ActionButton(String id, Material material, ButtonAction<C> action, ButtonRender<C> render) {
        this(id, new ButtonState<>(id, material, action, render));
    }

    public void init(GuiWindow<C> guiWindow) {
        state.init(guiWindow);
    }

    @Override
    public void init(String clusterID, WolfyUtilities api) {
        state.init(clusterID, api);
    }

    public boolean execute(GuiHandler<C> guiHandler, Player player, Inventory inventory, int slot, InventoryClickEvent event) throws IOException {
        if (!type.equals(ButtonType.DUMMY) && state.getAction() != null) {
            return state.getAction().run(guiHandler, player, inventory, slot, event);
        }
        return true;
    }

    @Override
    public void postExecute(GuiHandler<C> guiHandler, Player player, Inventory inventory, ItemStack itemStack, int slot, InventoryInteractEvent event) throws IOException {

    }

    @Override
    public void prepareRender(GuiHandler<C> guiHandler, Player player, Inventory inventory, ItemStack itemStack, int slot, boolean help) {
        if (state.getPrepareRender() != null) {
            state.getPrepareRender().prepare(guiHandler, player, inventory, itemStack, slot, help);
        }
    }

    @Override
    public void render(GuiHandler<C> guiHandler, Player player, Inventory inventory, int slot, boolean help) throws IOException {
        applyItem(guiHandler, player, inventory, state, slot, help);
    }

    public ButtonType getType() {
        return type;
    }

    public ButtonState<C> getState() {
        return state;
    }

    public String getId() {
        return id;
    }
}
