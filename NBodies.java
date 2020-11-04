import java.util.*;
import java.lang.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.*;
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
    private List<celestialBody> arrList;
    private double scale;
    Timer time = new Timer(0,this);
    int rotationX;
    int rotationY;
    int maxX = 768;
    int maxY = 768;
    double gravity = 6.67408e-11;

    public NBodies(String fileName) throws IOException, Exception
    {
        String fileInput = fileName;
        content = new ArrayList <>();

        try(BufferedReader read = new BufferedReader(new FileReader(fileInput)))
        {
            String line = "";
            while((line = read.readLine())!=null)
            {
                content.add(line.split(","));
            }
        }catch (Exception e){
            System.out.println("Error no file found");
            System.exit(0);
        }
        scale = Double.parseDouble(content.get(1)[0]);
       
            if(content.get(0)[0].equals("ArrayList"))
            {
                arrList = new ArrayList<>();
            }
            else{
                arrList = new LinkedList<>();
            }

            for(int i=2;i<content.size();i++)
            {
                name = content.get(i)[0];
                mass = Double.parseDouble(content.get(i)[1]);
                xValue = Integer.parseInt(content.get(i)[2]);
                yValue = Integer.parseInt(content.get(i)[3]);
                xDirection = Double.parseDouble(content.get(i)[4]);
                yDirection =  Double.parseDouble(content.get(i)[5]);
                size = Integer.parseInt(content.get(i)[6]);
                celestialBody createPlanet = new celestialBody(name,mass,xValue,yValue,xDirection,yDirection,size);
                System.out.println(createPlanet.toString());
                arrList.add(createPlanet);
            }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(int i =0;i<arrList.size();i++)
        {
            celestialBody body = arrList.get(i);
            g.setColor(Color.BLUE);
            g.fillOval((int) body.xValue(),(int) body.yValue(),body.size(),body.size());
            g.drawString(Integer.toString(i), (int) body.xValue() + body.size()*2, (int)body.yValue() + body.size()*2);
            g.drawString(String.format("%.3f",body.xVelocity()) + "" + String.format("%.3f",body.yVelocity()), (int) body.xValue() + body.size(), (int) body.yValue() + body.size() + 10);
            g.drawString(String.format("%.3f",body.xValue()) + "" + String.format("%.3f",body.yValue()), (int) body.xValue() + body.size(),(int) body.yValue() + body.size()+ 20);
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
        for(int i = 0;i<arrList.size();i++)
        {
            celestialBody body1 = arrList.get(i);
            double velocityChangeX = 0.0;
            double velocityChangeY = 0.0;
            for(int j = 0;j<arrList.size();j++)
            {
                if(i!=j)
                {
                    celestialBody body2 = arrList.get(j);
                    double xDistance = distance(body1.xValue(),body2.xValue());
                    double yDistance = distance(body1.yValue(),body2.yValue());
                    double distance = Math.sqrt(xDistance*xDistance+yDistance*yDistance);
                    double gravPull = gravitation(body1.getMass(),body2.getMass(),distance);
                    double gravityX = gravPull* xDistance/distance;
                    double gravityY = gravPull * yDistance/distance;

                    if(body1.xValue()<body2.xValue())
                    {
                        velocityChangeX-=gravityX;
                    }else{
                        velocityChangeX+=gravityX;
                    }

                    if(body1.yValue()<body2.yValue())
                    {
                        velocityChangeY-=gravityY;
                    }else{
                        velocityChangeY+=gravityY;
                    }
                }
            }
            body1.setVelx(body1.xVelocity() + velocityChangeX / scale / body1.getMass());
            body1.setVely(body1.yVelocity() + velocityChangeY / scale / body1.getMass());

            body1.setX(body1.xValue()+ body1.xVelocity());
            body1.setY(body1.yValue()+ body1.yVelocity());
        }
        repaint();
    }

    public static void main(String[] args) throws IOException, Exception
    {
        String fileName = "nbody_input.txt";
        NBodies t = new NBodies(fileName);
        JFrame jf = new JFrame(); 
        jf.setTitle("Celestial Bodies"); 
        jf.setSize(t.maxX, t.maxY); 
        jf.add(t);
        jf.setVisible(true); 
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
