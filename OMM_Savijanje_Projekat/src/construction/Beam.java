package construction;

import java.util.ArrayList;

import helper.Force;
import helper.Moment;

public class Beam extends Part
{
    public ArrayList<Force> froces;
    public ArrayList<Moment> moments;
    
    public Beam(double length) 
    {
        this.length = length;

        froces = new ArrayList<Force>();
        moments = new ArrayList<Moment>();
    }

    public Beam()
    {
        froces = new ArrayList<Force>();
        moments = new ArrayList<Moment>();
    }

    /*
        ne validni input -1;
     * levi moment ka desno 1;
     * levi moment ka levo 2;
     * Desni moment ka levo 3;
     * Desni moment ka desno 4;
     * Moemnt na sredinu 5
     */
    public int getMomentCase(Moment moment) 
    {
        if(moment.distance == 0)
        {
            if(moment.direction == "right") return 1;
            else if(moment.direction == "left") return 2;
        }
        else if(moment.distance == this.length)
        {
            if(moment.direction == "left") return 3;
            else if(moment.direction == "right") return 4;
        }

        return 5;
    }
    
    public double getAlphaSum()
    {
        double sum = 0;
    

        for(Moment moment : this.moments)
        {
            int c = getMomentCase(moment);

            switch(c)
            {
                case 1:
                    sum += (moment.intensity*this.length) / (3*E*I);
                break;
                case 2:
                    sum -= (moment.intensity*this.length) / (3*E*I);
                break;
                case 3:
                    sum+= (moment.intensity*this.length) / (6*E*I);
                break;
                case 4:
                    sum -= (moment.intensity*this.length) / (6*E*I);
                break;
                case 5:
                    if(moment.direction == "left") sum+=(moment.intensity*length)/(24*E*I);
                    else sum -= (moment.intensity*length)/(24*E*I);
            
                break;
            }
        }
        

        for (Force force : froces) 
        {
            if(force.distance == this.length/2)
            {
                if(force.direction == "down")
                {
                    sum+= (force.intensity*Math.pow(this.length, 2)) / (16*E*I);
                }
                else if(force.direction == "up")
                {
                    sum -=(force.intensity*Math.pow(this.length, 2)) / (16*E*I);
                }
            }
        }
        

        if(contLoad != null)
        {
            sum += (contLoad.q*Math.pow(this.length, 3))/(24*E*I);
        }


        return sum;
    }

    public double getBetaSum()
    {
        double sum = 0;

        for(Moment moment : this.moments)
        {
            int c = getMomentCase(moment);
            
            switch(c)
            {
                case 1:
                    sum -= (moment.intensity*this.length) / (6*E*I);
                break;
                case 2:
                    sum += (moment.intensity*this.length) / (6*E*I);
                break;
                case 3:
                    sum-= (moment.intensity*this.length) / (3*E*I);
                break;
                case 4:
                    sum+= (moment.intensity*this.length) / (3*E*I);
                break;
                case 5:
                    if(moment.direction == "left") sum+=(moment.intensity*length)/(24*E*I);
                    else sum -= (moment.intensity*length)/(24*E*I);
                break;
            }
        }

        for (Force force : froces) 
        {
            if(force.distance == this.length/2)
            {
                if(force.direction == "down")
                {
                    sum -= (force.intensity*Math.pow(this.length, 2)) / (16*E*I);
                }
                else if(force.direction == "up")
                {
                    sum +=(force.intensity*Math.pow(this.length, 2)) / (16*E*I);
                }
            }
        }

        if(contLoad != null)
        {
            sum -= (contLoad.q*Math.pow(this.length, 3))/(24*E*I);
        }

        return sum;
    }

    public double solveK2()
    {
        double sum = 0;

        for (Moment moment : this.moments) 
        {
            int c = getMomentCase(moment);
            switch(c)
            {
                case 1:
                    sum+= (moment.intensity*Math.pow(this.length, 2)) /(16*E*I);
                    break;
                case 2:
                    sum-= (moment.intensity*Math.pow(this.length, 2)) /(16*E*I);
                    break;
                case 3:
                    sum+=(moment.intensity*Math.pow(this.length, 2)) /(16*E*I);
                    
                break;
                case 4:
                    sum-=(moment.intensity*Math.pow(this.length, 2)) /(16*E*I);
                break;
            }   
        }
        
        for (Force force : froces) 
        {
            if(force.direction == "down")
            {
                sum +=(1f/48f)*((force.intensity*Math.pow(this.length, 2))/(E*I));
            }
            else if(force.direction == "up")
            {
                sum -=(1f/48f)*((force.intensity*Math.pow(this.length, 3))/(E*I));
            }
        }

        if(contLoad != null)
        {
            sum+=((5f/384f)*(contLoad.q*Math.pow(this.length, 4)/(E*I)));
        }

        return sum;
    }
}