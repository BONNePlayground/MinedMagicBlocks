package lv.id.bonne.minedmagicblocks.events;


import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import lv.id.bonne.minedmagicblocks.MinedMagicBlocks;
import world.bentobox.aoneblock.events.MagicBlockEvent;
import world.bentobox.bentobox.api.metadata.MetaDataValue;
import world.bentobox.bentobox.api.user.User;

public class MagicBlockListener implements Listener
{
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockBreak(MagicBlockEvent event)
    {
        if (event.getPlayerUUID() != null)
        {
            User user = User.getInstance(event.getPlayerUUID());

            user.putMetaData(MinedMagicBlocks.MINED_MAGIC_BLOCKS,
                new MetaDataValue(user.getMetaData(MinedMagicBlocks.MINED_MAGIC_BLOCKS).
                    map(MetaDataValue::asLong).
                    orElse(0L) + 1));
        }
    }
}
