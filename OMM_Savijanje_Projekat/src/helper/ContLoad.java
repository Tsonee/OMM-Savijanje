package helper;

public class ContLoad 
{
    public double lenght;
    public double q;
    
    public ContLoad(double lenght, double q)
    {
        this.lenght = lenght;
        this.q = q;
    }

    public double getForce()
    {
        return q*lenght;
    }
}
