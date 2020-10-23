import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.*;

public class NBodies extends JPanel
{
    private String name;
    private double mass;
    private double xValue;
    private double yValue;
    private double xDirection;
    private double yDirection;
    private double size;
    private List<String[]> content;

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
        private double xValue;
        private double yValue;
        private double xDirection;
        private double yDirection;
        private double size;

        public celestialBody(String name, double mass, double xValue, double yValue, double xDirection, double yDirection, double size)
        {
            this.name = name;
            this.mass = mass;
            this.xValue = xValue;
            this.yValue = yValue;
            this.xDirection = xDirection;
            this.yDirection = yDirection;
            this.size = size;
        }

        public double xValue()
        {
            return xValue;
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
        if(content.contains("ArrayList"))
        {
            List<celestialBody> arrList = new ArrayList<>();
            for(int i=2;i<content.size();i++)
            {
                name = content.get(i)[3];
                mass = Double.parseDouble(content.get(i)[4]);
                xValue = Double.parseDouble(content.get(i)[5]);
                yValue = Double.parseDouble(content.get(i)[6]);
                xDirection = Double.parseDouble(content.get(i)[7]);
                yDirection =  Double.parseDouble(content.get(i)[8]);
                size = Double.parseDouble(content.get(i)[9]);
                celestialBody createPlanet = new celestialBody(name,mass,xValue,yValue,xDirection,yDirection,size);
                arrList.add(createPlanet);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(100,100,20,20);
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
