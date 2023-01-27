package construction;

import java.util.ArrayList;

public class Construction 
{
    //int k1,k2,k3

    //k1 = fkonzl - sumaAlpha*lprepLevo
    //k2 = fkonzd + sumaBeta*lprepDesno
    //k3 = suma svih slucajeva za gredu

    public LeftOverpass leftOverpass = null;
    public RightOverpass rightOverpass = null;
    public Beam beam = null;

    public Construction()
    {
        
    }

    public void addBeam(double lenght)
    {
        this.beam = new Beam(lenght);
    }

    public void addLeftOverpass(LeftOverpass leftOverpass)
    {
        this.leftOverpass = leftOverpass;
    }

    public void addRightOverpass(RightOverpass rightOverpass)
    {
        this.rightOverpass = rightOverpass;
    }

    public void reduce()
    {
       
            if(rightOverpass.force != null)
            {
                beam.moments.add(rightOverpass.reduceForce(this.beam.length));
            }

            if(rightOverpass.moment != null)
            {
                beam.moments.add(rightOverpass.reduceMoment(this.beam.length));
            }

            if(rightOverpass.contLoad != null)
            {
                beam.moments.add(rightOverpass.reduceContLoad(this.beam.length));
            }
        

        //isto i za levo

            if(leftOverpass.force != null)
            {
                beam.moments.add(leftOverpass.reduceForce(beam.length));
            }

            if(leftOverpass.moment != null)
            {
                beam.moments.add(leftOverpass.reduceMoment());
            }

            if(leftOverpass.contLoad != null)
            {
                beam.moments.add(leftOverpass.reduceContLoad());
            }
        
    }

    public double solveK3()
    {
        return this.rightOverpass.getFkonz() + beam.getBetaSum()*rightOverpass.length;
    }

    public double solveK1()
    {
        return this.leftOverpass.getFkonz() - beam.getAlphaSum()*leftOverpass.length;
    }

    public boolean isEmpty()
    {
        return this.beam == null && this.leftOverpass == null && this.rightOverpass == null;
    }
}
