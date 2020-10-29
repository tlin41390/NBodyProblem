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
    private Random rand;
    Timer time = new Timer(5,this);
    double value=0;
    int velocity=2;
    int rotationX;
    int rotationY;

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
                g.fillOval(arrList.get(i).xValue(),arrList.get(i).yValue(),arrList.get(i).size(),arrList.get(i).size());
            }
        time.start();
    }

    public Double gravityFormula(celestialBody p1, celestialBody p2)
    {
        final double gravConstant = 9.8;
        double xDistance = (p1.xValue()-p2.xValue())*scale;
        double yDistance = (p1.yValue()-p2.yValue())*scale;
        double trueDistance = Math.sqrt((xDistance*xDistance)+(yDistance*yDistance));
        double acceleration = (gravConstant*((p1.size()*p2.size()))/trueDistance);
        return acceleration;
    }

    public void actionPerformed(ActionEvent e)
    {
        for(int i = 0;i<arrList.size()-1;i++)
        {
            value = gravityFormula(arrList.get(i),arrList.get(i+1));
            arrList.get(i).setVelx(((arrList.get(i).xVelocity()+value)/scale/arrList.get(i).getMass()));
            arrList.get(i).setX((int) (arrList.get(i).xValue()+arrList.get(i).xVelocity()));
            arrList.get(i).setVely(((arrList.get(i).yVelocity()+value)/scale/arrList.get(i).getMass()));
            arrList.get(i).setY((int) (arrList.get(i).yValue()+arrList.get(i).yVelocity()));
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
