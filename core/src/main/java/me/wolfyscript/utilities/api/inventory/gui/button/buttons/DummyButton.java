package me.wolfyscript.utilities.api.inventory.gui.button.buttons;

import me.wolfyscript.utilities.api.inventory.gui.GuiHandler;
import me.wolfyscript.utilities.api.inventory.gui.button.ButtonAction;
import me.wolfyscript.utilities.api.inventory.gui.button.ButtonRender;
import me.wolfyscript.utilities.api.inventory.gui.button.ButtonState;
import me.wolfyscript.utilities.api.inventory.gui.button.ButtonType;
import me.wolfyscript.utilities.api.inventory.gui.cache.CustomCache;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DummyButton<C extends CustomCache> extends ActionButton<C> {

    /*
    This Button acts as a dummy, it will not run the action, even if you set one for the ButtonState!
     */

    public DummyButton(String id, ButtonState<C> state) {
        super(id, ButtonType.DUMMY, state);
    }

    public DummyButton(String id) {
        super(id, ButtonType.DUMMY, null);
    }

    public DummyButton(String id, ItemStack itemStack) {
        this(id, new ButtonState(id, itemStack));
    }

    public DummyButton(String id, Material material) {
        this(id, new ButtonState(id, material));
    }

    public DummyButton(String id, ItemStack itemStack, ButtonAction<C> action) {
        this(id, itemStack, action, null);
    }

    public DummyButton(String id, ItemStack itemStack, ButtonRender<C> render) {
        this(id, itemStack, null, render);
    }

    public DummyButton(String id, ItemStack itemStack, ButtonAction<C> action, ButtonRender<C> render) {
        this(id, new ButtonState(id, itemStack, action, render));
    }

    public DummyButton(String id, Material material, ButtonAction<C> action) {
        this(id, material, action, null);
    }

    public DummyButton(String id, Material material, ButtonRender<C> render) {
        this(id, material, null, render);
    }

    public DummyButton(String id, Material material, ButtonAction<C> action, ButtonRender<C> render) {
        this(id, new ItemStack(material), action, render);
    }

    public boolean execute(GuiHandler<C> guiHandler, Player player, Inventory inventory, int slot, InventoryClickEvent event) {
        //NOTHING
        return true;
    }
}
