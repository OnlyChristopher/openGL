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
public class Figura02 implements GLEventListener {

    private GL gl;
    private static float trasladaX = -6f;
    private static float trasladaY = -5f;

    private static float traslada1X = 1;
    private static float traslada1Y = -5;

    private static float traslada2X = 0;
    private static float traslada2Y = 2;

    private static float traslada3X = 7.0f;
    private static float traslada3Y = 0;

    /*Iniciamos el valor en la escala en 1     
* para que cuando el objeto inicie el programa aun no se escale*/
    private static float escalaX = 0.5f;
    private static float escalaY = 0.5f;

    private static float escala1X = 1.2f;
    private static float escala1Y = 1.2f;

//Hacemos lo mismo para la rotacion sobre los ejes x,y
    private static float rotarX = 0;
    private static float rotarY = 0;

    public static float array[] = new float[8];
    public static float array2[] = new float[4];

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();
        

        canvas.addGLEventListener(new Figura02());
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
                if (arg0.getKeyCode() == KeyEvent.VK_A) {
                    escalaX += .1;
                    System.out.println("El valor de X en la escalacion: " + escalaX);
                }
                //Si obtenemos el codigo de la tecla 2,
                //escalaremos el objeto sobre el eje x, lo haremos mas pequeño...
                if (arg0.getKeyCode() == KeyEvent.VK_S) {
                    escalaX -= .1;
                    System.out.println("El valor de X en la escalacion: " + escalaX);
                }

                //SCALAMIENTO EN Y
                //escalaremos el objeto sobre el eje y, lo haremos mas grande...
                if (arg0.getKeyCode() == KeyEvent.VK_D) {
                    escalaY += .1;
                    System.out.println("El valor de Y en la escalacion: " + escalaY);
                }
                //Si obtenemos el codigo de la tecla 4,
                //escalaremos el objeto sobre el eje y, lo haremos mas pequeño
                if (arg0.getKeyCode() == KeyEvent.VK_Z) {
                    escalaY -= .1;
                    System.out.println("El valor de Y en la escalacion : " + escalaY);
                }

                //SCALAMIENTO EN X E Y 
                if (arg0.getKeyCode() == KeyEvent.VK_C) {
                    escalaX += .1;
                    escalaY += .1;
                    System.out.println("El valor de Y e X en la escalacion: " + escalaY + " y " + escalaX);
                }
                //Si obtenemos el codigo de la tecla 4,
                //escalaremos el objeto sobre el eje y, lo haremos mas pequeño
                if (arg0.getKeyCode() == KeyEvent.VK_V) {
                    escalaX -= .1;
                    escalaY -= .1;
                    System.out.println("El valor de Y e X en la escalacion : " + escalaY + " y " + escalaX);
                }

                //ROTAMIENTOS 
                if (arg0.getKeyCode() == KeyEvent.VK_1) {
                    rotarX += 1.0f;
                    System.out.println("El valor de X en la rotacion: " + rotarX);
                }
                if (arg0.getKeyCode() == KeyEvent.VK_2) {
                    rotarX -= 5;
                    System.out.println("El valor de X en la rotacion: " + rotarX);
                }
                if (arg0.getKeyCode() == KeyEvent.VK_3) {
                    rotarY += 2.5;
                    System.out.println("El valor de Y: " + rotarY);
                }
                if (arg0.getKeyCode() == KeyEvent.VK_4) {
                    rotarY -= 5;
                    System.out.println("El valor de Y:" + rotarY);
                }

