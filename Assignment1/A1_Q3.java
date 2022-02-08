import java.util.*;

public class A1_Q3 {
	
	public static ArrayList<String> Discussion_Board(String[] posts){
		ArrayList<String> words = new ArrayList<>();
		for(int i = 0; i < posts.length; i++){
			String[] temp = posts[i].split(" ");
			List<String> tempList = Arrays.asList(temp);
			words.addAll(tempList);
			words.remove(temp[0]);
		}

		// Doesnt differentiate words with the \n, could be a problem, could be nothing
		Map<String, Integer> recurrences = new LinkedHashMap<>();
		for (String w: words){
			if(recurrences.get(w) == null){
				recurrences.put(w, 1);
			}
			else{
				recurrences.put(w, recurrences.get(w) + 1);
			}
		}
		List<Map.Entry<String,Integer>> toSortList = new LinkedList(recurrences.entrySet());
		toSortList.sort(Map.Entry.comparingByValue());

		System.out.println(recurrences);
		System.out.println(toSortList);

		//System.out.println(toSortList.get(toSortList.size() -1).getKey());
		if (toSortList.get(toSortList.size() -1).getValue() < posts.length){
			return new ArrayList<>();
		}
		else{
			ArrayList<String> result = new ArrayList<>();
			int sizeOfWords = toSortList.size();
			while (sizeOfWords > 0){
				result.add(toSortList.get(sizeOfWords -1).getKey());
				sizeOfWords--;
				if (toSortList.size() - sizeOfWords == 3){
					break;
				}
			}

			return result;
		}


	}



}
