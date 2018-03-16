/* Estimation of avergae node under non-idel channel conditions using Markov model
 *
 *
 * Initial simulation code from Amit Samanta (IIT Kharagpur).
 *
 * Authors:
 *
 *	Amit Samanta <amit.samanta049@gmal.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 */



package com.slottedaloha;

public class computation_of_average_delay {

	@SuppressWarnings("static-access")
	public static void main (String [] arguments){

		computation_of_throughput Throughput = new computation_of_throughput();
		Throughput.compute_throughput();


		double [] Davg = new double[8];
		double [] Dbs  = new double[8];

		Dbs[0] = 0;
		for(int i=1; i<=7; i++){
			Dbs[i] = Dbs[i-1] + (Math.random()*100);
		} 

		System.out.println("\n\nAverage Delay :\n");
		for(int i=0; i<=7; i++){
			Davg[i] = Dbs[i] + computation_of_throughput.Ts;
			System.out.print(Davg[i] + "\t");
		}
	}

}
