package dungeonescape.ai;

import java.util.ArrayList;

import dungeonescape.helper.NPC_const;

public class NPCS {
	private ArrayList<NPC> npcs;
	
	public NPCS() {
		npcs = new ArrayList<NPC>();
		
		for (Integer npc_id : NPC_const.npc_list) {
			npcs.add(new NPC(npc_id));
		}
	}

	public ArrayList<NPC> getNpcs() {
		return npcs;
	}
}
