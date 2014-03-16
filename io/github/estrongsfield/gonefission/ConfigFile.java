package io.github.estrongsfield.gonefission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.configuration.file.FileConfiguration;
public class ConfigFile {
	public HashMap<String,List<List<List<Integer>>>> shapes; //3d shapes for structures
	public HashMap<String,Integer> triggerBlocks; //blocks that act as triggers for detection (next to a chest)
	FileConfiguration conf;
	Logger log;
	public ConfigFile(FileConfiguration getconf, Logger getlog) {
		shapes = new HashMap<String,List<List<List<Integer>>>>();
		triggerBlocks = new HashMap<String, Integer>();
		conf = getconf;
		log = getlog;
	}
	
	public void load() {
		
		//conf.options().copyDefaults(true);
		/*
		String[] loadList = (String[]) conf.getConfigurationSection("shapes").getKeys(false).toArray();
		
		for(int x=0;x<loadList.length;x++) {
			shapes.put(loadList[x],configToList(loadList[x]));
		}
		*/
		
		List<List<List<Integer>>> atomShape = new ArrayList<List<List<Integer>>>();
		int counter = 0;
		for(int x=0;x<5;x++) {
			atomShape.add(new ArrayList<List<Integer>>());
			for(int i=0;i<5;i++) {
				atomShape.get(x).add(new ArrayList<Integer>());
				for(int q=0;q<5;q++) {
					atomShape.get(x).get(i).add(q, counter);
					counter++;
				}
				
			}
		}
		
		listToConfig(atomShape,"DEFAULT",4);
		
		
		
	}
	
	/* turns a 3d List into a list of 2d grids in a file. File has 'layer' sections,
	 * each consisting of a list of strings. each string is a list of blocks separated
	 * by spaces.
	 * example:
	 * - 2 2 2 2 2
	 * - 2 3 3 3 2
	 * - 2 3 3 3 2
	 * - 2 3 3 3 2
	 * - 2 2 2 2 2
	 *  */
	public void listToConfig(List<List<List<Integer>>> atomShape,String keyType, Integer trigger) {
		//List<List<List<Integer>>> atomShape = new ArrayList<List<List<Integer>>>();
		
		List<String> slice = new ArrayList<String>();
		String curr = "shapes." +keyType+  ".";
		String buffer = "";
		Integer layer = 0;
		int stringSize = 0;
		for(int x=0;x<atomShape.size();x++) {
			for(int i=0;i<atomShape.get(x).size();i++) {
				for(int q=0;q<atomShape.get(x).get(i).size();q++) {
					buffer = buffer + atomShape.get(x).get(i).get(q) + " ";
				}
				stringSize ++;
				slice.add(buffer);
				buffer = "";
			}
			conf.set(curr + layer.toString(),slice);
			conf.set(curr + layer.toString() +"stringSize", stringSize);
			//slice.clear();
			slice = new ArrayList<String>();
			layer++;
			
		}
		conf.set(curr + "layerSize", layer); //to make reading easier
		conf.set(curr + "triggerBlock", trigger);
	}
	
	//takes the config file and dumps it into a 3d array.
	public List<List<List<Integer>>> configToList(String keyType) {
		
		String curr = "shapes." +keyType+  ".";
		String currentList = "";
		List<List<List<Integer>>> atomShape = new ArrayList<List<List<Integer>>>();
		int lSize = conf.getInt(curr + "layerSize");
		int sSize = conf.getInt(curr +"0" + "stringSize");
		for(int x=0;x<=lSize;x++) { //NOTE: there might be a problem with the <= here.
			List<String> slice = conf.getStringList(curr + x);
			atomShape.add(new ArrayList<List<Integer>>());
			for(int i=0;i<=sSize;i++) {
				currentList = slice.get(i);
				atomShape.get(x).add(new ArrayList<Integer>());
				for(int q=0;q<currentList.length();q++) {
					atomShape.get(x).get(i).add(Character.getNumericValue(currentList.charAt(q))); //LEFT OFF HERE. Check this.
				}
			}
			
		}
		
		
		return atomShape;
	}
	
	public Map<String,List<List<List<Integer>>>> getAllShapes() {
		if(shapes.size() != 0) 
			return shapes;
		else
			return null;
	}
	
	public List<List<List<Integer>>> getShape(String name){
		 if(shapes.size() != 0) 
			 return shapes.get(name);
		 else
			 return null;
	}
	
	
}
