package lv.id.bonne.itemsadderhook;

import org.bukkit.Material;

import lv.id.bonne.itemsadderhook.events.BlockInteractListener;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.api.flags.clicklisteners.CycleClick;
import world.bentobox.bentobox.managers.RanksManager;


/**
 * This class inits ItemsAdderHook Addon.
 */
public final class ItemsAdderHookAddon extends Addon
{
    /**
     * Executes code when loading the addon. This is called before {@link #onEnable()}.
     * This <b>must</b> be used to setup configuration, worlds and commands.
     */
    public void onLoad()
    {
    }


    /**
     * Executes code when enabling the addon. This is called after {@link #onLoad()}.
     * <br/> Note that commands and worlds registration <b>must</b> be done in {@link
     * #onLoad()}, if need be. Failure to do so <b>will</b> result in issues such as
     * tab-completion not working for commands.
     */
    public void onEnable()
    {
        // Check if it is enabled - it might be loaded, but not enabled.
        if (this.getPlugin() == null || !this.getPlugin().isEnabled())
        {
            this.logError("BentoBox is not available or disabled!");
            this.setState(State.DISABLED);
            return;
        }

        // Check if RoseStacker exists.
        if (!this.getServer().getPluginManager().isPluginEnabled("ItemsAdder"))
        {
            this.logError("ItemsAdder is not available or disabled!");
            this.setState(State.DISABLED);
            return;
        }

        // Register listener
        this.registerListener(new BlockInteractListener());
        this.registerFlag(ITEMS_ADDER_EXPLOSIONS);
    }


    /**
     * Executes code when disabling the addon.
     */
    public void onDisable()
    {
    }


    /**
     * This flag allows to switch which island member group can use explosive items from Items Adder.
     */
    public final static Flag ITEMS_ADDER_EXPLOSIONS =
        new Flag.Builder("ITEMS_ADDER_EXPLOSIONS", Material.TNT).
            type(Flag.Type.PROTECTION).
            defaultRank(RanksManager.MEMBER_RANK).
            clickHandler(new CycleClick("ITEMS_ADDER_EXPLOSIONS",
                RanksManager.VISITOR_RANK,
                RanksManager.OWNER_RANK)).
            build();
}
