
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class BJ11652 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static long[] cards;
	static long maxCard;
	static int maxCount = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		cards = new long[N];
		
		for(int i=0; i<N; i++) {
			long card = Long.parseLong(br.readLine());
			cards[i] = card;
		}
		
		Arrays.sort(cards);
		
		for(int i=0; i<cards.length; ) {
			long card = cards[i];

			int count = 0;
			while(i < cards.length) {
				if(cards[i] != card)
					break;
				
				count++;
				i++;
			}
			
			if(maxCount < count) {
				maxCount = count;
				maxCard = card;
			}
		}
		
//		System.out.println(maxCount + " " + maxCard);
		System.out.println(maxCard);
	}
}
