package logic;

public class Circle extends AbstractShape{

    private final double radius;

    public Circle( Integer quadrant, double radius )
    {
        super(quadrant);
        this.radius = radius;
    }

    @Override
    public boolean isInShape( double x, double y )
    {
        return this.isInQuadrant(x, y) &&
                (( Math.pow( x, 2 ) + Math.pow( y, 2 ) ) <= Math.pow( this.radius, 2 ));
    }
}