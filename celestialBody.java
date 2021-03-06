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

        public void setX(double updateX)
        {
            this.xValue = updateX;
        }

        public void setY(double updateY)
        {
            this.yValue = updateY;
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
            xDirection = velX;
        }

        public void setVely(double velY)
        {
            yDirection =velY;
        }


        public double yVelocity()
        {
            return this.yDirection;
        }

        public int size()
        {
            return this.size;
        }

        @Override
        public String toString()
        {
            return "Planet " + this.name +", mass = " + this.mass+ ", x= " +this.xValue()+  ", y = " + this.yValue +
            ", xVelocity = "+this.xDirection+", yVelocity =  "+this.yDirection+ ", radius = "+ this.size(); 
        }
    }