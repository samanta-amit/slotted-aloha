package com.slottedaloha;

public class computation_of_energy_consumption {

	public static void main (String [] arguments){

		double [] Etx  = new double[8];
		double [] Erx  = new double[8];
		double [] Eid  = new double[8];
		double [] Econ = new double[8];

		//computation_of_throughput Throughput = new computation_of_throughput();
		//Throughput.compute_throughput();

		//computation_for_retransmission retransmission = new computation_for_retransmission();
		//retransmission.retransmission_compuation();

		computation_of_idle_duration idle_duration = new computation_of_idle_duration();
		idle_duration.idle_duration_computation();

		double Etxi = 414*Math.pow(10, -6);    // Energy consumption for data transmission

		System.out.println("\n\nEnergy consumption during data transmission :\n");
		for(int i=0; i<=7; i++){
			Etx[i] = computation_for_retransmission.retransmission[i]*computation_of_throughput.Tfr*Etxi;
			System.out.print(Etx[i] + "\t");
		}

		@SuppressWarnings("unused")
		int ack = 1*8;
		double Erxi = 393*Math.pow(10, -6);   // Energy consumption for data reception

		//System.out.println("\n\nEnergy consumption during data reception :\n");
		for(int i=0; i<=7; i++){
			Erx[i] = computation_for_retransmission.retransmission[i]*computation_of_throughput.Tack*Erxi;
			//System.out.print(Erx[i] + "\t");
		}

		@SuppressWarnings("unused")
		double Tid = 15*Math.pow(10, -6);
		double Eidi = 267*Math.pow(10, -6);  // Energy consumption for idle state

		//System.out.println("\n\nEnergy consumption while waiting :\n");
		for(int i=0; i<=7; i++){
			Eid[i] = computation_of_idle_duration.idle[i]*(100*Math.pow(10, -6))*Eidi;
			//System.out.print(Eid[i] + "\t");
		}

		//System.out.println("\n\nTotal energy consumption :\n");
		for(int i=0; i<=7; i++){
			Econ[i] = Etx[i] + Erx[i] + Eid[i];
			//System.out.print(Econ[i]*Math.pow(10, 3) + "\t");
		}
	}
}
