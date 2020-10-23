import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.*;
import javax.swing.Timer;

public class NBodies extends JPanel
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
    private Random rand;

    private static class Node<celestialBody>
    {
        celestialBody data;
        Node<celestialBody> next;
        Node(celestialBody data)
        {
            this.data = data;
            next = null;
        }
    }

    private static class celestialBody
    {
        private String name;
        private double mass;
        private int xValue;
        private int yValue;
        private double xDirection;
        private double yDirection;
        private int size;

        public celestialBody(String name, double mass, int xValue, int yValue, double xDirection, double yDirection, int size)
        {
            this.name = name;
            this.mass = mass;
            this.xValue = xValue;
            this.yValue = yValue;
            this.xDirection = xDirection;
            this.yDirection = yDirection;
            this.size = size;
        }

        public String getName()
        {
            return this.name;
        }

        public int xValue()
        {
            return this.xValue;
        }

        public int yValue()
        {
            return this.yValue;
        }

        public int size()
        {
            return this.size;
        }
    }

    public NBodies(String fileName) throws IOException
    {
        String fileInput = fileName;
        content = new ArrayList<>();

        try(BufferedReader read = new BufferedReader(new FileReader(fileInput)))
        {
            String line = "";
            while((line = read.readLine())!=null){
                content.add(line.split(","));
            }
        }catch (Exception e){
            System.out.println("Error no file found");

        }
       
            arrList = new ArrayList<>();
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
        this.rand = new Random();
        super.paintComponent(g);
        for(int i =0;i<arrList.size();i++)
        {
            g.setColor(Color.RED);
            g.fillOval(arrList.get(i).xValue(),arrList.get(i).yValue(),arrList.get(i).size(),arrList.get(i).size());
        }
    }

    public static void main(String[] args) throws IOException
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
