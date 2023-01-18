package logic;
public class Triangle extends AbstractShape{

    private final double x;
    private final double y;
    private boolean low_quadrant = false;

    public Triangle( Integer quadrant, double x, double y )
    {
        super(quadrant);
        this.x = x;
        this.y = y;
        if ( this.quadrant == 3 || this.quadrant == 4) { this.low_quadrant = true; }
    }

    @Override
    public boolean isInShape(double x, double y)
    {
        if ( this.low_quadrant ){
            return this.isInQuadrant(x, y) && (y >= ( ( -this.y / this.x ) * x + this.y));
        } else {
            return this.isInQuadrant(x, y) && (y <= ( ( -this.y / this.x ) * x + this.y));
        }
    }
}