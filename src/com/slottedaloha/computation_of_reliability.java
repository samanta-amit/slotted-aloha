/* Computation of reliability
 *
 *
 * Initial simulation code from Amit Samanta (IIT Kharagpur).
 *
 * Author:
 *
 *	Amit Samanta <amit.samanta049@gmal.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 */


package com.slottedaloha;

public class computation_of_reliability {

	@SuppressWarnings("static-access")
	public static void main (String [] arguments){

		computation_of_b000 B000 = new computation_of_b000();
		double a = B000.alpha;
		System.out.println("Alpha: " + a);	


		computation_of_frame_transmission FRAME_TRANSMISSION = new computation_of_frame_transmission();
		computation_of_frame_transmission.compute_farme_transmission();


		double H [][] = new double[8][8];

		for(int i=0; i<=7; i++){
			for(int j=0; j<=7; j++){
				H [i][j] = 0;
			}
		}


		for(int i=0; i<=7; i++){
			H[i][0] = a*(B000.gamma[i][0]*FRAME_TRANSMISSION.F[i]);
			H[i][1]	= Math.pow(a, 2)*B000.gamma[i][0]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][1]*FRAME_TRANSMISSION.F[i]; 
			H[i][2]	= Math.pow(a, 3)*B000.gamma[i][0]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][1]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][2]*FRAME_TRANSMISSION.F[i];
			H[i][3]	= Math.pow(a, 4)*B000.gamma[i][0]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][1]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][2]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][3]*FRAME_TRANSMISSION.F[i];
			H[i][4]	= Math.pow(a, 5)*B000.gamma[i][0]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][1]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][2]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][3]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][4]*FRAME_TRANSMISSION.F[i];
			H[i][5]	= Math.pow(a, 6)*B000.gamma[i][0]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][1]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][2]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][3]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][4]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][5]*FRAME_TRANSMISSION.F[i];
			H[i][6]	= Math.pow(a, 7)*B000.gamma[i][0]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][1]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][2]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][3]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][4]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][5]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][6]*computation_of_frame_transmission.F[i];
			H[i][7]	= Math.pow(a, 8)*B000.gamma[i][0]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][1]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][2]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][3]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][4]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][5]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][6]*(1-FRAME_TRANSMISSION.F[i])*B000.gamma[i][7]*FRAME_TRANSMISSION.F[i];
		}

		/*
		for(int i=0; i<=7; i++){
			H[i][0] = a*(FP[i]);
			H[i][1]	= Math.pow(a, 2)*(1-FP[i])*FP[i]; 
			H[i][2]	= Math.pow(a, 3)*(1-FP[i])*(1-FP[i])*FP[i];
			H[i][3]	= Math.pow(a, 4)*(1-FP[i])*(1-FP[i])*(1-FP[i])*FP[i];
			H[i][4]	= Math.pow(a, 5)*(1-FP[i])*(1-FP[i])*(1-FP[i])*(1-FP[i])*FP[i];
			H[i][5]	= Math.pow(a, 6)*(1-FP[i])*(1-FP[i])*(1-FP[i])*(1-FP[i])*(1-FP[i])*FP[i];
			H[i][6]	= Math.pow(a, 7)*(1-FP[i])*(1-FP[i])*(1-FP[i])*(1-FP[i])*(1-FP[i])*(1-FP[i])*FP[i];
			H[i][7]	= Math.pow(a, 8)*(1-FP[i])*(1-FP[i])*(1-FP[i])*(1-FP[i])*(1-FP[i])*(1-FP[i])*(1-FP[i])*FP[i];
		}
		 */

		System.out.println("\n\nValues of H are: \n");
		for(int i=0; i<=7; i++){
			for(int j=0; j<=7; j++){
				System.out.print( "\t" + H[i][j] );
			}
			System.out.println( );
		}

		System.out.println( "\n\nValues of reliability are : \n");
		double [] reliability = new double[8];
		for(int i=0; i<=7; i++){
			reliability[i] = 0;
		}

		for(int i=0; i<=7; i++){
			for(int j=0; j<=7; j++){
				reliability[i] += H[i][j]; 
			}
			System.out.print( "\t" + reliability[i] );
		}

	}
}
