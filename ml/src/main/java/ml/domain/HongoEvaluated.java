package ml.domain;

public class HongoEvaluated extends Hongo{

	public HongoEvaluated() {}
	
	public HongoEvaluated(Hongo hongo,boolean poison) {
		super(hongo);
		this.poison = poison;
	}
	
	public boolean poison;
}
