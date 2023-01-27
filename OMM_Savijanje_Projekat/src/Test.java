import construction.Beam;
import construction.Construction;
import construction.LeftOverpass;
import construction.RightOverpass;
import helper.ContLoad;
import helper.Force;
import helper.Moment;

public class Test 
{
    public static void main(String[] args) 
    {
        Construction construction = new Construction();
        construction.beam = new Beam(2);
        construction.beam.E = 1;
        construction.beam.I = 1;
        construction.beam.moments.add(new Moment(1, "right", construction.beam.length/2));
        construction.leftOverpass = new LeftOverpass(1);
        construction.leftOverpass.E = 1;
        construction.leftOverpass.I = 1;
        construction.leftOverpass.addForce(new Force(0.25, "up", construction.leftOverpass.length));
        construction.rightOverpass = new RightOverpass(1);
        construction.rightOverpass.contLoad = new ContLoad(construction.rightOverpass.length, 0.66);
        construction.rightOverpass.E = 1;
        construction.rightOverpass.I = 1;
        
        construction.reduce();

        for (Moment m : construction.beam.moments) {
            System.out.println("Moment: " + m.intensity + " " + m.direction + " " + m.distance);
        }

        System.out.println("fk1: " + construction.solveK1());
        System.out.println("fk2: " + construction.beam.solveK2());
        System.out.println("fk3: " + construction.solveK3());
    }
}
