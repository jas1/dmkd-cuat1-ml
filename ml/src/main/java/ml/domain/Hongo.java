package ml.domain;

import ml.domain.EnumsRestringidas.AirHumidity;
import ml.domain.EnumsRestringidas.Color;
import ml.domain.EnumsRestringidas.Land;
import ml.domain.EnumsRestringidas.Shape;
import ml.domain.EnumsRestringidas.Size;
import ml.domain.EnumsRestringidas.Texture;

public class Hongo {
	
	public Color color;
	public Size size;
	public Shape shape;
	public Land land;
	public AirHumidity airHumidity;
	public Texture texture;
	
	public Hongo() {}
	public Hongo(Hongo hongo) {
		color = hongo.color;
		size = hongo.size;
		shape = hongo.shape;
		land = hongo.land;
		airHumidity = hongo.airHumidity;
		texture = hongo.texture;
	}

}
