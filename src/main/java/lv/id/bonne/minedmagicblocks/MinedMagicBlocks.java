package lv.id.bonne.minedmagicblocks;

import java.util.Optional;

import lv.id.bonne.minedmagicblocks.events.MagicBlockListener;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.addons.GameModeAddon;
import world.bentobox.bentobox.api.metadata.MetaDataValue;


/**
 * This class inits Addon.
 */
public final class MinedMagicBlocks extends Addon
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

        Optional<GameModeAddon> optionalAddon =
            this.getPlugin().getAddonsManager().getAddonByName("AOneBlock");

        if (optionalAddon.isEmpty())
        {
            this.logError("AOneBlock is not available or disabled!");
            this.setState(State.DISABLED);
            return;
        }

        // Register listener
        this.registerListener(new MagicBlockListener());

        optionalAddon.ifPresent(aoneblock ->
            this.getPlugin().getPlaceholdersManager().registerPlaceholder(aoneblock,
                "player_mined_total_magic_blocks",
                user -> user == null ? "0" :
                    String.valueOf(user.getMetaData(MINED_MAGIC_BLOCKS).
                        map(MetaDataValue::asLong).
                        orElse(0L))));
    }


    /**
     * Executes code when disabling the addon.
     */
    public void onDisable()
    {
    }


    public static final String MINED_MAGIC_BLOCKS = "mined-magic-blocks";
}
