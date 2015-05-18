package cn.sjtu.cloudboy.analysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;



public class TreeReliability {

    public static double getUnReliability(TreeNode a, TreeNode b){
        
        double publicReliability=1;
        double aOwnReliability = 1;
        double bOwnReliability = 1;
        
        while(a.getParent()!=null&&b.getParent()!=null){    
            if(a.equals(b)){
                //System.out.println("public "+a.getName());
                publicReliability=(1-a.getFailure())*publicReliability;
            }
            else{
                //System.out.println("aown "+a.getName());
                //System.out.println("bown "+b.getName());
                aOwnReliability = aOwnReliability *(1- a.getFailure());
                bOwnReliability = bOwnReliability *(1- b.getFailure());
            }
            a=a.getParent();
            b=b.getParent();
        }
       
        //System.out.println(publicReliability+" "+aOwnReliability+" "+ bOwnReliability);
       
        return (1-publicReliability)+ publicReliability* (1-aOwnReliability)* (1-bOwnReliability);
    }
    
    public static double getUnReliability(TreeNode a){
        double rel=1;
        while(a.getParent()!=null){
            rel=(1-a.getFailure())*rel;
            a=a.getParent();
        }
        return 1-rel;
    }
    
    public static double AvailabilityIn(int h, List<TreeNode> list){
    	List<TreeNode> tempList = new ArrayList<TreeNode>();
    	tempList.addAll(list);
        for(int i=0;i<h;i++){
        	tempList=cluster(tempList);
        }
        return 1-tempList.get(0).getTemp();
    }
 

    private static List<TreeNode> cluster(List<TreeNode> list) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        
        while(!list.isEmpty()){
            Set<TreeNode> compare=new TreeSet<TreeNode>();
            TreeNode node=list.get(0);
            compare.add(node);
            double whole=1;
            whole=whole*node.getTemp();
            for(int i=1;i<list.size();i++){//find a node has public parent in the rest of list
                TreeNode m=list.get(i);
                if(node.getParent().equals(m.getParent())){
                    if(compare.contains(m)){
                        list.remove(i);
                        i--;
                    }
                    else{
                        whole=whole*m.getTemp();
                        compare.add(m);
                        list.remove(i);
                        i--;
                    }
                    
                }
                
            }
            node=node.getParent();
            double t1=node.getFailure();
            double t2=(1-node.getFailure())*whole;
            node.setTemp(t1+t2);//node.getFailure()+(1-node.getFailure())*whole);
            result.add(node);
            list.remove(0);
        }
        //for(int i=0;i<result.size();i++){
       //     System.out.print(result.get(i).getName()+" "+result.get(i).getTemp());
       //}
       // System.out.println();
        return result;
    }
    
    public static double getCommCost(List<TreeNode> list){
    	
        Set<TreeNode> set= new HashSet<TreeNode>(list);
        List<TreeNode> list1=new ArrayList<TreeNode>(set);
        double sum=list1.size();
        for(int i=0;i<list1.size();i++){
            TreeNode n=list1.get(i);
            for(int j=0;j<list1.size()&&j!=i;j++){
                sum+=cost(n,list1.get(j));
            }
        }
        return sum;
        
    }

    private static double cost(TreeNode n, TreeNode m) {
        double distance=0;
        int pmCost=1;
        int zoneCost=2;
        int regionCost=3;
        int level=0;
        while(n!=m){
        	if(level ==0){
        		distance+=pmCost;
        	}
        	else if(level==1){
        		distance+=zoneCost;
        	}
        	else if(level ==2){
        		distance+=regionCost;
        	}
            level++;
            n=n.getParent();
            m=m.getParent();
        }
        return distance;
    }
    
    public static int getSoftware(List<TreeNode> list){
    	 Set<TreeNode> set= new HashSet<TreeNode>(list);
         List<TreeNode> list1=new ArrayList<TreeNode>(set);
         int sum=list1.size();
         return sum;
    }
    public static double distance(TreeNode n, TreeNode m) {
        double distance=0;
       
        while(n!=m){
        	distance++;
            n=n.getParent();
            m=m.getParent();
        }
        return distance;
    }
}
