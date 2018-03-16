/* Estimation of saturated throughput
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

public class computation_of_saturation_throughput {

	@SuppressWarnings("static-access")
	public static void main (String [] arguments){

		@SuppressWarnings("unused")
		computation_of_frame_transmission FRAME_TRANSMISSION = new computation_of_frame_transmission();
		computation_of_frame_transmission.compute_farme_transmission();

		computation_of_throughput Throughput = new computation_of_throughput();
		Throughput.compute_throughput();

		double [] optimal_tau = new double[8];
		double [] delta = new double[8];

		System.out.println("\n\nValues of delta[i] are : \n");
		for(int i=0; i<=7; i++){
			delta[i] = ( 4 - 2/( (1 - computation_of_throughput.Tc_star/computation_of_throughput.Td)*computation_of_frame_transmission.no_nodes_of_other_UP_transmitting[i]) );
			System.out.print(delta[i] + "\t");
		}

		System.out.println("\n\nOptimal transmission rate for different user priorities are : \n");
		for(int i=0; i<=7; i++){
			//optimal_tau[i] = (-1 + Math.sqrt( (computation_of_frame_transmission.n[i] + 2*(computation_of_frame_transmission.n[i]-1)*((computation_of_throughput.Tc_star/computation_of_throughput.Td) - 1))/computation_of_frame_transmission.n[i] ) ) / ((computation_of_frame_transmission.n[i] - 1)*((computation_of_throughput.Tc_star/computation_of_throughput.Td) - 1));
			optimal_tau[i] = ( (computation_of_frame_transmission.n[i]*delta[i]) - Math.sqrt( Math.pow(computation_of_frame_transmission.n[i], 2)*(Math.pow(delta[i], 2)) - (4*( Math.pow(computation_of_frame_transmission.n[i], 2) - computation_of_frame_transmission.n[i])*delta[i]) ) )/(2*( Math.pow(computation_of_frame_transmission.n[i], 2) - computation_of_frame_transmission.n[i]));
			System.out.print(optimal_tau[i] + "\t");
		}

		double [] psi_max        = new double[8];
		double [] varrho_optimal = new double[8];
		double [] nu_optimal     = new double[8];


		System.out.print("\n \nValues of varrho_optimal are: \n \n");
		for(int i=0; i<=7; i++){
			varrho_optimal[i] = ( 1 - Math.pow((1-optimal_tau[i]), computation_of_frame_transmission.n[i]) )*computation_of_frame_transmission.no_nodes_of_other_UP_transmitting[i];
			System.out.print( varrho_optimal[i] + "\t");
		}


		System.out.print("\n \nValues of nu_optimal are: \n \n");
		for(int i=0; i<=7; i++){
			nu_optimal[i] = ( ( computation_of_frame_transmission.n[i]*optimal_tau[i]*Math.pow((1-optimal_tau[i]), (computation_of_frame_transmission.n[i]-1)) )/( 1 - Math.pow((1-optimal_tau[i]), computation_of_frame_transmission.n[i]) ) )*computation_of_frame_transmission.Upsilon_star;
			System.out.print( nu_optimal[i] + "\t");
		}


		System.out.println("\n\nSaruration throughput for different user priorities are : \n");
		for(int i=0; i<=7; i++){
			psi_max[i] = (nu_optimal[i]*varrho_optimal[i]*(computation_of_frame_transmission.p + computation_of_frame_transmission.h) )/( (nu_optimal[i]*varrho_optimal[i]*computation_of_throughput.Ts) + ((1-varrho_optimal[i])*computation_of_throughput.Td) + (varrho_optimal[i]*(1-nu_optimal[i])*computation_of_throughput.Tc_star) );
			//psi_max[i] = (computation_of_frame_transmission.p + computation_of_frame_transmission.h) / (computation_of_throughput.Ts - computation_of_throughput.Tc_star + ( ( (computation_of_throughput.Tc_star/computation_of_throughput.Td) - (computation_of_throughput.Tc_star/computation_of_throughput.Td - 1)* Math.pow((1 - optimal_tau[i]), computation_of_frame_transmission.n[i]) ) / (computation_of_frame_transmission.n[i]*optimal_tau[i]*Math.pow((1 - optimal_tau[i]), (computation_of_frame_transmission.n[i]-1))*computation_of_frame_transmission.Upsilon_star) ));
			System.out.print(psi_max[i] + "\t");
		}

		
		System.out.println("\n\nNumerator : \n");
		for(int i=0; i<=7; i++){
			System.out.print((nu_optimal[i]*varrho_optimal[i]*(computation_of_frame_transmission.p + computation_of_frame_transmission.h) ) + "\t");
		}

		System.out.println("\n\nDenominator : \n");
		for(int i=0; i<=7; i++){
			System.out.print( (nu_optimal[i]*varrho_optimal[i]*computation_of_throughput.Ts) + ((1-varrho_optimal[i])*computation_of_throughput.Td) + (varrho_optimal[i]*(1-nu_optimal[i])*computation_of_throughput.Tc_star) + "\t");
		}
		
		/*
		System.out.println("\n\n(nu_optimal[i]*varrho_optimal[i]*computation_of_throughput.Ts) : \n");
		for(int i=0; i<=7; i++){
			System.out.print(  (nu_optimal[i]*varrho_optimal[i]*computation_of_throughput.Ts) + "\t");
		}
		
		System.out.println("\n\n((1-varrho_optimal[i])*computation_of_throughput.Td) : \n");
		for(int i=0; i<=7; i++){
			System.out.print(  ((1-varrho_optimal[i])*computation_of_throughput.Td) + "\t");
		}
		
		System.out.println("\n\n(varrho_optimal[i]*(1-nu_optimal[i])*computation_of_throughput.Tc_star)  : \n");
		for(int i=0; i<=7; i++){
			System.out.print(  (varrho_optimal[i]*(1-nu_optimal[i])*computation_of_throughput.Tc_star)  + "\t");
		}
		*/
	}
}
