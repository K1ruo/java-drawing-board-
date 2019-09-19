import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;
import java.util.List;


public class hb extends JFrame {
    private boolean eraser1 = false;//定义按钮状态变量
    private boolean brush1 = true;
    private boolean line1 = false;
    private boolean rectangle1 = false;
    private boolean triangle1 = false;
    private boolean circle1 = false;
    private boolean rectangle01 = false;
    private boolean circle01 = false;



    private JToolBar toolBar;//定义工具栏，按钮，菜单变量
    private JMenu set;
    private JMenu color;
    private JMenu help;
    private JMenu addword;
    private JMenuBar menubar;
    private JMenuItem pencolor;
    private JMenuItem small;
    private JMenuItem medium;
    private JMenuItem big;
    private JMenuItem background;
    private JMenuItem operate;
    private JMenuItem producer;
    private JMenuItem wordset;
    private JMenuItem wordadd;
    private JPanel downpanel;
    private JLabel timer;
    private JButton refresh;
    private JButton brush;
    private JButton eraser;
    private JButton line;
    private JButton rectangle;
    private JButton rectangle0;
    private JButton triangle;
    private JButton circle;
    private JButton circle0;
    private JButton save;
    private JButton exit;
    private JButton newfile;
    private JButton open;
    private JCheckBox bold,italic;
    private JComboBox styles;


    private int x1 = 0;//快速绘制图形时的鼠标起点与终点
    private int y1 = 0;
    private int x2 = 0;
    private int y2 = 0;




