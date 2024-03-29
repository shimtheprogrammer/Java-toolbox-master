 /*
  fpoint.java

  Demonstration of Kohonen Self-Organizing Maps
 
  Copyright (c) 1999, Tom Germano
  
  Worcester Polytechnic Institute course CS563
  http://www.cs.wpi.edu/~matt/cs563
  3/20/99
*/

/*
  This is just a simple class used to make the code look better. fpoints
    and ipoints are nothing more than three floats or integers with some
    procedures for manipulating them. These are used throughout the 
    program.
*/
import java.applet.*;
import java.util.*;
import java.lang.*;

public class soms {
   public float x,y,z;


public class ipoint {
   public int x,y,z;

   public ipoint() {
     x=0; y=0; z=0;
     }
     
   public ipoint(int x1, int y1, int z1) {
     x=x1; y=y1; z=z1;
     }

   public float dist() {
      return (float)Math.sqrt(x*x+y*y+z*z);
   }

   public void set(int x1, int y1, int z1) {
      x=x1;
      y=y1;
      z=z1;
   }
   
   public ipoint sub(ipoint m) {
      ipoint ret = new ipoint();
      ret.x=x-m.x;
      ret.y=y-m.y;
      ret.z=z-m.z;

      return ret;
   }

   public ipoint sub(int m) {
      ipoint ret = new ipoint();
      ret.x=x-m;
      ret.y=y-m;
      ret.z=z-m;

      return ret;
   }

   public ipoint add(ipoint m) {
      ipoint ret = new ipoint();
      ret.x=x+m.x;
      ret.y=y+m.y;
      ret.z=z+m.z;

      return ret;
   }

   public ipoint add(int m) {
      ipoint ret = new ipoint();
      ret.x=x+m;
      ret.y=y+m;
      ret.z=z+m;

      return ret;
   }

   public ipoint mult(int m) {
      ipoint ret = new ipoint();
      ret.x=x*m;
      ret.y=y*m;
      ret.z=z*m;

      return ret;
   }

   public ipoint mult(float m) {
      ipoint ret = new ipoint();
      ret.x=Math.round(x*m);
      ret.y=Math.round(y*m);
      ret.z=Math.round(z*m);

      return ret;
   }

   public ipoint div(int m) {
      ipoint ret = new ipoint();
      if (m!=0) {
         ret.x=x/m;
         ret.y=y/m;
         ret.z=z/m;
      }
      else
         ret.x=ret.y=ret.z=0;
      return ret;
   }

   public ipoint div(float m) {
      ipoint ret = new ipoint();
      if (m!=0.0f) {
         ret.x=Math.round(x/m);
         ret.y=Math.round(y/m);
         ret.z=Math.round(z/m);
      }
      else
         ret.x=ret.y=ret.z=0;
   
      return ret;
   }

   public int pipe(ipoint m) {
      return (x*m.x+y*m.y+z*m.z);
   }

   public void print() {
      System.out.println(" x = " + x);
      System.out.println(" y = " + y);
      System.out.println(" z = " + z);
      System.out.println("\n");
   }
};
   public soms()
     {
     x=0f; y=0f; z=0f;
     }
     
   public soms(float x1, float y1, float z1)
     {
     x=x1; y=y1; z=z1;
     }

   public float dist() {
      return (float)Math.sqrt(x*x+y*y+z*z);
   }

   public void set(float x1, float y1, float z1) {
      x=x1;
      y=y1;
      z=z1;
   }

   public void normalize()
   {
      float d=dist();
      x/=d;
      y/=d;
      z/=d; 
   }
   
   ipoint fti() {
      ipoint i= new ipoint(Math.round(x), Math.round(y), Math.round(z));
      return i;
   }                       

   public soms sub(soms m) {
      soms ret = new soms();
      ret.x=x-m.x;
      ret.y=y-m.y;
      ret.z=z-m.z;

      return ret;
   }

   public soms sub(int m) {
      soms ret = new soms();
      ret.x=x-(float)m;
      ret.y=y-(float)m;
      ret.z=z-(float)m;

      return ret;
   }

   public soms sub(float m) {
      soms ret = new soms();
      ret.x=x-m;
      ret.y=y-m;
      ret.z=z-m;

      return ret;
   }

   public soms add(soms m) {
      soms ret = new soms();
      ret.x=x+m.x;
      ret.y=y+m.y;
      ret.z=z+m.z;

      return ret;
   }

   public soms add(int m) {
      soms ret = new soms();
      ret.x=x+(float)m;
      ret.y=y+(float)m;
      ret.z=z+(float)m;

      return ret;
   }

   public soms add(float m)
   {
      soms ret = new soms();
      ret.x=x+m;
      ret.y=y+m;
      ret.z=z+m;

      return ret;
   }

   public soms mult(int m)
   {
      soms ret = new soms();
      ret.x=x*(float)m;
      ret.y=y*(float)m;
      ret.z=z*(float)m;

      return ret;
   }

   public soms mult(float m) {
      soms ret = new soms();
      ret.x=x*m;
      ret.y=y*m;
      ret.z=z*m;

      return ret;
   }

   public soms mult(double m) {
      soms ret = new soms();
      ret.x=(float)(x*m);
      ret.y=(float)(y*m);
      ret.z=(float)(z*m);

      return ret;
   }

   public soms div(int m) {
      soms ret = new soms();
      if (m!=0) {
         ret.x=x/m;
         ret.y=y/m;
         ret.z=z/m;
      }
      else
         ret.x=ret.y=ret.z=0;
      return ret;
   }

   public soms div(float m) {
      soms ret = new soms();
      if (m!=0.0) {
         ret.x=x/m;
         ret.y=y/m;
         ret.z=z/m;
      }
      else
         ret.x=ret.y=ret.z=0.0f;
   
      return ret;
   }

   public float pipe(soms m) {
      return (x*m.x+y*m.y+z*m.z);
   }

   public void print() {
      System.out.println(" x = " + x);
      System.out.println(" y = " + y);
      System.out.println(" z = " + z);
      System.out.println("\n");
   }
}