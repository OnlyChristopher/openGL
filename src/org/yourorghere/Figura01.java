/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author Christopher
 */
public class Figura01 implements GLEventListener{

    private GL gl;
    private static float trasladaX=-3f;    
    private static float trasladaY=-3f;

    private static float traslada1X=1;    
    private static float traslada1Y=0;
    
    private static float traslada2X=0;    
    private static float traslada2Y=2;
    
    private static float traslada3X=7.0f;    
    private static float traslada3Y=0;

    /*Iniciamos el valor en la escala en 1     
* para que cuando el objeto inicie el programa aun no se escale*/
    
    private static float escalaX=1.3f;    
    private static float escalaY=1.3f;
    
//Hacemos lo mismo para la rotacion sobre los ejes x,y
    
    private static float rotarX=0;
    private static float rotarY=0;
    
    public static float array[]=new float[8];
    public static float array2[]=new float[4];

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Figura01());
        frame.add(canvas);
        frame.setSize(1200, 900);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {
            }

            @Override
            public void keyPressed(KeyEvent arg0) {
            
            //SCALAMIENTO EN X
                //escalaremos el objeto sobre el eje x, lo haremos mas grande.
                if(arg0.getKeyCode()==KeyEvent.VK_A){
                escalaX+=.1;
                System.out.println("El valor de X en la escalacion: "+escalaX);
                }
                //Si obtenemos el codigo de la tecla 2,
                //escalaremos el objeto sobre el eje x, lo haremos mas pequeño...
                if(arg0.getKeyCode()==KeyEvent.VK_S){
                escalaX-=.1;
                System.out.println("El valor de X en la escalacion: "+escalaX);
                }
            
            //SCALAMIENTO EN Y
                //escalaremos el objeto sobre el eje y, lo haremos mas grande...
                if(arg0.getKeyCode()==KeyEvent.VK_D){
                escalaY+=.1;
                System.out.println("El valor de Y en la escalacion: "+escalaY);
                }
                //Si obtenemos el codigo de la tecla 4,
                //escalaremos el objeto sobre el eje y, lo haremos mas pequeño
                if(arg0.getKeyCode()==KeyEvent.VK_Z){
                escalaY-=.1;
                System.out.println("El valor de Y en la escalacion : "+escalaY);
                }
            
            //SCALAMIENTO EN X E Y 
                if(arg0.getKeyCode()==KeyEvent.VK_C){
                escalaX+=.1;
                escalaY+=.1;
                System.out.println("El valor de Y e X en la escalacion: "+escalaY + " y " + escalaX);
                }
                //Si obtenemos el codigo de la tecla 4,
                //escalaremos el objeto sobre el eje y, lo haremos mas pequeño
                if(arg0.getKeyCode()==KeyEvent.VK_V){   
                escalaX-=.1;
                escalaY-=.1;
                System.out.println("El valor de Y e X en la escalacion : "+escalaY + " y " + escalaX);
                }
            

            //ROTAMIENTOS 
                if(arg0.getKeyCode()==KeyEvent.VK_1){
                rotarX+=1.0f;
                System.out.println("El valor de X en la rotacion: "+ rotarX);
                }
                if(arg0.getKeyCode()==KeyEvent.VK_2){
                rotarX-=5;
                System.out.println("El valor de X en la rotacion: "+ rotarX);
                }
                if(arg0.getKeyCode()==KeyEvent.VK_3){
                rotarY+=2.5;
                System.out.println("El valor de Y: "+ rotarY);
                }
                if(arg0.getKeyCode()==KeyEvent.VK_4){
                rotarY-=5;
                System.out.println("El valor de Y:"+ rotarY);
                }
                
                
            //DESPLAZAMIENTOS CON LAS FLECHAS DEL TECLADO
                //trasladaremos el objeto sobre el eje x hacia la izquierda...
                if(arg0.getKeyCode()==KeyEvent.VK_LEFT){
                trasladaX-=.1;
                traslada1X-=.1;
                traslada2X-=.1;
                traslada3X-=.1;
                System.out.println("El valor de X en la traslacion: "+ trasladaX);
                }

                if(arg0.getKeyCode()==KeyEvent.VK_RIGHT){
                trasladaX+=.1;
                traslada1X+=.1;
                traslada2X+=.1;
                traslada3X+=.1;
                System.out.println("El valor de X en la traslacion: "+trasladaX);
                }

                if(arg0.getKeyCode()==KeyEvent.VK_UP){
                trasladaY+=.1;
                traslada1Y+=.1;
                traslada2Y+=.1;
                traslada3Y+=.1;
                System.out.println("El valor de Y en la traslacion: "+trasladaY);
                }

                if(arg0.getKeyCode()==KeyEvent.VK_DOWN){
                trasladaY-=.1;
                traslada1Y-=.1;
                traslada2Y-=.1;
                traslada3Y-=.1;
                System.out.println("El valor de Y en la traslacion: "+trasladaY);
                }

                if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE ){
                trasladaX=0;
                trasladaY=0;
                escalaX=1;
                escalaY=1;
                rotarX=0;
                rotarY=0;
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    

    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Move the "drawing cursor" around
        
        castillo(drawable);
        cohete(drawable);

        
        gl.glFlush();
    }
    protected void castillo(GLAutoDrawable drawable){
        gl.glTranslatef(trasladaX, trasladaY, -20f);
        gl.glRotatef(rotarX, 1, 0, 0);
        gl.glRotatef(rotarY, 0, 1, 0);
        gl.glScalef(escalaX, escalaY, 0);
        //Torre principal
        
        gl.glColor3f(0.0f, 0.50f, 0.9f);
        dibujarCuadrado(-1f, 5f, 1f, 5f, 1f, 0f, -1f, 0f, drawable);
        
        //Puerta
        gl.glColor3f(0.0f, 0.50f, 0.10f);
        dibujarCuadrado(-0.5f, 1.5f, 0.5f, 1.5f, 0.5f, 0f, -0.5f, 0f, drawable);
        
        //Torre Secundaria 1
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCuadrado(-3f, 2f, -1f, 2f, -1f, 0f, -3f, 0f, drawable);
        
        //Torre Secundaria 2
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCuadrado(1f, 3f, 3f, 3f, 3f, 0f, 1f, 0f, drawable);
        
        //Techo Secundario 1      
        gl.glColor3f(0, 1, 1);
        dibujarTriangulo2(0f, 6.54f, 1f, 5f, -1f, 5f, drawable);
        
        //Techo Principal
        gl.glColor3f(0, 1, 1);
        dibujarTriangulo2(-2f, 3.8f, -1f, 2f, -3f, 2f, drawable);
      
        //Techo Secudnario 2
        gl.glColor3f(0, 1, 1);
        dibujarTriangulo2(2f, 4.6f, 3f, 3f, 1f, 3f, drawable);
        
        //Bandera 1
        gl.glColor3f(0.9f, 0.30f, 0.20f);
        dibujarTriangulo(-2f, 4.6f, -1.4f, 4.2f, -2f, 3.8f, drawable);
        
         //Bandera 2
        gl.glColor3f(0.9f, 0.30f, 0.20f);
        dibujarTriangulo(2f, 5.4f, 2.6f, 5f, 2f, 4.6f, drawable);
        
        //Ventana 1
        gl.glColor3f(0.9f, 0.70f, 0.20f);
        dibujarCuadrado(-2f, 1.6f, -1.4f, 1f, -2f, 0.4f, -2.6f, 1f, drawable);
        
        
         //Ventana 2
        gl.glColor3f(0.9f, 0.70f, 0.20f);
        dibujarCuadrado(2f, 1.6f, 2.6f, 1f, 2f, 0.4f, 1.4f, 1f, drawable);
        dibujarCirculo(0.3f, 1.99f, 2.39f, drawable);
    }
    
    protected void cohete (GLAutoDrawable drawable){
        
        gl.glTranslatef(traslada1X, traslada1Y, -30f);
        gl.glRotatef(rotarX, 1, 0, 0);
        gl.glRotatef(rotarY, 0, 1, 0);
        gl.glScalef(escalaX, escalaY, 0);
        
        //Base cohete 1
        gl.glColor3f(0.9f, 0.70f, 0.10f);
        dibujarCuadrado(4.5f, 1.5f, 6f, 1.5f, 6f, 1f, 4.5f, 1f, drawable);
        
        //Base cohete 2
        gl.glColor3f(0.27f,0.22f,0.07f);
        dibujarCuadrado(4.5f, 2.5f, 6f, 2.5f, 6f, 1.5f, 4.5f, 1.5f, drawable);
        
        //Base cohete 3
        gl.glColor3f(0.9f, 0.70f, 0.10f);
        dibujarCuadrado(4.5f, 3f, 6f, 3f, 6f, 2.5f, 4.5f, 2.5f, drawable);
        
        //Base cohete 4
        gl.glColor3f(0.27f,0.22f,0.07f);
        dibujarCuadrado(4.5f, 4f, 6f, 4f, 6f, 3f, 4.5f, 3f, drawable);
        
        //Base cohete 5
        gl.glColor3f(0.9f, 0.70f, 0.10f);
        dibujarCuadrado(4.5f, 4.5f, 6f, 4.5f, 6f, 4f, 4.5f, 4f, drawable);
        
        //Base cohete 5
        gl.glColor3f(0.27f,0.22f,0.07f);
        dibujarCuadrado(4.5f, 5f, 6f, 5f, 6f, 4.5f, 4.5f, 4.5f, drawable);
        
        //Cabeza cohete
        gl.glColor3f(0.9f, 0.70f, 0.10f);
        dibujarTriangulo(5.2f, 6f, 6f, 5f, 4.5f, 5f, drawable);        
        
        //Circulo 1
        dibujarCirculo(0.3f, 5.23f, 3.5f, drawable);
        
        //Circulo 2
        dibujarCirculo(0.3f, 5.22f, 2.03f, drawable);
        
        
        //Cola 1
        gl.glColor3f(0.83f,0.31f,0.32f);
        dibujarCuadrado(3.6f, 4f, 4.5f, 4.25f, 4.5f, 3.23f, 3.6f, 3f, drawable);
        
        
         //Cola 2
        gl.glColor3f(0.83f,0.31f,0.32f);
        dibujarCuadrado(6f, 4.26f, 7f, 4f, 7f, 3f, 6f, 3.22f, drawable);  
        
        
         //Cola 3
        gl.glColor3f(0.83f,0.31f,0.32f);
        dibujarCuadrado(3.5f, 1.5f, 4.5f, 1.9f, 4.5f, 0.8f, 3.5f, 0.5f, drawable);  
        
         
        //Cola 4
        gl.glColor3f(0.83f,0.31f,0.32f);
        dibujarCuadrado(6f, 1.9f, 7f, 1.5f, 7f, 0.5f, 6f, 0.8f, drawable);  
        
        
        //Estrella 1
        dibujarTriangulo3(3.26f, 1.43f, 3.41f, 1.18f, 3.11f, 1.18f, drawable);
        dibujarTriangulo3(3.1f, 1.35f, 3.41f, 1.35f, 3.25f, 1.1f, drawable);
        
        
        //Estrella 2
        dibujarTriangulo3(3.67f, 2.65f, 3.82f, 2.4f, 3.52f, 2.4f, drawable);
        dibujarTriangulo3(3.51f, 2.57f, 3.82f, 2.57f, 3.66f, 2.32f, drawable);
        
        
        //Estrella 3
        dibujarTriangulo3(3.75f, 4.87f, 3.9f, 4.62f, 3.6f, 4.62f, drawable);
        dibujarTriangulo3(3.6f, 4.8f, 3.9f, 4.80f, 3.75f, 4.55f, drawable);  
        
        //Estrella 4
        dibujarTriangulo3(6.83f, 5.29f, 6.98f, 5.04f, 6.68f, 5.04f, drawable);
        dibujarTriangulo3(6.68f, 5.2f, 6.98f, 5.2f, 6.83f, 4.95f, drawable);  
        
        //Estrella 5
        dibujarTriangulo3(7.11f, 4.54f, 7.26f, 4.29f, 6.96f, 4.29f, drawable);
        dibujarTriangulo3(6.96f, 4.45f, 7.26f, 4.45f, 7.11f, 4.2f, drawable);
        
        
        //Estrella 6
        dibujarTriangulo3(7.42f, 3.52f, 7.57f, 3.27f, 7.27f, 3.27f, drawable);
        dibujarTriangulo3(7.27f, 3.42f, 7.57f, 3.42f, 7.42f, 3.17f, drawable);
        
        

        
    }

    protected void dibujarCuadrado(float x, float y, float x1, float y1, float x2, float y2, float x3, float y3, GLAutoDrawable arg0) {
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(x, y, 0.0f); // TOP LEFT
        gl.glVertex3f(x1, y1, 0.0f); // Top Right
        gl.glVertex3f(x2, y2, 0.0f); // Bottom Right

        gl.glVertex3f(x3, y3, 0.0f); // Bottom Left
        //Done Drawing The Quad
        gl.glEnd();
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    protected void dibujarTriangulo(float x, float y, float x1, float y1, float x2, float y2, GLAutoDrawable arg0) {
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x, y, 0.0f);
            gl.glVertex3f(x1, y1, 0.0f);
            gl.glVertex3f(x2, y2, 0.0f);
        gl.glEnd();
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
    
    protected void dibujarTriangulo2(float x, float y, float x1, float y1, float x2, float y2, GLAutoDrawable arg0) {
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f, 0.0f, 0.0f); // Set the current drawing color to red
            gl.glVertex3f(x, y, 0.0f);
            gl.glColor3f(0.0f, 1.0f, 0.0f);// Set the current drawing color to green
            gl.glVertex3f(x1, y1, 0.0f);
            gl.glColor3f(0.0f, 0.0f, 1.0f);   // Set the current drawing color to blue        
            gl.glVertex3f(x2, y2, 0.0f);
        gl.glEnd();
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
    
    protected void dibujarTriangulo3(float x, float y, float x1, float y1, float x2, float y2, GLAutoDrawable arg0) {
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(0.9f, 0.70f, 0.10f);// Set the current drawing color to red
            gl.glVertex3f(x, y, 0.0f);
            gl.glColor3f(0.27f,0.22f,0.07f);// Set the current drawing color to green
            gl.glVertex3f(x1, y1, 0.0f);
            gl.glColor3f(0.9f, 0.70f, 0.10f);;   // Set the current drawing color to blue        
            gl.glVertex3f(x2, y2, 0.0f);
        gl.glEnd();
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    protected void dibujarPunto(float x, float y, float x1, float y1, float x2, float y2, GLAutoDrawable arg0) {
        gl.glPointSize(10.0f);//set point size to 10 pixels
        gl.glBegin(GL.GL_POINTS); //starts drawing of points
        gl.glVertex3f(x, y, 0.0f);//upper-right corner
        gl.glVertex3f(x1, y1, 0.0f);//lower-left corner
        gl.glEnd();
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
    
     public static void dibujarCirculo(float radio, float x1, float y1, GLAutoDrawable arg){        
        float calx, caly;
        
        GL gl = arg.getGL();
        gl.glBegin(GL.GL_POLYGON);        
        for(float i=0; i<10; i+=0.01){
            calx = (float) (radio * Math.cos(i));
            caly = (float) (radio * Math.sin(i));
            
            gl.glVertex2f(calx+x1, caly+y1);
        }
        gl.glEnd();
    }
    
    

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    
}
