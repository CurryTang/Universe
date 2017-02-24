import java.lang.Math;

public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    //Constructor 1
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double rSquare = dx * dx + dy * dy;
        double r = Math.sqrt(rSquare);
        return r;
    }

    public double calcForceExertedBy(Planet p){
        // Note: Sign of the dx and dy
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double rSquare = dx * dx + dy * dy;
        double r = Math.sqrt(rSquare);
        double F = 6.67 * Math.pow(10, -11) * this.mass * p.mass / rSquare;
        return F;
    }

// The force you give to others
    public double calcForceExertedByX(Planet p){
        // Note: Sign of the dx and dy
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double rSquare = dx * dx + dy * dy;
        double r = Math.sqrt(rSquare);
        double F = 6.67 * Math.pow(10, -11) * this.mass * p.mass / rSquare;
        double Fx = F * dx / r;
        return -Fx;
    }

    public double calcForceExertedByY(Planet p){
        // Note: Sign of the dx and dy
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double rSquare = dx * dx + dy * dy;
        double r = Math.sqrt(rSquare);
        double F = 6.67 * Math.pow(10, -11) * this.mass * p.mass / rSquare;
        double Fy = F * dy / r;
        return -Fy;
    }

    public double calcNetForceExertedByX(Planet[] pa){
        double netForce = 0.0;
        for(Planet p : pa){
            if(this.equals(p)){
                continue;
            }
            double currForce = calcForceExertedByX(p);
            netForce += currForce;
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] pa){
        double netForce = 0.0;
        for(Planet p : pa){
            if(this.equals(p)){
                continue;
            }
            double currForce = calcForceExertedByY(p);
            netForce += currForce;
        }
        return netForce;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }

}