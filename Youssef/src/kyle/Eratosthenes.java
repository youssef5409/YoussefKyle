/*
 * Name: Kyle Blackie
 * Date: February 24 ,2017
 * Description: Find prime numbers from 1 - 1000
 */

package kyle;

import java.util.ArrayList;

/**
 *
 * @author 1blackiekyl
 */
public class Eratosthenes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //objects
        ArrayList<Integer> nums = new ArrayList<Integer>();        
        //variables
        int range = 1000;
        boolean numbers[] = new boolean[range];
        for(int i = 0; i < range - 1 ; i++){
            nums.add(i + 2);
            numbers[i] = true;

        }
        
        for(int i = 0; i < range - 1; i++){
            if(numbers[i] == true){
                for(int x = i + nums.get(i); x < range - 1;){
                    numbers[x] = false;
                    x += nums.get(i);
                    
                }
            }
        }
        
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == true){
                System.out.println(nums.get(i));
            }
        }
                   
        
        
        
    }
    
}
