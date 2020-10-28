public class celestialBody {

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

        public double getMass()
        {
            return this.mass;
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