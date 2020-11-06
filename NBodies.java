import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.io.BufferedReader;
import java.awt.Color;
import java.awt.*;
import java.io.FileNotFoundException;
import java.awt.Graphics;
import java.io.IOException;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class NBodies extends JPanel implements ActionListener
{
    private String name;
    private double mass;
    private int xValue;
    private int yValue; 
    private double xDirection;
    private double yDirection;
    private int size;
    private List<String[]> content;
    private List<celestialBody> bodyList;
    private double scale;
    Timer time = new Timer(1,this);
    int rotationX;
    int rotationY;
    int maxX = 768;
    int maxY = 768;
    double gravity = 6.67408e-11;

    public NBodies(String fileName) throws IOException, Exception
    {
        String fileInput = fileName;
        content = new ArrayList <>();
        String line = "";
        try(BufferedReader read = new BufferedReader(new FileReader(fileInput)))
        {
            while((line = read.readLine())!=null)
            {
                //adds the lines of the file to the container.
                content.add(line.split(","));
            }
        }catch (Exception e){
            //if file is not found.
            System.out.println("Error no file found");
            System.exit(0);
        }
        scale = Double.parseDouble(content.get(1)[0]);
            //checks to see if the file specifies for arraylist or not.
            if(content.get(0)[0].equals("ArrayList"))
            {
                bodyList = new ArrayList<>();
            }
            else{
                bodyList = new LinkedList<>();
            }

            for(int i=2;i<content.size();i++)
            {
                //this will loop through the content of the container, and then get the specific details of the 
                //file, and if the data is a string, then it will parse into either an int or a double. Finally it will
                //print out the planets and their data.
                name = content.get(i)[0];
                mass = Double.parseDouble(content.get(i)[1]);
                xValue = Integer.parseInt(content.get(i)[2]);
                yValue = Integer.parseInt(content.get(i)[3]);
                xDirection = Double.parseDouble(content.get(i)[4]);
                yDirection =  Double.parseDouble(content.get(i)[5]);
                size = Integer.parseInt(content.get(i)[6]);
                celestialBody createPlanet = new celestialBody(name,mass,xValue,yValue,xDirection,yDirection,size);
                System.out.println(createPlanet.toString());
                bodyList.add(createPlanet);
            }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(int i =0;i<bodyList.size();i++)
        {
            //this block will create a celestialbody based off of the list of planets, and then
            //create that planet with its x and y value and its size. Three are also drawstring
            //methods to keep track of the coordinates.
            celestialBody body = bodyList.get(i);
            g.setColor(Color.BLUE);
            g.fillOval((int) body.xValue(),(int) body.yValue(),body.size(),body.size());
            
            g.drawString(Integer.toString(i), (int) body.xValue() + body.size(), 
            (int)body.yValue() + body.size());

            g.drawString(String.format("%.3f",body.xVelocity()) + "" + String.format("%.3f",body.yVelocity()), 
            (int) body.xValue() + body.size(), (int) body.yValue() + body.size());

            g.drawString(String.format("%.3f",body.xValue()) + "" + String.format("%.3f",body.yValue()), 
            (int) body.xValue() + body.size(),(int) body.yValue() + body.size());
        }
        time.start();
    }

    public double distance(double distx, double disty)
    {
        return (distx-disty)*scale;
    }

    public double gravitation(double mass, double mass2, double distance)
    {
        return gravity*(mass*mass2)/(Math.pow(distance,2));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        double velocityChangeX = 0.0;
        double velocityChangeY = 0.0;
        for(int i = 0;i<bodyList.size();i++)
        {
            //create a planet and initialize delta x and y.
            celestialBody body1 = bodyList.get(i);
            for(int j = 0;j<bodyList.size();j++)
            {
                if(i!=j)
                {
                    //creates a second body based off of j, and then calculates the xdistance, the ydistance
                    //as well as the diagnal of both x and y distance to plug into the gravity formula. Later 
                    //create a gravity variable for both x and y.
                    celestialBody body2 = bodyList.get(j);
                    double xDistance = distance(body1.xValue(),body2.xValue());
                    double yDistance = distance(body1.yValue(),body2.yValue());
                    double distance = Math.sqrt(xDistance*xDistance+yDistance*yDistance);
                    double gravPull = gravitation(body1.getMass(),body2.getMass(),distance);
                    double gravityX = gravPull* xDistance/distance;
                    double gravityY = gravPull * yDistance/distance;
                    
                    //resolve collisions as well as checks for planets flying off the jframe.
                    if(body1.xValue()<body2.xValue())
                    {
                        velocityChangeX-=gravityX;
                    }else{
                        velocityChangeX+=gravityX;
                    }
                    if(body1.yValue()-body2.yValue()==0)
                    {
                        gravityY = 0.0;
                    }

                    if(body1.yValue()<body2.yValue())
                    {
                        velocityChangeY-=gravityY;
                    }else{
                        velocityChangeY+=gravityY;
                    }
                }
            }
            //constant update for the the velocity as well as the position for the x and y.
            body1.setVelx(body1.xVelocity() + velocityChangeX / scale / body1.getMass());
            body1.setVely(body1.yVelocity() + velocityChangeY / scale / body1.getMass());
            body1.setY(body1.yValue()+ body1.yVelocity());
            body1.setX(body1.xValue()+ body1.xVelocity());

        }
        repaint();
    }
    public static void main(String[] args) throws IOException, Exception
    {
        String fileName = "";
        fileName = args[0];
        if(args.length == 0)
        {
            throw new Exception("Error no file found");
        }
        NBodies t = new NBodies(fileName);
        JFrame jf = new JFrame(); 
        jf.setTitle("Celestial Bodies"); 
        jf.setSize(t.maxX, t.maxY); 
        jf.add(t);
        jf.setVisible(true); 
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
