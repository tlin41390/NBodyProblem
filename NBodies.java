import java.util.*;
import java.lang.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.awt.Color;
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
    Timer time = new Timer(50,this);
    int velocity=2;
    int rotationX;
    int rotationY;
    final double gravity = 9.8;

    public NBodies(String fileName) throws IOException, Exception
    {
        String fileInput = fileName;
        content = new ArrayList<>();

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
                System.out.println(createPlanet.getName());
                arrList.add(createPlanet);
            }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
            for(int i =0;i<arrList.size();i++)
            {
                g.setColor(Color.RED);
                g.fillOval((int) arrList.get(i).xValue(),(int) arrList.get(i).yValue(),arrList.get(i).size()*2,arrList.get(i).size()*2);
            }
        time.start();
    }

    public double getXdistance(celestialBody p1, celestialBody p2)
    {
        return(p1.xValue()-p2.xValue())*scale;
    }

    public double getYdistance(celestialBody p1, celestialBody p2)
    {
        return (p1.yValue()-p2.yValue())*scale;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for(int i = 0;i<arrList.size();i++)
        {
            double velocityChangeX = 0.0;
            double velocityChangeY = 0.0;
            celestialBody body1 = arrList.get(i);
            for(int j = 0;j<arrList.size();j++)
            {
                if(i!=j)
                {
                    celestialBody body2 = arrList.get(j);
                    double xDistance = getXdistance(body1,body2);
                    double yDistance = getYdistance(body1,body2);
                    double gravityX = gravity * (body1.getMass()*body2.getMass())/Math.pow(xDistance,2);
                    double gravityY = gravity * (body1.getMass()*body2.getMass())/Math.pow(yDistance,2);

                    if(body1.xValue()-body2.xValue()==0)
                    {
                        gravityX = 0.0;
                    }
                    
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
            body1.setVelx(body1.xVelocity()+velocityChangeX/scale/body1.getMass());
            body1.setVely(body1.yVelocity()+velocityChangeY/scale/body1.getMass());

            body1.setX(body1.xValue()+body1.xVelocity());
            body1.setY(body1.yValue()+body1.yVelocity());
        }
        repaint();
    }

    public static void main(String[] args) throws IOException, Exception
    {
        String fileName = "nbody_input.txt";
        NBodies t = new NBodies(fileName);
        JFrame jf = new JFrame(); 
        jf.setTitle("Celestial Bodies"); 
        jf.setSize(768, 768); // Window size defined in the class 
        jf.add(t); // This appears below "setVisible" in the video 
        jf.setVisible(true); 
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
