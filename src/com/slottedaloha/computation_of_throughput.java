/* Estimation of normalized throughput
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

public class computation_of_throughput {

	static double [] psi = new double[7+1];
	static double [] nu = new double[7+1];
	static double [] varrho = new double[7+1];
	static double data_rate = 971.4*Math.pow(10, 3);   // bps

	static double pExtraIFS = 10*Math.pow(10, -6);
	static double pSIFS = 75*Math.pow(10, -6);
	static double p_star = 255*8;
	static double Tfr = (computation_of_frame_transmission.p + computation_of_frame_transmission.h)/data_rate;              // Second ((255+7)*8)/(971.4*10^3)       // (Thr + Tpl)   // Delay in payload and header transmission
	static double Tfr_star = (p_star + computation_of_frame_transmission.h)/data_rate;
	static double Tp = 1*Math.pow(10, -6);         // Arbitrary                             // Propagation delay
	static double Tpr = 50*Math.pow(10, -6);                                                // Frame processing delay
	//double Tbc = 10*Math.pow(10, -6);       // Arbitrary                             // Duration of channel is busy
	static double Tack = 8/data_rate;              // Second (8)/(971.4*10^3)               // Delay in reception of acknowledgment 
	static double mTimeOut = 30*Math.pow(10, -6);                                           // Time out


	static double Ts_star = Tfr_star + Tack + 2*pSIFS + 2*Tp + pExtraIFS + Tpr;
	static double Tc_star = Tfr_star + Tack + 2*pSIFS + Tp + Tpr + mTimeOut;
	static double Td = 100*Math.pow(10, -6);
	static double Ts = Tfr + Tack + 2*pSIFS + 2*Tp + pExtraIFS + Tpr;


	public static void compute_throughput () {

		@SuppressWarnings("unused")
		computation_of_frame_transmission FRAME_TRANSMISSION = new computation_of_frame_transmission();
		computation_of_frame_transmission.compute_farme_transmission();


		System.out.print( "\n\nValue of Ts is: " + Ts );
		System.out.print( "\n\nValue of Tc_star is: " + Tc_star );


		System.out.print("\n \nValues of varrho are: \n \n");
		for(int i=0; i<=7; i++){
			varrho[i] = ( 1 - Math.pow((1-computation_of_frame_transmission.tau[i]), computation_of_frame_transmission.n[i]) )*computation_of_frame_transmission.no_nodes_of_other_UP_transmitting[i];
			System.out.print( varrho[i] + "\t");
		}


		System.out.print("\n \nValues of nu are: \n \n");
		for(int i=0; i<=7; i++){
			nu[i] = ( ( computation_of_frame_transmission.n[i]*computation_of_frame_transmission.tau[i]*Math.pow((1-computation_of_frame_transmission.tau[i]), (computation_of_frame_transmission.n[i]-1)) )/( 1 - Math.pow((1-computation_of_frame_transmission.tau[i]), computation_of_frame_transmission.n[i]) ) )*computation_of_frame_transmission.Upsilon_star;
			System.out.print( nu[i] + "\t");
		}

		//System.out.print("\n \nnu[i]*varrho[i]: \n \n");
		System.out.print("\n \npsi[i]: \n \n");

		for(int i=0; i<=7; i++){
			psi[i] = (nu[i]*varrho[i]*(computation_of_frame_transmission.p + computation_of_frame_transmission.h) )/( (nu[i]*varrho[i]*Ts) + ((1-varrho[i])*Td) + (varrho[i]*(1-nu[i])*Tc_star) );
			//System.out.print( nu[i]*varrho[i] + "\t");
			System.out.print( psi[i] + "\t" );
		}


		/*
		System.out.print("\n \n (nu[i]*varrho[i]*Ts): \n \n");
		for(int i=0; i<=7; i++){
			System.out.print( (nu[i]*varrho[i]*Ts) + "\t");
		}

		System.out.print("\n \n ((1-varrho[i])*Td) : \n \n");
		for(int i=0; i<=7; i++){
			System.out.print( ((1-varrho[i])*Td)  + "\t");
		}

		System.out.print("\n \n  (varrho[i]*(1-nu[i])*Tc_star): \n \n");
		for(int i=0; i<=7; i++){
			System.out.print(  (varrho[i]*(1-nu[i])*Tc_star) + "\t");
		}


		System.out.print("\n \nNumerator: \n \n");
		for(int i=0; i<=7; i++){
			System.out.print( (nu[i]*varrho[i]*(computation_of_frame_transmission.p + computation_of_frame_transmission.h)) + "\t");
		}

		System.out.print("\n \nDenominator: \n \n");
		for(int i=0; i<=7; i++){
			System.out.print( ( (nu[i]*varrho[i]*Ts) + ((1-varrho[i])*Td) + (varrho[i]*(1-nu[i])*Tc_star) ) + "\t");
		}

		System.out.print("\n \nThroughput: \n \n");
		for(int i=0; i<=7; i++){
			System.out.print( psi[i]+ "\t");
		}
		 */
	}

	@SuppressWarnings("static-access")
	public static void main (String [] arguments){
		computation_of_throughput Throughput = new computation_of_throughput();
		Throughput.compute_throughput();
	}
}
