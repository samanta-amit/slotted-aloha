/* Computation of retransmission
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

public class computation_for_retransmission {

	static double [] retransmission = new double[8];

	@SuppressWarnings("static-access")
	public double retransmission_compuation() {

		computation_of_frame_transmission FRAME_TRANSMISSION = new computation_of_frame_transmission();
		FRAME_TRANSMISSION.compute_farme_transmission();

		System.out.println("\n\nCompuation of retransmission : ");
		for(int i=0; i<=7; i++) {
			retransmission[i] = (1- computation_of_frame_transmission.xi[i])*
					( 1+2*computation_of_frame_transmission.xi[i]
							+3*Math.pow(computation_of_frame_transmission.xi[i],2)
							+4*Math.pow(computation_of_frame_transmission.xi[i],3)
							+5*Math.pow(computation_of_frame_transmission.xi[i],4)
							+6*Math.pow(computation_of_frame_transmission.xi[i],5) 
							+7*Math.pow(computation_of_frame_transmission.xi[i],6) 
							+8*Math.pow(computation_of_frame_transmission.xi[i],7)
							+9*Math.pow(computation_of_frame_transmission.xi[i],8) );
			System.out.print( retransmission[i] + "\t");
		}
		return 0;
	}

	public static void main (String [] arguments){
		computation_for_retransmission retransmission = new computation_for_retransmission();
		retransmission.retransmission_compuation();
	}

}
