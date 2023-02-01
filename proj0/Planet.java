public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    // Gravitational constant
    private double G = 6.67e-11;

    /** Constructor */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    //** Returns the distance between the supplied planet and
    /*   the planet that is doing the calculation. */
    public double calcDistance(Planet p) {
        double diffX = p.xxPos - this.xxPos;
        double diffY = p.yyPos - this.yyPos;
        double squareR = Math.pow(diffX, 2) + Math.pow(diffY, 2);
        double distance = Math.sqrt(squareR);
        return distance;
    }

    //** Returns the force exerted on this planet by the given planet. */
    public double calcForceExertedBy(Planet p) {
        return G * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
    }

    //** Returns the force exerted on this planet by the given planet
    /*   in the X and Y directions. */
    public double calcForceExertedByX(Planet p) {
        double diffX = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);
        double byX = this.calcForceExertedBy(p) * (diffX / r);
        return byX;
    }

    public double calcForceExertedByY(Planet p) {
        double diffY = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);
        double byY = this.calcForceExertedBy(p) * (diffY / r);
        return byY;
    }

    //** Returns the net X and Y force exerted by all planets in array
    /*   upon the current Planet. */
    public double calcNetForceExertedByX(Planet[] array) {
        double forceX = 0;
        for (Planet p : array) {
            if (!this.equals(p)) {
                forceX += this.calcForceExertedByX(p);
            }
        }
        return forceX;
    }

    public double calcNetForceExertedByY(Planet[] array) {
        double forceY = 0;
        for (Planet p : array) {
            if (!this.equals(p)) {
                forceY += this.calcForceExertedByY(p);
            }
        }
        return forceY;
    }

    //** Changeing the Planet's velocity and position
    /*   if a x-force of fX and a y-force of fY were 
    /*   applied for dt seconds. */
    public void update(double dt, double fX, double fY) {
        // Acceleration
        double xA = fX / this.mass;
        double yA = fY / this.mass;
        // New velocity
        this.xxVel = this.xxVel + dt * xA;
        this.yyVel = this.yyVel + dt * yA;
        // New position
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        String img = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, img);
        StdDraw.show();
    }
}