package job_selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class JobSelector {
	public static void main(String[] args) {
		String jfile = "progress/job_selection/jobs.csv";
		String wrfile = "progress/job_selection/items.csv";
		String lfile = "progress/job_selection/locations.csv";
		HashMap<String, Item> itemMap = ItemReader.parseItems(wrfile, lfile);
		HashMap<String, Job> jobMap = JobReader.parseJobs(jfile, itemMap);
		
		ArrayList<Job> jobs = new ArrayList<Job>(jobMap.values());
		Collection.sort(jobs);
		//We now have a list of jobs, sorted based on highest total reward.
		
		for (Job j : jobs) {
			System.out.print(j);
			System.out.print("Reward: " + j.totalReward());
			System.out.println(" Weight: " + j.totalWeight());
		}
		
//		final float W_LIMIT = 50f;
//		ArrayList<Round> rounds = new ArrayList<Round>();
//		Round currentRound = new Round(W_LIMIT);
//		for (Job j : jobs) {
//			for (String s : j.getPicks().keySet()) {
//				
//			}
//			
//		}
//		
//		System.out.println(rounds.size());
//		for (Round r : rounds) {
//			System.out.println(r.getRoute());
//		}
	}
}
