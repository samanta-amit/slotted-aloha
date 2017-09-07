package com.slottedaloha;

public class computation_of_b000 {

	double alpha = 0.99;
	//double alpha = (0.9 - Math.random()*0.18)/0.9;
	double beta_bar = 0.8;
	int m = 7;
	double La = 1;
	double Lc = 2;

	//========================== GAMMA value initialization ==========================

	double gamma [][] =new double[][] { { 0.125, 0.125, 0.0625, 0.0625, 0.06250, 0.06250, 0.06250, 0.06250 }, 
			{ 0.125, 0.125, 0.0625, 0.0625, 0.09375, 0.09375, 0.09375, 0.09375 },  
			{ 0.250, 0.250, 0.1250, 0.1250, 0.06250, 0.06250, 0.09375, 0.09375 }, 
			{ 0.250, 0.250, 0.1250, 0.1250, 0.12500, 0.12500, 0.12500, 0.12500 }, 
			{ 0.375, 0.375, 0.1875, 0.1875, 0.12500, 0.12500, 0.12500, 0.12500 }, 
			{ 0.375, 0.375, 0.1875, 0.1875, 0.18750, 0.18750, 0.18750, 0.18750 }, 
			{ 0.500, 0.500, 0.2500, 0.2500, 0.12500, 0.12500, 0.18750, 0.18750 }, 
			{ 1.000, 1.000, 0.5000, 0.5000, 0.25000, 0.25000, 0.25000, 0.25000 } 
	};

	//========================== LAMBDA value initialization ==========================

	static double [] lambda = { 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};

	public double b000_compuation() {

		double beta = Math.pow(beta_bar, (Lc-La));
		double omega = 0;
		double psi = 0;

		double A = (1 - Math.pow(beta, m)) /(1 - beta);     // DONE!
		double B = (beta_bar*(1 - beta))/(1 - beta_bar);    // DONE!
		double C = Math.pow(beta, m);						// DONE!
		double [] nabla = new double[8];

		//========================== NABLA value initialization ==========================	

		for(int i=1; i<7; i++) {
			nabla[i]  = 0;
		}

		for(int i=0; i<=7; i++) {
			for(int j=0; j<=m; j++) {
				nabla[i] = nabla[i] + (Math.pow(beta, j))/(gamma[i][j]);
			}
		}

		//========================== OMEGA value initialization ==========================	

		//System.out.println("\nValue of omega: \n");
		for(int i=0; i<=7; i++) {
			omega = omega + nabla[i]*lambda[i];			 
		}
		//System.out.println(omega);

		//========================== PSI value initialization ==========================	

		//System.out.println("\nValue of psi: \n");
		for(int i=0; i<=7; i++) {
			psi = psi + lambda[i]/gamma[i][0];			 
		}
		//System.out.println(psi);

		double D = omega;                                   // DONE!
		double E = psi;                                     // DONE!

		//========================== b000 value initialization ==========================

		double den_b000 = A*(La + B + 1/alpha -1) - C + D + E + 2;		
		double b000 = 1/den_b000;
		System.out.println("\nValue of b000 is: " + b000);
		System.out.println("\nValue of alpha is: " + alpha);
		return b000;
	}


	//========================== Main function ==========================

	public static void main (String [] arguments){
		computation_of_b000 B000 = new computation_of_b000();
		B000.b000_compuation();
	}
}
