package application.Logic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
 
 
public class NonTouchingLoop {
	private ArrayList<ArrayList<String> > results ;
	private int n ;
 
	public NonTouchingLoop(int n){
		this.results = new ArrayList<ArrayList<String> >(100);
		this.n = n ;
	}
	void findSubsets(String[] array, int index, ArrayList<String> combination) {
 
	if (index == array.length) {
		if(combination.size() == n )
		{
			boolean touching = false ;
			 Map< String, Integer> hm1  = new HashMap<String, Integer>();
			for(String loop : combination)
			{
				String[] currencies = loop.split(" ");
				for(int i=0 ;i<currencies.length;i++)
				{
					int count = hm1.containsKey(currencies[i]) ? hm1.get(currencies[i]) : 0;
					if(count >= 1)
						touching = true ;
					hm1.put(currencies[i], count + 1);
				}
			}
			if(!touching)
			{
				ArrayList<String> NonTouching = new ArrayList<String>() ;
				for(int i=0 ;i<combination.size();i++)
					NonTouching.add(combination.get(i)) ;
				this.results.add(NonTouching) ;		
			}
 
		}
 
	return;
 
	}
 
	combination.add(array[index]);
 
	findSubsets(array, index + 1, combination);
 
	combination.remove(combination.size()-1);
 
	findSubsets(array, index + 1, combination);
 
	}
 
	void printArr() {
		for(int i=0 ;i<this.results.size();i++)
		{
			System.out.print(this.results.get(i));
 
			System.out.println();
		}
	}
	ArrayList<ArrayList<String>> getResults() {
		return this.results ;
	}
 
	public String[] ConvertListIntoStringArray(Integer[][] loops)
	{
		String str="" ;
		String array[] = new String[loops.length] ;
		int in=0 ;
		for(Integer[] list : loops) {
			for(Integer num : list) {
				str+=num;
				str+=" " ;
			}
			array[in++] =str ;
			str="" ;	
		}
		return array ;
	}
 
	public ArrayList<ArrayList<String>>  getNonTouchingLoops(Integer[][] loops) {
		String[] arr = this.ConvertListIntoStringArray(loops) ;
		ArrayList<String> combination = new ArrayList<String>();
		this.findSubsets(arr, 0, combination);
		return this.getResults() ;
 
 
	}
}
