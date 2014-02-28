package io.github.estrongsfield.gonefission.listeners;

import java.util.ArrayList;

import io.github.estrongsfield.gonefission.GoneFission;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class NormalNukePrimeListener implements Listener {
	GoneFission main;
	ArrayList<Block>  nuke;
	
	public NormalNukePrimeListener(GoneFission main) {
		this.main = main;
		nuke = new ArrayList<Block>();
	}
	
	//if a player breaks a block, this listener checks
	//if it is part of a warhead, if not, it sends an error.
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Block trigger = event.getBlock();
		if(trigger.getType().equals(Material.REDSTONE_LAMP_OFF)) {
			ArrayList<Block> adj = new ArrayList<Block>(); //check all surroundings
			
			adj.add(trigger.getRelative(BlockFace.NORTH));
			adj.add(trigger.getRelative(BlockFace.SOUTH));
			adj.add(trigger.getRelative(BlockFace.EAST));
			adj.add(trigger.getRelative(BlockFace.WEST));
			boolean error = true; //if there is an error
			int select = 0;
			for(int x=0;x<adj.size();x++) {
				int counter = 0; //to make sure there is only one chest.
				if(adj.get(x).getType().equals(Material.CHEST)) {
					
					counter++;
					select = x;
				}
			}
			if(count > 1)
				error = true;
			else {
				nuke.add(trigger);
				nuke.add(adj.get(select)); //nuke[0] is the trigger. nuke[1] is the chest
				error = getBlocks();
			}
			
		}
	}
	
	//true if there was an error
	public boolean getBlocks() {
		BlockFace dir = nuke.get(0).getFace(nuke.get(1));
		nuke.add(nuke.get(1).getRelative(dir));
		nuke.add(nuke.get(2).getRelative(dir));
		nuke.add(nuke.get(3).getRelative(dir));
		Block a = nuke.get(3).getRelative(0,1,0); //this may be the wrong y
		nuke.add(a.getRelative(dir.getOppositeFace()));
		nuke.add(nuke.get(4).getRelative(dir.getOppositeFace()));
		nuke.add(nuke.get(5).getRelative(dir.getOppositeFace()));
		nuke.add(nuke.get(6).getRelative(dir.getOppositeFace()));
		nuke.add(nuke.get(6).getRelative(arg0)) //need to get a block to the right
		//left off here. use BlockFace of broken block facing chest
		//to iterate over all blocks in bomb
		
	}

    //alternate method. uses files?
    public boolean getBlock2(){

    }

	
	public BlockFace getLeft() {
		
	}
	
	public BlockFace getRight() {
		
	}
	

}
