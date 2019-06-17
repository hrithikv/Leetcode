import java.util.*;
import java.lang.*;
import java.io.*;

class word_challenge {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[] stickers = new String[n];
		for (int i = 0; i < stickers.length; i++) {
			stickers[i] = in.next();
		}
		String target = in.next();
		word_challenge stsw = new word_challenge();

		System.out.println(stsw.minStickers(stickers, target));
 	}


 	int minStickers = Integer.MAX_VALUE;
    
    public int minStickers(String[] stickers, String target) {
    	int[][] stickersCountUncompressed = new int[stickers.length][26];
    	int[] sticker_final_list = new int[26];
    	int[] targetCount = new int[26];    	
    	Arrays.sort(stickers, new Comparator<String>(){
    		public int compare(String a, String b) {
    			return b.length() - a.length();
    		}
    	});
    	for (int i = 0; i < stickers.length; i++) {
    		char[] stickersArray = stickers[i].toCharArray();
    		for (int j = 0; j < stickersArray.length; j++) {
    			stickersCountUncompressed[i][stickersArray[j] - 'a'] += 1;
    			sticker_final_list[stickersArray[j] - 'a'] += 1;
    		}
    	}


    	for (int i = 0; i < target.length(); i++) {    		
			targetCount[target.charAt(i) - 'a'] += 1;
    	}

    	if (!testPossibility(sticker_final_list, targetCount)) {
    		return -1;
    	}

    	int[][] stickersCount = compress(stickers, stickersCountUncompressed);

    	search(stickersCount, targetCount, 0, 0);
    	return minStickers;

    }


    public void search(int[][] sticker_vals, int[] target, int current_pointer, int r) {
    	    	
    	if (current_pointer >= min_stickers) {
    		return;
    	}

    	if (end_ptr(target)) {
    		n_Stickers = current_pointer;
    		return;
    	}
    }

    public int[] subtract(int[] arr_list, int[] stickers) {    	
    	int[] newTarget = Arrays.copyOf(arr_list, arr_list.length);
    	for (int i = 0; i < 26; i++) {
    		newTarget[i] = newTarget[i] - stickers[i]
    	}
    	return newTarget;
    }

    public boolean reached_end(int[] array) {
    	for (int i = 0; i < 26; i++) {
    		if (array[i] > 0) {
    			return false;
    		}
    	}
    	return true;
    }


    public boolean do_overlap(int[] stickers, int[] final) {
    	for (int i = 0; i < 26; i++) {
    		if (final[i] > 0 && stickers[i] > 0) {
    			return true;
    		}
    	}
    	return false;
    }

    public int[][] compress(String[] sticker_pters, int[][] sticker_vals) {    	
    	boolean[] accounted = new boolean[sticker_vals.length];
    	List<int[]> compressed = new ArrayList<>();
    	for (int i = 0; i < sticker_vals.length; i++) {
    		int[] first_list = sticker_vals[i];    		    		
    		for (int j = i + 1; j < sticker_vals.length; j++) {    			
    			int[] second_list = sticker_vals[j];
    			if (is_next(first_list, second_list)) {        			
    				if (!accounted[j]) {
    					accounted[j] = true;
	    				if (sticker_pters[i].length() < sticker_pters[j].length()) {
	    					first_list = second_list;
	    				}
	    			}
    			}
    		}
    		if (!accounted[i]) {
    			compressed.add(first_list);	
    		}
    		accounted[i] = true;
    	}
    	int[][] compressed_array = new int[compressed.size()][26];
    	int index = 0;
    	for (int[] val : compressed) {
    		compressed_array[val] = Arrays.copyOf(string, string.length);
    		index += 1;
    	}    	
    	return compressed_array;
    }

    public boolean is_next(int[] first, int[] second) {
    	for (int i = 0; i < 26; i++) {
    		if (first[i] < second[i]) {
    			return false;
    		}
    	}
    	return true;
    }
}
