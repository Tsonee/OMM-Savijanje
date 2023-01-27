import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.BranchElement;

import construction.Beam;
import construction.Construction;
import construction.LeftOverpass;
import construction.RightOverpass;
import helper.ContLoad;
import helper.Force;
import helper.Moment;

public class Main
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Construction contruction = null;
        Boolean running = true;

        while(running){

            System.out.println("Izaberite opciju: ");
            System.out.println("1 - Izracunati ugibe");
            System.out.println("2 - Napravite novu konstrukciju");
            System.out.println("3 - Dodajte komponente(greda, prepusti)");
            System.out.println("4 - dodajte momente,sile ili opterecenja");
            System.out.println("0 - Izadjite iz programa");
            System.out.println("--------------------");
        
            int choice1 = sc.nextInt();
          
            
            switch(choice1)
            {
                case 1:
                    contruction.reduce();
                    if(contruction == null)
                    {
                        System.out.println("--------------------");
                        System.out.println("Napravite novu konstrukciju!");
                        System.out.println("--------------------");
                        break;
                    }
                    if(contruction.beam != null)
                    {
                        System.out.println("--------------------");
                        System.out.println("fk2: " + contruction.beam.solveK2());
                        System.out.println("--------------------");
                    }
                    if(contruction.leftOverpass != null)
                    {
                        System.out.println("--------------------");
                        System.out.println("fk1: " + contruction.solveK1());
                        System.out.println("--------------------");
                    }
                    if(contruction.rightOverpass != null)
                    {
                        System.out.println("--------------------");
                        System.out.println("fk3: " + contruction.solveK3());
                        System.out.println("--------------------");
                        break;
                    }
                    if(contruction.isEmpty())
                    {
                        System.out.println("--------------------");
                        System.out.println("Dodajte komponente konstrukciji!");
                        System.out.println("--------------------");
                    }
                break;
                case 2:
                    contruction = null;
                    contruction = new Construction();
                    System.out.println("--------------------");
                    System.out.println("Napravljena nova konstrukcija!");
                    System.out.println("--------------------");
                                    
                break;
                case 3:
                    if(contruction == null)
                    {
                        System.out.println("--------------------");
                        System.out.println("Napravite novu konstrukciju!");
                        System.out.println("--------------------");
                        break;
                    }
                    System.out.println("Izaberite opciju:");
                    System.out.println("1 - Dodajte novu gredu");
                    System.out.println("2 - Dodajte novi levi prepust");
                    System.out.println("3 - Dodajte novi desni prepust");
                    System.out.println("0 - nazad");
                    System.out.println("--------------------");

                    int choice2 = sc.nextInt();
                    switch(choice2)
                    {
                        case 1:
                            contruction.beam = new Beam();
                            System.out.print("Unesite duzinu grede: ");
                            contruction.beam.length = sc.nextDouble();
                            System.out.println("Unesite vrednosti za E i I redom: ");
                            contruction.beam.E = sc.nextDouble();
                            contruction.beam.I = sc.nextDouble();
                            System.out.println("--------------------");
                            System.out.println("Nova greda dodata!");
                            System.out.println("--------------------");
                        break;
                        case 2: 
                            contruction.leftOverpass = new LeftOverpass();
                            System.out.print("Unesite duzinu levog prepusta: ");
                            contruction.leftOverpass.length = sc.nextDouble();
                            System.out.println("Unesite vrednosti za E i I redom: ");
                            contruction.leftOverpass.E = sc.nextDouble();
                            contruction.leftOverpass.I = sc.nextDouble();
                            System.out.println("--------------------");
                            System.out.println("Nov levi prepust dodat!");
                            System.out.println("--------------------");
                        break;
                        case 3:
                            contruction.rightOverpass = new RightOverpass();
                            System.out.print("Unesite duzinu desnog prepusta: ");
                            contruction.rightOverpass.length = sc.nextDouble();
                            System.out.println("Unesite vrednosti za E i I redom: ");
                            contruction.rightOverpass.E = sc.nextDouble();
                            contruction.rightOverpass.I = sc.nextDouble();
                            System.out.println("--------------------");
                            System.out.println("Nov desni prepust dodat!");
                            System.out.println("--------------------");
                        break;
                        case 0:
                        break;
                        default:
                        System.out.println("--------------------");
                        System.out.println("Pogresna komanda!");
                        System.out.println("--------------------");
                    }
                break;
                case 4:
                    if(contruction == null)
                    {
                        System.out.println("--------------------");
                        System.out.println("Napravite konstrukciju prvo!");
                        System.out.println("--------------------");
                        break;
                    }
                    
                    System.out.println("Izaberite opciju:");
                    System.out.println("1 - Greda ");
                    System.out.println("2 - Levi prepust");
                    System.out.println("3 - Desni prepust");
                    System.out.println("0 - nazad");
                    System.out.println("--------------------");

                    int choice3 = sc.nextInt();

                    switch(choice3)
                    {
                        case 1:
                            if(contruction.beam == null)
                            {
                                
                                System.out.println("Dodajte gredu prvo!\n");
                                System.out.println("--------------------");
                                break;
                            }

                            System.out.println("Izaberite opciju:");
                            System.out.println("1 - Dodajte silu na sredini grede");
                            System.out.println("2 - Dodajte moment");
                            System.out.println("3 - Dodajte kontinualno opterecenje");
                            System.out.println("0 - Nazad");
                            System.out.println("--------------------");
                            int choice4 = sc.nextInt();

                            switch(choice4)
                            {
                                case 1:
                                    System.out.print("Unesite intenzitet sile: ");
                                    double intensity = sc.nextDouble();
                                    sc.nextLine();
                                    System.out.print("Unesite pravac silie(W - ka gore, S - ka dole): ");
                                    String direction = sc.next();
                                    
                                    if(direction.equals("S"))
                                    {
                                        contruction.beam.froces.add(new Force(intensity, "down", contruction.beam.length/2));
                                        System.out.println("--------------------");
                                        System.out.println("Sila na sredini dodata!");
                                        System.out.println("--------------------");
                                    }
                                    else if(direction.equals("W")) 
                                    {
                                        contruction.beam.froces.add(new Force(intensity, "up", contruction.beam.length/2));
                                        System.out.println("--------------------");
                                        System.out.println("Sila na sredini dodata!");
                                        System.out.println("--------------------");
                                    }
                                    else
                                    {
                                        System.out.println("--------------------");
                                        System.out.println("Pogresan unos!\n");
                                        System.out.println("--------------------");
                                        break;
                                    }
                                break;
                                case 2:
                                System.out.print("Unesite intenzitet momenta: ");
                                    double intensity1 = sc.nextDouble();
                                    sc.nextLine();
                                    System.out.print("Unesite pravac momenta(A - ka levo, D - ka desno): ");
                                    String direction1 = sc.next();
                                    System.out.print("Unesite poziciju momenta(S-sredina, L-levi kraj, D-desni kraj): ");
                                    String dist1 = sc.next();
                                    double numDisct;

                                    if(dist1.equals("S"))
                                    {
                                        numDisct = contruction.beam.length/2;
                                    }
                                    else if(dist1.equals("L"))
                                    {
                                        numDisct = 0;
                                    }
                                    else if(dist1.equals("R"))
                                    {
                                        numDisct = contruction.beam.length;
                                    }
                                    else
                                    {
                                        System.out.println("--------------------");
                                        System.out.println("Pogresan unos!");
                                        System.out.println("--------------------");
                                        break;
                                    }
                                    
                                    if(direction1.equals("A"))
                                    {
                                        contruction.beam.moments.add(new Moment(intensity1, "left", numDisct));
                                        System.out.println("--------------------");
                                        System.out.println("Moment dodat!");
                                        System.out.println("--------------------");
                                    }
                                    else if(direction1.equals("D")) 
                                    {
                                        contruction.beam.moments.add(new Moment(intensity1, "right", numDisct));
                                        System.out.println("--------------------");
                                        System.out.println("Moment dodat!");
                                        System.out.println("--------------------");
                                    }
                                    else
                                    {
                                        System.out.println("--------------------");
                                        System.out.println("Pogresan unos!");
                                        System.out.println("--------------------");
                                        break;
                                    }
                                break;
                                case 3: 
                                    System.out.print("Unesite q: ");
                                    contruction.beam.contLoad = new ContLoad(contruction.beam.length,sc.nextDouble());
                                    System.out.println("--------------------");
                                    System.out.println("Dodato kontinualno opterecenje!");
                                    System.out.println("--------------------");
                                break;
                                case 0:
                                break;
                                default:
                                System.out.println("--------------------");
                                System.out.println("Pogresna komanda!");
                                System.out.println("--------------------");
                            }
                        break;
                        case 2:
                        if(contruction.leftOverpass == null)
                        {
                            
                            System.out.println("Dodajte levi prepust!\n");
                            System.out.println("--------------------");
                            break;
                        }

                        System.out.println("Izaberite opciju:");
                        System.out.println("1 - Dodajte silu na kraju prepusta");
                        System.out.println("2 - Dodajte moment na kraju prepusta");
                        System.out.println("3 - Dodajte kontinualno opterecenje");
                        System.out.println("0 - Nazad");
                        System.out.println("--------------------");
                        int choice5 = sc.nextInt();

                        switch(choice5)
                        {
                            case 1:
                                System.out.println("Unesite intenzitet sile: ");
                                double intensity = sc.nextDouble();
                                sc.nextLine();
                                System.out.print("Unesite pravac silie(W - ka gore, S - ka dole): ");
                                String direction = sc.next();

                                if(direction.equals("S"))
                                    {
                                        contruction.leftOverpass.addForce(new Force(intensity, "down", contruction.leftOverpass.length));
                                        System.out.println("--------------------");
                                        System.out.println("Sila dodata!");
                                        System.out.println("--------------------");
                                    }
                                    else if(direction.equals("W")) 
                                    {
                                        contruction.leftOverpass.addForce(new Force(intensity, "up", contruction.leftOverpass.length));
                                        System.out.println("--------------------");
                                        System.out.println("Sila na sredini dodata!");
                                        System.out.println("--------------------");
                                    }
                                    else
                                    {
                                        System.out.println("--------------------");
                                        System.out.println("Pogresan unos!\n");
                                        System.out.println("--------------------");
                                        break;
                                    }
                            break;
                            case 2:
                                System.out.println("Unesite intenzitet 0momenta: ");
                                    double intensity1 = sc.nextDouble();
                                    sc.nextLine();
                                    System.out.print("Unesite pravac momenta(A - ka levo, D - ka desno): ");
                                    String direction1 = sc.next();

                                    if(direction1.equals("A"))
                                        {
                                            contruction.leftOverpass.addMoment(new Moment(intensity1, "left", contruction.leftOverpass.length));
                                            System.out.println("--------------------");
                                            System.out.println("Moment dodat!");
                                            System.out.println("--------------------");
                                        }
                                        else if(direction1.equals("D")) 
                                        {
                                            contruction.leftOverpass.addMoment(new Moment(intensity1, "right", contruction.leftOverpass.length));
                                            System.out.println("--------------------");
                                            System.out.println("Moment Dodat");
                                            System.out.println("--------------------");
                                        }
                                        else
                                        {
                                            System.out.println("--------------------");
                                            System.out.println("Pogresan unos!\n");
                                            System.out.println("--------------------");
                                            break;
                                        }
                                break;
                                case 3:
                                    System.out.print("Unesite q: ");
                                    contruction.leftOverpass.contLoad = new ContLoad(contruction.leftOverpass.length,sc.nextDouble());
                                    System.out.println("--------------------");
                                    System.out.println("Dodato kontinualno opterecenje!");
                                    System.out.println("--------------------");
                                break;
                                case 0:
                            break;
                        }
                        
                        break;
                        case 3: 
                        if(contruction.rightOverpass == null)
                        {
                            
                            System.out.println("Dodajte desni prepust!\n");
                            System.out.println("--------------------");
                            break;
                        }

                        System.out.println("Izaberite opciju:");
                        System.out.println("1 - Dodajte silu na kraju prepusta");
                        System.out.println("2 - Dodajte moment na kraju prepusta");
                        System.out.println("3 - Dodajte kontinualno opterecenje");
                        System.out.println("0 - Nazad");
                        System.out.println("--------------------");
                        int choice6 = sc.nextInt();

                        switch(choice6)
                        {
                            case 1:
                                System.out.println("Unesite intenzitet sile: ");
                                double intensity = sc.nextDouble();
                                sc.nextLine();
                                System.out.print("Unesite pravac silie(W - ka gore, S - ka dole): ");
                                String direction = sc.next();

                                if(direction.equals("S"))
                                    {
                                        contruction.rightOverpass.addForce(new Force(intensity, "down", contruction.rightOverpass.length));
                                        System.out.println("--------------------");
                                        System.out.println("Sila dodata!");
                                        System.out.println("--------------------");
                                    }
                                    else if(direction.equals("W")) 
                                    {
                                        contruction.rightOverpass.addForce(new Force(intensity, "up", contruction.rightOverpass.length));
                                        System.out.println("--------------------");
                                        System.out.println("Sila na sredini dodata!");
                                        System.out.println("--------------------");
                                    }
                                    else
                                    {
                                        System.out.println("--------------------");
                                        System.out.println("Pogresan unos!\n");
                                        System.out.println("--------------------");
                                        break;
                                    }
                            break;
                            case 2:
                                System.out.println("Unesite intenzitet momenta: ");
                                    double intensity1 = sc.nextDouble();
                                    sc.nextLine();
                                    System.out.print("Unesite pravac momenta(A - ka levo, D - ka desno): ");
                                    String direction1 = sc.next();

                                    if(direction1.equals("A"))
                                        {
                                            contruction.rightOverpass.addMoment(new Moment(intensity1, "left", contruction.rightOverpass.length));
                                            System.out.println("--------------------");
                                            System.out.println("Moment dodat!");
                                            System.out.println("--------------------");
                                        }
                                        else if(direction1.equals("D")) 
                                        {
                                            contruction.rightOverpass.addMoment(new Moment(intensity1, "right", contruction.rightOverpass.length));
                                            System.out.println("--------------------");
                                            System.out.println("Moment Dodat");
                                            System.out.println("--------------------");
                                        }
                                        else
                                        {
                                            System.out.println("--------------------");
                                            System.out.println("Pogresan unos!\n");
                                            System.out.println("--------------------");
                                            break;
                                        }
                                break;
                                case 3:
                                    System.out.print("Unesite q: ");
                                    contruction.rightOverpass.contLoad = new ContLoad(contruction.rightOverpass.length,sc.nextDouble());
                                    System.out.println("--------------------");
                                    System.out.println("Dodato kontinualno opterecenje!");
                                    System.out.println("--------------------");
                                break;
                                case 0:
                            break;
                        }
                        break;
                        case 0: 
                        break;
                        default:
                        System.out.println("--------------------");
                        System.out.println("Pogresna komanda!");
                        System.out.println("--------------------");
                    }
                    
                break;
                case 0: running = false;
                break;
                
                default : 
                /*System.out.println("--------------------");
                System.out.println("Pogresna komanda!");
                System.out.println("--------------------");*/
                for (Moment moment : contruction.beam.moments) {
                    System.out.println("Moment: " + moment.intensity + moment.direction + moment.distance);
                }
            }
        }
    }

}
