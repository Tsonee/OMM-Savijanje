package construction;

import helper.ContLoad;
import helper.Force;
import helper.Moment;

public class LeftOverpass extends Part
{
    Force force = null;
    Moment moment = null;

    public LeftOverpass(double length) 
    {
        super();
        this.length = length;
    
        this.name = "Left Overpass";
    }

    public LeftOverpass() 
    {
        this.name = "Left Overpass";
    }

    public void addContLoad(ContLoad contLoad)
    {
        this.contLoad = contLoad;
    } 

    public void addMoment(Moment moment)
    {
        this.moment = moment;
    }

    public void addForce(Force force)
    {
        this.force = force;
    }

    public double getFkonz()
    {
        if(force != null)
        {
            if(force.direction == "down")
            {
                return force.intensity*Math.pow(this.length, 3)/(3*E*I);
            }
            else if(force.direction == "up")
            {
                return -1*(force.intensity*Math.pow(this.length, 3)/(3*E*I));
            }
        }

        if(moment != null)
        {
            if(moment.direction == "right")
            {
                return -1*moment.intensity*Math.pow(this.length, 2)/(2*E*I);
            }
            else if(moment.direction == "left")
            {
                return (moment.intensity*Math.pow(this.length, 2)/(2*E*I));
            }
        }

        if(contLoad != null)
        {
            return contLoad.q*Math.pow(this.length, 4)/(8*E*I);
        }

        return 0;
    }

    public Moment reduceForce(double length)
    {
        if(this.force.direction == "down")
        {
            return new Moment(force.distance*force.intensity, "left", 0);
        }

        return new Moment(force.intensity*force.distance, "right", 0);
    }

    public Moment reduceMoment()
    {
        return new Moment(this.moment.intensity, this.moment.direction,0);
    }

    public Moment reduceContLoad()
    {
        return new Moment((this.contLoad.lenght/2)*contLoad.getForce(), "left", 0);
    }

}
