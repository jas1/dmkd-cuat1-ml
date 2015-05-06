package ml.domain;

import java.util.Random;

public abstract class EnumsRestringidas {
	public enum Color {red, brown, gray};
	public enum Size {small, large};
	public enum Shape {round,elongated};
	public enum Land {humid,dry};
	public enum AirHumidity {low,high};
	public enum Texture {smooth, rough};
	private static final Random rand = new Random();
	
	public static Color getRandomColor(){
		return Color.values()[rand.nextInt(Color.values().length)];
	}
	
	public static Size getRandomSize(){
		return Size.values()[rand.nextInt(Size.values().length)];
	}
	
	public static Shape getRandomShape(){
		return Shape.values()[rand.nextInt(Shape.values().length)];
	}
	
	public static Land getRandomLand(){
		return Land.values()[rand.nextInt(Land.values().length)];
	}
	
	
	public static AirHumidity getRandomAirHumidity(){
		return AirHumidity.values()[rand.nextInt(AirHumidity.values().length)];
	}
	
	
	public static Texture getRandomTexture(){
		return Texture.values()[rand.nextInt(Texture.values().length)];
	}
	
	public static boolean getRandomPoison(){
		return rand.nextBoolean();
	}

	
//  #     • color {red, brown, gray}
//  #     • size {small, large}
//  #     • shape {round,elongated}
//  #     • land {humid,dry}
//  #     • air humidity {low,high}
//  #     • texture {smooth, rough}
	
}
