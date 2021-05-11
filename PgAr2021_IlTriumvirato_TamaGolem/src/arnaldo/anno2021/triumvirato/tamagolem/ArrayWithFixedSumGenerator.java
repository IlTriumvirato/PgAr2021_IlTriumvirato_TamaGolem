package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;
import java.util.Collections;

import it.unibs.fp.mylib.ManipolaArray;

public class ArrayWithFixedSumGenerator {
	private static int rangeMax;
	private static int rangeMin;
	private static int[] arrayComune;

	private static int getRandomIntNonZero(int min, int max) {
		int valore=0;
		if(min==max) {
			return max;
		}else {
			if(min>max) {
				int temp=max;
				max=min;
				min=temp;
			}

			//will not always output zero since if 
			while(valore==0) {
				int rangeWidth=max-min;
				valore=((int)(Math.random()*(rangeWidth+1)))+min;
			}
			
			return valore;
		}
	}
	

	
	public static void subArraySumRecursion(int start, int end, int sum) {
		int random1,random2;
		do{
			if(sum>0) {
				random1=getRandomIntNonZero(sum+rangeMin,rangeMax);
			}else {
				random1=getRandomIntNonZero(rangeMin,sum+rangeMax);
			}
			
			random2=sum-random1;
		}while(random1==0||random2==0);
		
		int elementsAmount=end-start+1;
		if(elementsAmount>2) {
			int middleIndex=(end+start)/2;
			subArraySumRecursion(start,middleIndex,random1);
			subArraySumRecursion(middleIndex+1,end,random2);
		}else {
			if(elementsAmount==1) {
				arrayComune[start]=sum;
			}else if(elementsAmount==2){
				arrayComune[start]=random1;
				arrayComune[end]=random2;
			}else {
				//shouldn't happen, error if it happens
			}
		}
	}
	
	public static ArrayList<Integer> generateArrayListWithFixedSum(int size,int rangeMaximum, int sum){
		arrayComune=new int[size];
		rangeMax=rangeMaximum;
		rangeMin=-rangeMaximum;
		subArraySumRecursion(0,size-1,sum);
		
		ArrayList<Integer> finalArray=ManipolaArray.convertToArrayListInteger(arrayComune);
		
		Collections.shuffle(finalArray);
		
		//this new is basically just out of fear of deletion in future situations
		return new ArrayList<Integer>(finalArray);
	}


	public static int[] generateCommonArrayWithFixedSum(int size,int rangeMaximum,int sum){
		int[] values=ManipolaArray.convertToCommonArrayInt(generateArrayListWithFixedSum(size,rangeMaximum,sum));
		
		return values;
	}
	/*public static void main(String[] args) {
		ManipolaArray.printArray(generateCommonArrayWithFixedSum(10,9,0),10);
	}*/


}
