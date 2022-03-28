package com.uhcl.parade;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.uhcl.parade.config.FileUploadProperties;



@SpringBootApplication
//@EnableAutoConfiguration
@CrossOrigin
@EnableConfigurationProperties({
    FileUploadProperties.class
})
public class ParadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParadeApplication.class, args);
		
/*int[] arr = new int[]{1,2,3,4,5,1};
		

int sum = 0; // initialize sum of whole array
int leftsum = 0; // initialize leftsum

 Find sum of the whole array 
for (int i = 0; i < arr.length; ++i)
    sum += arr[i];

for (int i = 0; i < arr.length; ++i) {
    sum -= arr[i]; // sum is now right sum for index i

    if (leftsum == sum){
    	System.out.println(arr[i]+" is a equilibrium point Method 1");
    	break;
    }
        

    leftsum += arr[i];
}
		
		
		for(int i=1;i<arr.length-1;i++){
			int leftSum =0,rightsum = 0;
			
			for(int j=0;j<arr.length;j++){
				
				if(j<i){
					leftSum = leftSum+arr[j];
				}else if(j>i){
					rightsum = rightsum+arr[j];
				}
				
			}
			
			if(leftSum == rightsum){
				System.out.println(arr[i]+" is a equilibrium point");
				break;
			}
			
		}
		*/
	}
}
