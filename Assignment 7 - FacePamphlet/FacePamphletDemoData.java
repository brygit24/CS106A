/* File:  FacePamphletDemoData.java
 * This class contains methods that populate
 * the database with demo profiles.
 */

import acm.util.ErrorException;

public class FacePamphletDemoData implements FacePamphletConstants {
	/*
	 * Constructor 
	 */
	public FacePamphletDemoData() {		
	}
	// Add pics to the associated profiles
	// if one or more of .jpg's missing return notification
	// have the corresponding profiles as of the demoArray
	public boolean addDemoPics(FacePamphletDatabase inDB) {
	    for (int i = 0; i < demoArray.length; i++) {	    	
	    	if (addCheckDemoPics(inDB, demoArray[i]) == false) {
	    		return false;
	    	}	    	
	    }
	    return true;
	}	
	// helper for addDemoPics 
 	private boolean addCheckDemoPics(FacePamphletDatabase inDB, String inStr) { 		
 		try {	
 		    if (inDB.getProfile(inStr).setImage(inStr+".jpg") == false) {
 			    return false;
 			}
 		} 
 		catch (ErrorException ex) { 			
            new ErrorException(ex);			
 			return false;
 		} 		
 		return true;	
 	} 		
    // add demo profiles in accordance with demo array,
 	// just names, no pic, friends or status
 	public void addDemoPeople (FacePamphletDatabase inDB) {
 		for (int i = 0; i < demoArray.length; i++) {
 			//no need to check validity...
 			FacePamphletProfile entry = new FacePamphletProfile(demoArray[i]);
 			String key = entry.getName();
 			inDB.addProfile(key, entry);
 		}
 	}	
	// link the demo friends by association
 	// note, since I did not code the "reciprical" friend in the 
 	// "profile" class,  I have to manually do it here.
	public void linkFriends(FacePamphletDatabase inDB) {
		// curlys
 		inDB.getProfile("curly").addFriend("moe");
 		inDB.getProfile("curly").addFriend("larry");
 		
 		// larrys friends		
		inDB.getProfile("larry").addFriend("curly");
		inDB.getProfile("larry").addFriend("moe");
		
		//moe's friends
		inDB.getProfile("moe").addFriend("curly");
		inDB.getProfile("moe").addFriend("larry"); 
		
		// fred
		inDB.getProfile("fred flintstone").addFriend("wilma flintstone");
		inDB.getProfile("fred flintstone").addFriend("barney rubble");
		inDB.getProfile("fred flintstone").addFriend("pebbles");
		inDB.getProfile("fred flintstone").addFriend("betty rubble");
		inDB.getProfile("fred flintstone").addFriend("egburt");
		inDB.getProfile("fred flintstone").addFriend("bad luck schleprock");
		inDB.getProfile("fred flintstone").addFriend("joe rockhead");
		inDB.getProfile("fred flintstone").addFriend("bam bam");
	
		
		// wilma
		inDB.getProfile("wilma flintstone").addFriend("fred flintstone");
		inDB.getProfile("wilma flintstone").addFriend("barney rubble");
		inDB.getProfile("wilma flintstone").addFriend("pebbles");
		inDB.getProfile("wilma flintstone").addFriend("betty rubble");
		inDB.getProfile("wilma flintstone").addFriend("egburt");
		inDB.getProfile("wilma flintstone").addFriend("bad luck schleprock");
		inDB.getProfile("wilma flintstone").addFriend("joe rockhead");
		inDB.getProfile("wilma flintstone").addFriend("bam bam");
		
		// barney
		inDB.getProfile("barney rubble").addFriend("fred flintstone");
		inDB.getProfile("barney rubble").addFriend("wilma flintstone");
		inDB.getProfile("barney rubble").addFriend("pebbles");
		inDB.getProfile("barney rubble").addFriend("betty rubble");
		inDB.getProfile("barney rubble").addFriend("egburt");
		inDB.getProfile("barney rubble").addFriend("bad luck schleprock");
		inDB.getProfile("barney rubble").addFriend("joe rockhead");
		inDB.getProfile("barney rubble").addFriend("bam bam");
				
		// pebbles
		inDB.getProfile("pebbles").addFriend("fred flintstone");
		inDB.getProfile("pebbles").addFriend("wilma flintstone");
		inDB.getProfile("pebbles").addFriend("barney rubble");
		inDB.getProfile("pebbles").addFriend("betty rubble");
		inDB.getProfile("pebbles").addFriend("egburt");
		inDB.getProfile("pebbles").addFriend("bad luck schleprock");
		inDB.getProfile("pebbles").addFriend("joe rockhead");
		inDB.getProfile("pebbles").addFriend("bam bam");
		
		// betty
		inDB.getProfile("betty rubble").addFriend("fred flintstone");
		inDB.getProfile("betty rubble").addFriend("wilma flintstone");
		inDB.getProfile("betty rubble").addFriend("barney rubble");
		inDB.getProfile("betty rubble").addFriend("pebbles");
		inDB.getProfile("betty rubble").addFriend("egburt");
		inDB.getProfile("betty rubble").addFriend("bad luck schleprock");
		inDB.getProfile("betty rubble").addFriend("joe rockhead");
		inDB.getProfile("betty rubble").addFriend("bam bam");
		
		// egburt		
		inDB.getProfile("egburt").addFriend("fred flintstone");
		inDB.getProfile("egburt").addFriend("wilma flintstone");
		inDB.getProfile("egburt").addFriend("barney rubble");
		inDB.getProfile("egburt").addFriend("pebbles");
		inDB.getProfile("egburt").addFriend("betty rubble");
		inDB.getProfile("egburt").addFriend("bad luck schleprock");
		inDB.getProfile("egburt").addFriend("joe rockhead");
		inDB.getProfile("egburt").addFriend("bam bam");
		
		//scheprock
		inDB.getProfile("bad luck schleprock").addFriend("fred flintstone");
		inDB.getProfile("bad luck schleprock").addFriend("wilma flintstone");
		inDB.getProfile("bad luck schleprock").addFriend("barney rubble");
		inDB.getProfile("bad luck schleprock").addFriend("pebbles");
		inDB.getProfile("bad luck schleprock").addFriend("betty rubble");
		inDB.getProfile("bad luck schleprock").addFriend("egburt");
		inDB.getProfile("bad luck schleprock").addFriend("joe rockhead");
		inDB.getProfile("bad luck schleprock").addFriend("bam bam");
		
		//joe rockhead
		inDB.getProfile("joe rockhead").addFriend("fred flintstone");
		inDB.getProfile("joe rockhead").addFriend("wilma flintstone");
		inDB.getProfile("joe rockhead").addFriend("barney rubble");
		inDB.getProfile("joe rockhead").addFriend("pebbles");
		inDB.getProfile("joe rockhead").addFriend("betty rubble");
		inDB.getProfile("joe rockhead").addFriend("egburt");
		inDB.getProfile("joe rockhead").addFriend("bad luck schleprock");
		inDB.getProfile("joe rockhead").addFriend("bam bam");
		
		//bam bam
		inDB.getProfile("bam bam").addFriend("fred flintstone");
		inDB.getProfile("bam bam").addFriend("wilma flintstone");
		inDB.getProfile("bam bam").addFriend("barney rubble");
		inDB.getProfile("bam bam").addFriend("pebbles");
		inDB.getProfile("bam bam").addFriend("betty rubble");
		inDB.getProfile("bam bam").addFriend("egburt");
		inDB.getProfile("bam bam").addFriend("bad luck schleprock");
		inDB.getProfile("bam bam").addFriend("joe rockhead");
		
		
		// al
		inDB.getProfile("al bundy").addFriend("peggy bundy");
		inDB.getProfile("al bundy").addFriend("kelly bundy");
		inDB.getProfile("al bundy").addFriend("bud bundy");		
		inDB.getProfile("al bundy").addFriend("griff");
		inDB.getProfile("al bundy").addFriend("bob rooney");
		inDB.getProfile("al bundy").addFriend("officer dan");
		inDB.getProfile("al bundy").addFriend("marci rhoades");
		inDB.getProfile("al bundy").addFriend("steve rhoades");
		
		// marci
		inDB.getProfile("marci rhoades").addFriend("peggy bundy");
		inDB.getProfile("marci rhoades").addFriend("kelly bundy");
		inDB.getProfile("marci rhoades").addFriend("bud bundy");		
		inDB.getProfile("marci rhoades").addFriend("griff");
		inDB.getProfile("marci rhoades").addFriend("bob rooney");
		inDB.getProfile("marci rhoades").addFriend("officer dan");
		inDB.getProfile("marci rhoades").addFriend("al bundy");
		inDB.getProfile("marci rhoades").addFriend("steve rhoades");
		
		// steve
		inDB.getProfile("steve rhoades").addFriend("peggy bundy");
		inDB.getProfile("steve rhoades").addFriend("kelly bundy");
		inDB.getProfile("steve rhoades").addFriend("bud bundy");		
		inDB.getProfile("steve rhoades").addFriend("griff");
		inDB.getProfile("steve rhoades").addFriend("bob rooney");
		inDB.getProfile("steve rhoades").addFriend("officer dan");
		inDB.getProfile("steve rhoades").addFriend("al bundy");
		inDB.getProfile("steve rhoades").addFriend("marci rhoades");
				
		// bob rooney
		inDB.getProfile("bob rooney").addFriend("peggy bundy");
		inDB.getProfile("bob rooney").addFriend("kelly bundy");
		inDB.getProfile("bob rooney").addFriend("bud bundy");		
		inDB.getProfile("bob rooney").addFriend("griff");
		inDB.getProfile("bob rooney").addFriend("marci rhoades");
		inDB.getProfile("bob rooney").addFriend("officer dan");
		inDB.getProfile("bob rooney").addFriend("al bundy");
		inDB.getProfile("bob rooney").addFriend("steve rhoades");
		
		
		// officer dan
		inDB.getProfile("officer dan").addFriend("peggy bundy");
		inDB.getProfile("officer dan").addFriend("kelly bundy");
		inDB.getProfile("officer dan").addFriend("bud bundy");		
		inDB.getProfile("officer dan").addFriend("griff");
		inDB.getProfile("officer dan").addFriend("marci rhoades");
		inDB.getProfile("officer dan").addFriend("bod rooney");
		inDB.getProfile("officer dan").addFriend("al bundy");
		inDB.getProfile("officer dan").addFriend("steve rhoades");
		
		//peggy
		inDB.getProfile("peggy bundy").addFriend("officer dan");
		inDB.getProfile("peggy bundy").addFriend("kelly bundy");
		inDB.getProfile("peggy bundy").addFriend("bud bundy");		
		inDB.getProfile("peggy bundy").addFriend("griff");
		inDB.getProfile("peggy bundy").addFriend("marci rhoades");
		inDB.getProfile("peggy bundy").addFriend("bod rooney");
		inDB.getProfile("peggy bundy").addFriend("al bundy");
		inDB.getProfile("peggy bundy").addFriend("steve rhoades");
		
		//kelly
		inDB.getProfile("kelly bundy").addFriend("officer dan");
		inDB.getProfile("kelly bundy").addFriend("peggy bundy");
		inDB.getProfile("kelly bundy").addFriend("bud bundy");		
		inDB.getProfile("kelly bundy").addFriend("griff");
		inDB.getProfile("kelly bundy").addFriend("marci rhoades");
		inDB.getProfile("kelly bundy").addFriend("bod rooney");
		inDB.getProfile("kelly bundy").addFriend("al bundy");
		inDB.getProfile("kelly bundy").addFriend("steve rhoades");
						
		//bud
		inDB.getProfile("bud bundy").addFriend("officer dan");
		inDB.getProfile("bud bundy").addFriend("peggy bundy");
		inDB.getProfile("bud bundy").addFriend("kelly bundy");		
		inDB.getProfile("bud bundy").addFriend("griff");
		inDB.getProfile("bud bundy").addFriend("marci rhoades");
		inDB.getProfile("bud bundy").addFriend("bod rooney");
		inDB.getProfile("bud bundy").addFriend("al bundy");
		inDB.getProfile("bud bundy").addFriend("steve rhoades");
				
		//griff
		inDB.getProfile("griff").addFriend("officer dan");
		inDB.getProfile("griff").addFriend("peggy bundy");
		inDB.getProfile("griff").addFriend("kelly bundy");		
		inDB.getProfile("griff").addFriend("bud bundy");
		inDB.getProfile("griff").addFriend("marci rhoades");
		inDB.getProfile("griff").addFriend("bod rooney");
		inDB.getProfile("griff").addFriend("al bundy");
		inDB.getProfile("griff").addFriend("steve rhoades");
								
		// gomez
		inDB.getProfile("gomez").addFriend("ophelia");
		inDB.getProfile("gomez").addFriend("lurch");
		inDB.getProfile("gomez").addFriend("cousin it");		
		inDB.getProfile("gomez").addFriend("morticia");
		inDB.getProfile("gomez").addFriend("uncle fester");
		
		// ophelia
		inDB.getProfile("ophelia").addFriend("gomez");
		inDB.getProfile("ophelia").addFriend("lurch");
		inDB.getProfile("ophelia").addFriend("cousin it");		
		inDB.getProfile("ophelia").addFriend("morticia");
		inDB.getProfile("ophelia").addFriend("uncle fester");
		
		// lurch
		inDB.getProfile("lurch").addFriend("gomez");
		inDB.getProfile("lurch").addFriend("ophelia");
		inDB.getProfile("lurch").addFriend("cousin it");		
		inDB.getProfile("lurch").addFriend("morticia");
		inDB.getProfile("lurch").addFriend("uncle fester");
		
		// cousin it
		inDB.getProfile("cousin it").addFriend("gomez");
		inDB.getProfile("cousin it").addFriend("ophelia");
		inDB.getProfile("cousin it").addFriend("lurch");		
		inDB.getProfile("cousin it").addFriend("morticia");
		inDB.getProfile("cousin it").addFriend("uncle fester");
		
		// morticia
		inDB.getProfile("morticia").addFriend("gomez");
		inDB.getProfile("morticia").addFriend("ophelia");
		inDB.getProfile("morticia").addFriend("lurch");		
		inDB.getProfile("morticia").addFriend("cousin it");
		inDB.getProfile("morticia").addFriend("uncle fester");
		
		// uncle fester
		inDB.getProfile("uncle fester").addFriend("gomez");
		inDB.getProfile("uncle fester").addFriend("ophelia");
		inDB.getProfile("uncle fester").addFriend("lurch");		
		inDB.getProfile("uncle fester").addFriend("cousin it");
		inDB.getProfile("uncle fester").addFriend("morticia");		
	}	
	// demo profile names..
	String demoArray[] = new String[] {"moe", "larry", "curly", "fred flintstone", "wilma flintstone", 
			                          "barney rubble", "betty rubble","pebbles", "egburt", 
			                           "bad luck schleprock", "joe rockhead", "bam bam", "al bundy", "officer dan",
			                           "peggy bundy", "kelly bundy", "bud bundy", "griff", "bob rooney", 
			                           "steve rhoades", "marci rhoades", "uncle fester", "lurch" , 
			                           "gomez" ,"morticia", "ophelia", "cousin it" }; 

}

