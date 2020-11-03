import java.util.*;
public class celestialBody {

        private String name;
        private double mass;
        private double xValue;
        private double yValue;
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

        public void setX(double xValue)
        {
            this.xValue += xDirection;
        }

        public void setY(double yValue)
        {
            this.yValue += yDirection;
        }

        public double xValue()
        {
            return this.xValue;
        }

        public double getMass()
        {
            return this.mass;
        }

        public double yValue()
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