    class DrawCanvas extends Canvas    //重写、拓展canvas方法
    {
        private Image image = null;
        public void setImage(Image image) {
            this.image = image;
        }
        @Override
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0, null);//图像绘制
        }
        @Override
        public void update(Graphics g) {
            paint(g);
        }
    }

    class Shapes //快速绘制图形
    {
            public void rectShape(Graphics2D g,int x,int y,int width,int heigth) {
                //矩形
                g.drawRect(x, y, width, heigth);
            }
            public void lineShape(Graphics2D g,int x1,int y1,int x2,int y2) {
                //直线
                g.drawLine(x1, y1, x2, y2);
            }
            public void ovalShape(Graphics2D g,int x,int y, int width, int height) {
                //圆
                g.drawOval(x, y, width, height);
            }
            public void trangleShape(Graphics2D g,int []x,int []y, int pointNum) {
                //三角形
                g.drawPolygon(x, y, pointNum);
            }
        }


        //定义对象
        BufferedImage image = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_BGR);
        Graphics gr = image.getGraphics();
        Graphics2D g = (Graphics2D) gr;
        DrawCanvas canvas = new DrawCanvas();
        Shapes shape = new Shapes();
        Color penColor = Color.BLACK;
        Color backColor = Color.WHITE;


    public hb()//构造函数
    {
        setTitle("绘图板");
        this.setBounds(0,0,1024,768);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        time(); //时间部分
        set();  //界面设置
        respond();//响应设置
    }

    //按钮图片的实现方法，在set函数中被调用
    public static JButton change(JButton button, String url, int width, int height)
       {
       button.setBounds(0, 0, width, height);
       ImageIcon buttonImg = new ImageIcon(url);
       //传参修改图片大小，否则无法图片无法对应按键大小
       Image temp = buttonImg.getImage().getScaledInstance(button.getWidth(), button.getHeight(), buttonImg.getImage().SCALE_DEFAULT);
       button = new JButton(new ImageIcon(temp));
       return button;
       }


    //工具栏、菜单、按钮部分的初始设置
    private void set()
         {

             //容器内部对象设置和添加
             g.setColor(backColor);
             g.fillRect(0, 0, 1024, 768);
             g.setColor(penColor);
             canvas.setImage(image);
             getContentPane().add(canvas, BorderLayout.CENTER);
             downpanel=new JPanel();Container container=this.getContentPane();
             downpanel.add(timer);
             container.add(downpanel,BorderLayout.SOUTH);
             toolBar=new JToolBar(JToolBar.VERTICAL);
             container.add(toolBar, BorderLayout.WEST);
             menubar=new JMenuBar();
             setJMenuBar(menubar);
             set=new JMenu("画笔设置");
             color=new JMenu("背景颜色");
             help=new JMenu("帮助");
             menubar.add(set);
             menubar.add(color);
             menubar.add(help);


             //菜单项信息设置
             pencolor=new JMenuItem("画笔颜色");
             small=new JMenuItem("小画笔");
             medium=new JMenuItem("中画笔");
             big=new JMenuItem("大画笔");
             set.add(pencolor);
             set.add(small);
             set.add(medium);
             set.add(big);
             background=new JMenuItem("背景颜色");
             color.add(background);
             operate=new JMenuItem("画板信息");
             help.add(operate);
             producer=new JMenuItem("关于作者");
             help.add(producer);


             //按钮信息设置
             refresh=new JButton();
             refresh=hb.change(refresh,"link/refresh.png",20,25) ;
             refresh.setToolTipText("重新画");
             toolBar.add(refresh);
             toolBar.addSeparator();
             brush=new JButton();
             brush=hb.change(brush,"link/brush.png",15,20);
             brush.setToolTipText("画笔");
             toolBar.add(brush);
             eraser=new JButton();
             eraser=hb.change(eraser,"link/eraser.png",15,20);
             eraser.setToolTipText("橡皮");
             toolBar.add(eraser);
             toolBar.addSeparator();
             line=new JButton();
             line=hb.change(line,"link/line.png",15,20);
             toolBar.add(line);
             rectangle=new JButton();
             rectangle=hb.change(rectangle,"link/rectangle.png",15,20);
             toolBar.add(rectangle);
             rectangle0=new JButton();
             rectangle0=hb.change(rectangle0,"link/rectangle0.png",15,20);
             toolBar.add(rectangle0);
             triangle=new JButton();
             triangle=hb.change(triangle,"link/triangle.png",15,20);
             toolBar.add(triangle);
             circle=new JButton();
             circle=hb.change(circle,"link/circle.png",15,20);
             toolBar.add(circle);
             circle0=new JButton();
             circle0=hb.change(circle0,"link/circle0.png",15,20);
             toolBar.add(circle0);
             toolBar.addSeparator();
             save=new JButton();
             save=hb.change(save,"link/save.png",20,25);
             save.setToolTipText("保存文件");
             toolBar.add(save);
             exit=new JButton();
             toolBar.addSeparator();
             exit=hb.change(exit,"link/exit.png",20,25);
             exit.setToolTipText("退出");
             toolBar.add(exit);
             newfile=new JButton();
             newfile=hb.change(newfile,"link/newfile.png",20,25);
             newfile.setToolTipText("新建空白");
             toolBar.addSeparator();
             toolBar.add(newfile);
             toolBar.addSeparator();
             open=new JButton();
             open=hb.change(open,"link/open.png",20,25);
             open.setToolTipText("打开文件");
             toolBar.add(open);
             this.setVisible(true);
             this.setSize(1024,768);

             GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();

             }





      //下方显示时间实现
    private void time()
    {
           timer = new JLabel();
           timer.setFont(new Font("楷体", Font.BOLD, 18));
           timer.setSize(200, 30);
           timer.setForeground(Color.black);
           new Thread(new Runnable() {
           @Override
           public void run() {
               while (true) {
                   timer.setText(new Date().toString());
                   try {
                       Thread.sleep(1000);//线程睡眠
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       }).start();
   }



   private void respond()//响应方法设置
    {
             canvas.addMouseMotionListener(new MouseMotionAdapter() {
                 @Override
                 public void mouseDragged(final MouseEvent e) {
                if(brush1)
                    //判断是否笔刷
                {
                  if (x1 > 0 && y1 > 0) {
                    //判断鼠标位置

                  if (eraser1) {
                    //判断是否橡皮，橡皮实现：和背景色覆盖
                       g.setColor(backColor);
                       g.fillRect(x1, y1, 10, 10);//橡皮大小
                       } else {
                     //图形绘制
                       x2 = e.getX();
                       y2 = e.getY();
                       g.setColor(penColor);
                       g.drawLine(x1, y1, x2, y2);
                              }
                  }
                     //获取坐标
                    x1 = e.getX();
                    y1 = e.getY();
                    canvas.repaint();

                 }}
             });
        canvas.addMouseListener(new MouseListener() {

            List<Point> pol = new ArrayList<Point>();



            @Override
            public void mouseClicked(MouseEvent e) {
                if (line1) {
                    pol.add(e.getPoint());
                    gr = image.getGraphics();
                    gr.setColor(Color.black);
                    gr.fillOval(e.getX()+7, e.getY()+30, 2, 2);
                    if (e.isMetaDown()) {
                        gr = image.getGraphics();
                        gr.setColor(Color.black);
                        for (int i = 0; i < pol.size() - 1; i++) {
                            Point po1 = pol.get(i);
                            Point po2 = pol.get(i + 1);
                            gr.drawLine(po1.x+7, po1.y+30, po2.x+7, po2.y+30);
                        }
                        gr.drawLine(pol.get(0).x+7, pol.get(0).y+30, pol.get(pol.size() - 1).x+7, pol.get(pol.size() - 1).y+30);
                        pol.clear();
                        canvas.repaint();
                    }
                }


            }


            @Override
            public void mouseReleased(MouseEvent e) {
            }


            @Override
            public void mousePressed(MouseEvent e) {
            }


            @Override
            public void mouseExited(MouseEvent e) {
            }


            @Override
            public void mouseEntered(MouseEvent e) {
            }


        });

             canvas.addMouseListener(new MouseAdapter() {
                 @Override
                 public void mousePressed(MouseEvent e) {
                     g.setColor(penColor);
                     //x1,y1为鼠标按下时的坐标
                     x1 = e.getX();
                     y1 = e.getY();
                 }

                 @Override
                 public void mouseReleased(MouseEvent e) {
                     //x2,y2为获取鼠标释放时的坐标
                     x2 = e.getX();
                     y2 = e.getY();


                     //快速绘制响应
                    if (rectangle1) {
                         //空心矩形绘制
                         shape.rectShape(g, x1, y1, x2 - x1, y2 - y1);
                         canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                     } else if (rectangle01) {
                         //实心矩形绘制
                         g.fillRect(x1, y1, x2 - x1, y2 - y1);
                         canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                     } else if (triangle1) {
                         //三角形绘制，三点绘图，前两点为鼠标按下与释放，第三个点由前两点决定
                         int x[] = new int[3];
                         int y[] = new int[3];
                         x[0] = x1;
                         y[0] = y1;
                         x2 = e.getX();
                         y2 = e.getY();
                         x[1] = x2;
                         y[1] = y2;
                         //自行计算第三个点
                         x[2] = x1 - x2 / 2;
                         y[2] = y2;
                         shape.trangleShape(g, x, y, 3);
                         canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                     } else if (circle1) {

                         //圆形绘制，方式与矩形类似
                         //空心圆绘制
                         shape.ovalShape(g, x1, y1, x2 - x1, y2 - y1);
                         canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                     } else if (circle01) {
                         //实心圆绘制
                         g.fillOval(x1, y1, x2 - x1, y2 - y1);
                         canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                     }
                     else {
                         //坐标归零
                         x1 = 0;
                         y1 = 0;
                     }
                     //画板重绘
                     canvas.repaint();
                 }

             });


             //各个按钮响应
             //清空按钮响应
             refresh.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {//取背景色，直接覆盖
                     g.setColor(backColor);
                     g.fillRect(0, 0, 1024, 768);
                     g.setColor(penColor);
                     canvas.repaint();
                 }
             });

             //笔刷按钮响应
             brush.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     //设置按钮状态
                     brush1 = true;
                     eraser1 = false;
                     rectangle1 = false;
                     line1 = false;
                     triangle1 = false;
                     circle1 = false;
                     canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                 }
             });

             //橡皮按钮响应
             eraser.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     eraser1 = true;
                     brush1 = true;
                     rectangle1 = false;
                     line1 = false;
                     triangle1 = false;
                     circle1 = false;
                     //设置悬浮图标
                     Toolkit kit = Toolkit.getDefaultToolkit();
                     Image img = kit.createImage("link/eraser.png");
                     Cursor c = kit.createCustomCursor(img, new Point(0, 0), "");
                     canvas.setCursor(c);
                 }
             });

            //直线按钮响应
             line.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // TODO Auto-generated method stub


                 }
             });

            //空心矩形按钮响应
             rectangle.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // TODO Auto-generated method stub
                     line1 = false;
                     rectangle1 = true;
                     triangle1 = false;
                     circle1 = false;
                     rectangle01= false;
                     circle01 = false;
                     brush1 = false;
                     canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                 }
             });

            //实心矩形按钮响应
             rectangle0.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // TODO Auto-generated method stub
                     line1 = false;
                     rectangle1  = false;
                     triangle1 = false;
                     circle1  = false;
                     rectangle01= true;
                     circle01 = false;
                     brush1 = false;
                     eraser1 = false;
                     canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                 }
             });

            //三角形按钮响应
             triangle.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // TODO Auto-generated method stub
                     line1 = false;
                     rectangle1  = false;
                     triangle1 = true;
                     circle1  = false;
                     rectangle01= false;
                     circle01 = false;
                     brush1 = false;
                     canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                 }
             });

            //空心圆形按钮响应
             circle.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // TODO Auto-generated method stub
                     line1 = false;
                     rectangle1  = false;
                     triangle1 = false;
                     circle1  = true;
                     rectangle01= false;
                     circle01 = false;
                     brush1 = false;
                     canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                 }
             });

            //实心圆形按钮响应
          circle0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                line1 = false;
                rectangle1  = false;
                triangle1 = false;
                circle1  = false;
                rectangle01= false;
                circle01 = true;
                brush1 = false;
                canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });


          //新建空白文件响应
            newfile.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // TODO Auto-generated method stub
                     canvas.setImage(image);
                     penColor = Color.BLACK;
                     backColor = Color.WHITE;
                     set();
                     respond();
                     time();

                 }
             });

           //保存按钮响应
             save.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                         //文件操作
                     JFileChooser chooser = new JFileChooser();
                     FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(".png", "png");
                     chooser.setFileFilter(jpgFilter);
                     int returnVal = chooser.showSaveDialog(hb.this);
                     if (returnVal == JFileChooser.APPROVE_OPTION) {
                         String selectPath = chooser.getSelectedFile().getPath() + ".png";
                         //捕捉异常
                         try {
                             ImageIO.write(image, "png", new File(selectPath));
                             File f = new File(selectPath);
                             if (f.exists()) {
                                 JOptionPane.showMessageDialog(hb.this, "成功保存！", "提示",
                                         JOptionPane.INFORMATION_MESSAGE);
                             }
                         } catch (IOException e1) {
                             e1.printStackTrace();
                         }
                     }
                 }
             });

            //退出按钮响应
             exit.addActionListener(new ActionListener() {

                 @Override
                 public void actionPerformed(ActionEvent e) {

                     int i = JOptionPane.showConfirmDialog(hb.this, "您确定要退出吗？", "提示", JOptionPane.YES_NO_OPTION);
                     if (i == JOptionPane.YES_OPTION) {
                         System.exit(0);
                     }
                 }

             });

             //打开文件响应
             open.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     BufferedImage image1 = image;
                     JFileChooser chooser = new JFileChooser();
                     FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(".png", "png");
                     chooser.setFileFilter(jpgFilter);
                     int returnVal = chooser.showOpenDialog(hb.this);
                     if (returnVal == JFileChooser.APPROVE_OPTION) {
                         File path = chooser.getSelectedFile();
                         Image[] array = new Image[10];
                         try {
                             image = ImageIO.read(path);
                         } catch (IOException e1) {
                             e1.printStackTrace();
                         }
                         array[0] = image;
                         canvas.setImage(array[0]);
                         canvas.repaint();
                         System.out.println(path);

                          image = image1;
                     }
                 }
             });

            //画笔颜色菜单项响应
             pencolor.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     Color frColor = JColorChooser.showDialog(hb.this, "画笔颜色设置", Color.BLACK);
                     if (!(frColor == null)) {
                         penColor = frColor;
                     }
                     g.setColor(penColor);
                     canvas.repaint();
                 }
             });

            //小画笔菜单项响应
             small.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     BasicStroke s = new BasicStroke(1);
                     g.setStroke(s);
                 }
             });

            //中画笔菜单项响应
             medium.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     BasicStroke s = new BasicStroke(5);
                     g.setStroke(s);
                 }
             });

            //大画笔菜单项响应
             big.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     BasicStroke s = new BasicStroke(10);
                     g.setStroke(s);
                 }
             });

             //背景颜色菜单项响应
             background.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     Color bgColor = JColorChooser.showDialog(hb.this, "背景颜色设置", Color.WHITE);
                     if (!(bgColor == null)) {
                         backColor = bgColor;
                     }
                     g.setColor(backColor);
                     g.fillRect(0, 0, 1024, 768);
                     g.setColor(penColor);
                     canvas.repaint();
                 }
             });

             //画板信息菜单项响应
             operate.addActionListener(new ActionListener() {

                 @Override
                 public void actionPerformed(ActionEvent e) {
                     JOptionPane.showMessageDialog(hb.this,
                             "上方为设置菜单，左方为各种器件按钮，下方为时间信息。", "画图板信息",
                             JOptionPane.INFORMATION_MESSAGE);
                 }
             });

             //关于作者菜单项响应
             producer.addActionListener(new ActionListener() {

                 @Override
                 public void actionPerformed(ActionEvent e) {
                     JOptionPane.showMessageDialog(hb.this,
                             "作者", "关于作者",
                             JOptionPane.INFORMATION_MESSAGE);
                 }
             });



             //退出确认框
             this.addWindowListener(new WindowAdapter() {

                 @Override
                 public void windowClosing(WindowEvent e) {

                     int i = JOptionPane.showConfirmDialog(hb.this, "您确定要退出吗？", "提示", JOptionPane.YES_NO_OPTION);
                     if (i == JOptionPane.YES_OPTION) {
                         System.exit(0);
                     }
                     else{
                     }
                 }
             });
         }

        public  static void main(String arg[])
     {

         new hb();

     }

}