import java.util.*;
public class celestialBody {

        private String name;
        private double mass;
        private int xValue;
        private int yValue;
        private double xDirection;
        private double yDirection;
        private double updateX;
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

        public void setX(int xValue)
        {
            this.xValue += xDirection;
        }

        public void setY(int yValue)
        {
            this.yValue += yDirection;
        }

        public int xValue()
        {
            return this.xValue;
        }

        public double getMass()
        {
            return this.mass;
        }

        public int yValue()
        {
            return this.yValue;
        }

        public double xVelocity()
        {
            return this.xDirection;
        }

        public void setVelx(double velX)
        {
            xDirection +=velX;
        }

        public void setVely(double velY)
        {
            yDirection +=velY;
        }


        public double yVelocity()
        {
            return this.yDirection;
        }

        public int size()
        {
            return this.size;
        }
    }