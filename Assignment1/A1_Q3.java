import java.util.*;

public class A1_Q3 {
	
	public static ArrayList<String> Discussion_Board(String[] posts){

		HashMap<String, HashMap<String,Integer>> wordsOfUsers = new LinkedHashMap<>();
		Set<String> wordsUsed = new HashSet<>();
		Set<String>	users = new HashSet<>();

		// Makes a map of users and their words with their frequency
		for(int i = 0; i < posts.length; i++){
			String[] temp = posts[i].split(" ");
			HashMap<String, Integer> wordsRecurrence = new LinkedHashMap<>();
			for (int j = 1; j < temp.length; j++){
				if (wordsRecurrence.get(temp[j]) == null){
					wordsRecurrence.put(temp[j], 1);
				}
				else{
					wordsRecurrence.put(temp[j], wordsRecurrence.get(temp[j]) + 1);
				}
				wordsUsed.add(temp[j]);
			}

			if (wordsOfUsers.get(temp[0]) == null){
				wordsOfUsers.put(temp[0], wordsRecurrence);
			}
			else {
				HashMap<String, Integer> tempHashMap = new LinkedHashMap<>(wordsOfUsers.get(temp[0]));
				for (int j = 1; j < temp.length; j++) {
					if (wordsOfUsers.get(temp[0]).get(temp[j]) == null){
						tempHashMap.put(temp[j], 1);
					}
					else{
						tempHashMap.put(temp[j],wordsOfUsers.get(temp[0]).get(temp[j]) + 1);
					}
					wordsOfUsers.put(temp[0], tempHashMap);
				}
			}
			users.add(temp[0]);
		}

		// Finds the words that all users said with their total frequency
		HashMap<String,Integer> finalWordsWithRecurrences = new LinkedHashMap<>() ;
		Set<String> wordsEveryoneSaid = new HashSet<>();
		for (String w: wordsUsed) {
			int counterOfRepetition = 0;
			for (String u: users) {
				wordsEveryoneSaid.add(w);
				if (!wordsOfUsers.get(u).containsKey(w)){
					wordsEveryoneSaid.remove(w);
					counterOfRepetition = 0;
					break;
				}
				counterOfRepetition += wordsOfUsers.get(u).get(w);
			}
			if(counterOfRepetition != 0){
				finalWordsWithRecurrences.put(w, counterOfRepetition);
			}

		}

		// Sorts the words if frequency is the same
		List<Map.Entry<String,Integer>> toSortList = new LinkedList(finalWordsWithRecurrences.entrySet());
		toSortList.sort(Map.Entry.comparingByValue());
		for (int i = 0; i < toSortList.size() - 1; i++) {
			if (toSortList.get(i).getValue().equals(toSortList.get(i+1).getValue())){
				List<Map.Entry<String,Integer>> temp = new LinkedList<>();
				temp.add(0, toSortList.get(i));
				temp.add(1, toSortList.get(i+1));
				temp.sort(Map.Entry.comparingByKey());
				toSortList.set(i, temp.get(1));
				toSortList.set(i+1, temp.get(0));
			}
		}



		// Tests prints
//		System.out.println("All the words with corresponding users: " + wordsOfUsers);
//		System.out.println("All words written: "+ wordsUsed);
//		System.out.println("All users: " + users);
//		System.out.println("Final words: " + wordsEveryoneSaid);
//		System.out.println("Final words with reccurences: " + finalWordsWithRecurrences);
//		System.out.println("Final sorted words: " + toSortList);

		// Return the expected values
		if (toSortList.size() == 0){
			return new ArrayList<>();
		}
		else{
			ArrayList<String> result = new ArrayList<>();
			int sizeOfWords = toSortList.size();
			while (sizeOfWords > 0){
				result.add(toSortList.get(sizeOfWords-1).getKey());
				sizeOfWords--;
			}
			return result;
		}

	}

	public static void main(String[] args) {
		String[] Test0 = {
				"user1 doubledutch double double dutch",
				"user2 dutch doubledutch doubledutch double",
				"user3 not double dutch doubledutch"
		};

		System.out.println(Discussion_Board(Test0));
	}



}
