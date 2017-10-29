package FinalBoss;

import java.util.ArrayList;
import java.util.Random;

import Game.GameData;

public class NeuralNetwork
{
	ArrayList<double[]> inputs;
	ArrayList<Double> outputs;
	GameData gd;
	
	double[][] HLweights;
	double[] Hvalues;
	double[] Aweights;
	
	final float LEARNINGRATE = 0.00001F;
	double error;
	
	public NeuralNetwork(GameData gd)
	{
		this.gd = gd;
		
		HLweights = new double[9][7];
		Hvalues = new double[7];
		Aweights = new double[7];
		
		
		error = Double.NEGATIVE_INFINITY;
	}
	
	public void update()
	{
		while(!Double.isFinite(error) || Double.isNaN(error))
		{
			initializeWeights();
			
			run();
			
			if(!Double.isFinite(error) || Double.isNaN(error))
			{
				System.out.println("Network Failed, Retrying");
			}
		}
		
		
		System.out.println("Network Done");
		
	}
	
	public void run()
	{	
		error = 1;
		int derp = 0;
		
		while(Math.abs(error) > LEARNINGRATE && Double.isFinite(error) && 
				!Double.isNaN(error))
		{
			error = 0;
			
			inputs = gd.binput;
			outputs = gd.boutput;
			
			for(int index = 0; index < inputs.size(); index ++)
			{
				double output = 0;
				
				//Gets Weighted Sum
				for(int i = 0; i < HLweights.length; i ++)
				{
					for(int j = 0; j < HLweights[i].length; j ++)
					{
						Hvalues[j] += HLweights[i][j] * inputs.get(index)[i];
					}
				}
				
				//
				for(int i = 0; i < Hvalues.length; i ++)
				{
					output += Hvalues[i] * Aweights[i];
				}
				
				error += outputs.get(index) - output;
				
				for(int i = 0; i < HLweights.length; i ++)
				{
					for(int j = 0; j < HLweights[i].length; j ++)
					{
						HLweights[i][j] += inputs.get(index)[i] * error * LEARNINGRATE;
					}
				}
				
				for(int j = 0; j < Aweights.length; j ++)
				{
					Aweights[j] += inputs.get(index)[j] * error * LEARNINGRATE;
				}
			}
			System.out.println("Iteration: " + derp + " done. Error: " + error);
			derp ++;
		}
	}
	
	private void initializeWeights()
	{	
		System.out.println("Initializing Weights");
		
		for(int i = 0; i < HLweights.length; i ++)
		{
			for(int j = 0; j < HLweights[i].length; j ++)
			{
				HLweights[i][j] = Math.random();
			}
		}
		
		for(int i = 0; i < Aweights.length; i ++)
		{
			Aweights[i] = Math.random();
		}
	}
}