                //DESPLAZAMIENTOS CON LAS FLECHAS DEL TECLADO
                //trasladaremos el objeto sobre el eje x hacia la izquierda...
                if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
                    trasladaX -= .1;
                    traslada1X -= .1;
                    traslada2X -= .1;
                    traslada3X -= .1;
                    System.out.println("El valor de X en la traslacion: " + trasladaX);
                }

                if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
                    trasladaX += .1;
                    traslada1X += .1;
                    traslada2X += .1;
                    traslada3X += .1;
                    System.out.println("El valor de X en la traslacion: " + trasladaX);
                }

                if (arg0.getKeyCode() == KeyEvent.VK_UP) {
                    trasladaY += .1;
                    traslada1Y += .1;
                    traslada2Y += .1;
                    traslada3Y += .1;
                    System.out.println("El valor de Y en la traslacion: " + trasladaY);
                }

                if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
                    trasladaY -= .1;
                    traslada1Y -= .1;
                    traslada2Y -= .1;
                    traslada3Y -= .1;
                    System.out.println("El valor de Y en la traslacion: " + trasladaY);
                }

                if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    trasladaX = 0;
                    trasladaY = 0;
                    escalaX = 1;
                    escalaY = 1;
                    rotarX = 0;
                    rotarY = 0;
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
        

        robot(drawable);
        mujer(drawable);

        gl.glFlush();
    }

    protected void robot(GLAutoDrawable drawable) {
        gl.glTranslatef(trasladaX, trasladaY, -20f);
        gl.glRotatef(rotarX, 1, 0, 0);
        gl.glRotatef(rotarY, 0, 1, 0);
        gl.glScalef(escalaX, escalaY, 0);

        //Pie 1        
        gl.glColor3f(0.0f, 0.50f, 0.9f);
         gl.glPushMatrix();
        dibujarCuadrado(1.5f, 1.5f, 3.5f, 1.5f, 3.5f, 0f, 1.5f, 0f, drawable);

        //Pie 2        
        gl.glColor3f(0.0f, 0.50f, 0.9f);
        dibujarCuadrado(5f, 1.5f, 7f, 1.5f, 7f, 0f, 5f, 0f, drawable);

        //Pierna 1
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCuadrado(2f, 5f, 3f, 5f, 3f, 1.5f, 2f, 1.5f, drawable);

        //Pierna 2
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCuadrado(5.5f, 5f, 6.5f, 5f, 6.5f, 1.5f, 5.5f, 1.5f, drawable);

        //Cuerpo 
        gl.glColor3f(0.9f, 0.70f, 0.20f);
        dibujarCuadrado2(1.5f, 9.5f, 7f, 9.5f, 7f, 5f, 1.5f, 5f, drawable);

        //Brazo 1
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCuadrado(-1.5f, 9.5f, 1.5f, 9.5f, 1.5f, 9f, -1.5f, 9f, drawable);

        //Brazo 2
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCuadrado(7f, 9.5f, 10f, 9.5f, 10f, 9f, 7f, 9f, drawable);

        //Mano 1
        gl.glColor3f(0.9f, 0.70f, 0.20f);
        dibujarCirculo(0.6f, -2.12f, 9.3f, drawable);

        //Mano 2
        gl.glColor3f(0.9f, 0.70f, 0.20f);
        dibujarCirculo(0.6f, 10.59f, 9.23f, drawable);

        //Cuello
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCuadrado(4f, 10.5f, 4.5f, 10.5f, 4.5f, 9.5f, 4f, 9.5f, drawable);

        //Cabeza
        gl.glColor3f(0.9f, 0.70f, 0.20f);
        dibujarCuadrado(3f, 13f, 5.5f, 13f, 5.5f, 10.5f, 3f, 10.5f, drawable);

        //Casco
        gl.glColor3f(0f, 0.53f, 0.9f);
        dibujarCuadrado(3f, 14.5f, 5.5f, 14.5f, 6f, 13f, 2.5f, 13f, drawable);

        //Orejas        
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCuadrado(2.6f, 12.6f, 3f, 12.6f, 3f, 12f, 2.6f, 12f, drawable);
        dibujarCuadrado(5.5f, 12.6f, 5.9f, 12.6f, 5.9f, 12f, 5.5f, 12f, drawable);

        //Boca
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCuadrado(3.5f, 11.3f, 5f, 11.3f, 5f, 11f, 3.5f, 11f, drawable);

        //Ojos
        gl.glColor3f(0f, 0.53f, 0.9f);
        dibujarCirculo(0.4f, 3.73f, 12.42f, drawable);
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCirculo(0.3f, 3.73f, 12.42f, drawable);

        gl.glColor3f(0f, 0.53f, 0.9f);
        dibujarCirculo(0.4f, 4.8f, 12.4f, drawable);
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        dibujarCirculo(0.3f, 4.8f, 12.4f, drawable);

        //Nariz
        dibujarCirculo(0.3f, 4.27f, 11.62f, drawable);

    }

    protected void mujer(GLAutoDrawable drawable) {

        gl.glTranslatef(traslada1X, traslada1Y, -20f);
        gl.glRotatef(rotarX, 1, 0, 0);
        gl.glRotatef(rotarY, 0, 1, 0);
        gl.glScalef(escala1X, escala1Y, 0);

        //Base cohete 1
        dibujarCuadrado2(18f, 12f, 21f, 12f, 21f, 9f, 18f, 9f, drawable);

        //Falda
        gl.glColor3f(0.9f, 0.59f, 0.20f);
        dibujarTriangulo(18f, 9f, 21f, 9f, 19.5f, 7f, drawable);
        gl.glColor3f(0.9f, 0.30f, 0.20f);
        dibujarTriangulo(18f, 9f, 19.5f, 7f, 16.8f, 7f, drawable);
        dibujarTriangulo(21f, 9f, 22.5f, 7f, 19.5f, 7f, drawable);

        //Pierna 1 y 2
        gl.glColor3f(0.9f, 0.70f, 0.20f);
        dibujarCuadrado(17.8f, 7f, 18.4f, 7f, 18.4f, 3f, 17.8f, 3f, drawable);
        dibujarCuadrado(20.66f, 7f, 21.2f, 7f, 21.2f, 3.74f, 20.64f, 3.74f, drawable);
        dibujarCuadrado(21.2f, 4.83f, 23.28f, 3.52f, 23.07f, 3.22f, 21.2f, 4.38f, drawable);

        //Pies
        gl.glColor3f(0.9f, 0.30f, 0.20f);
        dibujarCirculo(0.8f, 18.09f, 2.27f, drawable);
        dibujarCirculo(0.8f, 23.72f, 2.9f, drawable);

        //Brazo 1 y 2
        gl.glColor3f(0.9f, 0.70f, 0.20f);
        dibujarCuadrado(18f, 12f, 18f, 11.56f, 16.36f, 10.66f, 16.25f, 10.96f, drawable);
        dibujarCuadrado(21f, 12f, 23.24f, 10.85f, 23.07f, 10.58f, 21f, 11.59f, drawable);

        //Manos
        gl.glColor3f(0.9f, 0.30f, 0.20f);
        dibujarCirculo(0.8f, 15.79f, 10.63f, drawable);
        dibujarCirculo(0.8f, 23.61f, 10.44f, drawable);

        //Cabeza
        gl.glColor3f(1f, 0.85f, 0.9f);
        dibujarCirculo(1.6f, 19.54f, 13.61f, drawable);

        //Ojos
        gl.glColor3f(0.41f, 0.38f, 0.33f);
        dibujarCirculo(0.3f, 18.74f, 14.16f, drawable);
        dibujarCirculo(0.3f, 20.33f, 14.12f, drawable);

        //Cabello
        gl.glColor3f(0.41f, 0.38f, 0.33f);
        dibujarCuadrado(19f, 16f, 20.05f, 16f, 20.06f, 15.12f, 19f, 15.12f, drawable);
        dibujarCuadrado(17.51f, 15.07f, 19f, 16f, 19f, 15.12f, 18.08f, 14.35f, drawable);
        dibujarCuadrado(20.05f, 16f, 21.66f, 15.13f, 21.07f, 14.32f, 20.06f, 15.12f, drawable);

        dibujarTriangulo(20.7f, 14.61f, 22.12f, 13.3f, 20.97f, 12.89f, drawable);
        dibujarTriangulo(18.23f, 14.48f, 17.93f, 13.05f, 17.02f, 13.75f, drawable);

        //Sol
        gl.glColor3f(1f, 0.94f, 0.28f);
        dibujarCirculo(1.2f, 15.84f, 17.78f, drawable);

        gl.glColor3f(1f, 0.76f, 0.31f);
        dibujarCuadrado(15.55f, 20f, 16f, 20f, 15.99f, 18.94f, 15.6f, 18.93f, drawable);
        dibujarCuadrado(16.96f, 19.76f, 17.24f, 19.54f, 16.57f, 18.69f, 16.32f, 18.87f, drawable);
        dibujarCuadrado(17.67f, 18.96f, 17.84f, 18.75f, 16.97f, 18.13f, 16.89f, 18.3f, drawable);
        dibujarCuadrado(17.97f, 17.83f, 17.96f, 17.59f, 17f, 17.63f, 17f, 17.84f, drawable);
        dibujarCuadrado(17.75f, 16.8f, 17.63f, 16.59f, 16.75f, 17.06f, 16.87f, 17.23f, drawable);
        dibujarCuadrado(16f, 15.75f, 15.75f, 15.74f, 15.73f, 16.62f, 15.99f, 16.63f, drawable);
        dibujarCuadrado(14.42f, 16.08f, 14.19f, 16.3f, 14.92f, 17.07f, 15.16f, 16.83f, drawable);
        dibujarCuadrado(13.93f, 17.75f, 14.67f, 17.74f, 14.66f, 17.47f, 13.9f, 17.46f, drawable);
        dibujarCuadrado(13.92f, 18.58f, 14.07f, 18.91f, 14.88f, 18.45f, 14.75f, 18.21f, drawable);
        dibujarCuadrado(14.59f, 19.64f, 14.85f, 19.79f, 15.32f, 18.83f, 15.1f, 18.69f, drawable);
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

    protected void dibujarCuadrado2(float x, float y, float x1, float y1, float x2, float y2, float x3, float y3, GLAutoDrawable arg0) {
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.8f, 0.53f, 0.9f);
        gl.glVertex3f(x, y, 0.0f); // TOP LEFT
        gl.glVertex3f(x1, y1, 0.0f); // Top Right
        gl.glColor3f(0f, 0.53f, 0.9f);
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
        gl.glColor3f(0.27f, 0.22f, 0.07f);// Set the current drawing color to green
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

    public static void dibujarCirculo(float radio, float x1, float y1, GLAutoDrawable arg) {
        float calx, caly;

        GL gl = arg.getGL();
        gl.glBegin(GL.GL_POLYGON);
        for (float i = 0; i < 10; i += 0.01) {
            calx = (float) (radio * Math.cos(i));
            caly = (float) (radio * Math.sin(i));
            gl.glVertex2f(calx + x1, caly + y1);
        }
        gl.glEnd();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

}
