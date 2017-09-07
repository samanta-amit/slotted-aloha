package com.slottedaloha;

public class computation_of_frame_retransmission {

	static double [] F = new double[8];                                         // Initialization of failure probability
	static int h = (7+2)*8; // Header size in bits
	static int p = 255*8;   // Payload size in bits
	//static int []  n = { 8, 8, 8, 8, 8, 8, 8, 8 };
	static int []  n = { 1, 1, 1, 1, 1, 1, 1, 1 };
	static double [] tau = new double[8];                                       // Initialization of transmission probability
	static double Upsilon;
	static double Upsilon_star;
	static double [] no_nodes_of_other_UP_transmitting = new double[8];
	static double [] one_node_of_UPi_transmitting = new double[8];
	static double no_nodes_transmitting = 1;
	static double [] xi = new double[8];                                        // Initialization of collision probability

	public static void compute_farme_transmission () {

		double b000;
		@SuppressWarnings("unused")
		double ALPHA;
		@SuppressWarnings("unused")
		int m;
		double beta_bar;
		computation_of_b000 B000 = new computation_of_b000();
		b000 = B000.b000_compuation();
		ALPHA = B000.alpha;
		m = B000.m;
		beta_bar = B000.beta_bar;

		double beta = Math.pow(beta_bar, (B000.Lc-B000.La));

		@SuppressWarnings("unused")
		double B = (beta_bar*(1 - beta))/(1 - beta_bar);



		double [] lambda = new double[8];                                    // Initialization of probability of choosing user priority 
		double [] only_one_node_of_UPi_is_transmitting = new double[8];

		for(int i=0; i<=7; i++){
			only_one_node_of_UPi_is_transmitting[i] = 1;
		}

		for(int i=0; i<=7; i++){
			no_nodes_of_other_UP_transmitting[i] = 1;
		}


		//========================== LAMBDA value initialization ==========================================

		int sample = 1000;
		int [] Rand = new int[sample];
		int [] count = new int[sample];

		for(int j=0; j<=7; j++) {
			count[j] = 0;
		}

		//System.out.println("Values of Rand: \n");
		for(int j=0; j<=7; j++) {
			for(int i=0; i<sample; i++) {
				Rand[i] = (int) (Math.random()*8);
				//System.out.println(Rand[i]);
				if(Rand[i] == j){
					count[j] = count[j] +1;
				}
			}
		}


		//System.out.println("\nLambda values are: \n");
		for(int i=0; i<=7; i++){
			lambda[i] = ((double)(count[i])/sample);
			//System.out.println(lambda[i]);
		}

		//========================== Calculation of frame transmission probability ==========================	


		/*
		double [] T = new double[8];

		System.out.println("\nValues of T are: \n");
		for(int i=0; i<=7; i++){
			for (int j=0; j<=6; j++){
				T[i] += Math.pow(beta, j)*B000.gamma[i][j+1];

			}
			System.out.print(T[i] + "\t");
		}

		System.out.println("\n\nFrame transmission probablity for different user priorities: \n");
		for(int i=0; i<=7; i++){
			//tau[i] = ALPHA*lambda[i]*b000*(T[i]+1);
			tau[i] = ALPHA*b000*(T[i]+1);
			//tau[i] = b000*(T[i]+1);
			System.out.print(tau[i] + "\t");
		}
		 */


		double Q [][] = new double[8][8];
		double conditional_tau [] = new double[8];
		for(int i=0; i<=7; i++){
			for(int j=0; j<=7; j++){
				Q[i][j] = 0;
			}
		}

		System.out.println("\nFrame conditional transmission probablity for different user priorities: ");
		for(int i=0; i<=7; i++){
			for(int j=0; j<=7; j++){
				Q[i][0] = B000.gamma[i][j]*B000.alpha*b000;
				Q[i][1] = Q[i][0]*B000.beta_bar*B000.alpha;
				Q[i][2] = Q[i][1]*B000.beta_bar*B000.alpha;
				Q[i][3] = Q[i][2]*B000.beta_bar*B000.alpha;
				Q[i][4] = Q[i][3]*B000.beta_bar*B000.alpha;
				Q[i][5] = Q[i][4]*B000.beta_bar*B000.alpha;
				Q[i][6] = Q[i][5]*B000.beta_bar*B000.alpha;
				Q[i][7] = Q[i][6]*B000.beta_bar*B000.alpha;
			}
			tau[i] = Q[i][0] + Q[i][1] + Q[i][2] + Q[i][3] + Q[i][4] + Q[i][5] + Q[i][6] + Q[i][7];
			conditional_tau[i] = tau[i]/.125;
			System.out.print( conditional_tau[i] + "\t");
		}

		System.out.println("\n\nFrame transmission probablity for different user priorities: ");
		for(int i=0; i<=7; i++){
			System.out.print( tau[i] + "\t");
		}


		/*
		System.out.println("\n\nFrame transmission probablity for different user priorities: ");
		for(int i=0; i<=7; i++){
			tau[i] = B000.lambda[i]*B000.alpha*(B000.gamma[i][0] + B000.beta_bar*B000.gamma[i][1] + Math.pow(B000.beta_bar, 2)*B000.gamma[i][2] + + Math.pow(B000.beta_bar, 3)*B000.gamma[i][3]
					+ Math.pow(B000.beta_bar, 4)*B000.gamma[i][4] + Math.pow(B000.beta_bar, 5)*B000.gamma[i][5] + Math.pow(B000.beta_bar, 6)*B000.gamma[i][6] + Math.pow(B000.beta_bar, 7)*B000.gamma[i][7])*b000;
			System.out.print( tau[i] + "\t");
		}
		 */


		//========================== Calculation of frame collision probability =============================


		System.out.println("\n\nP(no_nodes_transmitting): ");
		for(int i=0; i<=7; i++){
			no_nodes_transmitting *= Math.pow((1-tau[i]), n[i]);
		}
		System.out.println(no_nodes_transmitting);

		System.out.println("\nP(no_nodes_of_other_UP_transmitting): \n");
		for(int i=0; i<=7; i++){
			for(int j=0; j<=7; j++){
				if(i!=j){
					no_nodes_of_other_UP_transmitting[i] *= Math.pow((1-tau[j]), n[j]);
				}
			}
			System.out.print(no_nodes_of_other_UP_transmitting[i] + "\t");
		}

		//System.out.println("\n\nP(one_node_of_UPi_transmitting): \n");
		for(int i=0; i<=7; i++){
			one_node_of_UPi_transmitting[i] = n[i]*tau[i]*Math.pow((1-tau[i]), (n[i]-1));
			//System.out.print(one_node_of_UPi_transmitting[i] + "\t");
			//System.out.print(Math.pow((1-tau[i]), (n[i]-1)) + "\t");
		}

		System.out.println("\n\nMultiplication: ");
		for(int i=0; i<=7; i++){
			System.out.print(no_nodes_of_other_UP_transmitting[i]*one_node_of_UPi_transmitting[i] + "\t");
		}

		System.out.println("\n\nFrame collision probablity for different user priorities: ");
		for(int i=0; i<=7; i++){
			xi[i] = 1 - no_nodes_transmitting - no_nodes_of_other_UP_transmitting[i]*one_node_of_UPi_transmitting[i];
			System.out.print( xi[i] + "\t" );
		}


		//==========================  Calculation of frame failure probability ===============================


		int exp = (int) (Math.random()*4);
		int quo = (int) (Math.random()*9);
		double BER = (1+quo)*Math.pow(10, -(4+exp));
		System.out.println("\nexp = " + (1+exp));
		System.out.println("\n\nBER = " + BER);
		//double BER = 0.00001;


		Upsilon = 1 - (double)( Math.pow((1 - BER), (h+p)) );
		Upsilon_star = (double)( Math.pow((1 - BER), (h+p)) );

		System.out.println("PER (Upsilon) = " + Upsilon);
		System.out.println("(Upsilon_star) = " + Upsilon_star);

		System.out.println("\n\nFrame faliure probablity for different user priorities: ");
		for(int i=0; i<=7; i++){
			F[i] = (xi[i] + (1-xi[i])*Upsilon);
			System.out.print( F[i] + "\t");
		}

		//========================== Calculation of reliability ==============================================

		//double W = 1 - Math.pow((1 - beta), m)*b000;
		//System.out.println("\nReliability: " + W);

	}

	@SuppressWarnings("static-access")
	public static void main (String [] arguments){
		computation_of_frame_transmission FRAME_TRANSMISSION = new computation_of_frame_transmission();
		FRAME_TRANSMISSION.compute_farme_transmission();
	}


}
