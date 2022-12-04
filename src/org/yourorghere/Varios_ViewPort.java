package org.yourorghere;

import com.sun.opengl.util.Animator;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import javax.swing.JFrame;
import com.sun.opengl.util.GLUT;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.yourorghere.Figura01.dibujarCirculo;
import static org.yourorghere.Figura02.dibujarCirculo;

public class Varios_ViewPort extends JFrame implements KeyListener, MouseListener, MouseMotionListener {

    static GL gl;
    static GLU glu;
    static GLUT glut;
    static GLCanvas canvas;

    //variables para extraer el ancho y alto de la ventana
    static int ancho, alto;

    //Variables para la rotacion
    private static float rotarX = -60;
    private static float rotarY = 0;
    private static float rotarZ = 0;

    //Variables para la traslacion
    private static float trasladaX = -0.9f;
    private static float trasladaY = -8f;
    private static float trasladaZ = 0;

    private static float traslada1X = -15f;
    private static float traslada1Y = -8f;

    private static float traslada2X = 3.0f;
    private static float traslada2Y = 0;

    private static float traslada3X = 7.0f;
    private static float traslada3Y = 0;
    /*Iniciamos el valor en la escala en 1     
* para que cuando el objeto inicie el programa aun no se escale*/

    private static float escalaX = 0.8f;
    private static float escalaY = 0.8f;

    private static float escala1X = 0.7f;
    private static float escala1Y = 0.7f;

    private float view_rotx = 20.0f;
    private float view_roty = 30.0f;

    private int oldMouseX;
    private int oldMouseY;

    public static float array[] = new float[8];
    public static float array2[] = new float[4];

    public static void main(String args[]) {
        Varios_ViewPort myframe = new Varios_ViewPort();
        myframe.setVisible(true);
        myframe.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public Varios_ViewPort() {
        setSize(1300, 900);
        setLocationRelativeTo(null);
        setTitle("Muchas figuras con Viewport");
        setResizable(false);
        GraphicListener listener = new GraphicListener();
        alto = this.getHeight();
        ancho = this.getWidth();
        //Se crea el objeto de la clase canvas
        canvas = new GLCanvas();
        gl = canvas.getGL();
        glu = new GLU();
        glut = new GLUT();

        /*Añadimos el oyente de eventos para el renderizado de OPENGL,
         esto automaticamente llamara a init() y renderizara los graficos cuyo codigo
         haya sido escrito dentro del metodo dysplay*/
        canvas.addGLEventListener(listener);
        getContentPane().add(canvas);

        Animator animator = new Animator(canvas);
        animator.start();
        addKeyListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        oldMouseX = e.getX();
        oldMouseY = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaY = 360.0f * ((float) (x - oldMouseX) / (float) size.width);
        float thetaX = 360.0f * ((float) (oldMouseY - y) / (float) size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public class GraphicListener implements GLEventListener {

        public void display(GLAutoDrawable drawable) {
            gl = drawable.getGL();
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);

            //Dibujo del robot
            gl.glViewport(0, (alto / 2) - 300, ancho / 2, alto);
            gl.glLoadIdentity();
            gl.glOrtho(-8, 8, -8, 8, -8, 8);
            gl.glTranslatef(trasladaX, trasladaY, 1.5f);
            gl.glScalef(escalaX, escalaY, 0);
            glu.gluLookAt(0, 3, 0, 0, 0, 0, 0, 0, -.5 + trasladaZ);
            gl.glRotatef(rotarX, 1f, 0f, 0f);
            gl.glRotatef(rotarY, 0f, 1f, 0f);
            gl.glRotatef(rotarZ, 0f, 0f, 1f);
            robot(drawable);


            //Dibujo del mujer
            gl.glViewport(600, (alto / 2) - 300, ancho / 2, alto);
            gl.glLoadIdentity();
            gl.glOrtho(-8, 8, -8, 8, -8, 8);
            gl.glTranslatef(traslada1X, traslada1Y, 1.5f);
            gl.glScalef(escala1X, escala1Y, 0);
            glu.gluLookAt(0, 3, 0, 0, 0, 0, 0, 0, -.5 + trasladaZ);
            gl.glRotatef(rotarX, 1f, 0f, 0f);
            gl.glRotatef(rotarY, 0f, 1f, 0f);
            gl.glRotatef(rotarZ, 0f, 0f, 1f);
            mujer(drawable);

            gl.glFlush();

        }//display

        public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        }

        public void init(GLAutoDrawable arg0) {
        }

        public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        }//reshape...

    }//graphic listener    

