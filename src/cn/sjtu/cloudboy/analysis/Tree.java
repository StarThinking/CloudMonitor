package cn.sjtu.cloudboy.analysis;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Tree {
	

	public static double regionFailure = 0.05;
	public static double zoneFailure = 0.07;
	public static double hostFailure = 0.1;
	public static TreeNode cloud = new TreeNode("cloud", null, Tree.regionFailure, Tree.regionFailure);
	
	public static TreeNode z1 = new TreeNode("ZONE1", Tree.cloud, Tree.zoneFailure, Tree.zoneFailure);
	public static TreeNode z2 = new TreeNode("ZONE2", Tree.cloud, Tree.zoneFailure, Tree.zoneFailure);

	public static TreeNode pm1 = new TreeNode("172.16.1.1", Tree.z1, Tree.hostFailure, Tree.hostFailure);
	public static TreeNode pm2 = new TreeNode("172.16.1.2", Tree.z1, Tree.hostFailure, Tree.hostFailure);
	public static TreeNode pm3 = new TreeNode("172.16.1.3", Tree.z2, Tree.hostFailure, Tree.hostFailure);
	public static TreeNode pm4 = new TreeNode("172.16.1.4", Tree.z2, Tree.hostFailure, Tree.hostFailure);
//
//	public static TreeNode r1 = new TreeNode("region1", cloud, regionFailure,
//			regionFailure);
//	public static TreeNode r2 = new TreeNode("region2", cloud, regionFailure,
//			regionFailure);
//	public static TreeNode r3 = new TreeNode("region3", cloud, regionFailure,
//			regionFailure);
//	public static TreeNode r4 = new TreeNode("region4", cloud, regionFailure,
//			regionFailure);
//	public static TreeNode r5 = new TreeNode("region5", cloud, regionFailure,
//			regionFailure);
//	
//	
//	public static TreeNode z11 = new TreeNode("zone11", r1, zoneFailure,
//			zoneFailure);
//	public static TreeNode z12 = new TreeNode("zone12", r1, zoneFailure,
//			zoneFailure);
//	public static TreeNode z13 = new TreeNode("zone13", r1, zoneFailure,
//			zoneFailure);
//	public static TreeNode z14 = new TreeNode("zone14", r1, zoneFailure,
//			zoneFailure);
//	public static TreeNode z15 = new TreeNode("zone15", r1, zoneFailure,
//			zoneFailure);
//	
//
//	public static TreeNode z21 = new TreeNode("zone21", r2, zoneFailure,
//			zoneFailure);
//	public static TreeNode z22 = new TreeNode("zone22", r2, zoneFailure,
//			zoneFailure);
//	public static TreeNode z23 = new TreeNode("zone23", r2, zoneFailure,
//			zoneFailure);
//	public static TreeNode z24 = new TreeNode("zone24", r2, zoneFailure,
//			zoneFailure);
//	public static TreeNode z25 = new TreeNode("zone25", r2, zoneFailure,
//			zoneFailure);
//
//	public static TreeNode z31 = new TreeNode("zone31", r3, zoneFailure,
//			zoneFailure);
//	public static TreeNode z32 = new TreeNode("zone32", r3, zoneFailure,
//			zoneFailure);
//	public static TreeNode z33 = new TreeNode("zone33", r3, zoneFailure,
//			zoneFailure);
//	public static TreeNode z34 = new TreeNode("zone34", r3, zoneFailure,
//			zoneFailure);
//	public static TreeNode z35 = new TreeNode("zone35", r3, zoneFailure,
//			zoneFailure);
//
//	public static TreeNode z41 = new TreeNode("zone41", r4, zoneFailure,
//			zoneFailure);
//	public static TreeNode z42 = new TreeNode("zone42", r4, zoneFailure,
//			zoneFailure);
//	public static TreeNode z43 = new TreeNode("zone43", r4, zoneFailure,
//			zoneFailure);
//	public static TreeNode z44 = new TreeNode("zone44", r4, zoneFailure,
//			zoneFailure);
//	public static TreeNode z45 = new TreeNode("zone45", r4, zoneFailure,
//			zoneFailure);
//
//	public static TreeNode z51 = new TreeNode("zone51", r5, zoneFailure,
//			zoneFailure);
//	public static TreeNode z52 = new TreeNode("zone52", r5, zoneFailure,
//			zoneFailure);
//	public static TreeNode z53 = new TreeNode("zone53", r5, zoneFailure,
//			zoneFailure);
//	public static TreeNode z54 = new TreeNode("zone54", r5, zoneFailure,
//			zoneFailure);
//	public static TreeNode z55 = new TreeNode("zone55", r5, zoneFailure,
//			zoneFailure);
//
//	// region1
//	public static TreeNode p111 = new TreeNode("PM111", z11, hostFailure,
//			hostFailure);
//	public static TreeNode p112 = new TreeNode("PM112", z11, hostFailure,
//			hostFailure);
//	public static TreeNode p113 = new TreeNode("PM113", z11, hostFailure,
//			hostFailure);
//	public static TreeNode p114 = new TreeNode("PM114", z11, hostFailure,
//			hostFailure);
//	public static TreeNode p115 = new TreeNode("PM115", z11, hostFailure,
//			hostFailure);
//	public static TreeNode p116 = new TreeNode("PM116", z11, hostFailure,
//			hostFailure);
//	public static TreeNode p117 = new TreeNode("PM117", z11, hostFailure,
//			hostFailure);
//	
//
//	public static TreeNode p121 = new TreeNode("PM121", z12, hostFailure,
//			hostFailure);
//	public static TreeNode p122 = new TreeNode("PM122", z12, hostFailure,
//			hostFailure);
//	public static TreeNode p123 = new TreeNode("PM123", z12, hostFailure,
//			hostFailure);
//	public static TreeNode p124 = new TreeNode("PM124", z12, hostFailure,
//			hostFailure);
//	public static TreeNode p125 = new TreeNode("PM125", z12, hostFailure,
//			hostFailure);
//	public static TreeNode p126 = new TreeNode("PM126", z12, hostFailure,
//			hostFailure);
//	public static TreeNode p127 = new TreeNode("PM127", z12, hostFailure,
//			hostFailure);
//
//	public static TreeNode p131 = new TreeNode("PM131", z13, hostFailure,
//			hostFailure);
//	public static TreeNode p132 = new TreeNode("PM132", z13, hostFailure,
//			hostFailure);
//	public static TreeNode p133 = new TreeNode("PM133", z13, hostFailure,
//			hostFailure);
//	public static TreeNode p134 = new TreeNode("PM134", z13, hostFailure,
//			hostFailure);
//	public static TreeNode p135 = new TreeNode("PM135", z13, hostFailure,
//			hostFailure);
//	public static TreeNode p136 = new TreeNode("PM136", z13, hostFailure,
//			hostFailure);
//	public static TreeNode p137 = new TreeNode("PM137", z13, hostFailure,
//			hostFailure);
//
//	public static TreeNode p141 = new TreeNode("PM141", z14, hostFailure,
//			hostFailure);
//	public static TreeNode p142 = new TreeNode("PM142", z14, hostFailure,
//			hostFailure);
//	public static TreeNode p143 = new TreeNode("PM143", z14, hostFailure,
//			hostFailure);
//	public static TreeNode p144 = new TreeNode("PM144", z14, hostFailure,
//			hostFailure);
//	public static TreeNode p145 = new TreeNode("PM145", z14, hostFailure,
//			hostFailure);
//	public static TreeNode p146 = new TreeNode("PM146", z14, hostFailure,
//			hostFailure);
//	public static TreeNode p147 = new TreeNode("PM147", z14, hostFailure,
//			hostFailure);
//	
//	public static TreeNode p151 = new TreeNode("PM151", z15, hostFailure,
//			hostFailure);
//	public static TreeNode p152 = new TreeNode("PM152", z15, hostFailure,
//			hostFailure);
//	public static TreeNode p153 = new TreeNode("PM153", z15, hostFailure,
//			hostFailure);
//	public static TreeNode p154 = new TreeNode("PM154", z15, hostFailure,
//			hostFailure);
//	public static TreeNode p155 = new TreeNode("PM155", z15, hostFailure,
//			hostFailure);
//	public static TreeNode p156 = new TreeNode("PM156", z15, hostFailure,
//			hostFailure);
//	public static TreeNode p157 = new TreeNode("PM157", z15, hostFailure,
//			hostFailure);
//	
//	// region2
//	public static TreeNode p211 = new TreeNode("PM211", z21, hostFailure,
//			hostFailure);
//	public static TreeNode p212 = new TreeNode("PM212", z21, hostFailure,
//			hostFailure);
//	public static TreeNode p213 = new TreeNode("PM213", z21, hostFailure,
//			hostFailure);
//	public static TreeNode p214 = new TreeNode("PM214", z21, hostFailure,
//			hostFailure);
//	public static TreeNode p215 = new TreeNode("PM215", z21, hostFailure,
//			hostFailure);
//	public static TreeNode p216 = new TreeNode("PM216", z21, hostFailure,
//			hostFailure);
//	public static TreeNode p217 = new TreeNode("PM217", z21, hostFailure,
//			hostFailure);
//
//	public static TreeNode p221 = new TreeNode("PM221", z22, hostFailure,
//			hostFailure);
//	public static TreeNode p222 = new TreeNode("PM222", z22, hostFailure,
//			hostFailure);
//	public static TreeNode p223 = new TreeNode("PM223", z22, hostFailure,
//			hostFailure);
//	public static TreeNode p224 = new TreeNode("PM224", z22, hostFailure,
//			hostFailure);
//	public static TreeNode p225 = new TreeNode("PM225", z22, hostFailure,
//			hostFailure);
//	public static TreeNode p226 = new TreeNode("PM226", z22, hostFailure,
//			hostFailure);
//	public static TreeNode p227 = new TreeNode("PM227", z22, hostFailure,
//			hostFailure);
//
//	public static TreeNode p231 = new TreeNode("PM231", z23, hostFailure,
//			hostFailure);
//	public static TreeNode p232 = new TreeNode("PM232", z23, hostFailure,
//			hostFailure);
//	public static TreeNode p233 = new TreeNode("PM233", z23, hostFailure,
//			hostFailure);
//	public static TreeNode p234 = new TreeNode("PM234", z23, hostFailure,
//			hostFailure);
//	public static TreeNode p235 = new TreeNode("PM235", z23, hostFailure,
//			hostFailure);
//	public static TreeNode p236 = new TreeNode("PM236", z23, hostFailure,
//			hostFailure);
//	public static TreeNode p237 = new TreeNode("PM237", z23, hostFailure,
//			hostFailure);
//	
//	public static TreeNode p241 = new TreeNode("PM241", z24, hostFailure,
//			hostFailure);
//	public static TreeNode p242 = new TreeNode("PM242", z24, hostFailure,
//			hostFailure);
//	public static TreeNode p243 = new TreeNode("PM243", z24, hostFailure,
//			hostFailure);
//	public static TreeNode p244 = new TreeNode("PM244", z24, hostFailure,
//			hostFailure);
//	public static TreeNode p245 = new TreeNode("PM245", z24, hostFailure,
//			hostFailure);
//	public static TreeNode p246 = new TreeNode("PM246", z24, hostFailure,
//			hostFailure);
//	public static TreeNode p247 = new TreeNode("PM247", z24, hostFailure,
//			hostFailure);
//	
//	public static TreeNode p251 = new TreeNode("PM251", z25, hostFailure,
//			hostFailure);
//	public static TreeNode p252 = new TreeNode("PM252", z25, hostFailure,
//			hostFailure);
//	public static TreeNode p253 = new TreeNode("PM253", z25, hostFailure,
//			hostFailure);
//	public static TreeNode p254 = new TreeNode("PM254", z25, hostFailure,
//			hostFailure);
//	public static TreeNode p255 = new TreeNode("PM255", z25, hostFailure,
//			hostFailure);
//	public static TreeNode p256 = new TreeNode("PM256", z25, hostFailure,
//			hostFailure);
//	public static TreeNode p257 = new TreeNode("PM257", z25, hostFailure,
//			hostFailure);
//	// region3
//	public static TreeNode p311 = new TreeNode("PM311", z31, hostFailure,
//			hostFailure);
//	public static TreeNode p312 = new TreeNode("PM312", z31, hostFailure,
//			hostFailure);
//	public static TreeNode p313 = new TreeNode("PM313", z31, hostFailure,
//			hostFailure);
//	public static TreeNode p314 = new TreeNode("PM314", z31, hostFailure,
//			hostFailure);
//	public static TreeNode p315 = new TreeNode("PM315", z31, hostFailure,
//			hostFailure);
//	public static TreeNode p316 = new TreeNode("PM316", z31, hostFailure,
//			hostFailure);
//	public static TreeNode p317 = new TreeNode("PM317", z31, hostFailure,
//			hostFailure);
//
//	public static TreeNode p321 = new TreeNode("PM321", z32, hostFailure,
//			hostFailure);
//	public static TreeNode p322 = new TreeNode("PM322", z32, hostFailure,
//			hostFailure);
//	public static TreeNode p323 = new TreeNode("PM323", z32, hostFailure,
//			hostFailure);
//	public static TreeNode p324 = new TreeNode("PM324", z32, hostFailure,
//			hostFailure);
//	public static TreeNode p325 = new TreeNode("PM325", z32, hostFailure,
//			hostFailure);
//	public static TreeNode p326 = new TreeNode("PM326", z32, hostFailure,
//			hostFailure);
//	public static TreeNode p327 = new TreeNode("PM327", z32, hostFailure,
//			hostFailure);
//
//	public static TreeNode p331 = new TreeNode("PM331", z33, hostFailure,
//			hostFailure);
//	public static TreeNode p332 = new TreeNode("PM332", z33, hostFailure,
//			hostFailure);
//	public static TreeNode p333 = new TreeNode("PM333", z33, hostFailure,
//			hostFailure);
//	public static TreeNode p334 = new TreeNode("PM334", z33, hostFailure,
//			hostFailure);
//	public static TreeNode p335 = new TreeNode("PM335", z33, hostFailure,
//			hostFailure);
//	public static TreeNode p336 = new TreeNode("PM336", z33, hostFailure,
//			hostFailure);
//	public static TreeNode p337 = new TreeNode("PM337", z33, hostFailure,
//			hostFailure);
//	
//	public static TreeNode p341 = new TreeNode("PM341", z34, hostFailure,
//			hostFailure);
//	public static TreeNode p342 = new TreeNode("PM342", z34, hostFailure,
//			hostFailure);
//	public static TreeNode p343 = new TreeNode("PM343", z34, hostFailure,
//			hostFailure);
//	public static TreeNode p344 = new TreeNode("PM344", z34, hostFailure,
//			hostFailure);
//	public static TreeNode p345 = new TreeNode("PM345", z34, hostFailure,
//			hostFailure);
//	public static TreeNode p346 = new TreeNode("PM346", z34, hostFailure,
//			hostFailure);
//	public static TreeNode p347 = new TreeNode("PM347", z34, hostFailure,
//			hostFailure);
//	
//	public static TreeNode p351 = new TreeNode("PM351", z35, hostFailure,
//			hostFailure);
//	public static TreeNode p352 = new TreeNode("PM352", z35, hostFailure,
//			hostFailure);
//	public static TreeNode p353 = new TreeNode("PM353", z35, hostFailure,
//			hostFailure);
//	public static TreeNode p354 = new TreeNode("PM354", z35, hostFailure,
//			hostFailure);
//	public static TreeNode p355 = new TreeNode("PM355", z35, hostFailure,
//			hostFailure);
//	public static TreeNode p356 = new TreeNode("PM356", z35, hostFailure,
//			hostFailure);
//	public static TreeNode p357 = new TreeNode("PM357", z35, hostFailure,
//			hostFailure);
//
//	// region4
//	public static TreeNode p411 = new TreeNode("PM411", z41, hostFailure,
//			hostFailure);
//	public static TreeNode p412 = new TreeNode("PM412", z41, hostFailure,
//			hostFailure);
//	public static TreeNode p413 = new TreeNode("PM413", z41, hostFailure,
//			hostFailure);
//	public static TreeNode p414 = new TreeNode("PM414", z41, hostFailure,
//			hostFailure);
//	public static TreeNode p415 = new TreeNode("PM415", z41, hostFailure,
//			hostFailure);
//	public static TreeNode p416 = new TreeNode("PM416", z41, hostFailure,
//			hostFailure);
//	public static TreeNode p417 = new TreeNode("PM417", z41, hostFailure,
//			hostFailure);
//
//	public static TreeNode p421 = new TreeNode("PM421", z42, hostFailure,
//			hostFailure);
//	public static TreeNode p422 = new TreeNode("PM422", z42, hostFailure,
//			hostFailure);
//	public static TreeNode p423 = new TreeNode("PM423", z42, hostFailure,
//			hostFailure);
//	public static TreeNode p424 = new TreeNode("PM424", z42, hostFailure,
//			hostFailure);
//	public static TreeNode p425 = new TreeNode("PM425", z42, hostFailure,
//			hostFailure);
//	public static TreeNode p426 = new TreeNode("PM426", z42, hostFailure,
//			hostFailure);
//	public static TreeNode p427 = new TreeNode("PM427", z42, hostFailure,
//			hostFailure);
//
//	public static TreeNode p431 = new TreeNode("PM431", z43, hostFailure,
//			hostFailure);
//	public static TreeNode p432 = new TreeNode("PM432", z43, hostFailure,
//			hostFailure);
//	public static TreeNode p433 = new TreeNode("PM433", z43, hostFailure,
//			hostFailure);
//	public static TreeNode p434 = new TreeNode("PM434", z43, hostFailure,
//			hostFailure);
//	public static TreeNode p435 = new TreeNode("PM435", z43, hostFailure,
//			hostFailure);
//	public static TreeNode p436 = new TreeNode("PM436", z43, hostFailure,
//			hostFailure);
//	public static TreeNode p437 = new TreeNode("PM437", z43, hostFailure,
//			hostFailure);
//	
//	public static TreeNode p441 = new TreeNode("PM441", z44, hostFailure,
//			hostFailure);
//	public static TreeNode p442 = new TreeNode("PM442", z44, hostFailure,
//			hostFailure);
//	public static TreeNode p443 = new TreeNode("PM443", z44, hostFailure,
//			hostFailure);
//	public static TreeNode p444 = new TreeNode("PM444", z44, hostFailure,
//			hostFailure);
//	public static TreeNode p445 = new TreeNode("PM445", z44, hostFailure,
//			hostFailure);
//	public static TreeNode p446 = new TreeNode("PM446", z44, hostFailure,
//			hostFailure);
//	public static TreeNode p447 = new TreeNode("PM447", z44, hostFailure,
//			hostFailure);
//	
//	public static TreeNode p451 = new TreeNode("PM451", z45, hostFailure,
//			hostFailure);
//	public static TreeNode p452 = new TreeNode("PM452", z45, hostFailure,
//			hostFailure);
//	public static TreeNode p453 = new TreeNode("PM453", z45, hostFailure,
//			hostFailure);
//	public static TreeNode p454 = new TreeNode("PM454", z45, hostFailure,
//			hostFailure);
//	public static TreeNode p455 = new TreeNode("PM455", z45, hostFailure,
//			hostFailure);
//	public static TreeNode p456 = new TreeNode("PM456", z45, hostFailure,
//			hostFailure);
//	public static TreeNode p457 = new TreeNode("PM457", z45, hostFailure,
//			hostFailure);
//	
//	// region5
//	public static TreeNode p511 = new TreeNode("PM511", z51, hostFailure,
//			hostFailure);
//	public static TreeNode p512 = new TreeNode("PM512", z51, hostFailure,
//			hostFailure);
//	public static TreeNode p513 = new TreeNode("PM513", z51, hostFailure,
//			hostFailure);
//	public static TreeNode p514 = new TreeNode("PM514", z51, hostFailure,
//			hostFailure);
//	public static TreeNode p515 = new TreeNode("PM515", z51, hostFailure,
//			hostFailure);
//	public static TreeNode p516 = new TreeNode("PM516", z51, hostFailure,
//			hostFailure);
//	public static TreeNode p517 = new TreeNode("PM517", z51, hostFailure,
//			hostFailure);
//
//	public static TreeNode p521 = new TreeNode("PM521", z52, hostFailure,
//			hostFailure);
//	public static TreeNode p522 = new TreeNode("PM522", z52, hostFailure,
//			hostFailure);
//	public static TreeNode p523 = new TreeNode("PM523", z52, hostFailure,
//			hostFailure);
//	public static TreeNode p524 = new TreeNode("PM524", z52, hostFailure,
//			hostFailure);
//	public static TreeNode p525 = new TreeNode("PM525", z52, hostFailure,
//			hostFailure);
//	public static TreeNode p526 = new TreeNode("PM526", z52, hostFailure,
//			hostFailure);
//	public static TreeNode p527 = new TreeNode("PM527", z52, hostFailure,
//			hostFailure);
//
//	public static TreeNode p531 = new TreeNode("PM531", z53, hostFailure,
//			hostFailure);
//	public static TreeNode p532 = new TreeNode("PM532", z53, hostFailure,
//			hostFailure);
//	public static TreeNode p533 = new TreeNode("PM533", z53, hostFailure,
//			hostFailure);
//	public static TreeNode p534 = new TreeNode("PM534", z53, hostFailure,
//			hostFailure);
//	public static TreeNode p535 = new TreeNode("PM535", z53, hostFailure,
//			hostFailure);
//	public static TreeNode p536 = new TreeNode("PM536", z53, hostFailure,
//			hostFailure);
//	public static TreeNode p537 = new TreeNode("PM537", z53, hostFailure,
//			hostFailure);
//	
//	public static TreeNode p541 = new TreeNode("PM541", z54, hostFailure,
//			hostFailure);
//	public static TreeNode p542 = new TreeNode("PM542", z54, hostFailure,
//			hostFailure);
//	public static TreeNode p543 = new TreeNode("PM543", z54, hostFailure,
//			hostFailure);
//	public static TreeNode p544 = new TreeNode("PM544", z54, hostFailure,
//			hostFailure);
//	public static TreeNode p545 = new TreeNode("PM545", z54, hostFailure,
//			hostFailure);
//	public static TreeNode p546 = new TreeNode("PM546", z54, hostFailure,
//			hostFailure);
//	public static TreeNode p547 = new TreeNode("PM547", z54, hostFailure,
//			hostFailure);
//	
//	public static TreeNode p551 = new TreeNode("PM551", z55, hostFailure,
//			hostFailure);
//	public static TreeNode p552 = new TreeNode("PM552", z55, hostFailure,
//			hostFailure);
//	public static TreeNode p553 = new TreeNode("PM553", z55, hostFailure,
//			hostFailure);
//	public static TreeNode p554 = new TreeNode("PM554", z55, hostFailure,
//			hostFailure);
//	public static TreeNode p555 = new TreeNode("PM555", z55, hostFailure,
//			hostFailure);
//	public static TreeNode p556 = new TreeNode("PM556", z55, hostFailure,
//			hostFailure);
//	public static TreeNode p557 = new TreeNode("PM557", z55, hostFailure,
//			hostFailure);
	
	public static int random(int start, int end) {
		int ran = new Random().nextInt(end - start) + start;
		return ran;
	}

	public static TreeNode randomEquivalence(List<TreeNode> list,int threshold) {
		while(threshold<=3){
			List<TreeNode> can=new ArrayList<TreeNode>();
			for(int i=0;i<list.size();i++){
				List<TreeNode> temp=getLeavesBrothers(list.get(i), threshold);
				can.addAll(temp);
			}
			Set<TreeNode> set= new HashSet<TreeNode>(can);
			List<TreeNode> candidates=new ArrayList<TreeNode>(set);
			candidates.removeAll(list);
			if(candidates.isEmpty()){
				System.out.println("try bigger");
				threshold++;
			}
			else{
				TreeNode attempt=candidates.get(random(0,candidates.size()));
				//print(candidates);
				for(int i=0;i<10;i++){
					if(!list.contains(attempt)){
						return attempt;
					}
					else{
						attempt=candidates.get(random(0,candidates.size()));;
					}
				}
				System.out.println("try bigger");
				threshold++;
			}
			
		}
		return null;
	}
	public static TreeNode getParent(TreeNode node, int threshold){
		TreeNode n=node;
		while(threshold!=0){
			n=n.getParent();
			threshold--;
		}
		return n;
	}
	
	public static TreeNode getRandomChildren(TreeNode node, int threshold){
		TreeNode n=node;
		while(threshold!=0){
			List<TreeNode> list=n.getChildren();
			n=list.get(Tree.random(0, list.size()));
			threshold--;
		}
		return n;
	}
	
	public static List<TreeNode> getLeavesBrothers(TreeNode node, int threshold){
		TreeNode commParent = getParent(node, threshold);
		Queue<TreeNode> queue = new  LinkedList<TreeNode>();
		queue.add(commParent);
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		while(!queue.isEmpty()){
			TreeNode pointer = queue.poll();
			if(pointer.getChildren().isEmpty()){
				list.add(pointer);
			}
			else{
				queue.addAll(pointer.getChildren());
			}
		}
		list.remove(node);
		return list;
	}
	
	public static void main(String[] args){
		
	}
	public static void print(List<TreeNode> list){
		for(int i=0;i<list.size();i++){
			System.out.print(list.get(i).getName()+",");
		}
		System.out.println();
	}

}
