
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class DFAGUI extends JPanel {

    int[][] t;
    String[] alph;
    int states;
    int finalState;

    Color bgColor = new Color(30,30,30);
    Color nodeColor = new Color(173,216,230);
    Color finalColor = new Color(144,238,144);
    Color edgeColor = Color.WHITE;
    Color textColor = Color.WHITE;

    public void setData(int[][] t, String[] alph, int states, int finalState) {
        this.t = t;
        this.alph = alph;
        this.states = states;
        this.finalState = finalState;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(bgColor);
        g2.fillRect(0,0,getWidth(),getHeight());

        if (t == null) return;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int r = 30, gap = 130, y = 220;

        int[] x = new int[states];
        for(int i=0;i<states;i++) x[i] = 100 + i*gap;

        
        for(int i=0;i<states;i++){
            g2.setColor(i==finalState ? finalColor : nodeColor);
            g2.fillOval(x[i],y,2*r,2*r);

            g2.setColor(edgeColor);
            g2.drawOval(x[i],y,2*r,2*r);

            if(i==finalState)
                g2.drawOval(x[i]-5,y-5,2*r+10,2*r+10);

            g2.setColor(Color.BLACK);
            g2.drawString("q"+i,x[i]+r-10,y+r+5);
        }

        // START
        g2.setColor(edgeColor);
        g2.drawLine(x[0]-60,y+r,x[0],y+r);
        drawArrow(g2,x[0]-60,y+r,x[0],y+r);

        // TRANSITIONS
        for(int i=0;i<states;i++){

            Map<Integer,StringBuilder> map = new HashMap<>();

            for(int j=0;j<alph.length;j++){
                int to = t[i][j];
                map.putIfAbsent(to,new StringBuilder());
                if(map.get(to).length()>0) map.get(to).append(",");
                map.get(to).append(alph[j]);
            }

            for(int to: map.keySet()){

                String label = map.get(to).toString();

                int x1 = x[i]+2*r, y1 = y+r;
                int x2 = x[to], y2 = y+r;

                g2.setColor(edgeColor);

                if(to==i){
                    int lx=x[i]+r-15, ly=y-35;
                    g2.drawOval(lx,ly,30,25);
                    g2.setColor(textColor);
                    g2.drawString(label,lx+5,ly-5);
                    drawArrow(g2,lx+15,ly,x[i]+r,y);
                }
                else if(Math.abs(i-to)==1){
                    g2.drawLine(x1,y1,x2,y2);
                    g2.setColor(textColor);
                    g2.drawString(label,(x1+x2)/2-10,y1-10);
                    drawArrow(g2,x1,y1,x2,y2);
                }
                else{
                    int cx=(x1+x2)/2;
                    int cy=(i<to)?y-60:y+100;

                    QuadCurve2D q=new QuadCurve2D.Float();
                    q.setCurve(x1,y1,cx,cy,x2,y2);
                    g2.draw(q);

                    int lx=(x1+2*cx+x2)/4;
                    int ly=(y1+2*cy+y2)/4;

                    g2.setColor(textColor);
                    g2.drawString(label,lx-10,ly);

                    drawArrow(g2,cx,cy,x2,y2);
                }
            }
        }
    }

    void drawArrow(Graphics2D g2,int x1,int y1,int x2,int y2){
        double angle=Math.atan2(y2-y1,x2-x1);
        int len=10;

        int xA=x2-(int)(len*Math.cos(angle-Math.PI/6));
        int yA=y2-(int)(len*Math.sin(angle-Math.PI/6));

        int xB=x2-(int)(len*Math.cos(angle+Math.PI/6));
        int yB=y2-(int)(len*Math.sin(angle+Math.PI/6));

        g2.drawLine(x2,y2,xA,yA);
        g2.drawLine(x2,y2,xB,yB);
    }
}
