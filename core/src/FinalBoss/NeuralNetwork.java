package FinalBoss;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import Game.GameData;

public class NeuralNetwork
{
	ArrayList<double[]> inputs;
	ArrayList<Double> outputs;
	GameData gd;
	
	Random gen;
	double[][] HLweights;
	double[] Hvalues;
	double[] Aweights;
	
	final float LEARNINGRATE = 0.001F;
	double TEerror;
	
	public NeuralNetwork(GameData gd)
	{
		this.gd = gd;
		
		HLweights = new double[9][7];
		Hvalues = new double[7];
		Aweights = new double[7];
		gen = new Random();
		
		
		TEerror = Double.NaN;
	}
	
	public void Update()
	{	
		int derp = 0;
		TEerror = Double.MAX_VALUE;
		inputs = gd.binput;
		outputs = gd.boutput;
		
		initializeWeights();
		
		while(Math.abs(TEerror) > 0.00001  && Double.isFinite(TEerror) && 
				!Double.isNaN(TEerror))
		{
			if(Gdx.input.isKeyPressed(Input.Keys.J))
			{
				TEerror = 0;
				
				for(int index = 0; index < inputs.size(); index++)
				{
					TEerror += calculateError(index);
				}
				
				adjustWeights(TEerror / inputs.size());
				
				System.out.println("Iteration: " + derp + " done. Error: " + TEerror);
				derp ++;
				
				if(!(Double.isFinite(TEerror) && !Double.isNaN(TEerror)))
				{
					System.out.println("Network Failed, Retrying");
					initializeWeights(Double.MAX_VALUE);
				}
			}
		}
		
//		Prints Results
		System.out.println("---------------------------------------------------");
//		System.out.println("Network Done");
//		System.out.println("Initial Inputs: ");
//		for(int i = 0; i < inputs.get(0).length; i ++)
//		{
//			System.out.println(inputs.get(0)[i] + ", " + inputs.get(1)[i]);
//		}
//		System.out.println("Initial Outputs: " + outputs.get(0) + " " + outputs.get(1));
	}
	
	public double calculateError(int index)
	{
		double output = 0;
		
		for(int i = 0; i < Hvalues.length;i ++)
		{
			Hvalues[i]= 0;
		}
		
		//Gets Weighted Sum
		for(int i = 0; i < HLweights.length; i ++)
		{
			for(int j = 0; j < HLweights[i].length; j ++)
			{
				Hvalues[j] += HLweights[i][j] * inputs.get(index)[i];
			}
		}
		
		//Gets final weighted output
		for(int i = 0; i < Hvalues.length; i ++)
		{
			output += Hvalues[i] * Aweights[i];
		}
		
		return outputs.get(index) - 1 / (1 + Math.pow(Math.E, output));
	}
	
	public void adjustWeights(double error)
	{
		System.out.println("OG Error: " + error);
		for(int index = 0; index < inputs.size(); index ++)
		{
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
				System.out.println(Aweights[j]);
			}
		}
	}
	
	public double calculate(double[] input)
	{
		double output = Double.NaN;
		
		if(input.length >=  HLweights.length)
		{
			output = 0;
			
			//Gets Weighted Sum
			for(int i = 0; i < HLweights.length; i ++)
			{
				for(int j = 0; j < HLweights[i].length; j ++)
				{
					Hvalues[j] += HLweights[i][j] * input[i];
				}
			}
			
			//Gets final weighted output
			for(int i = 0; i < Hvalues.length; i ++)
			{
				output += Hvalues[i] * Aweights[i];
			}
		}
		
		return output;
	}
	
	private void initializeWeights()
	{	
		System.out.println("Initializing Weights");
		
		for(int i = 0; i < HLweights.length; i ++)
		{
			for(int j = 0; j < HLweights[i].length; j ++)
			{
				HLweights[i][j] = gen.nextInt();
			}
		}
		
		for(int i = 0; i < Aweights.length; i ++)
		{
			Aweights[i] = gen.nextInt();
		}
	}
	
	private void initializeWeights(double error)
	{	
		initializeWeights();
		this.TEerror = error;
	}
}
