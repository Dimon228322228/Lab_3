package logic;
public class Square extends AbstractShape{

    private final double x;
    private final double y;
    private boolean low_quadrant = false;

    public Square( Integer quadrant, double x, double y ) {
        super(quadrant);
        this.x = x;
        this.y = y;
        if ( this.quadrant == 3 || this.quadrant == 4) { this.low_quadrant = true; }
    }

    @Override
    public boolean isInShape(double x, double y)
    {
        if (this.low_quadrant)
        {
            if (this.quadrant == 4)
            {
                return this.isInQuadrant(x, y) && (this.x >= x) && (this.y <= y);
            } else {
                return this.isInQuadrant(x, y) && (this.x <= x) && (this.y <= y);
            }
        } else {
            if (this.quadrant == 1)
            {
                return this.isInQuadrant(x, y) && (this.x >= x) && (this.y >= y);
            } else {
                return this.isInQuadrant(x, y) && (this.x <= x) && (this.y >= y);
            }
        }
    }
}