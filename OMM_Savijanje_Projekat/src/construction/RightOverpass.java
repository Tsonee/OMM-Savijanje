package construction;

import helper.ContLoad;
import helper.Force;
import helper.Moment;

public class RightOverpass extends Part
{
    Force force = null;
    Moment moment = null;

    public RightOverpass(double length) 
    {
        this.length = length;

        this.name = "Right Overpass";
    }

    public RightOverpass() 
    {
        this.name = "Right Overpass";
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
                return moment.intensity*Math.pow(this.length, 2)/(2*E*I);
            }
            else if(moment.direction == "left")
            {
                return -1*(moment.intensity*Math.pow(this.length, 2)/(2*E*I));
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
            return new Moment(force.distance*force.intensity, "right", length);
        }

        return new Moment(force.intensity*force.distance, "left", length);
    }

    public Moment reduceMoment(double lenght)
    {
        return new Moment(this.moment.intensity, this.moment.direction, lenght);
    }

    public Moment reduceContLoad(double length)
    {
        return new Moment((this.contLoad.lenght/2)*contLoad.getForce(), "right", length);
    }
}