    protected void castillo(GLAutoDrawable drawable) {

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

    protected void cohete(GLAutoDrawable drawable) {

        //Base cohete 1
        gl.glColor3f(0.9f, 0.70f, 0.10f);
        dibujarCuadrado(4.5f, 1.5f, 6f, 1.5f, 6f, 1f, 4.5f, 1f, drawable);

        //Base cohete 2
        gl.glColor3f(0.27f, 0.22f, 0.07f);
        dibujarCuadrado(4.5f, 2.5f, 6f, 2.5f, 6f, 1.5f, 4.5f, 1.5f, drawable);

        //Base cohete 3
        gl.glColor3f(0.9f, 0.70f, 0.10f);
        dibujarCuadrado(4.5f, 3f, 6f, 3f, 6f, 2.5f, 4.5f, 2.5f, drawable);

        //Base cohete 4
        gl.glColor3f(0.27f, 0.22f, 0.07f);
        dibujarCuadrado(4.5f, 4f, 6f, 4f, 6f, 3f, 4.5f, 3f, drawable);

        //Base cohete 5
        gl.glColor3f(0.9f, 0.70f, 0.10f);
        dibujarCuadrado(4.5f, 4.5f, 6f, 4.5f, 6f, 4f, 4.5f, 4f, drawable);

        //Base cohete 5
        gl.glColor3f(0.27f, 0.22f, 0.07f);
        dibujarCuadrado(4.5f, 5f, 6f, 5f, 6f, 4.5f, 4.5f, 4.5f, drawable);

        //Cabeza cohete
        gl.glColor3f(0.9f, 0.70f, 0.10f);
        dibujarTriangulo(5.2f, 6f, 6f, 5f, 4.5f, 5f, drawable);

        //Circulo 1
        dibujarCirculo(0.3f, 5.23f, 3.5f, drawable);

        //Circulo 2
        dibujarCirculo(0.3f, 5.22f, 2.03f, drawable);

        //Cola 1
        gl.glColor3f(0.83f, 0.31f, 0.32f);
        dibujarCuadrado(3.6f, 4f, 4.5f, 4.25f, 4.5f, 3.23f, 3.6f, 3f, drawable);

        //Cola 2
        gl.glColor3f(0.83f, 0.31f, 0.32f);
        dibujarCuadrado(6f, 4.26f, 7f, 4f, 7f, 3f, 6f, 3.22f, drawable);

        //Cola 3
        gl.glColor3f(0.83f, 0.31f, 0.32f);
        dibujarCuadrado(3.5f, 1.5f, 4.5f, 1.9f, 4.5f, 0.8f, 3.5f, 0.5f, drawable);

        //Cola 4
        gl.glColor3f(0.83f, 0.31f, 0.32f);
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

    protected void robot(GLAutoDrawable drawable) {

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
        gl.glVertex3f(x, y, 0.0f);   // Top
        gl.glVertex3f(x1, y1, 0.0f); // Bottom Left
        gl.glVertex3f(x2, y2, 0.0f);  // Bottom Right
        gl.glVertex3f(x3, y3, 0.0f);  // Bottom Right
        // Done Drawing The Quad
        gl.glEnd();
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

    protected void dibujarCirculo(float radio, float x, float y, GLAutoDrawable arg0) {
        gl.glBegin(GL.GL_POLYGON);
        for (float i = 0; i <= 500; i += 0.01) {
            double angle = 2 * Math.PI * i / 500;
            float cos = (float) (radio * cos(i));
            float sin = (float) (radio * sin(i));
            gl.glVertex2f(cos + (x), sin + (y));
        }
        // Done Drawing The Quad
        gl.glEnd();
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

    protected void dibujarTriangulo(float x, float y, float x1, float y1, float x2, float y2, GLAutoDrawable arg0) {
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex3f(x, y, 0.0f);   // Top
        gl.glVertex3f(x1, y1, 0.0f); // Bottom Left
        gl.glVertex3f(x2, y2, 0.0f);  // Bottom Right
        // Finished Drawing The Triangle
        gl.glEnd();
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

    protected void dibujarLinea(float x, float y, float x1, float y1, GLAutoDrawable arg0) {
        gl.glLineWidth(5f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(x, y, 0);
        gl.glVertex3f(x1, y1, 0);
        gl.glEnd();
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    protected void dibujarSonrisa(float x, float y, GLAutoDrawable arg0) {
        float angulo;
        int i;
        gl.glPointSize(3f);
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(1.0f, 0.5f, 0.0f);
        for (i = 0; i < 180; i += 1) {
            angulo = (float) i * 2.14159f / -180.0f; // grados a radianes
            gl.glVertex3f((float) Math.cos(angulo) + x, (float) Math.sin(angulo) + y, 0.0f);
        }
        gl.glEnd();
    }
    //Creacion del metodo que detecta las teclas pulsadas

    public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_X) {
            rotarX += 1.0f;
            System.out.println("El valor de X en la rotacion: " + rotarX);
        }

        if (arg0.getKeyCode() == KeyEvent.VK_A) {
            rotarX -= 1.0f;
            System.out.println("El valor de X en la rotacion: " + rotarX);
        }

        if (arg0.getKeyCode() == KeyEvent.VK_Y) {
            rotarY += 1.0f;
            System.out.println("El valor de Y en la rotacion: " + rotarY);
        }

        if (arg0.getKeyCode() == KeyEvent.VK_B) {
            rotarY -= 1.0f;
            System.out.println("El valor de Y en la rotacion: " + rotarY);
        }

        if (arg0.getKeyCode() == KeyEvent.VK_Z) {
            rotarZ += 1.0f;
            System.out.println("El valor de Z en la rotacion: " + rotarZ);
        }

        if (arg0.getKeyCode() == KeyEvent.VK_C) {
            rotarZ -= 1.0f;
            System.out.println("El valor de Z en la rotacion: " + rotarZ);
        }

        if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
            trasladaZ += .10f;
            System.out.println("El valor de Z: " + trasladaZ);
        }

        if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
            trasladaZ -= .10f;
            System.out.println("El valor de Z: " + trasladaZ);
        }

        if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
            rotarX = 0;
            rotarY = 0;
            rotarZ = 0;
            trasladaZ = 0;
        }
    }//fin keyPressed

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

}
