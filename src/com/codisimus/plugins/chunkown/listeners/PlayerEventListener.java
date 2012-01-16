package com.codisimus.plugins.chunkown.listeners;

import com.codisimus.plugins.chunkown.ChunkOwn;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

/**
 * Listens for commands and griefing events
 * 
 * @author Codisimus
 */
public class PlayerEventListener extends PlayerListener {

    /**
     * Buckets can only be emptied within an OwnedChunk by the owner, a coowner, or an admin
     * 
     * @param event The PlayerBucketEmptyEvent that occurred
     */
    @Override
    public void onPlayerBucketEmpty (PlayerBucketEmptyEvent event) {
        if (!ChunkOwn.canBuild(event.getPlayer(), event.getBlockClicked().getRelative(event.getBlockFace())))
            event.setCancelled(true);
    }

    /**
     * Buckets can only be filled within an OwnedChunk by the owner, a coowner, or an admin
     * 
     * @param event The PlayerBucketFillEvent that occurred
     */
    @Override
    public void onPlayerBucketFill (PlayerBucketFillEvent event) {
        if (!ChunkOwn.canBuild(event.getPlayer(), event.getBlockClicked()))
            event.setCancelled(true);
    }
    
    /**
     * Chests can only be opened within an OwnedChunk by the owner, a coowner, or an admin
     * 
     * @param event The PlayerInteractEvent that occurred
     */
    @Override
    public void onPlayerInteract (PlayerInteractEvent event) {
        //Return if the event was not opening a Chest
        if (event.getMaterial() != Material.CHEST || event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        
        if (!ChunkOwn.canBuild(event.getPlayer(), event.getClickedBlock()))
            event.setCancelled(true);
    }
    
    /**
     * Updates the last time that Players that own Chunks were seen
     * 
     * @param event The PlayerJoinEvent that occurred
     */
    @Override
    public void onPlayerJoin (PlayerJoinEvent event) {
        String name = event.getPlayer().getName();
        
        Object object = ChunkOwn.chunkCounter.get(name);
        if (object == null)
            return;
        
        if ((Integer)object > 0)
            ChunkOwn.lastDaySeen.setProperty(name, String.valueOf(ChunkOwn.getDayAD()));
        
        ChunkOwn.saveLastSeen();
    }
    
    /**
     * Updates the last time that Players that own Chunks were seen
     * 
     * @param event The PlayerQuitEvent that occurred
     */
    @Override
    public void onPlayerQuit (PlayerQuitEvent event) {
        String name = event.getPlayer().getName();
        
        Object object = ChunkOwn.chunkCounter.get(name);
        if (object == null)
            return;
        
        if ((Integer)object > 0)
            ChunkOwn.lastDaySeen.setProperty(name, String.valueOf(ChunkOwn.getDayAD()));
        
        ChunkOwn.saveLastSeen();
    }
}
