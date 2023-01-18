package logic;

public abstract class AbstractShape implements Shape{

    protected Integer quadrant;

    public AbstractShape( Integer quadrant ){
        this.quadrant = quadrant;
    }

    public abstract boolean isInShape( double x, double y );

    protected boolean isInQuadrant( double x, double y )
    {
        if (x >= 0 && y >= 0 && this.quadrant == 1){
            return true;
        } else if (x <= 0 && y >= 0 && this.quadrant == 2){
            return true;
        } else if (x <= 0 && y <= 0 && this.quadrant == 3){
            return true;
        } else return x >= 0 && y <= 0 && this.quadrant == 4;
    }
}