public class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[5];
        for(int i = 0;i < 5;i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String name = "images/" + in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass,name);
        }
        return planets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0.0, 0.0, "/images/starfield.jpg");
        for(int i = 0;i < planets.length;i++){
            planets[i].draw();
        }
        StdDraw.show();
        double time = 0.0;
        Planet[][] otherPlanet = new Planet[][]{{planets[1], planets[2], planets[3], planets[4]},
                                                {planets[0], planets[2], planets[3], planets[4]},
                                                {planets[1], planets[0], planets[3], planets[4]},
                                                {planets[1], planets[2], planets[0], planets[4]},
                                                {planets[1], planets[2], planets[3], planets[0]}};
        while(time < T){
            //StdAudio.play("2001.mid");
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for(int i = 0;i < planets.length;i++){
                xForces[i] = planets[i].calcNetForceExertedByX(otherPlanet[i]);
                yForces[i] = planets[i].calcNetForceExertedByY(otherPlanet[i]);
            }
            for(int i = 0;i < planets.length;i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0.0, 0.0, "/images/starfield.jpg");
            for(int i = 0;i < planets.length;i++){
                planets[i].draw();
            }
            StdDraw.show(10);
            time = time + dt;
        }
        //StdAudio.close();
    }
}
